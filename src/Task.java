import java.time.LocalDate;

public class Task implements Comparable<Task> {
    private String name;
    private int priority;
    private LocalDate deadline;  // changed from String to LocalDate
    private boolean completed;

    public Task(String name, int priority, LocalDate deadline) {
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
        this.completed = false;
    }

    public String getName() { return name; }
    public int getPriority() { return priority; }
    public LocalDate getDeadline() { return deadline; }
    public boolean isCompleted() { return completed; }

    public void setName(String name) { this.name = name; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public void completeTask() {
        this.completed = true;
    }

    @Override
    public int compareTo(Task other) {
        // Lower priority number = higher priority
        int priorityCompare = Integer.compare(this.priority, other.priority);
        if (priorityCompare != 0) {
            return priorityCompare;
        }
        // Handle null deadlines: consider null deadline as "after" any non-null deadline
        if (this.deadline == null && other.deadline == null) return 0;
        if (this.deadline == null) return 1;
        if (other.deadline == null) return -1;

        return this.deadline.compareTo(other.deadline);
    }

    @Override
    public String toString() {
        String deadlineStr = (deadline == null) ? "No deadline" : deadline.toString();
        String status = completed ? "Completed" : "Pending";
        return String.format("%s | Priority: %d | Deadline: %s | %s", name, priority, deadlineStr, status);
    }
}
