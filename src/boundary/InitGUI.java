package boundary;

import javax.swing.*;
import java.awt.*;
import entity.Login;

public class InitGUI {
    private JFrame mainFrame;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public InitGUI() {
        initializeFrame();
    }

    private void initializeFrame() {
        mainFrame = new JFrame("Movie Ticket Reservation System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainFrame.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        mainFrame.setLocationRelativeTo(null);
        
        // Start with login view
        LoginView loginView = new LoginView(mainFrame, new Login());
        mainFrame.setContentPane(loginView);
    }

    public void show() {
        mainFrame.setVisible(true);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}