package com.cn.thread.use;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/22
 * @Description:
 */
public class AliveThread{

    static class AliveThreadTest extends Thread {
        Thread currentThread = Thread.currentThread();

        String threadName;

        public AliveThreadTest(String threadName) {
            this.threadName = threadName;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println(threadName+"线程状态"+i);
            }
        }
    }

    static class AliveThreadTest2 extends Thread {
        Thread currentThread = Thread.currentThread();

        String threadName;

        public AliveThreadTest2(String threadName) {
            this.threadName = threadName;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                yield();
                System.out.println(threadName+"线程状态"+i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AliveThreadTest thread = new AliveThreadTest("AliveThread");
        AliveThreadTest2 thread2 = new AliveThreadTest2("AliveThread2");
        System.out.println("未开启线程前的状态"+thread.isAlive());
        thread.start();
        thread2.start();

    }

}
