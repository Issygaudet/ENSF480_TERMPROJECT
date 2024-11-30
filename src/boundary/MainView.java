package boundary;

import javax.swing.*;

import controller.InstanceController;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.*;
import database.ControlDatabase;

import java.util.Calendar;

public class MainView extends JPanel {
    private JComboBox<String> theaterSelector; // Add a JComboBox for theater selection
    private JComboBox<String> movieSelector;
    private JComboBox<String> showtimeSelector;
    private JSpinner ticketQuantity;
    private JButton addToCartButton;
    private JButton viewCartButton;
    private JButton backButton;
    private JLabel priceLabel;
    private JFrame parentFrame;
    private Map<String, Movie> movieMap; // Map to store movie names and corresponding Movie objects
    private Map<String, Theatre> theatreMap;
    private Map<String, Showtime> showtimeMap; 

    public MainView(JFrame parent) {
        this.parentFrame = parent;
        this.movieMap = new HashMap<>(); // Initialize the map
        this.theatreMap = new HashMap<>();
        this.showtimeMap = new HashMap<>();
        setLayout(new GridBagLayout());
        initializeComponents();
        loadMoviesFromDatabase(); // Fetch movies from the database
        loadTheatersFromDatabase(); // Fetch theaters from the database
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

        // Theater Selection
        JLabel theaterLabel = new JLabel("Select Theater:");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(theaterLabel, gbc);

        theaterSelector = new JComboBox<>();
        gbc.gridx = 1;
        add(theaterSelector, gbc);

        // Initially hide movie selection, showtime, and ticket quantity
        JLabel movieLabel = new JLabel("Select Movie:");
        gbc.gridy = 2;
        gbc.gridx = 0;
        movieSelector = new JComboBox<>();
        movieSelector.setEnabled(false);  // Initially disable movieSelector
        add(movieLabel, gbc);
        gbc.gridx = 1;
        add(movieSelector, gbc);

        JLabel showtimeLabel = new JLabel("Select Showtime:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        showtimeSelector = new JComboBox<>();
        showtimeSelector.setEnabled(false);  // Initially disable showtimeSelector
        add(showtimeLabel, gbc);
        gbc.gridx = 1;
        add(showtimeSelector, gbc);

        // Ticket Quantity
        JLabel quantityLabel = new JLabel("Number of Tickets:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        ticketQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        ticketQuantity.setEnabled(false);  // Initially disable ticketQuantity
        add(quantityLabel, gbc);
        gbc.gridx = 1;
        add(ticketQuantity, gbc);

        // Price
        priceLabel = new JLabel("Price per ticket: $12.00");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(priceLabel, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addToCartButton = new JButton("Add to Cart");
        viewCartButton = new JButton("View Cart");
        backButton = new JButton("Return to Login Page");
        buttonPanel.add(addToCartButton);
        buttonPanel.add(viewCartButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
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

    private void loadTheatersFromDatabase() {
        ControlDatabase database = ControlDatabase.getInstance(); // Get the database instance
        database.fetchAllTheatres(); // Fetch theatres from the database
    
        ArrayList<Theatre> theatres = new ArrayList<>(database.getAllTheatres());
        theaterSelector.removeAllItems(); // Clear existing items in the dropdown
        theatreMap.clear(); // Clear the map to avoid stale data
    
        for (Theatre theatre : theatres) {
            theaterSelector.addItem(theatre.getLocation()); // Add theatre location to dropdown
            theatreMap.put(theatre.getLocation(), theatre); // Map theatre name to the Theatre object
        }
    }

    // Method to load showtimes from the database
    private void loadShowtimesFromDatabase(Movie movie, Theatre theatre) {
        ControlDatabase database = ControlDatabase.getInstance(); // Get the database instance
        ArrayList<Showtime> showtimes = database.fetchShowtimesForMovieAndTheatre(movie.getMovieId(), theatre.getTheatreId());

        showtimeSelector.removeAllItems(); // Clear existing showtimes
        showtimeMap.clear(); // Clear the map to avoid stale data

        // Populate the showtime selector with available showtimes
        for (Showtime showtime : showtimes) {
            String timeText = showtime.getTime().toString(); // Will return a format like "HH:mm:ss"
            showtimeSelector.addItem(timeText);
            showtimeMap.put(timeText, showtime);
        }
        

        // Enable the showtime selector
        showtimeSelector.setEnabled(true);
    }

    private void setupActionListeners() {
        // Update movie list based on the selected theatre
        theaterSelector.addActionListener(e -> {
            updateMovieSelector();
            showtimeSelector.removeAllItems(); // Clear showtimes when theatre changes
            showtimeSelector.setEnabled(false); // Disable until movie is selected
            ticketQuantity.setEnabled(false); // Disable until showtime is selected
        });
    
        // Update price label when a new movie is selected
        movieSelector.addActionListener(e -> {
            updatePriceLabel();
            String selectedMovieName = (String) movieSelector.getSelectedItem();
            String selectedTheatreName = (String) theaterSelector.getSelectedItem();
            
            if (selectedMovieName != null && selectedTheatreName != null) {
                Movie selectedMovie = movieMap.get(selectedMovieName);
                Theatre selectedTheatre = theatreMap.get(selectedTheatreName);
                
                if (selectedMovie != null && selectedTheatre != null) {
                    // Fetch and populate showtimes
                    ArrayList<Showtime> showtimes = ControlDatabase.getInstance()
                        .fetchShowtimesForMovieAndTheatre(selectedMovie.getMovieId(), selectedTheatre.getTheatreId());
                    
                    showtimeSelector.removeAllItems();
                    showtimeMap.clear();
                    
                    for (Showtime showtime : showtimes) {
                        if (showtime != null && showtime.getTime() != null) {
                            Calendar calendar = Calendar.getInstance();
//                            calendar.setTime(showtime.getTime());
//                            String timeStr = String.format("%02d:%02d",
//                                calendar.get(Calendar.HOUR_OF_DAY),
//                                calendar.get(Calendar.MINUTE));
                            String timeStr = showtime.getTime();
                            showtimeSelector.addItem(timeStr);
                            showtimeMap.put(timeStr, showtime);
                        }
                    }
                    
                    showtimeSelector.setEnabled(!showtimes.isEmpty());
                }
            }
        });
        
        showtimeSelector.addActionListener(e -> {
            ticketQuantity.setEnabled(true);
        });

        // Add to Cart button functionality
        addToCartButton.addActionListener(e -> {
            String selectedMovieName = (String) movieSelector.getSelectedItem();
            String selectedShowtime = (String) showtimeSelector.getSelectedItem();
            int quantity = (Integer) ticketQuantity.getValue();
        
            if (selectedMovieName != null && selectedShowtime != null) {
                Movie selectedMovie = movieMap.get(selectedMovieName);
                Showtime showtime = showtimeMap.get(selectedShowtime);
                Theatre selectedTheatre = theatreMap.get((String) theaterSelector.getSelectedItem());

                for (int i = 0; i < quantity; i++) {
                    Ticket ticket = new Ticket(selectedMovie, selectedTheatre, showtime.getTime().toString(), showtime, "A" + (i + 1));
                    InstanceController.getInstance().getTicketCart().addToCart(ticket);
                }

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
                JOptionPane.showMessageDialog(this,
                    "Please select a movie and showtime.",
                    "Selection Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            // Check if a user is logged in
            if (InstanceController.getInstance().getUser() != null) {
                // Perform logout
                // InstanceController.getInstance().logout();
        
                // Show logout success message
                JOptionPane.showMessageDialog(parentFrame, 
                    "Logged out successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        
            // Navigate to login view
            LoginView loginView = new LoginView(parentFrame);
            parentFrame.setContentPane(loginView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        // View cart button functionality
        viewCartButton.addActionListener(e -> {
            CartView cartView = new CartView(parentFrame);
            parentFrame.setContentPane(cartView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }

    private void updateMovieSelector() {
        // Get the selected theatre
        String selectedTheatreName = (String) theaterSelector.getSelectedItem();
        Theatre selectedTheatre = theatreMap.get(selectedTheatreName);
    
        if (selectedTheatre != null) {
            // Fetch movies for the selected theatre from the database
            ControlDatabase database = ControlDatabase.getInstance();
            ArrayList<Movie> moviesForTheatre = new ArrayList<>(database.getMoviesForTheatre(selectedTheatre.getTheatreId()));
    
            movieSelector.removeAllItems(); // Clear existing items in the dropdown
            movieMap.clear(); // Clear the movie map to avoid stale data
    
            // Populate the movie selector with movies associated with the selected theatre
            for (Movie movie : moviesForTheatre) {
                movieSelector.addItem(movie.getName()); // Add movie names to dropdown
                movieMap.put(movie.getName(), movie); // Map movie name to the Movie object
            }
    
            movieSelector.setEnabled(true); // Enable movie selection
        } else {
            movieSelector.setEnabled(false); // Disable if no theatre is selected
        }
    }

    // Method to load showtimes based on selected movie and theatre
    private void updateShowtimeSelector() {
        String selectedMovieName = (String) movieSelector.getSelectedItem();
        String selectedTheatreName = (String) theaterSelector.getSelectedItem();
    
        // Check if a valid movie and theatre are selected
        if (selectedMovieName != null && selectedTheatreName != null && movieMap.containsKey(selectedMovieName)) {
            Movie selectedMovie = movieMap.get(selectedMovieName);
            Theatre selectedTheatre = theatreMap.get(selectedTheatreName);
    
            // Fetch the showtimes for this movie and theatre
            loadShowtimesFromDatabase(selectedMovie, selectedTheatre);
        }
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
