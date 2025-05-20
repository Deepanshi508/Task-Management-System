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
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        taskInput = new JTextField(20);
        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Complete Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton editButton = new JButton("Edit Task");

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        JPanel topPanel = new JPanel();
        topPanel.add(taskInput);
        topPanel.add(addButton);
        topPanel.add(completeButton);
        topPanel.add(deleteButton);
        topPanel.add(editButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);

        refreshTaskList();

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
                String taskName = selected.substring(4);
                manager.completeTask(taskName);
                refreshTaskList();
            }
        });

        deleteButton.addActionListener(e -> {
            String selected = taskList.getSelectedValue();
            if (selected != null) {
                String taskName = selected.substring(4);
                manager.deleteTask(taskName);
                refreshTaskList();
            }
        });

        editButton.addActionListener(e -> {
            String selected = taskList.getSelectedValue();
            if (selected != null) {
                String oldTaskName = selected.substring(4);
                String newTaskName = JOptionPane.showInputDialog(this, "Edit Task Name:", oldTaskName);
                if (newTaskName != null && !newTaskName.trim().isEmpty()) {
                    manager.editTask(oldTaskName, newTaskName.trim());
                    refreshTaskList();
                }
            }
        });
    }

    private void refreshTaskList() {
        taskListModel.clear();
        for (Task t : manager.getTasks()) {
            taskListModel.addElement(t.toString());
        }
    }
}
