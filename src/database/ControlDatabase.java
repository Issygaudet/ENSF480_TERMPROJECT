package database;

import java.util.HashMap;
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

  public UserRegistered getUserRegistered(int id) {
    return this.registeredUsersMap.getOrDefault(id, null);
  }

  public ScreeningRoom getScreeningRoom(int id) {
    return this.screeningRoomsMap.getOrDefault(id, null);
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

  public void addShowtime(Showtime showtime) {
    showtimeMap.put(showtime.getShowtimeId(), showtime);
  }

  public void updateShowtime(Showtime showtime) {
    showtimeMap.put(showtime.getShowtimeId(), showtime);
  }

  public void removeShowtime(Showtime showtime){
    showtimeMap.remove(showtime.getShowtimeId());
  }
}