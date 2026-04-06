package 线程池异步获取结果;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 方案二：使用 ExecutorCompletionService（谁先完成谁返回）
 *
 * 【核心原理】
 * - CompletionService 内部维护一个阻塞队列，任务完成后结果自动进入队列
 * - 调用 take() 或 poll() 可以立即获取已完成的任務結果
 * - 特点：无需按顺序等待，谁先完成谁先返回，提高效率
 *
 * 【使用场景】
 * - 批量任务处理，只需获取最快的 N 个结果
 * - 竞速场景（如多个数据源查询，返回最快的结果）
 * - 超时控制场景（只等待一定时间）
 */
public class CompletionServiceMergeExample {

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
        System.out.println("【方案二】ExecutorCompletionService（谁先完成谁返回）");
        System.out.println("特点：CompletionService.take() 立即返回已完成的任务");
        System.out.println("=".repeat(60));

        ExecutorService executor = Executors.newFixedThreadPool(5);
        CompletionService<PartialResult> completionService = new ExecutorCompletionService<>(executor);

        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tasks.add(new Task(i));
        }

        System.out.println("\n[主线程] 提交所有任务到 CompletionService...");
        System.out.println("-".repeat(60));

        for (Task task : tasks) {
            completionService.submit(() -> task.readData());
        }

        List<PartialResult> allResults = new ArrayList<>();
        try {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("[主线程] 等待获取已完成的任务（第 %d 个完成）...%n", i + 1);

                Future<PartialResult> future = completionService.take();
                PartialResult result = future.get();

                System.out.printf("[CompletionService] 队列返回已完成任务: %d%n", result.getValue());
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
