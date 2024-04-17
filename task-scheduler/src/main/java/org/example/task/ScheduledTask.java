package org.example.task;

public abstract class ScheduledTask {

    protected final ExecutionContext context;

    public ScheduledTask(ExecutionContext context) {
        this.context = context;
    }

    public void execute(){
        this.context.execute();
    }

    public abstract long getExecutionTime();

    public abstract boolean isRecurring();

    public abstract ScheduledTask getNextScheduledTask();

}
