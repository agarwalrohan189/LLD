package org.example;

import org.example.store.TaskStore;
import org.example.task.ScheduledTask;

public class TaskRunner implements Runnable{

    private final TaskStore<ScheduledTask> taskStore;
    private boolean running;

    public TaskRunner(TaskStore<ScheduledTask> taskStore) {
        this.taskStore = taskStore;
        running=true;
    }

    @Override
    public void run() {
        while(running){
            ScheduledTask task = taskStore.poll();
            if(task==null) break;
            task.execute();
            if(task.isRecurring()){
                taskStore.add(task.getNextScheduledTask());
            }
        }
    }

    public void stop(){
        this.running = false;
    }
}
