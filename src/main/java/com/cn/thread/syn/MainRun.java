package com.cn.thread.syn;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/26
 * @Description:
 */
public class MainRun {
    public static void main(String[] args) {
        MyRun run = new MyRun();
        run.start();
    }
}

class MyRun extends Thread {
    @Override
    public void run() {
        Sub sub = new Sub();
        sub.operSubMethod();
    }
}
