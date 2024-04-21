package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
    private BlockingQueue<Runnable> taskQueue;
    private List<WorkerRunnable> workerRunnableList;
    private boolean isStopped = false;

    public ThreadPool(int numThreads, int maxTasks) {
        taskQueue = new ArrayBlockingQueue<>(maxTasks);
        workerRunnableList = new ArrayList<>();
        for(int i=0;i<numThreads;i++){
            WorkerRunnable workerRunnable = new WorkerRunnable(taskQueue);
            workerRunnableList.add(workerRunnable);
        }
        for(WorkerRunnable workerRunnable: workerRunnableList){
            new Thread(workerRunnable).start();
        }
    }

    public synchronized void execute(Runnable task){
        if(isStopped){
            throw new IllegalStateException("Thread pool stopped");
        }
        this.taskQueue.offer(task);
    }

    public synchronized void stop(){
        System.out.println("Stop called for thread pool");
        this.isStopped = true;
        for(WorkerRunnable workerRunnable: workerRunnableList){
            workerRunnable.doStop();
        }
    }

    public synchronized void waitUntilAllFinished(){
        while(this.taskQueue.size()>0){
            try {
                System.out.println("Waiting to finish... current size: "+this.taskQueue.size());
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
