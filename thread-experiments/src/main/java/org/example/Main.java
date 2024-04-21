package org.example;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");

        executorService();
    }

    private static void executorService(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(newRunnable("task 1.1"));
        executorService.execute(newRunnable("task 1.2"));
        executorService.execute(newRunnable("task 1.3"));

        Future future = executorService.submit(newRunnable("future task 1"));
        System.out.println(future.isDone());

        try {
            future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println(future.isDone());

        executorService.shutdown();
    }

    private static Runnable newRunnable(String msg){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": "+ msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private static void threadPool(){
        ThreadPool threadPool = new ThreadPool(3,10);

        for(int i=0;i<10;i++){
            int taskNo = i;
            threadPool.execute(() -> {
                System.out.println("Running task no: "+ taskNo + " on thread with name: " + Thread.currentThread().getName());
            });
        }

        threadPool.waitUntilAllFinished();
        threadPool.stop();
    }
}