import javax.swing.SwingUtilities;

import ui.GameFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }
}
