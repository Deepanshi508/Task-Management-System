import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        System.out.println("=== Task Management System ===");

        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. Complete Task");
            System.out.println("3. List Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter task name: ");
                    String name = sc.nextLine().trim();
                    if (!name.isEmpty()) {
                        manager.addTask(new Task(name));
                        System.out.println("Task added.");
                    } else {
                        System.out.println("Task name cannot be empty.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter task name to mark complete: ");
                    String name = sc.nextLine();
                    manager.completeTask(name);
                    System.out.println("Task marked as completed (if found).");
                }
                case 3 -> manager.listTasks();
                case 4 -> {
                    System.out.println("Exiting... Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
