package com.cn.thread.syn;

import java.util.concurrent.TimeUnit;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/26
 * @Description:
 */
public class Main {
    public int num = 10;
    synchronized public void operMainMethod() {
        try {
            num--;
            System.out.println("main print " +num);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
