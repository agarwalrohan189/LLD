package org.example.store;

import org.example.task.ScheduledTask;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTaskStore implements TaskStore<ScheduledTask> {
    PriorityBlockingQueue<ScheduledTask> priorityBlockingQueue;

    public PriorityBlockingQueueTaskStore(Comparator<ScheduledTask> comparator, int size) {
        priorityBlockingQueue = new PriorityBlockingQueue<>(size, comparator);
    }

    @Override
    public ScheduledTask poll() {
        return priorityBlockingQueue.poll();
    }

    @Override
    public ScheduledTask peek() {
        return priorityBlockingQueue.peek();
    }

    @Override
    public void remove(ScheduledTask task) {
        priorityBlockingQueue.remove(task);
    }

    @Override
    public void add(ScheduledTask task) {
        priorityBlockingQueue.add(task);
    }
}
