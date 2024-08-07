public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.addTask(new Task("Complete Java Project"));
        manager.addTask(new Task("Write README"));

        manager.completeTask("Complete Java Project");
        manager.listTasks();
    }
}
