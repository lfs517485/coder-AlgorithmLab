package 线程池异步获取结果;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 方案一：使用 Future + ExecutorService（最经典）
 *
 * 【核心原理】
 * - 通过 ExecutorService.submit() 提交任务，返回 Future 对象
 * - Future.get() 会阻塞等待任务完成，然后按提交顺序返回结果
 * - 特点：按任务提交顺序依次获取结果，即使后面的任务先完成也要等待前面的任务
 *
 * 【使用场景】
 * - 任务间有依赖关系，需要按顺序处理
 * - 需要知道每个结果对应哪个任务
 * - 简单的并行任务场景
 */
public class FutureExecutorMergeExample {

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
        System.out.println("【方案一】Future + ExecutorService（最经典）");
        System.out.println("特点：按提交顺序获取结果，Future.get() 会阻塞等待");
        System.out.println("=".repeat(60));

        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tasks.add(new Task(i));
        }

        List<Future<PartialResult>> futures = new ArrayList<>();
        for (Task task : tasks) {
            Future<PartialResult> future = executor.submit(() -> task.readData());
            futures.add(future);
        }

        System.out.println("\n[主线程] 所有任务已提交，开始按顺序获取结果...");
        System.out.println("-".repeat(60));

        List<PartialResult> allResults = new ArrayList<>();
        try {
            for (int i = 0; i < futures.size(); i++) {
                Future<PartialResult> future = futures.get(i);

                System.out.printf("[主线程] 等待第 %d 个任务完成（提交顺序）...\n", i + 1);
                PartialResult result = future.get();
                System.out.printf("[主线程] 获取到第 %d 个任务结果: %d%n", i + 1, result.getValue());

                allResults.add(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        System.out.println("-".repeat(60));
        System.out.println("[主线程] 所有结果获取完毕，开始合并...");
        FinalResult finalResult = merge(allResults);
        System.out.println("[主线程] 最终结果：" + finalResult);
    }
}
