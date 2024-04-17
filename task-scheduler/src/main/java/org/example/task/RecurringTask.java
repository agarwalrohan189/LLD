package org.example.task;

public class RecurringTask extends ScheduledTask{
    private long executionTime;
    private long interval;

    public RecurringTask(ExecutionContext context, long executionTime, long interval) {
        super(context);
        this.executionTime = executionTime;
        this.interval = interval;
    }

    @Override
    public long getExecutionTime() {
        return executionTime;
    }

    @Override
    public boolean isRecurring() {
        return true;
    }

    @Override
    public ScheduledTask getNextScheduledTask() {
        return new RecurringTask(context, executionTime+interval, interval);
    }
}
