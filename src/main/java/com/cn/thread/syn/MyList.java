package com.cn.thread.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/4/6
 * @Description:
 */
public class MyList {

    private List<String> list = new ArrayList();

    synchronized public void add(String value) {
        String currentName = Thread.currentThread().getName();
        System.out.println("currentName="+currentName+"执行了add方法");
        list.add(value);
        System.out.println("currentName="+currentName+"退出了add方法");
    }

    synchronized public int getSize() {
        String currentName = Thread.currentThread().getName();
        System.out.println("currentName="+currentName+"执行了getSize方法");
        int size = list.size();
        System.out.println("currentName="+currentName+"退出了getSize方法");
        return size;
    }

    public static void main(String[] args) {
        MyList list = new MyList();
        ThreadA threadA = new ThreadA(list);
        threadA.setName("A");
        threadA.start();
        ThreadB threadB = new ThreadB(list);
        threadB.setName("B");
        threadB.start();
    }

}

class ThreadA extends Thread {
    private MyList list;

    public ThreadA(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
           list.add("threadA"+(i+1));
        }
    }
}
class ThreadB extends Thread {
    private MyList list;

    public ThreadB(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            list.add("threadB"+(i+1));
        }
    }
}
