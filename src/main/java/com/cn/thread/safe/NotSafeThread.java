package com.cn.thread.safe;

import java.util.concurrent.TimeUnit;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/23
 * @Description:
 */
public class NotSafeThread {

    static class NotSafeDemo extends Thread {

        private int count = 5;

        public NotSafeDemo(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (count > 0) {
                count--;
                System.out.println(currentThread().getName() + count);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        NotSafeDemo t1 = new NotSafeDemo("A");
//        NotSafeDemo t2 = new NotSafeDemo("B");
//        NotSafeDemo t3 = new NotSafeDemo("C");
//        t1.start();
//        t2.start();
//        t3.start();
        StopThread t4 = new StopThread("D");
        t4.start();
//        t4.interrupt();
        //主线程休眠的时间，t4线程已经执行完成退出。所以拿到的线程中断信号是默认值
        TimeUnit.MILLISECONDS.sleep(2);
//        System.out.println("线程是否存活" + t4.isAlive());
//        System.out.println(t4.interrupted());
//        System.out.println(t4.isInterrupted());

        t4.stop();
    }
}

class StopThread extends Thread {

    public StopThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                if (i < 0) {
                    throw new InterruptedException();
                }

                System.out.println(i);
//                sleep(2000000);
            }

        } catch (InterruptedException e) {
            System.out.println("进入StopThread的run方法，中断线程" + this.isInterrupted());
        }
    }

    /**
     * 停止线程的方法
     * 1、捕获中断信号，向外抛出异常达到停止线程的效果
     * 2、长时间休眠也可以达到中断线程的效果
     * 3、调用stop方法，不推荐使用。强制停止线程可能会造成一些清理性工作得不到完成，
     * 被锁定的资源如果被解锁，可能会产生数据不一致的情况
     */
}
