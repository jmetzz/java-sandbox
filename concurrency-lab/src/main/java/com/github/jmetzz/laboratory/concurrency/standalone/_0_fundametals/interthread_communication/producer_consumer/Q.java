package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.interthread_communication.producer_consumer;


public class Q {
    private int n;
    private boolean valueSet = false; // safe initialization guaranteed since this is a static initialization

    synchronized  int get(){

        /**
         * Although wait( ) normally waits until notify( ) or notifyAll( ) is called,
         * there is a possibility that in very rare cases the waiting thread could be awakened
         * due to a spurious wakeup. In this case, a waiting thread resumes
         * without notify( ) or notifyAll( ) having been called.
         * (In essence, the thread resumes for no apparent reason.)
         * Because of this remote possibility, Oracle recommends that calls to wait( )
         * should take place within a loop that checks the condition on which the thread is waiting
         */
        while(!valueSet){
            try{
                /* suspend the execution until Producer notifies that some data is ready */
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getCause());
            }
        }
        System.out.println("Got : " + n);
        valueSet = false;
        notify(); // notify the thread waiting on the same intrinsic lock (this) so it can resume
        return n;
    }


    synchronized void put(int n){
        /**
         * Although wait( ) normally waits until notify( ) or notifyAll( ) is called,
         * there is a possibility that in very rare cases the waiting thread could be awakened
         * due to a spurious wakeup. In this case, a waiting thread resumes
         * without notify( ) or notifyAll( ) having been called.
         * (In essence, the thread resumes for no apparent reason.)
         * Because of this remote possibility, Oracle recommends that calls to wait( )
         * should take place within a loop that checks the condition on which the thread is waiting
         */
        while(valueSet){
            try{
                /* suspend the execution until Consumer notifies that data was consumed */
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getCause());
            }
        }

        this.n = n;
        valueSet = true;
        System.out.println("Put: " + n);
        notify(); // notify the thread waiting on the same intrinsic lock (this) so it can resume
    }
}
