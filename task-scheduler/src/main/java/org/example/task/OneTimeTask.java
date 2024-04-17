package org.example.task;

public class OneTimeTask extends ScheduledTask{
    private long executionTime;

    public OneTimeTask(ExecutionContext context, long executionTime) {
        super(context);
        this.executionTime = executionTime;
    }


    @Override
    public long getExecutionTime() {
        return executionTime;
    }

    @Override
    public boolean isRecurring() {
        return false;
    }

    @Override
    public ScheduledTask getNextScheduledTask() {
        return null;
    }
}
