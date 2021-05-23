package com.cn.thread.syn;

import java.util.concurrent.TimeUnit;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/26
 * @Description:
 */
public class SelfPrivateNum{
//    int num = 0;  //实例变量非线程安全

    synchronized public void printNum(String name) {
        //局部变量是线程私有的变量，线程安全
        int num = 0;
        try {
            if (name.equals("a")) {
                num = 100;
                TimeUnit.SECONDS.sleep(1);
            } else {
                num = 200;
            }
            System.out.println(name + " "+num);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        //两个不用的对象锁
        SelfPrivateNum selfPrivateNum = new SelfPrivateNum();
        SelfPrivateNum selfPrivateNum2 = new SelfPrivateNum();
        SelfThreadA a = new SelfThreadA(selfPrivateNum);
        SelfThreadB b = new SelfThreadB(selfPrivateNum2);
        a.start();
        b.start();
    }

}

class SelfThreadA extends Thread {

    private SelfPrivateNum selfPrivateNum;

    public SelfThreadA(SelfPrivateNum selfPrivateNum) {
        this.selfPrivateNum = selfPrivateNum;
    }

    @Override
    public void run() {
        selfPrivateNum.printNum("a");
    }
}

class SelfThreadB extends Thread {

    private SelfPrivateNum selfPrivateNum;

    public SelfThreadB(SelfPrivateNum selfPrivateNum) {
        this.selfPrivateNum = selfPrivateNum;
    }

    @Override
    public void run() {
        selfPrivateNum.printNum("b");
    }
}
