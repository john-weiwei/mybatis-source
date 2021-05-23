package com.cn.thread.use;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangWeiWei
 * @date 2021/5/23 20:44
 * @description
 */
public class JoinThread {
    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 = new Thread1("thread1");
        Thread2 t2 = new Thread2("thread2");
        Thread3 t3 = new Thread3("thread3");
        t1.start();
        t1.join(1000);
        t2.start();
        t2.join(2000);
        t3.start();
        t3.join(3000);

        System.out.println("main");

    }

    static class Thread1 extends Thread {
//        Thread2 t2 = new Thread2("thread2");
        private String name;

        public Thread1(String name) {
            this.name = name;
        }

        @SneakyThrows
        @Override
        public void run() {
//            t2.start();
//            t2.join();
            System.out.println(name);
//            try {
//                TimeUnit.SECONDS.sleep(4);
//                System.out.println("业务执行耗时");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    static class Thread2 extends Thread {
        private String name;

        public Thread2(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(name);
        }
    }

    static class Thread3 extends Thread {
        private String name;

        public Thread3(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(name);

        }
    }
}
