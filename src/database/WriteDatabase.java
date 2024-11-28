package database;

import entity.*;
import entity.Date;

import java.sql.*;

public class WriteDatabase {
    private ControlDatabase controlDatabase;
    private Connection conn;

    private Connection getConnection() {
        if (conn != null) {
            return conn;
        }
        controlDatabase = ControlDatabase.getInstance();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_theatre_app?" +
                    "user=ensf480&password=ensf480");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            conn = null;
        }
        return conn;
    }

    private void saveAnnouncements() throws SQLException {}
    private void saveBankInfo() throws SQLException {}
    private void saveRegisteredUsers() throws SQLException {}
    private void saveTheatres() throws SQLException {}
    private void saveScreeningRooms() throws SQLException {}
    private void saveShows() throws SQLException {}

}
