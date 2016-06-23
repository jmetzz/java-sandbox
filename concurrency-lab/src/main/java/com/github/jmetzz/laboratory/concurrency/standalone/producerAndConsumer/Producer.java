package com.github.jmetzz.laboratory.concurrency.standalone.producerAndConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Jean Metz on 18-Nov-15.
 */
public class Producer implements Runnable {

    private BlockingQueue<Message> queue;


    public Producer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("" + i);
            try {
                Thread.sleep(i);
                queue.put(msg);
                System.out.println("Produced " + msg.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //adding exit message
        Message msg = new Message("exit");
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
