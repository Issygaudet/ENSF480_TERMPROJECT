package boundary;

import javax.swing.*;
import java.awt.*;
import entity.*;

public class RegistrationView extends JPanel {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton backToLoginButton;
    private JFrame parentFrame;

    public RegistrationView(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new GridBagLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        JLabel titleLabel = new JLabel("New User Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 20, 40, 20);
        add(titleLabel, gbc);

        // Name field
        addField("Name:", nameField = new JTextField(20), gbc, 1);
        
        // Email field
        addField("Email:", emailField = new JTextField(20), gbc, 2);
        
        // Password field
        addField("Password:", passwordField = new JPasswordField(20), gbc, 3);
        
        // Confirm Password field
        addField("Confirm Password:", confirmPasswordField = new JPasswordField(20), gbc, 4);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(registerButton);

        backToLoginButton = new JButton("Back to Login");
        backToLoginButton.setPreferredSize(new Dimension(120, 30));
        buttonPanel.add(backToLoginButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 5, 5, 5);
        add(buttonPanel, gbc);

        // Add action listeners

        registerButton.addActionListener(e -> {
            // Here you would normally validate registration info
            // For now, just navigate to MainView
            MainView mainView = new MainView(parentFrame);
            parentFrame.setContentPane(mainView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        
        backToLoginButton.addActionListener(e -> {
            LoginView loginView = new LoginView(parentFrame, new Login());
            parentFrame.setContentPane(loginView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }

    private void addField(String labelText, JTextField field, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        add(field, gbc);
    }
}