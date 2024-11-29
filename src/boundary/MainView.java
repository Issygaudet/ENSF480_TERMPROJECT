package boundary;

import javax.swing.*;

import controller.InstanceController;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.*;
import database.ControlDatabase;

public class MainView extends JPanel {
    private JComboBox<String> movieSelector;
    private JComboBox<String> showtimeSelector;
    private JSpinner ticketQuantity;
    private JButton addToCartButton;
    private JButton viewCartButton;
    private JLabel priceLabel;
    private JFrame parentFrame;
    private Map<String, Movie> movieMap; // Map to store movie names and corresponding Movie objects

    private JButton backButton;
    private JButton logoutButton;

    public MainView(JFrame parent) {
        this.parentFrame = parent;
        this.movieMap = new HashMap<>(); // Initialize the map
        setLayout(new GridBagLayout());
        initializeComponents();
        loadMoviesFromDatabase(); // Fetch movies from the database
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

        movieSelector = new JComboBox<>();
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
        backButton = new JButton("Return to Login Page");
        logoutButton = new JButton("Logout");
        buttonPanel.add(addToCartButton);
        buttonPanel.add(viewCartButton);
        buttonPanel.add(backButton);
        buttonPanel.add(logoutButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setupActionListeners();
    }

    private void loadMoviesFromDatabase() {
        ControlDatabase database = ControlDatabase.getInstance(); // Get the database instance
        database.fetchAllMovies(); // Fetch movies from the database
    
        ArrayList<Movie> movies = new ArrayList<>(database.getAllMovies());
        movieSelector.removeAllItems(); // Clear existing items in the dropdown
        movieMap.clear(); // Clear the movie map to avoid stale data
    
        for (Movie movie : movies) {
            movieSelector.addItem(movie.getName()); // Add movie names to the dropdown
            movieMap.put(movie.getName(), movie); // Store the mapping between name and movie object
        }
    
        updatePriceLabel(); // Set the initial price label
    }

    private void setupActionListeners() {
        // Update price label when a new movie is selected
        movieSelector.addActionListener(e -> updatePriceLabel());
    
        // Add to Cart button functionality
        addToCartButton.addActionListener(e -> {
            // Logic to add selected movie, showtime, and quantity to the cart
            String selectedMovieName = (String) movieSelector.getSelectedItem();
            String selectedShowtime = (String) showtimeSelector.getSelectedItem();
            int quantity = (int) ticketQuantity.getValue();

            if (selectedMovieName != null && selectedShowtime != null) {
                Movie selectedMovie = movieMap.get(selectedMovieName);
                // (Movie movie, Theatre theatre, String date, Showtime showtime, String seat) 
                Ticket ticket = new Ticket(selectedMovie, null, null, null, "A5");
                InstanceController.getInstance().getTicketCart().addToCart(ticket);
                

                JOptionPane.showMessageDialog(parentFrame, 
                    "Added to cart successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

                // Refresh the MainView
                MainView mainView = new MainView(parentFrame);
                parentFrame.setContentPane(mainView);
                parentFrame.revalidate();
                parentFrame.repaint();
            } else {
                JOptionPane.showMessageDialog(parentFrame, 
                    "Please select a movie and showtime.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        // View cart button functionality
        viewCartButton.addActionListener(e -> {
            CartView cartView = new CartView(parentFrame);
            parentFrame.setContentPane(cartView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        // back button functionality
        backButton.addActionListener(e -> {
            LoginView lv = new LoginView(parentFrame);
            parentFrame.setContentPane(lv);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

         // Logout button functionality
         logoutButton.addActionListener(e -> {
            // Perform logout
            // InstanceController.getInstance().logout();

            // Show logout success message
            JOptionPane.showMessageDialog(parentFrame, 
                "Logged out successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

            // Navigate to login view
            LoginView loginView = new LoginView(parentFrame);
            parentFrame.setContentPane(loginView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

    }
    
    private void updatePriceLabel() {
        String selectedMovieName = (String) movieSelector.getSelectedItem();
        if (selectedMovieName != null && movieMap.containsKey(selectedMovieName)) {
            Movie selectedMovie = movieMap.get(selectedMovieName);
            priceLabel.setText("Price per ticket: $" + String.format("%.2f", selectedMovie.getPrice()));
        } else {
            priceLabel.setText("Price per ticket: N/A");
        }
    }
}
