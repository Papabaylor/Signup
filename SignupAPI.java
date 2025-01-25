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
    public boolean signupUser(String fullName, String email, String password, String dietaryPreferences, String location, boolean useGPS, boolean emailAlerts, boolean pushNotifications) {
        if (validateInput(fullName, email, password)) {
            return createUserAccount(fullName, email, password, dietaryPreferences, location, useGPS, emailAlerts, pushNotifications);
        } else {
            return false;
        }
    }

    // Method to validate input
    private boolean validateInput(String fullName, String email, String password) {
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return false;
        }
        // Additional validation logic can be added here
        return true;
    }

    // Method to create user account in the database
    private boolean createUserAccount(String fullName, String email, String password, String dietaryPreferences, String location, boolean useGPS, boolean emailAlerts, boolean pushNotifications) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO users (fullName, email, password, dietaryPreferences, location, useGPS, emailAlerts, pushNotifications) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, dietaryPreferences);
            preparedStatement.setString(5, location);
            preparedStatement.setBoolean(6, useGPS);
            preparedStatement.setBoolean(7, emailAlerts);
            preparedStatement.setBoolean(8, pushNotifications);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
