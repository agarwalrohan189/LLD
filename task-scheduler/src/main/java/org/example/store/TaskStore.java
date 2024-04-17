package org.example.store;

import org.example.task.ScheduledTask;

public interface TaskStore<T extends ScheduledTask> {

    T poll();

    T peek();

    void remove(T t);

    void add(T t);
}
