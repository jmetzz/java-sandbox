package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.interthread_communication.producer_consumer;


public class Producer implements  Runnable{
    private Q q;

    public Producer(Q q){
        this.q = q;
        new Thread(this, "Producer").start();
        // OBS:
        // 1. This is a very bad example!
        // 2. never start a Thread from the constructor,
        // since this can lead to unsafe initialization or
        // partial initialized objects.
    }

    @Override
    public void run() {
        int i = 0;
        while (true){
            q.put(i++);
        }
    }
}
