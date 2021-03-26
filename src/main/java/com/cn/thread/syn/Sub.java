package com.cn.thread.syn;

import java.util.concurrent.TimeUnit;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/26
 * @Description:
 */
public class Sub extends Main {
    synchronized public void operSubMethod() {
        try {
            while (num > 0) {
                num--;
                System.out.println("sub print " +num);
                TimeUnit.MILLISECONDS.sleep(100);
                this.operMainMethod();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 结论：当存在父子继承关系时，子类可以通过"可重入锁"执行父类的同步方法
     */
}
