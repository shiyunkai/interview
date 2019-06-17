package com.interview.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/17 08:51
 * @Description:
 */
public class PoolTest {

    public static void main(String[] args) {
        // 固定线程池的使用
        // fixedThreadPoolDemo();

        // 单任务线程池的使用
        // singleThreadPoolDemo();

        // 可变线程池的使用
        // volatileThreadPoolDemo();

        // 定时任务线程池的使用
        scheduleThreadPoolDemo();
    }


    /**
     * 固定线程池的使用
     * 不管execute执行几次，始终只会有固定长度的线程在执行任务
     */
    private static void fixedThreadPoolDemo() {
        // 创建一个可重用的固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        execute(pool);
    }


    /**
     * 单任务线程池的使用
     * 不管execute执行几次，始终只会有一个线程在执行任务
     */
    private static void singleThreadPoolDemo() {
        // 创建一个使用单个worker线程的Executor，以无界队列方式来运行该线程
        ExecutorService pool = Executors.newSingleThreadExecutor();
        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        execute(pool);
    }

    /**
     * 可变线程池的使用
     * execute执行n次，就会有n个线程来处理
     */
    private static void volatileThreadPoolDemo() {
        // 创建一个使用单个worker线程的Executor，以无界队列方式来运行该线程
        ExecutorService pool = Executors.newCachedThreadPool();
        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        execute(pool);
    }

    /**
     * 定时线程池的使用
     *
     */
    private static void scheduleThreadPoolDemo() {
        // 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        MyThread t4 = new MyThread();
        MyThread t5 = new MyThread();
        MyThread t6 = new MyThread();
        // 将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        // 使用定时执行风格的方法, t5和t6在10秒后执行
        pool.schedule(t5,10, TimeUnit.SECONDS);
        pool.schedule(t6,15, TimeUnit.SECONDS);
        pool.shutdown();
    }


    /**
     *  执行线程任务
     * @param pool
     */
    private static void execute(ExecutorService pool) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        MyThread t4 = new MyThread();
        MyThread t5 = new MyThread();
        MyThread t6 = new MyThread();
        // 将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        // 关闭线程池
        pool.shutdown();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "正在执行...");
    }
}

