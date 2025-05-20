import java.time.LocalDate;

public class Task implements Comparable<Task> {
    private String name;
    private boolean completed;
    private int priority; // Lower number = higher priority
    private LocalDate deadline;

    public Task(String name, int priority, LocalDate deadline) {
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void completeTask() {
        this.completed = true;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        }
        if (this.deadline != null && other.deadline != null) {
            return this.deadline.compareTo(other.deadline);
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s | Priority: %d | Deadline: %s | Completed: %s",
                name,
                priority,
                (deadline != null) ? deadline.toString() : "None",
                completed ? "Yes" : "No");
    }
}
