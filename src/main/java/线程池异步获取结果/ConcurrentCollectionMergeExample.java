package 线程池异步获取结果;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 方案三：使用并发集合（ConcurrentLinkedQueue + CopyOnWriteArrayList）
 *
 * 【核心原理】
 * - ConcurrentLinkedQueue：无界线程安全队列，基于 CAS 实现高性能
 * - CopyOnWriteArrayList：写时复制列表，适合读多写少场景
 * - CountDownLatch：协调多线程完成，用于主线程等待
 * - 特点：线程直接将结果放入共享集合，主线程等待所有任务完成后统一处理
 *
 * 【使用场景】
 * - 事件驱动系统，收集各个处理器的结果
 * - 需要在任务执行过程中实时查看部分结果
 * - 分布式计算中的结果聚合
 */
public class ConcurrentCollectionMergeExample {

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

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=".repeat(60));
        System.out.println("【方案三】并发集合（ConcurrentLinkedQueue + CopyOnWriteArrayList）");
        System.out.println("特点：线程安全集合收集结果，CountDownLatch 协调完成");
        System.out.println("=".repeat(60));

        ExecutorService executor = Executors.newFixedThreadPool(5);

        CopyOnWriteArrayList<PartialResult> results = new CopyOnWriteArrayList<>();
        AtomicInteger completedCount = new AtomicInteger(0);

        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tasks.add(new Task(i));
        }

        System.out.println("\n[主线程] 初始化并发集合，启动所有任务...");
        System.out.println("-".repeat(60));

        CountDownLatch latch = new CountDownLatch(tasks.size());

        for (Task task : tasks) {
            executor.submit(() -> {
                PartialResult result = task.readData();
                results.add(result);

                int count = completedCount.incrementAndGet();
                System.out.printf("[工作线程-%s] 任务 %d 完成，加入结果集合（当前完成: %d/10）%n",
                        Thread.currentThread().getName().replace("pool-1-thread-", ""),
                        result.getValue(), count);

                latch.countDown();
            });
        }

        System.out.println("[主线程] 等待所有任务完成...");
        latch.await();

        System.out.println("-".repeat(60));
        System.out.println("[主线程] 所有任务完成，开始合并...");
        FinalResult finalResult = merge(new ArrayList<>(results));
        System.out.println("[主线程] 最终结果：" + finalResult);

        executor.shutdown();
    }
}
