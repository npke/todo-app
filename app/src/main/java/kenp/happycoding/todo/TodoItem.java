package kenp.happycoding.todo;

import java.text.SimpleDateFormat;
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

    public TodoItem() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriorityString() {
        if (priority == 1)
            return "High";
        else if (priority == 2)
            return "Medium";
        return "Low";
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDueDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
        return dateFormat.format(new Date(dueDate));
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getPriority() {
        return priority;
    }

    public long getDueDate() {
        return dueDate;
    }

    public int getId() {
        return id;
    }
}
