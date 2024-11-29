package boundary;

import javax.swing.*;

import controller.InstanceController;

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

    private JTextField cardNumberField;
    private JTextField cardExpiryField;
    private JTextField cardCVVField;
    private JTextField cardHolderField;
    private final float MONTHLY_FEE = 20.0f;

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
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
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

        // Add credit card fields
        JLabel paymentLabel = new JLabel("Payment Information - $20 Monthly Fee");
        paymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 5, 10, 5);
        add(paymentLabel, gbc);

        addField("Card Holder Name:", cardHolderField = new JTextField(20), gbc, 6);
        addField("Card Number:", cardNumberField = new JTextField(16), gbc, 7);
        addField("Expiry Date (MM/YY):", cardExpiryField = new JTextField(5), gbc, 8);
        addField("CVV:", cardCVVField = new JTextField(3), gbc, 9);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
            
        registerButton = new JButton("Register & Pay");
        registerButton.setPreferredSize(new Dimension(120, 30));
        buttonPanel.add(registerButton);

        backToLoginButton = new JButton("Back to Login");
        backToLoginButton.setPreferredSize(new Dimension(120, 30));
        buttonPanel.add(backToLoginButton);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 5, 5, 5);
        add(buttonPanel, gbc);

        // Add action listeners

          registerButton.addActionListener(e -> {
        if (validateFields()) {
            // Create UserBankInfo object
                UserBankInfo bankInfo = new UserBankInfo(
                    0, // bankInfoID
                    cardNumberField.getText(), // No need to parse as int anymore
                    cardHolderField.getText(),
                    new Date(1, 1, 2024), // example expiry date
                    Integer.parseInt(cardCVVField.getText())
                );

            Date registrationDate = new Date(1, 1, 2024);

            // Create UserRegistered object
            UserRegistered newUser = new UserRegistered(
                0,  // Add userID as first parameter
                nameField.getText(), 
                emailField.getText(),
                new String(passwordField.getPassword()),
                bankInfo, 
                registrationDate
            );

            // Process initial payment
            Payment initialPayment = new Payment(0, MONTHLY_FEE, new Date(1, 1, 2024)); // Example date
            newUser.makePayment(initialPayment);
            newUser.payAnnualFee(); // Mark as paid

            // Set user in InstanceController
            InstanceController.getInstance().setUser(newUser);
            
            // Navigate to MainView
            MainView mainView = new MainView(parentFrame);
            parentFrame.setContentPane(mainView);
            parentFrame.revalidate();
            parentFrame.repaint();
        }
    });
        
        backToLoginButton.addActionListener(e -> {
            LoginView loginView = new LoginView(parentFrame); 
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

    private boolean validateFields() {
        // Basic validation
        if (nameField.getText().isEmpty() || 
            emailField.getText().isEmpty() ||
            passwordField.getPassword().length == 0 ||
            cardHolderField.getText().isEmpty() ||
            cardNumberField.getText().isEmpty() ||
            cardExpiryField.getText().isEmpty() ||
            cardCVVField.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(this,
                "Please fill in all fields",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
        // Add additional validation as needed (card number format, expiry date format, etc.)
        return true;
    }
}