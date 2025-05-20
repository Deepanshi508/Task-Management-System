public class Task implements Comparable<Task> {
    private String name;
    private int priority;
    private String deadline;
    private boolean completed;

    public Task(String name, int priority, String deadline) {
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
        this.completed = false;
    }

    public String getName() { return name; }
    public int getPriority() { return priority; }
    public String getDeadline() { return deadline; }
    public boolean isCompleted() { return completed; }

    public void setName(String name) { this.name = name; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setDeadline(String deadline) { this.deadline = deadline; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority)
            return Integer.compare(this.priority, other.priority);
        return this.deadline.compareTo(other.deadline);
    }

    @Override
    public String toString() {
        return String.format("%s (Priority: %d, Deadline: %s) [%s]", 
            name, priority, deadline, completed ? "Completed" : "Pending");
    }
}
