// src/boundary/SeatMapView.java
package boundary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import entity.*;
import controller.InstanceController;

public class SeatMapView extends JPanel {
    private JFrame parentFrame;
    private ScreeningRoom screeningRoom;
    private int ticketsToSelect;
    private ArrayList<Seat> selectedSeats = new ArrayList<>();
    private JButton[][] seatButtons;
    private Movie selectedMovie;        // Add these
    private Showtime selectedShowtime;  // fields

    public SeatMapView(JFrame parent, ScreeningRoom room, int tickets, Movie movie, Showtime showtime) {
        this.parentFrame = parent;
        this.screeningRoom = room;
        this.ticketsToSelect = tickets;
        this.selectedMovie = movie;         // Store the
        this.selectedShowtime = showtime;   // selected values
        setLayout(new BorderLayout(10, 10));
        initializeComponents();
    }

    private void initializeComponents() {
        // Screen label at the top
        JLabel screenLabel = new JLabel("SCREEN", SwingConstants.CENTER);
        screenLabel.setFont(new Font("Arial", Font.BOLD, 20));
        screenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(screenLabel, BorderLayout.NORTH);

        // Seat grid
        JPanel seatsPanel = new JPanel(new GridLayout(screeningRoom.getRows(), screeningRoom.getColumns(), 5, 5));
        seatButtons = new JButton[screeningRoom.getRows()][screeningRoom.getColumns()];

        for (int i = 0; i < screeningRoom.getRows(); i++) {
            for (int j = 0; j < screeningRoom.getColumns(); j++) {
                Seat seat = screeningRoom.getSeat(i + 1, j + 1);
                if (seat == null) {
                    System.out.println("Seat not found at Row " + (i + 1) + ", Column " + (j + 1));
                    JButton placeholder = new JButton("N/A");
                    placeholder.setEnabled(false);
                    seatsPanel.add(placeholder);
                    continue;
                }
                JButton seatButton = createSeatButton(seat);
                seatButtons[i][j] = seatButton;
                seatsPanel.add(seatButton);
            }
        }

        add(seatsPanel, BorderLayout.CENTER);

        // Control panel with Confirm and Cancel buttons
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton confirmButton = new JButton("Confirm Selection");
        JButton cancelButton = new JButton("Cancel");

        controlPanel.add(confirmButton);
        controlPanel.add(cancelButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Action listener for Confirm button
        confirmButton.addActionListener(e -> {
            if (selectedSeats.size() == ticketsToSelect) {
                TicketCart cart = InstanceController.getInstance().getTicketCart();
                for (Seat seat : selectedSeats) {
                    cart.addToCart(new Ticket(
                        generateTicketId(),
                        selectedMovie,                // Use the stored movie
                        screeningRoom.getTheatre(),
                        java.time.LocalDate.now().toString(), // Current date
                        selectedShowtime,             // Use the stored showtime
                        "Row " + ((seat.getSeatId() / screeningRoom.getColumns()) + 1) + 
                        " Seat " + ((seat.getSeatId() % screeningRoom.getColumns()) + 1)
                    ));
                }

                // Navigate to CartView
                CartView cartView = new CartView(parentFrame);
                parentFrame.setContentPane(cartView);
                parentFrame.revalidate();
                parentFrame.repaint();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Please select exactly " + ticketsToSelect + " seats.",
                    "Selection Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action listener for Cancel button
        cancelButton.addActionListener(e -> {
            MainView mainView = new MainView(parentFrame);
            parentFrame.setContentPane(mainView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }

        private JButton createSeatButton(Seat seat) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(40, 40));
        
        // Calculate which seats are reserved (3rd row)
        int row = (seat.getSeatId() / screeningRoom.getColumns()) + 1;
        boolean isReservedSeat = (row == 3);
        
        // Check if user is registered
        boolean isRegisteredUser = InstanceController.getInstance().getUser() instanceof UserRegistered;
        
        // Set initial color
        if (isReservedSeat) {
            button.setBackground(Color.RED);
        } else {
            button.setBackground(seat.isAvailable() ? Color.GREEN : Color.RED);
        }
        
        button.setOpaque(true);
        button.setBorderPainted(false);
    
        button.addActionListener(e -> {
            if (seat.isAvailable()) {
                // Check if guest user is trying to select reserved seat
                if (isReservedSeat && !isRegisteredUser) {
                    JOptionPane.showMessageDialog(this,
                        "This seat is reserved for registered users only.",
                        "Reserved Seat",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (!selectedSeats.contains(seat) && selectedSeats.size() < ticketsToSelect) {
                    selectedSeats.add(seat);
                    button.setBackground(Color.BLUE);
                } else if (selectedSeats.contains(seat)) {
                    selectedSeats.remove(seat);
                    button.setBackground(isReservedSeat ? Color.RED : Color.GREEN);
                }
            }
        });
    
        return button;
    }

    /**
     * Generates a unique ticket ID based on the current timestamp.
     * @return A unique ticket identifier.
     */
    private String generateTicketId() {
        return "TKT" + System.currentTimeMillis();
    }
}