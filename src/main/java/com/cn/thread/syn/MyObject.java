package com.cn.thread.syn;

import java.util.concurrent.TimeUnit;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/26
 * @Description:
 */
public class MyObject {

    synchronized public void methodA() {
        try {
            System.out.println("begin methodA " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(5);
//            System.out.println("end methodA " + Thread.currentThread().getName());
            System.out.println("end methodA " + System.currentTimeMillis());
        } catch (Exception e) {

        }
    }

    synchronized public void methodB() {
        try {
            System.out.println("begin methodB " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(5);
            System.out.println("end methodB " +System.currentTimeMillis());
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        MyObject obj = new MyObject();
        MethodA methodA = new MethodA(obj);
        methodA.setName("A");
        methodA.start();
        MethodB methodB = new MethodB(obj);
        methodB.setName("B");
        methodB.start();

    }
    /**
     * 共享资源才需要同步化（原因：不是共享的资源，进行同步是没有意义的）
     * 两个线程分别调用同步和非同步方法，是并行执行的（原因：A线程访问同步方法，拿到锁之后可以往下执行，B线程调用非同步方法
     * 不需要等待A释放锁，所以A、B两个线程可以并行执行）
     * 两个线程分别调用两个不同的同步的方法，也是需要排序的（原因：A线程拿到对象锁，B线程需要等待A释放对象锁之后才能
     * 拿到锁，进入到具体的方法，这就是所谓的排队）
     */
}

class MethodA extends Thread {

    private MyObject obj;

    public MethodA(MyObject obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        super.run();
        obj.methodA();
    }
}
class MethodB extends Thread {

    private MyObject obj;

    public MethodB(MyObject obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        super.run();
        obj.methodB();
    }
}