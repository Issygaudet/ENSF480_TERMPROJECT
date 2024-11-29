package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.*;

public class ControlDatabase {
  private static ControlDatabase instance;
  private Map<Integer, UserBankInfo> bankInfoMap;
  private Map<Integer, Movie> moviesMap;
  private Map<Integer, UserRegistered> registeredUsersMap;
  private Map<Integer, ScreeningRoom> screeningRoomsMap;
  private Map<Integer, Receipt> receiptMap;
  private Map<Integer, Showtime> showtimeMap;
  private Map<Integer, Theatre> theatreMap;

  // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/MOVIE_THEATRE_APP";  // Update with your database URL
    private static final String USER = "registered_user";  // Your database username
    private static final String PASSWORD = "registered_pass";  // Your database password
    
    // Method to get the connection
    public static Connection getConnection() throws SQLException {
        try {
            // Load the database driver (this may vary depending on your database)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Failed to create database connection.", e);
        }
    }
  

  public static ControlDatabase getInstance() {
    if (instance == null) {
      instance = new ControlDatabase();
    }
    return instance;
  }

  public ControlDatabase() {
    this.bankInfoMap = new HashMap<>();
    this.moviesMap = new HashMap<>();
    this.registeredUsersMap = new HashMap<>();
    this.screeningRoomsMap = new HashMap<>();
    this.receiptMap = new HashMap<>();
    this.showtimeMap = new HashMap<>();
    this.theatreMap = new HashMap<>();
  }

  public UserBankInfo getBankInfo(int id) {
    return this.bankInfoMap.getOrDefault(id, null);
  }

  public Movie getMovie(int id) {
    return this.moviesMap.getOrDefault(id, null);
  }
  public List<Movie> getAllMovies() {
    return new ArrayList<>(this.moviesMap.values());
  }
  

  public UserRegistered getUserRegistered(int id) {
    return this.registeredUsersMap.getOrDefault(id, null);
  }

  public ScreeningRoom getScreeningRoom(int id) {
    return this.screeningRoomsMap.getOrDefault(id, null);
  }

  public Map<Integer, UserRegistered> getAllRegisteredUsers() {
    return this.registeredUsersMap;
  }

  public Receipt getReceipt(int id) {
    return this.receiptMap.getOrDefault(id, null);
  }

  public Theatre getTheatre(int id) {return this.theatreMap.getOrDefault(id, null);}
  public Set<Integer> getMovieIDs() {
    return this.moviesMap.keySet();
  }

  public Set<Integer> getRegisteredUserIDs() {
    return this.registeredUsersMap.keySet();
  }


  public void addBankInfo(UserBankInfo bankInfo) {
    this.bankInfoMap.put(bankInfo.getBankInfoID(), bankInfo);
  }

  public void addMovie(Movie movie) {
    this.moviesMap.put(movie.getMovieId(), movie);
  }

  public void addRegisteredUser(UserRegistered user) {
    this.registeredUsersMap.put(user.getUserID(), user);
  }

  public void addTheatre(Theatre theatre) {
    this.theatreMap.put(theatre.getTheatreId(), theatre);
  }

  public void addScreeningRoom(ScreeningRoom room) {
    this.screeningRoomsMap.put(room.getRoomId(), room);
  }


  public void updateMovie(int movieId, String movieName, String genre, float duration) {
    Movie movie = getMovie(movieId);
    movie.setName(movieName);
    movie.setGenre(genre);
    movie.setDuration(duration);
  }

  public void removeMovie(int movieId) {
    moviesMap.remove(movieId);
  }
  public void fetchAllMovies() {
    String query = "SELECT * FROM MOVIE"; // SQL query to fetch all movies
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            int movieId = rs.getInt("ID_no");
            String name = rs.getString("Movie_Name");
            String genre = rs.getString("Genre");
            int year = rs.getInt("Release_Year");
            String director = rs.getString("Director");
            float duration = rs.getFloat("Duration");
            float rating = rs.getFloat("Rating");
            String code = rs.getString("Movie_Code");
            float price = rs.getFloat("Movie_Price");
            String description = rs.getString("Movie_Description");

            Movie movie = new Movie(movieId, name, genre, year, director, duration, rating, code, price, description);
            this.moviesMap.put(movieId, movie); // Add movie to the map
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

  public void addShowtime(Showtime showtime) {
    showtimeMap.put(showtime.getShowtimeId(), showtime);
  }

  public void updateShowtime(Showtime showtime) {
    showtimeMap.put(showtime.getShowtimeId(), showtime);
  }

  public void removeShowtime(Showtime showtime){
    showtimeMap.remove(showtime.getShowtimeId());
  }
  
  public static Date convertSqlDateToEntityDate(java.sql.Date sqlDate) {
    if (sqlDate == null) {
        return null;
    }
    // Extract year, month, and day from java.sql.Date
    int year = sqlDate.getYear() + 1900; // getYear() returns years since 1900
    int month = sqlDate.getMonth() + 1;  // getMonth() returns months from 0-11
    int day = sqlDate.getDate();
    
    // Return a new custom Date object
    return new Date(day, month, year);
}

  public UserBankInfo getUserBankInfoById(int bankInfoId) {
    UserBankInfo bankInfo = null;
    String query = "SELECT * FROM USER_BANK_INFO WHERE BankInfoID = ?";  // Adjust the table name if necessary

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, bankInfoId);  // Set the bankInfoID parameter

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Retrieve data from result set
            int cardNumber = rs.getInt("CardNumber");
            String cardHolder = rs.getString("CardHolder");
            Date expiryDate = convertSqlDateToEntityDate(rs.getDate("ExpiryDate"));  // Assuming you have a Date object in the database
            int cvv = rs.getInt("CVV");

            // Create a new UserBankInfo object using the retrieved data
            bankInfo = new UserBankInfo(bankInfoId, cardNumber, cardHolder, expiryDate, cvv);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle SQL errors
    }
    return bankInfo;
}

  public UserRegistered getUserRegisteredByEmail(String email) {
    UserRegistered user = null;
    String query = "SELECT * FROM REGISTERED_USER WHERE User_Email = ?";  // SQL query

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, email);  // Set the email parameter

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Retrieve data from result set
            int userId = rs.getInt("ID_no");
            String firstName = rs.getString("First_Name");
            String lastName = rs.getString("Last_Name");
            String userEmail = rs.getString("User_Email");
            String password = rs.getString("User_Password");

            // Retrieve bank info ID and create a dummy UserBankInfo object (assuming bank info is stored separately)
            int bankInfoId = rs.getInt("User_Bank_Info");
            UserBankInfo bankInfo = getUserBankInfoById(bankInfoId); // Assuming method to fetch bank info from the database

            // Create a Date object from the User's join date info
            Date dateJoined = new Date(rs.getInt("User_Day"), rs.getInt("User_Month"), rs.getInt("User_Year"));

            // Now create the UserRegistered object with the new constructor
            user = new UserRegistered(userId, firstName + " " + lastName, userEmail, password, bankInfo, dateJoined);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle SQL errors
    }
    return user;
}

}