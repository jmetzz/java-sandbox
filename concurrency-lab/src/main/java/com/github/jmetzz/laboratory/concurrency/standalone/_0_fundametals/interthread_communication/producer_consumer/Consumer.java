package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.interthread_communication.producer_consumer;


public class Consumer implements Runnable{
    private Q q;

    public Consumer(Q q){
        this.q = q;
        new Thread(this, "Consumer").start();
        // OBS:
        // 1. This is a very bad example!
        // 2. never start a Thread from the constructor,
        // since this can lead to unsafe initialization or
        // partial initialized objects.
    }


    @Override
    public void run() {
        while (true){
            q.get();
        }
    }
}
