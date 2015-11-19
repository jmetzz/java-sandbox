package com.github.jmetzz.example;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Jean Metz on 18-Nov-15.
 */
public class Consumer implements Runnable {

    private BlockingQueue<Message> queue;

    private String name;

    public Consumer(String name, BlockingQueue<Message> q) {
        this.queue = q;
        this.name = name;
    }


    @Override
    public void run() {
        try {
            Message msg;
            //consuming messages until exit message is received

            while ((msg = queue.take()).getMessage() != "exit") {
                Thread.sleep(10);
                System.out.println(name + " consumed msg: " + msg.getMessage());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
