public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TaskManagerGUI().setVisible(true);
        });
    }
}
