import java.util.*;
import java.io.*;

public class TaskManager {
    private List<Task> tasks;
    private final String FILE_NAME = "tasks.dat";

    public TaskManager() {
        loadTasks();
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    public void completeTask(String taskName) {
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(taskName)) {
                task.completeTask();
                break;
            }
        }
        saveTasks();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    private void saveTasks() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadTasks() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tasks = (List<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            tasks = new ArrayList<>();
        }
    }
}
