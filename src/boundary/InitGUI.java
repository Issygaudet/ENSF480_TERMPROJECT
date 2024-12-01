package boundary;

import javax.swing.*;
import java.awt.*;
import entity.*;

public class InitGUI {
    private JFrame mainFrame;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public InitGUI() {
        initializeFrame();
    }

    private void initializeFrame() {
        mainFrame = new JFrame("AcmePlex Cinemas Ticket Booking");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainFrame.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        mainFrame.setLocationRelativeTo(null);
        
        // Start with login view - removed Login parameter
        LoginView loginView = new LoginView(mainFrame);
        mainFrame.setContentPane(loginView);
    }

    public void show() {
        mainFrame.setVisible(true);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}