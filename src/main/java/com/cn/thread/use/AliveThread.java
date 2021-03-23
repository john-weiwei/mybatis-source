package com.cn.thread.use;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/22
 * @Description:
 */
public class AliveThread{

    static class AliveThreadTest extends Thread {

        AtomicInteger ai = new AtomicInteger(0);

        Thread currentThread = Thread.currentThread();

        String threadName;

        public AliveThreadTest(String threadName) {
            this.threadName = threadName;
        }

        @SneakyThrows
        @Override
        public void run() {
//            for (int i = 0; i < 100000; i++) {
//                ai.incrementAndGet();
//                TimeUnit.SECONDS.sleep(1);
//                System.out.println(threadName+"线程状态"+i);
//            }
            while (true) {
                ai.incrementAndGet();
            }

        }
    }

    static class AliveThreadTest2 extends Thread {
        Thread currentThread = Thread.currentThread();

        AtomicInteger ai = new AtomicInteger(0);

        String threadName;

        public AliveThreadTest2(String threadName) {
            this.threadName = threadName;
        }

        @SneakyThrows
        @Override
        public void run() {
//            long start = System.currentTimeMillis();
//            for (int i = 0; i < 100000; i++) {
//                yield();
//                ai.incrementAndGet();
//                System.out.println(threadName+"线程状态"+i);
//            }
            while (true) {
                ai.incrementAndGet();
            }
//            System.out.println("用时："+(System.currentTimeMillis()-start)+"毫秒");

        }
    }

    public static void main(String[] args) throws InterruptedException {
        AliveThreadTest thread = new AliveThreadTest("AliveThread");
        thread.setPriority(thread.getPriority()-3);
//        System.out.println("未开启线程前的状态"+thread.isAlive());
        thread.start();
        AliveThreadTest2 thread2 = new AliveThreadTest2("AliveThread2");
        thread2.setPriority(thread.getPriority()+3);
        thread2.start();
        TimeUnit.SECONDS.sleep(5);
        thread.stop();
        thread2.stop();
        System.out.println("a:"+thread.ai.get());
        System.out.println("b:"+thread2.ai.get());
//        TimeUnit.SECONDS.sleep(3);
//        System.out.println(thread.getId());
//        System.out.println("开启线程的状态"+thread.isAlive());

    }

    /**
     * 数值越小，优先级越高
     * 线程优先级具有随机性，即优先级高的线程不一定会被CPU优先调用
     */

}
