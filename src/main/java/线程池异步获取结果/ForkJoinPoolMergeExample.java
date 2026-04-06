package 线程池异步获取结果;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 方案五：使用 ForkJoinPool + RecursiveTask（分治思想）
 *
 * 【核心原理】
 * - ForkJoinPool 是 JDK 7 引入的并行计算框架，使用工作窃取算法
 * - 工作窃取：空闲线程可以从其他线程队列尾部获取任务执行
 * - RecursiveTask：可返回结果的任务，通过 fork() 分支，join() 合并结果
 * - 特点：将大问题递归拆分为小问题，并行处理后再合并结果
 *
 * 【使用场景】
 * - 大数据量的并行计算（如归并排序、快速排序）
 * - 递归分治算法（如汉诺塔、迷宫求解）
 * - 树形结构的并行遍历
 * - 科学计算（矩阵运算、蒙特卡洛模拟）
 */
public class ForkJoinPoolMergeExample {

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

    /**
     * 分治任务：递归拆分任务，当任务数量小于阈值时直接执行，否则拆分为子任务
     */
    static class SumTask extends RecursiveTask<Integer> {
        private final List<Task> tasks;
        private final int start;
        private final int end;
        private static final int THRESHOLD = 3;  // 阈值：超过此数量则拆分

        public SumTask(List<Task> tasks, int start, int end) {
            this.tasks = tasks;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            // 当任务数量在阈值内，直接计算
            if (end - start <= THRESHOLD) {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    Task task = tasks.get(i);
                    PartialResult result = task.readData();
                    String workerName = Thread.currentThread().getName();
                    int workerId = workerName.contains("-") ? 
                            Integer.parseInt(workerName.substring(workerName.lastIndexOf("-") + 1)) : 0;
                    System.out.printf("[ForkJoin-Worker-%d] 执行任务 %d（范围: %d-%d）%n",
                            workerId, result.getValue(), start, end - 1);
                    sum += result.getValue();
                }
                return sum;
            }

            // 任务数量超过阈值，拆分为两个子任务
            int mid = start + (end - start) / 2;
            System.out.printf("[ForkJoin] 任务拆分: [%d-%d] -> [%d-%d] + [%d-%d]%n",
                    start, end - 1, start, mid - 1, mid, end - 1);

            SumTask leftTask = new SumTask(tasks, start, mid);
            SumTask rightTask = new SumTask(tasks, mid, end);

            // fork() 异步执行子任务
            leftTask.fork();
            rightTask.fork();

            // join() 获取子任务结果并合并
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("【方案五】ForkJoinPool + RecursiveTask（分治思想）");
        System.out.println("特点：工作窃取算法，递归拆分并行执行");
        System.out.println("=".repeat(60));

        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tasks.add(new Task(i));
        }

        System.out.println("\n[主线程] 初始化任务，设置分治阈值...");
        System.out.println("-".repeat(60));
        System.out.printf("任务总数: %d, 分治阈值: %d, 预期拆分次数: %d%n",
                tasks.size(), SumTask.THRESHOLD, (tasks.size() + SumTask.THRESHOLD - 1) / SumTask.THRESHOLD);
        System.out.println("-".repeat(60));

        long startTime = System.currentTimeMillis();

        ForkJoinPool pool = ForkJoinPool.commonPool();
        SumTask mainTask = new SumTask(tasks, 0, tasks.size());
        int result = pool.invoke(mainTask);

        System.out.println("-".repeat(60));
        System.out.println("[ForkJoinPool] 所有任务执行完毕");
        System.out.println("[主线程] 最终结果：FinalResult{total=" + result + "}");
        System.out.println("[主线程] 总耗时: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
