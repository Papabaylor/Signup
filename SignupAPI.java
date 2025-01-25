// SignupAPI.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupAPI {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/filtertofork";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        // Placeholder for main method
    }

    // Method for user signup
    public boolean signupUser(String username, String password, String email, String dietaryPreferences, String location, boolean useGPS, boolean emailAlerts, boolean pushNotifications) {
    
        if (validateInput(username, password, email)) {
            return createUserAccount(username, password, email, dietaryPreferences, location, useGPS, emailAlerts, pushNotifications);
        } else {
            return false;
if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
    eturn false;
}
    // Method to validate input
    private boolean validateInput(String username, String password, String email) {
        // Placeholder for input validation logic
        return true;
    }

    // Method to create user account in the database
    private boolean createUserAccount(String username, String password, String email, String dietaryPreferences, String location, boolean useGPS, boolean emailAlerts, boolean pushNotifications) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
String sql = "INSERT INTO users (username, password, email, dietary_preferences, location, use_gps, email_alerts, push_notifications) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";String sql = "INSERT INTO users (username, password, email, dietary_preferences, location, use_gps, email_alerts, push_notifications) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, dietaryPreferences);
            stmt.setString(5, location);
            stmt.setBoolean(6, useGPS);
            stmt.setBoolean(7, emailAlerts);
            stmt.setBoolean(8, pushNotifications);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
