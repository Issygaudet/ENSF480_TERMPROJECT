package boundary;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import entity.*;
import controller.InstanceController;



public class CartView extends JPanel {
    private JList<String> cartItems;
    private JLabel totalLabel;
    private JButton checkoutButton;
    private JButton continueShoppingButton;
    private JFrame parentFrame;

    public CartView(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new BorderLayout(10, 10));
        initializeComponents();
    }

    private void initializeComponents() {
        // Header
        JLabel titleLabel = new JLabel("Shopping Cart", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Cart Items
        DefaultListModel<String> listModel = new DefaultListModel<>();
        ArrayList<Ticket> tickets = InstanceController.getInstance().getTicketCart().getTicketsInCart();
        if (tickets.isEmpty()) {
            listModel.addElement("Cart is currently empty.");
        } else {
            for (Ticket ticket : tickets) {
                listModel.addElement(ticket.toString());
            }
        }
        // listModel.addElement("Movie 1 - 2:00 PM (2 tickets) - $24.00");
        cartItems = new JList<>(listModel);
        cartItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(cartItems);
        add(scrollPane, BorderLayout.CENTER);

        // South Panel (Total + Buttons)
        JPanel southPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        
        // totalLabel = new JLabel("Total: $24.00", SwingConstants.RIGHT);
        totalLabel = new JLabel("Total: $" + InstanceController.getInstance().getTicketCart().getTotalPrice(), SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        southPanel.add(totalLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        continueShoppingButton = new JButton("Continue Shopping");
        checkoutButton = new JButton("Proceed to Checkout");
        buttonPanel.add(continueShoppingButton);
        buttonPanel.add(checkoutButton);
        southPanel.add(buttonPanel);

        add(southPanel, BorderLayout.SOUTH);

        setupActionListeners();
    }

    private void setupActionListeners() {
        checkoutButton.addActionListener(e -> {
            CheckoutView checkoutView = new CheckoutView(parentFrame);
            parentFrame.setContentPane(checkoutView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        continueShoppingButton.addActionListener(e -> {
            MainView mainView = new MainView(parentFrame);
            parentFrame.setContentPane(mainView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }
}
