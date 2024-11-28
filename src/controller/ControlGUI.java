package controller;

import boundary.InitGUI;
import javax.swing.SwingUtilities;

public class ControlGUI {
    private InitGUI gui;
    private static ControlGUI instance = null;

    private ControlGUI() {
        gui = new InitGUI();
    }

    public static ControlGUI getInstance() {
        if (instance == null) {
            instance = new ControlGUI();
        }
        return instance;
    }

    public void startGUI() {
        SwingUtilities.invokeLater(() -> {
            gui.show();
        });
    }

    public static void main(String[] args) {
        ControlGUI controller = ControlGUI.getInstance();
        controller.startGUI();
    }

    public InitGUI getGUI() {
        return gui;
    }
}