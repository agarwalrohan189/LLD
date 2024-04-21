package org.example;

import java.util.concurrent.BlockingQueue;

public class WorkerRunnable implements Runnable{
    private Thread thread;
    private BlockingQueue<Runnable> taskQueue;
    private boolean isStopped;

    public WorkerRunnable(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
        isStopped = false;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while(!isStopped){
            try {
                Runnable runnable = taskQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public synchronized void doStop(){
        this.thread.interrupt();
        isStopped = true;
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}
