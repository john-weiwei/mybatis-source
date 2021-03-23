package com.cn.thread.safe;

import java.util.concurrent.TimeUnit;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/23
 * @Description:
 */
public class ExecutorStop {
    private String username = "b";
    private String password = "bb";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ExecutorStop() {

    }

    synchronized public void printString(String username, String password) {
        try {
            this.username = username;
            TimeUnit.MILLISECONDS.sleep(500);
            this.password = password;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorStop executorStop = new ExecutorStop();
        MyThread1 t1 = new MyThread1(executorStop);
        t1.start();
        t1.stop();
        System.out.println(executorStop.getUsername()+"  "+executorStop.getPassword());
    }

    /**
     * 调用stop方法强制停止线程，可能会造成数据不一致的情况
     */
}

class MyThread1 extends Thread {

    private ExecutorStop executorStop;

    public MyThread1(ExecutorStop executorStop) {
        this.executorStop = executorStop;
    }

    @Override
    public void run() {
        executorStop.printString("a","aa");
    }
}

