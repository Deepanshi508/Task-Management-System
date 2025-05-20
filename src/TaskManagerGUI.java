import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TaskManagerGUI extends JFrame {
    private TaskManager manager;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;

    public TaskManagerGUI() {
        manager = new TaskManager();

        setTitle("Task Manager");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inputs
        JTextField taskNameInput = new JTextField(15);
        JTextField priorityInput = new JTextField(5);
        JTextField deadlineInput = new JTextField(10);
        deadlineInput.setToolTipText("YYYY-MM-DD or leave empty");

        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Complete Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton editButton = new JButton("Edit Task");

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Task Name:"));
        inputPanel.add(taskNameInput);
        inputPanel.add(new JLabel("Priority (int):"));
        inputPanel.add(priorityInput);
        inputPanel.add(new JLabel("Deadline (YYYY-MM-DD):"));
        inputPanel.add(deadlineInput);
        inputPanel.add(addButton);
        inputPanel.add(completeButton);
        inputPanel.add(deleteButton);
        inputPanel.add(editButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);

        refreshTaskList();

        addButton.addActionListener(e -> {
            String name = taskNameInput.getText().trim();
            String prioStr = priorityInput.getText().trim();
            String deadlineStr = deadlineInput.getText().trim();

            if (name.isEmpty() || prioStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Task name and priority are required.");
                return;
            }

            int priority;
            try {
                priority = Integer.parseInt(prioStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Priority must be an integer.");
                return;
            }

            LocalDate deadline = null;
            if (!deadlineStr.isEmpty()) {
                try {
                    deadline = LocalDate.parse(deadlineStr);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid deadline format. Use YYYY-MM-DD.");
                    return;
                }
            }

            if (!manager.addTask(new Task(name, priority, deadline))) {
                JOptionPane.showMessageDialog(this, "Task with this name already exists.");
                return;
            }

            taskNameInput.setText("");
            priorityInput.setText("");
            deadlineInput.setText("");
            refreshTaskList();
        });

        completeButton.addActionListener(e -> {
            String selected = taskList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a task first.");
                return;
            }
            String taskName = extractTaskName(selected);
            if (manager.completeTask(taskName)) {
                refreshTaskList();
            }
        });

        deleteButton.addActionListener(e -> {
            String selected = taskList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a task first.");
                return;
            }
            String taskName = extractTaskName(selected);
            if (manager.deleteTask(taskName)) {
                refreshTaskList();
            }
        });

        editButton.addActionListener(e -> {
            String selected = taskList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a task first.");
                return;
            }
            String oldName = extractTaskName(selected);

            String newName = JOptionPane.showInputDialog(this, "Enter new task name:", oldName);
            if (newName == null || newName.trim().isEmpty()) return;

            String prioStr = JOptionPane.showInputDialog(this, "Enter new priority (int):");
            if (prioStr == null || prioStr.trim().isEmpty()) return;

            int newPriority;
            try {
                newPriority = Integer.parseInt(prioStr.trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Priority must be an integer.");
                return;
            }

            String deadlineStr = JOptionPane.showInputDialog(this, "Enter new deadline (YYYY-MM-DD) or leave empty:");
            LocalDate newDeadline = null;
            if (deadlineStr != null && !deadlineStr.trim().isEmpty()) {
                try {
                    newDeadline = LocalDate.parse(deadlineStr.trim());
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid deadline format.");
                    return;
                }
            }

            if (!manager.editTask(oldName, newName.trim(), newPriority, newDeadline)) {
                JOptionPane.showMessageDialog(this, "Edit failed. Maybe task with new name already exists.");
                return;
            }
            refreshTaskList();
        });
    }

    private String extractTaskName(String listEntry) {
        // Task name is before the first " | "
        int idx = listEntry.indexOf(" | ");
        return (idx == -1) ? listEntry : listEntry.substring(0, idx);
    }
