package com.cn.thread.syn;

import java.util.concurrent.TimeUnit;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/26
 * @Description:
 */
public class DirtyRead {

    private String username = "a";
    private String password = "aa";
    synchronized public void setValue(String username,String password) {
        try {
            this.username = username;
            TimeUnit.SECONDS.sleep(5);
            this.password = password;
            System.out.println("setValue method thread name " + Thread.currentThread().getName()+" username="+username
            +" password= "+password);
        } catch (Exception e) {

        }
    }

    synchronized public String getValue() {
        return toString();
    }

    @Override
    public String toString() {
        return "DirtyRead{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        DirtyRead dirtyRead = new DirtyRead();
        DirtyThreadA dirtyThreadA = new DirtyThreadA(dirtyRead);
        dirtyThreadA.setName("A");
        dirtyThreadA.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(dirtyRead.getValue());
    }

    /**
     * 结论：
     * 1、当A线程调用任意对象加入了synchronized关键字的xxx方法，A线程就拿到了对象锁，其他访问这个方法的
     * 线程必须要等待A线程释放对象锁才能调用成功；相反，其他线程可以调用这个对象的任意非同步方法。
     * 2、当A线程调用任意对象加入了synchronized关键字的xxx方法，A线程就拿到了对象锁，其他访问这个对象的
     * 任意synchronized方法都必须等待A线程释放对象锁才能得到调用
     */
}

class DirtyThreadA extends Thread {

    private DirtyRead dirtyRead;

    public DirtyThreadA(DirtyRead dirtyRead) {
        this.dirtyRead = dirtyRead;
    }

    @Override
    public void run() {
        super.run();
        dirtyRead.setValue("b","bb");
    }
}