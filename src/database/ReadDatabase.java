package database;

import entity.*;
import entity.Date;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReadDatabase {
    private ControlDatabase controlDatabase;
    private Connection conn;

    private Connection getConnection() {
        if (conn != null) {
            return conn;
        }
        controlDatabase = ControlDatabase.getInstance();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_theatre_app?" +
            "user=registered_user&password=registered_pass");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            conn = null;
        }
        return conn;
    }

    public void populateDatabase() {
        try {
            getMovies();
            getAnnouncements();
            getBankInfo();
            getRegisteredUsers();
            getTheatres();
            getScreeningRooms();
            getShows();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            conn = null;
        }
    }

    private void getMovies() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.movie;");
        while (resultSet.next()) {
            int movieId = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String genre = resultSet.getString(3);
            int year = resultSet.getInt(4);
            String director = resultSet.getString(5);
            float duration = resultSet.getFloat(6);
            float rating = resultSet.getFloat(7);
            String code = resultSet.getString(8);
            float price = resultSet.getFloat(9);
            String description = resultSet.getString(10);
            Movie movie = new Movie(movieId, title, genre, year, director, duration, rating, code, price, description);
            ControlDatabase.getInstance().addMovie(movie);
        }
    }
    private void getAnnouncements() throws SQLException {
        this.controlDatabase = ControlDatabase.getInstance();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.announcements;");
        while (resultSet.next()) {
            int announcementID = resultSet.getInt(1);
            int privateDay = resultSet.getInt(2);
            int privateMonth = resultSet.getInt(3);
            int privateYear = resultSet.getInt(4);
            int publicDay = resultSet.getInt(5);
            int publicMonth = resultSet.getInt(6);
            int publicYear = resultSet.getInt(7);
            int movieID = resultSet.getInt(8);
            //TODO add announcement class?
        }
    }

    private void getBankInfo() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.bank_info;");
        while (resultSet.next()) {
            // TODO agree on data type
            int bankInfoID = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String cardNumber = resultSet.getString(4);
            UserBankInfo info = new UserBankInfo(bankInfoID, cardNumber,
                    firstName + " " + lastName, new Date(0, 6, 2028), 123);
            ControlDatabase.getInstance().addBankInfo(info);
        }
    }
    private void getRegisteredUsers() throws SQLException {
        this.controlDatabase = ControlDatabase.getInstance();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.registered_user;");
        while (resultSet.next()) {
            int userID = resultSet.getInt(1);
            //String username = resultSet.getString(2);
            String password = resultSet.getString(2);
            String firstName = resultSet.getString(3);
            String lastName = resultSet.getString(4);
            String email = resultSet.getString(5);
            int bankID = resultSet.getInt(6);
            int day = resultSet.getInt(7);
            int month = resultSet.getInt(8);
            int year = resultSet.getInt(9);
            String name = firstName + " " + lastName;
            Date registrationDate = new Date(day, month, year);
            UserRegistered registered = new UserRegistered(userID, name, email, password, controlDatabase.getBankInfo(bankID), registrationDate);
            controlDatabase.addRegisteredUser(registered);
        }
    }
    private void getTheatres() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.theatre;");
        while (resultSet.next()) {
            int theatreId = resultSet.getInt(1);
            String location = resultSet.getString(2);
            Theatre theatre = new Theatre(theatreId, location, new ArrayList<Movie>());  
            controlDatabase.addTheatre(theatre);
        }
    }
    private void getScreeningRooms() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.screening_room;");
        while (resultSet.next()) {
            int roomID = resultSet.getInt(1);
            int rows = resultSet.getInt(2);
            int columns = resultSet.getInt(3);
            int theatreID = resultSet.getInt(4);
            ScreeningRoom room = new ScreeningRoom(roomID, rows, columns);
            room.setTheatre(controlDatabase.getTheatre(theatreID));  // Associate room with the correct theater
            controlDatabase.addScreeningRoom(room);
        }
    }private void getShows() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.shows;");
        while (resultSet.next()) {
            int showID = resultSet.getInt(1);        // Showtime ID
            int movieID = resultSet.getInt(2);       // Movie ID
            Movie movie = controlDatabase.getMovie(movieID);  // Retrieve Movie object by movieID
            int roomID = resultSet.getInt(3);        // Screening room ID
            int hour = resultSet.getInt(4);          // Hour part of the time
            int mins = resultSet.getInt(5);          // Minute part of the time
            
            // Create a Time object from the hour and minutes
            Time showtime = Time.valueOf(String.format("%02d:%02d:00", hour, mins));  // Format as HH:mm:ss
            
            // Assuming you need a Date object for the 'date' field. This could be modified if you want to use the actual show date.
            Date date = new Date();  // Current date for now, or could be updated as needed
            
            // Create the Showtime object using the Time and Date
            Showtime show = new Showtime(showID, movieID, movie, controlDatabase.getScreeningRoom(roomID).getTheatre(), showtime, date);
            
            // Add the Showtime to the database instance
            ControlDatabase.getInstance().addShowtime(show);
        }
    }
    


    public static void main(String[] args) {
        ReadDatabase readDatabase = new ReadDatabase();
        readDatabase.populateDatabase();
    }

}
