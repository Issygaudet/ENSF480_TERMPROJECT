package boundary;

import javax.swing.*;

import controller.InstanceController;
import entity.UserRegistered;

import java.awt.*;

public class CheckoutView extends JPanel {
    private JTextField cardHolder;
    private JTextField cardNumberField;
    private JTextField expiryField;
    private JTextField cvvField;
    private JButton confirmButton;
    private JButton backButton;
    private JFrame parentFrame;

    public CheckoutView(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new GridBagLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        JLabel titleLabel = new JLabel("Checkout");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Order Summary
        JLabel summaryLabel = new JLabel("Order Total: $24.00");
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 1;
        add(summaryLabel, gbc);

        // Payment Details
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;

        // ONLY RQST CARD DETAILS IF NOT RU
        if (!(InstanceController.getInstance().getUser() instanceof UserRegistered)) {
            // Card Holder
            addField("Card Holder:", cardHolder = new JTextField(20), gbc, 2);
            // Card Number
            addField("Card Number:", cardNumberField = new JTextField(16), gbc, 3);
            
            // Expiry Date
            addField("Expiry (MM/YY):", expiryField = new JTextField(5), gbc, 4);
            
            // CVV
            addField("CVV:", cvvField = new JTextField(3), gbc, 5);
        }

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        backButton = new JButton("Back to Cart");
        confirmButton = new JButton("Confirm Payment");
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);

        gbc.gridx = 0;
        // gbc.gridy = 5;
        gbc.gridy= (InstanceController.getInstance().getUser() == null) ?2:6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        setupActionListeners();
    }

    private void addField(String labelText, JTextField field, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        gbc.gridx = 0;
        gbc.gridy = row;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(field, gbc);
    }

    private void setupActionListeners() {
        backButton.addActionListener(e -> {
            CartView cartView = new CartView(parentFrame);
            parentFrame.setContentPane(cartView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        confirmButton.addActionListener(e -> {
            if (validateFields()) {
                JOptionPane.showMessageDialog(parentFrame, 
                    "Payment successful! Your tickets have been booked.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                
                MainView mainView = new MainView(parentFrame);
                parentFrame.setContentPane(mainView);
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });

    }

    private boolean validateFields() {
        // Basic validation
        if (!(InstanceController.getInstance().getUser() instanceof UserRegistered)) {
            if (cardHolder.getText().isEmpty() || 
                cardNumberField.getText().isEmpty() ||
                expiryField.getText().isEmpty() ||
                cvvField.getText().isEmpty()) {
                
                JOptionPane.showMessageDialog(this,
                    "Please fill in all card details",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
