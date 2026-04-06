package 线程池异步获取结果;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 方案四：使用 CompletableFuture（异步编程）
 *
 * 【核心原理】
 * - CompletableFuture 是 JDK 8 引入的异步编程利器
 * - 支持链式调用、组合操作（thenApply、thenCompose、thenCombine 等）
 * - 支持异常处理（exceptionally、handle）
 * - 支持多个 Future 组合（allOf、anyOf）
 * - 特点：函数式编程风格，代码简洁优雅，支持复杂的异步流程编排
 *
 * 【使用场景】
 * - 微服务调用链，多个服务并行调用后合并结果
 * - 需要在异步任务完成后执行后续操作
 * - 需要处理异常或设置超时
 * - Stream 流式处理与异步结合
 */
public class CompletableFutureMergeExample {

    static class PartialResult {
        private final int value;

        public PartialResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    static class FinalResult {
        private final int total;

        public FinalResult(int total) {
            this.total = total;
        }

        @Override
        public String toString() {
            return "FinalResult{total=" + total + "}";
        }
    }

    static class Task {
        private final int id;

        public Task(int id) {
            this.id = id;
        }

        public PartialResult readData() {
            try {
                long sleepTime = 100 + (long)(Math.random() * 400);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return new PartialResult(id);
        }
    }

    private static FinalResult merge(List<PartialResult> results) {
        int total = 0;
        for (PartialResult pr : results) {
            total += pr.getValue();
        }
        return new FinalResult(total);
    }

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("【方案四】CompletableFuture（异步编程）");
        System.out.println("特点：函数式编程，支持链式调用、组合操作");
        System.out.println("=".repeat(60));

        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tasks.add(new Task(i));
        }

        System.out.println("\n[主线程] 创建 CompletableFuture 列表，提交所有任务...");
        System.out.println("-".repeat(60));

        long startTime = System.currentTimeMillis();

        List<CompletableFuture<PartialResult>> futures = new ArrayList<>();

        for (Task task : tasks) {
            CompletableFuture<PartialResult> future = CompletableFuture.supplyAsync(
                    () -> {
                        PartialResult result = task.readData();
                        System.out.printf("[CompletableFuture-%s] 任务 %d 执行完成%n",
                                Thread.currentThread().getName().replace("pool-1-thread-", ""),
                                result.getValue());
                        return result;
                    },
                    executor
            );
            futures.add(future);
        }

        System.out.println("-".repeat(60));

        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        allOf.thenRun(() -> {
            System.out.printf("[主线程] 所有 %d 个 CompletableFuture 任务完成，开始合并...%n", tasks.size());
            List<PartialResult> results = new ArrayList<>();
            for (CompletableFuture<PartialResult> future : futures) {
                results.add(future.join());
            }
            FinalResult finalResult = merge(results);
            System.out.println("[主线程] 最终结果：" + finalResult);
            System.out.println("[主线程] 总耗时: " + (System.currentTimeMillis() - startTime) + "ms");
            executor.shutdown();
        });

        try {
            allOf.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
