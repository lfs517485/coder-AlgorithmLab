package cn.bugstack.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 线程排队执行综合演示程序 ===\n");

        // 演示1: synchronized
        System.out.println("1. 使用 synchronized 实现（非公平锁）:");
        demoMethod1();
        Thread.sleep(3000);

        // 演示2: ReentrantLock
        System.out.println("\n2. 使用 ReentrantLock 实现（公平锁）:");
        demoMethod2();
        Thread.sleep(3000);



    }

    private static void demoMethod1() {
        Object lock = new Object();
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            new Thread(() -> {
                synchronized (lock) {
                    System.out.println("  任务 " + taskId + " 开始执行...");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {}
                    System.out.println("  任务 " + taskId + " 完成");
                }
            }).start();
        }
    }

    private static void demoMethod2() {
        ReentrantLock lock = new ReentrantLock(true);
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println("  任务 " + taskId + " 开始执行...");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {}
                    System.out.println("  任务 " + taskId + " 完成");
                } finally {
                    lock.unlock();
                }
            }).start();
        }
    }



}