package com.cn.thread.syn;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/26
 * @Description:
 */
public class Reentrant {
    public static void main(String[] args) {
        ServiceThread serviceThread = new ServiceThread();
        serviceThread.start();
    }

    /**
     * 可重入锁：自己可以再次获取自己的内部锁
     * 如果一个线程已经拿到了某个对象锁，若此时对象锁还没有释放，
     * 当前线程想要再次拿到这个对象锁是可以拿得到的
     *
     * 原理：在锁机制中，可重入锁其实是内部维护了一个变量，当它判断拿到锁的
     * 线程持有的锁是同一个时，通过计数器累加获取锁的次数，即锁的可重入
     */
}

class Service {
    synchronized public void service1() {
        System.out.println("service1");
        service2();
    }

    synchronized public void service2() {
        System.out.println("service2");
        service3();
    }

    synchronized public void service3() {
        System.out.println("service3");
    }
}

class ServiceThread extends Thread {
    @Override
    public void run() {
        Service service = new Service();
        service.service1();
    }
}

