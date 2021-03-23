package com.cn.thread.safe;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/23
 * @Description:
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
//        List<Thread> threads = IntStream.range(1,3).mapToObj(ThreadJoin::create).collect(Collectors.toList());
//
//        threads.forEach(Thread::start);
//
//        for (Thread t : threads
//            ) {
//            t.join();
//        }
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Thread.currentThread().getName()+"#"+i);
//            TimeUnit.SECONDS.sleep(1);
//        }

        JoinDemo t1 = new JoinDemo();
        t1.start();
//        TimeUnit.SECONDS.sleep(5);
        t1.stop();
        t1.join();
        System.out.println("main end");
    }

    private static Thread create(int tag) {
       return new Thread(() -> {
           for (int i = 0; i < 10; i++) {
               System.out.println(Thread.currentThread().getName()+"#"+i);
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       },String.valueOf(tag));
    }

    /**
     * join方法会使当前线程永远等待，直到线程被中断，或者join的线程执行结束
     */
}

class JoinDemo extends Thread {

    private Integer num = 0;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(num++);
            if (num > 10) {
                return;
            }
        }
    }
}
