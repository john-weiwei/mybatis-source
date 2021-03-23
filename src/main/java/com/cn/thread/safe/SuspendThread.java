package com.cn.thread.safe;

import java.util.concurrent.TimeUnit;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/23
 * @Description:
 */
public class SuspendThread {
    public static void main(String[] args) throws InterruptedException {
//        SuspendDemo thread = new SuspendDemo();
//        thread.start();
//        TimeUnit.SECONDS.sleep(2);
//        //A
//        thread.suspend();
//        System.out.println("A"+thread.getNum());
//        TimeUnit.SECONDS.sleep(2);
//        System.out.println("A"+thread.getNum());
//        //B
//        thread.resume();
//        TimeUnit.SECONDS.sleep(2);
//        System.out.println("B"+thread.getNum());
//        //C
//        thread.suspend();
//        System.out.println("C"+thread.getNum());
//        TimeUnit.SECONDS.sleep(2);
//        System.out.println("C"+thread.getNum());

//        SynchronizedObject obj = new SynchronizedObject();
//        Thread t1 = new Thread(obj::printString);
//        t1.setName("a");
//        t1.start();
//
//        Thread t2 = new Thread(() -> {
//            System.out.println("t2启动了，但访问不了printString方法");
//            System.out.println("因为printString被a线程锁定了，并且a永远suspend了");
//            obj.printString();
//        });
//        t2.setName("b");
//        t2.start();

        SuspendDemo t = new SuspendDemo();
        t.start();
        TimeUnit.SECONDS.sleep(2);
        t.suspend();
        System.out.println("main end");

    }
}

class SuspendDemo extends Thread {

    private Integer num = 0;

    public Integer getNum() {
        return num;
    }

    @Override
    public void run() {
        while (true) {
            num++;
            System.out.println(num);
        }
    }
}

class SynchronizedObject {

    synchronized public void printString() {
        System.out.println("begin");
        Thread thread = Thread.currentThread();
        if (thread.getName().equals("a")) {
            thread.suspend();
            System.out.println("a调用了suspend方法");
        }
    }

    /**
     * suspend和resume是线程独占的，
     */
}

