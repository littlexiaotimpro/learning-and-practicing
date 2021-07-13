package com.practice.thread.base.sync;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 线程管道通信
 */
public class PipedMain {

    public static void main(String[] args) throws InterruptedException, IOException {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        // 这里注意一定要连接，才能通信
        writer.connect(reader);

        new Thread(new Reader(reader)).start();
        Thread.sleep(1000);
        new Thread(new Writer(writer)).start();
    }

    private static class Reader implements Runnable {
        private final PipedReader reader;

        public Reader(PipedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            System.out.println("this is reader");
            int receive = 0;
            try {
                while ((receive = reader.read()) != -1) {
                    System.out.print((char) receive);
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Writer implements Runnable {
        private final PipedWriter writer;

        public Writer(PipedWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            System.out.println("this is writer");
            try {
                writer.write("test");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
