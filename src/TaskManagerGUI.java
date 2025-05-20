import javax.swing.*;
import java.awt.*;

public class TaskManagerGUI extends JFrame {
    private TaskManager manager;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInput;

    public TaskManagerGUI() {
        manager = new TaskManager();

        setTitle("Task Manager");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI components
        taskInput = new JTextField(20);
        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Complete Task");
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        // Layout
        JPanel topPanel = new JPanel();
        topPanel.add(taskInput);
        topPanel.add(addButton);
        topPanel.add(completeButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Load existing tasks
        refreshTaskList();

        // Button actions
        addButton.addActionListener(e -> {
            String name = taskInput.getText().trim();
            if (!name.isEmpty()) {
                manager.addTask(new Task(name));
                taskInput.setText("");
                refreshTaskList();
            }
        });

        completeButton.addActionListener(e -> {
            String selected = taskList.getSelectedValue();
            if (selected != null) {
                String taskName = selected.substring(4); // remove [✓] or [ ] + space
                manager.completeTask(taskName);
                refreshTaskList();
            }
        });
    }

    private void refreshTaskList() {
        taskListModel.clear();
        for (Task t : manager.getTasks()) {
            taskListModel.addElement(t.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TaskManagerGUI().setVisible(true);
        });
    }
}
