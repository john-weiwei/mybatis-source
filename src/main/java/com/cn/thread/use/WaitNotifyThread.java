package com.cn.thread.use;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangWeiWei
 * @date 2021/5/23 11:47
 * @description
 */
public class WaitNotifyThread {

    private volatile static boolean flag = true;
    private static Object lock = new Object();

    public static void main(String[] args) {
        WaitThread waitThread = new WaitThread();
        NotifyThread notifyThread = new NotifyThread();
        waitThread.start();
        notifyThread.start();
    }

    private static class WaitThread extends Thread {

        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println("顾客来了");
                        TimeUnit.SECONDS.sleep(2);
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("顾客已接待");
            }
        }
    }

    private static class NotifyThread extends Thread {

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    System.out.println("接待客人");
                    lock.notifyAll();
                    flag = false;
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
