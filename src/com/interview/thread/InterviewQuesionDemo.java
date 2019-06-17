package com.interview.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/17 12:01
 * @Description:
 */
public class InterviewQuesionDemo {

    private static int num;
    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) {
        // 1. 控制某个方法允许并发访问的线程的个数？
        // concurrentLimit();

        // 2. 三个线程 a、b、c 并发运行，b,c 需要 a 线程的数据怎么实现:?
        // threadCommunication();

        // 3. 同一个类中的2个方法都加了同步锁，多个线程能同时访问同一个类中的这两个方法吗?
        // Lock不能
        // threadLock();
        // Synchronized 修饰的Runnable可以ee
        // threadSynchronized();

        // 4. 什么情况下导致死锁，遇到线程死锁该怎么解决?
        // 死锁的定义：所谓死锁是指多个线程因竞争资源而造成的一种僵局（互相等待），
        // 若无外力作用，这些进程都将无法向前推进
        // 死锁产生的必要条件：
        // （1）互斥条件
        // （2）不剥夺条件
        // （3）请求和保持条件
        // （5）循环等待条件
        // threadDeadLock();

        // 5.如何避免死锁
        // （1）加锁顺序（线程按照一定的顺序加锁）
        // threadLockOrder();
        // （2）加锁时限（线程尝试获取锁的时候加上一定的时限，超过时限则放弃对该锁的请求，并释放自己的锁）
        // threadLockOuttime();

        // 6.Java中多线程间的通信怎么实现？
        // （1） 共享变量
        // threadShareVariate();
        // （2）wait/notify机制
        threadProducerAndConsumer();

    }

    private static void threadProducerAndConsumer() {
        Resource r = new Resource();
        Producer pro = new Producer(r);
        Consumer con = new Consumer(r);
        new Thread(pro).start();
        new Thread(con).start();
    }

    private static void threadShareVariate() {
        // 同一个对象
        final MySignal signal = new MySignal();
        // 线程1设置hasDataToProcess值为true
        final Thread t1 = new Thread(() -> signal.setHasDataToProcess(true));
        t1.start();
        // 线程2取这个值hasDataToProcess
        final Thread t2 = new Thread(() -> {
            try {
                // 等待线程1完成然后取值
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag = signal.getHasDataToProcess();
            System.out.println("t1线程改变以后的值:"+flag);
        });
        t2.start();
    }

    private static void threadLockOuttime() {
        final Lock lock = new ReentrantLock();
        final DeadLock2 td1 = new DeadLock2();
        final DeadLock2 td2 = new DeadLock2();

        Thread t1 = new Thread(() -> {
            td1.flag = 1;
            String tName = Thread.currentThread().getName();
            try {
                if (lock.tryLock(5, TimeUnit.SECONDS)) {
                    System.out.println(tName + "获取到锁！");
                } else {
                    System.out.println(tName + "获取不到锁！");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            td1.money();
            lock.unlock();
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            td1.flag = 0;
            String tName = Thread.currentThread().getName();
            try {
                if (lock.tryLock(5, TimeUnit.SECONDS)) {
                    System.out.println(tName + "获取到锁！");
                } else {
                    System.out.println(tName + "获取不到锁！");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            td2.money();
            lock.unlock();
        });
        t2.start();
    }

    private static void threadLockOrder() {
        final DeadLock2 td1 = new DeadLock2();
        final DeadLock2 td2 = new DeadLock2();

        Thread t1 = new Thread(() -> {
            td1.flag = 1;
            td1.money();
        });
        t1.start();

        new Thread(() -> {
            try {
                // 让t1先执行完
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            td2.flag = 0;
            td2.money();
        }).start();
    }

    private static void threadDeadLock() {
        DeadLock td1 = new DeadLock();
        DeadLock td2 = new DeadLock();
        td1.flag = 1;
        td2.flag = 0;
        new Thread(td1).start();
        new Thread(td2).start();
    }

    private static void threadSynchronized() {
        qq t = new qq();
        new Thread(t.run3).start();
        new Thread(t.run4).start();
    }

    private static void threadLock() {
        qq t = new qq();
        new Thread(t.run1).start();
        new Thread(t.run2).start();
    }

    private static void threadCommunication() {
        Thread tA = new Thread(() -> {
            // 模拟耗时操作之后初始化变量num
            try {
                Thread.sleep(1000);
                num = 1;
                // 初始化参数完成后释放两个permit
                semaphore.release(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread tB = new Thread(() -> {
            // 获取permit，如果semaphore没有permit则等待，如果有则消耗一个
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "获取到num的值为:" + num);
        }
        );

        Thread tC = new Thread(() -> {
            // 获取permit，如果semaphore没有permit则等待，如果有则消耗一个
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "获取到num的值为：" + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 同时开启三个线程
        tA.start();
        tB.start();
        tC.start();
    }


    /**
     * 控制某个方法允许并发访问的线程的个数？
     */
    public static void concurrentLimit() {
        // 最多允许访问5次
        Semaphore semaphore = new Semaphore(5, true);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> test(semaphore)).start();
        }
    }

    public static void test(Semaphore semaphore) {
        try {
            // 申请一个请求
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + "进来了");
            Thread.sleep(1000);
            // 释放一个请求
            System.out.println(Thread.currentThread().getName() + "走了");
            semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class qq {
    private Lock lock = new ReentrantLock();

    // 方法1
    public Runnable run1 = () -> {
        lock.lock();
        for (int count = 0; count < 1000; count++) {
            // 打印是否执行该方法
            System.out.println(Thread.currentThread().getName() + " run1 " + count++);
        }
        lock.unlock();
    };

    // 方法1
    public Runnable run2 = () -> {
        lock.lock();
        for (int count = 0; count < 1000; count++) {
            // 打印是否执行该方法
            System.out.println(Thread.currentThread().getName() + " run2 " + count++);
        }
        lock.unlock();
    };

    // 方法3
    public Runnable run3 =
            new Runnable() {
                @Override
                public void run() {
                    // 这里的this指的是run3对象，这里不能使用lambda，因为lambda中的this指向的是外部类
                    System.out.println("run3 this is " + this);
                    synchronized (this) {
                        for (int count = 0; count < 100; count++) {
                            // 打印是否执行该方法
                            System.out.println(Thread.currentThread().getName() + " run3 " + count++);
                        }
                    }
                }
            };


    // 方法4
    public Runnable run4 = new Runnable() {
        @Override
        public void run() {
            // 这里的this指的是run4对象，这里不能使用lambda，因为lambda中的this指向的是外部类
            System.out.println("run4 this is " + this);
            synchronized (this) {
                for (int count = 0; count < 100; count++) {
                    // 打印是否执行该方法
                    System.out.println(Thread.currentThread().getName() + " run4 " + count++);
                }
            }
        }
    };

}

/**
 * 一个简单的死锁类
 * 当 DeadLock 类的对象 flag==1 时（td1），先锁定 o1,睡眠 500 毫秒
 * 而 td1 在睡眠的时候另一个 flag==0 的对象（td2）线程启动，先锁定 o2,睡眠 500 毫秒
 * td1 睡眠结束后需要锁定 o2 才能继续执行，而此时 o2 已被 td2 锁定；
 * td2 睡眠结束后需要锁定 o1 才能继续执行，而此时 o1 已被 td1 锁定；
 * td1、td2 相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁
 */
class DeadLock implements Runnable {
    public int flag = 1;
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("0");
                }
            }
        }
    }

    public void money(int flag) {
        this.flag = flag;
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("0");
                }
            }
        }
    }
}


class DeadLock2 {
    public int flag = 1;
    private static Object o1 = new Object(), o2 = new Object();

    public void money() {
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("0");
                }
            }
        }
    }
}

class MySignal {
    // 共享的变量
    private boolean hasDataToProcess = false;

    public void setHasDataToProcess(boolean hasDataToProcess) {
        this.hasDataToProcess = hasDataToProcess;
    }

    public boolean getHasDataToProcess() {
        return hasDataToProcess;
    }
}

// 资源类
class Resource{
    private String name;
    private int count = 1;
    // 是否有资源可以消费
    private boolean flag = false;

    // 生产资源
    public synchronized void set(String name){
        while(flag){
            try {
                // 等待消费者消费
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
        System.out.println(Thread.currentThread().getName()+"..生产者:"+name);
        flag = true;
        // 唤醒等待中的消费者
        this.notifyAll();
    }

    // 消费资源
    public synchronized void out(){
        while (!flag){
            try {
                // 等待生产都生产
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"...消费者:"+this.name);
            flag = false;
            // 唤醒生产者，生产资源
            this.notifyAll();
        }
    }

}

// 生产者
class Producer implements Runnable{
    private Resource res;

    Producer(Resource res){
        this.res = res;
    }

    @Override
    public void run() {
        while (true){
            res.set("商品");
        }
    }
}

// 消费者
class Consumer implements Runnable{

    private Resource res;

    Consumer(Resource res){
        this.res = res;
    }


    @Override
    public void run() {
        while (true){
            res.out();
        }
    }
}