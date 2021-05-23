package com.cn.thread.syn;

import java.util.concurrent.TimeUnit;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/26
 * @Description:
 */
public class TestException {

    synchronized public void testMethod() {
        String threadName = Thread.currentThread().getName();
        try {
            if (threadName.equals("a")) {
                while (true) {
                    System.out.println("normal executor testMethod");
//                    TimeUnit.MILLISECONDS.sleep(500);
                    Integer.parseInt("a");
                }
            } else {
                System.out.println("a 线程异常退出，b 线程得到执行");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestException testException = new TestException();
        ExceptionService threadA = new ExceptionService(testException);
        threadA.setName("a");
        threadA.start();
        TimeUnit.SECONDS.sleep(1);
        ExceptionService threadB = new ExceptionService(testException);
        threadB.setName("b");
        threadB.start();
    }

    /**
     * 发生异常，锁自动被释放
     */

}

class ExceptionService extends Thread {

    private TestException testException;

    public ExceptionService(TestException testException) {
        this.testException = testException;
    }

    @Override
    public void run() {
        super.run();
        testException.testMethod();
    }
}
