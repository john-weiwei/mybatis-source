package com.cn.thread.safe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/23
 * @Description:
 */
public class CloseThread {

    static class NormalClose extends Thread {

        private volatile boolean closed = false;

        AtomicInteger ai = new AtomicInteger(0);

        @Override
        public void run() {
            System.out.println("it will be start work");
            while (!closed && !isInterrupted()) {
                //normal execute
                ai.incrementAndGet();
            }
            System.out.println("exit work");

        }

        public void close() {
            this.closed = true;
            this.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NormalClose t1 = new NormalClose();
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.close();
        System.out.println(t1.ai.get());
    }
}
