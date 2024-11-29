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
    
    private void saveRegisteredUsers() throws SQLException {
        String sql = "INSERT INTO registered_user (ID_no, User_Password, First_Name, Last_Name, " +
                     "User_Email, User_Bank_Info, User_Day, User_Month, User_Year) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE User_Password=?, First_Name=?, Last_Name=?, " +
                     "User_Email=?, User_Bank_Info=?, User_Day=?, User_Month=?, User_Year=?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            for (UserRegistered user : controlDatabase.getAllRegisteredUsers().values()) {
                // Insert values
                stmt.setInt(1, user.getUserID());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getName().split(" ")[0]); // First name (assumes full name is space-separated)
                stmt.setString(4, user.getName().split(" ")[1]); // Last name
                stmt.setString(5, user.getEmail());
                stmt.setInt(6, user.getBankInfo().getBankInfoID());
                stmt.setInt(7, user.getJoinDay());
                stmt.setInt(8, user.getJoinMonth());
                stmt.setInt(9, user.getJoinYear());
    
                // Update values for ON DUPLICATE KEY
                stmt.setString(10, user.getPassword());
                stmt.setString(11, user.getName().split(" ")[0]); // First name
                stmt.setString(12, user.getName().split(" ")[1]); // Last name
                stmt.setString(13, user.getEmail());
                stmt.setInt(14, user.getBankInfo().getBankInfoID());
                stmt.setInt(15, user.getJoinDay());
                stmt.setInt(16, user.getJoinMonth());
                stmt.setInt(17, user.getJoinYear());
    
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveSingleUser(UserRegistered user) throws SQLException {
        String sql = "INSERT INTO registered_user (ID_no, User_Password, First_Name, Last_Name, " +
                     "User_Email, User_Bank_Info, User_Day, User_Month, User_Year) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE User_Password=?, First_Name=?, Last_Name=?, " +
                     "User_Email=?, User_Bank_Info=?, User_Day=?, User_Month=?, User_Year=?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            // Insert values
            stmt.setInt(1, user.getUserID());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName().split(" ")[0]); // First name
            stmt.setString(4, user.getName().split(" ")[1]); // Last name
            stmt.setString(5, user.getEmail());
            stmt.setInt(6, user.getBankInfo().getBankInfoID());
            stmt.setInt(7, user.getJoinDay());
            stmt.setInt(8, user.getJoinMonth());
            stmt.setInt(9, user.getJoinYear());
    
            // Update values for ON DUPLICATE KEY
            stmt.setString(10, user.getPassword());
            stmt.setString(11, user.getName().split(" ")[0]); // First name
            stmt.setString(12, user.getName().split(" ")[1]); // Last name
            stmt.setString(13, user.getEmail());
            stmt.setInt(14, user.getBankInfo().getBankInfoID());
            stmt.setInt(15, user.getJoinDay());
            stmt.setInt(16, user.getJoinMonth());
            stmt.setInt(17, user.getJoinYear());
    
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    private void saveTheatres() throws SQLException {}
    private void saveScreeningRooms() throws SQLException {}
    private void saveShows() throws SQLException {}

}
