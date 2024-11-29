package controller;

import boundary.InitGUI;
import database.ReadDatabase;
import javax.swing.SwingUtilities;

public class ControlGUI {
    private InitGUI gui;
    private static ControlGUI instance = null;

    private ControlGUI() {
        gui = new InitGUI();
        initializeDatabase(); // Initialize and populate the database on startup
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

    private void initializeDatabase() {
        ReadDatabase readDatabase = new ReadDatabase();
        readDatabase.populateDatabase();
        System.out.println("Database initialized and populated.");
    }

    public InitGUI getGUI() {
        return gui;
    }

    public static void main(String[] args) {
        ControlGUI controller = ControlGUI.getInstance();
        controller.startGUI();
    }
}
