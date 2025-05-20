import java.time.LocalDate;
import java.util.*;

public class TaskManager {
    private PriorityQueue<Task> taskQueue;
    private LinkedList<Task> taskList;  // maintains insertion order or history
    private HashMap<String, Task> taskMap;

    public TaskManager() {
        taskQueue = new PriorityQueue<>();
        taskList = new LinkedList<>();
        taskMap = new HashMap<>();
    }

    public boolean addTask(Task task) {
        if (taskMap.containsKey(task.getName())) {
            return false; // Task with same name exists
        }
        taskQueue.offer(task);
        taskList.add(task);
        taskMap.put(task.getName(), task);
        return true;
    }

    public boolean completeTask(String taskName) {
        Task task = taskMap.get(taskName);
        if (task != null && !task.isCompleted()) {
            task.completeTask();
            return true;
        }
        return false;
    }

    public boolean editTask(String oldName, String newName, int newPriority, LocalDate newDeadline) {
        Task task = taskMap.get(oldName);
        if (task == null || taskMap.containsKey(newName)) {
            return false;
        }
        // Remove old task from priority queue (inefficient, but works for demo)
        taskQueue.remove(task);
        // Update task details
        task.setName(newName);
        task.setPriority(newPriority);
        task.setDeadline(newDeadline);
        // Add back to priority queue
        taskQueue.offer(task);

        // Update map key if name changed
        if (!oldName.equals(newName)) {
            taskMap.remove(oldName);
            taskMap.put(newName, task);
        }
        return true;
    }

    public boolean deleteTask(String taskName) {
        Task task = taskMap.remove(taskName);
        if (task == null) {
            return false;
        }
        taskQueue.remove(task);
        taskList.remove(task);
        return true;
    }

    public List<Task> getAllTasksOrderedByPriority() {
        List<Task> list = new ArrayList<>(taskQueue);
        Collections.sort(list);
        return list;
    }

    public List<Task> getTasksInInsertionOrder() {
        return new ArrayList<>(taskList);
    }
}
