package Entity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin {
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:DB480.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addMovie(String movieName, String genre, int duration) {
        String sql = "INSERT INTO movies(name, genre, duration) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movieName);
            pstmt.setString(2, genre);
            pstmt.setInt(3, duration);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyMovie(int movieId, String movieName, String genre, int duration) {
        String sql = "UPDATE movies SET name = ?, genre = ?, duration = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movieName);
            pstmt.setString(2, genre);
            pstmt.setInt(3, duration);
            pstmt.setInt(4, movieId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeMovie(int movieId) {
        String sql = "DELETE FROM movies WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, movieId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addShowtime() {
        // Implementation here
    }

    public void modifyShowtime() {
        // Implementation here
    }

    public void removeShowtime() {
        // Implementation here
    }
}