package com.cn.thread.syn;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/4/6
 * @Description:
 */
public class NullSync {

    private Object mutex = null;

    public void method1() {
        synchronized (mutex) {
            System.out.println("执行sync，同步代码块");
        }
    }

    public static void main(String[] args) {
        NullSync ns = new NullSync();
        ns.method1();
    }

    /**
     * 不能使用空对象作为对象监视器
     * Monitor对象不能为空
     */
}
