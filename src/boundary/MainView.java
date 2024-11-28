package boundary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import entity.*;

public class MainView extends JPanel {
    private JComboBox<String> movieSelector;
    private JComboBox<String> showtimeSelector;
    private JSpinner ticketQuantity;
    private JButton addToCartButton;
    private JButton viewCartButton;
    private JLabel priceLabel;
    private JFrame parentFrame;

    public MainView(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new GridBagLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Movie Selection");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Movie Selection
        JLabel movieLabel = new JLabel("Select Movie:");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(movieLabel, gbc);

        movieSelector = new JComboBox<>(new String[]{"Movie 1", "Movie 2", "Movie 3"});
        gbc.gridx = 1;
        add(movieSelector, gbc);

        // Showtime Selection
        JLabel showtimeLabel = new JLabel("Select Showtime:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(showtimeLabel, gbc);

        showtimeSelector = new JComboBox<>(new String[]{"2:00 PM", "5:00 PM", "8:00 PM"});
        gbc.gridx = 1;
        add(showtimeSelector, gbc);

        // Ticket Quantity
        JLabel quantityLabel = new JLabel("Number of Tickets:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(quantityLabel, gbc);

        ticketQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        gbc.gridx = 1;
        add(ticketQuantity, gbc);

        // Price
        priceLabel = new JLabel("Price per ticket: $12.00");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(priceLabel, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addToCartButton = new JButton("Add to Cart");
        viewCartButton = new JButton("View Cart");
        buttonPanel.add(addToCartButton);
        buttonPanel.add(viewCartButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setupActionListeners();
    }

    private void setupActionListeners() {
        viewCartButton.addActionListener(e -> {
            CartView cartView = new CartView(parentFrame);
            parentFrame.setContentPane(cartView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }
}

