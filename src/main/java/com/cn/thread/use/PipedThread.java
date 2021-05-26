package com.cn.thread.use;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author ZhangWeiWei
 * @date 2021/5/23 20:29
 * @description
 */
public class PipedThread {
    public static void main(String[] args) {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        try {
            writer.connect(reader);
            Thread thread = new Thread(new Print(reader),"PrintThread");
            thread.start();
            int receive = 0;
            while ((receive = System.in.read()) != -1) {
                writer.write(receive);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Print implements Runnable {
        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.print((char)receive);
                }
            } catch (Exception e) {

            }
        }
    }
}
