import view.Panel;

public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Panel().setVisible(true);
        });
    }
}