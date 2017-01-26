package kenp.happycoding.todo;

import java.util.Date;

public class TodoItem {
    private int id;
    private String name;
    private int priority;
    private long dueDate;

    public TodoItem(int id, String name, int priority, long dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
