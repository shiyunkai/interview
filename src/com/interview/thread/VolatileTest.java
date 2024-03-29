package com.interview.thread;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/17 11:33
 * @Description:
 */
public class VolatileTest {
    public static void main(String[] args) {
        final Counter counter = new Counter();
        for (int i = 0; i < 1000; i++) {
            new Thread(()->counter.inc()).start();
        }
        System.out.println(counter);
    }
}

class Counter{
    private volatile int count = 0;

    public void inc(){
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    @Override
    public String toString() {
        return "[count="+count+"]";
    }
}
