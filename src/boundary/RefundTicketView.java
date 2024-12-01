package boundary;

import controller.InstanceController;
import entity.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RefundTicketView extends JPanel {
    private JFrame parentFrame;
    private JTextField ticketNumber;

    public RefundTicketView(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new BorderLayout(10, 10));
        initializeComponents();
    }

    private void initializeComponents() {
        // Screen label at top
        JLabel screenLabel = new JLabel("Refund Ticket", SwingConstants.CENTER);
        screenLabel.setFont(new Font("Arial", Font.BOLD, 20));
        screenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(screenLabel, BorderLayout.NORTH);

        // Ticket number input
        JPanel ticketNumberPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel ticketNumberLabel = new JLabel("Ticket Number:");
        ticketNumber = new JTextField(10);
        ticketNumberPanel.add(ticketNumberLabel);
        ticketNumberPanel.add(ticketNumber);
        add(ticketNumberPanel, BorderLayout.CENTER);

        // Control panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton confirmButton = new JButton("Confirm Refund");
        JButton cancelButton = new JButton("Cancel");

        controlPanel.add(confirmButton);
        controlPanel.add(cancelButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Add action listeners
        // confirmButton.addActionListener(e -> {
        //     String ticketNumberStr = ticketNumber.getText();
        //     if (!ticketNumberStr.matches("[0-9]+")) {
        //         JOptionPane.showMessageDialog(parentFrame, "Please enter a valid ticket number.");
        //         return;
        //     }
        //     int ticketNumber = Integer.parseInt(ticketNumberStr);
        //     //temp
        //     double ticketPrice = 10.00; //TODO get ticket price from database
        //     double refundAmount = 0.85;
        //     if (InstanceController.getInstance().getUser() instanceof UserRegistered) {
        //         refundAmount = 1.00;
        //     }
        //     JOptionPane.showMessageDialog(parentFrame, "Ticket number: " + ticketNumber + " refunded successfully. $" +
        //             (ticketPrice * refundAmount) + " has been refunded to your account.");
        //     goToMain();
        // });

        // cancelButton.addActionListener(e -> {
        //     goToMain();
        // });
        confirmButton.addActionListener(e -> {
            String ticketNumberStr = ticketNumber.getText();
            if (!ticketNumberStr.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(parentFrame, "Please enter a valid ticket number.");
                return;
            }

            String ticketId = ticketNumberStr.trim();
            List<Ticket> tickets = InstanceController.getInstance().getTicketCart().getTicketsInCart();
            Ticket ticketToRefund = null;
            for (Ticket ticket : tickets) {
                if (ticket.getTicketID().equals(ticketId)) {
                    ticketToRefund = ticket;
                    break;
                }
            }

            if (ticketToRefund != null) {
                //IF MORE THAN 72 HRS
                if (ticketToRefund.canCancel()) {
                    InstanceController.getInstance().getTicketCart().removeFromCart(ticketToRefund);
                    double ticketPrice = ticketToRefund.getMovie().getPrice();
                    double refundAmount = 0.85;
                    if (InstanceController.getInstance().getUser() instanceof UserRegistered) {
                        refundAmount = 1.00;
                    }
                    JOptionPane.showMessageDialog(parentFrame, "Ticket number: " + ticketNumberStr + " refunded successfully. $" +
                            (ticketPrice * refundAmount) + " has been refunded to your account.");
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Tickets can only be refunded up to 72 hours prior to the show.", "Refund Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Ticket not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            goToMain();
        });
    }

    private void goToMain() {
        MainView mainView = new MainView(parentFrame);
        parentFrame.setContentPane(mainView);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}