import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void completeTask(String taskName) {
        for (Task task : tasks) {
            if (task.getName().equals(taskName)) {
                task.completeTask();
                break;
            }
        }
    }

    public void listTasks() {
        for (Task task : tasks) {
            System.out.println("Task: " + task.getName() + " | Completed: " + task.isCompleted());
        }
    }
}
