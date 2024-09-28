import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DatabaseManager {
    private Connection conn;

    // Database connection setup
    public void connect() {
        try {
            // Use the database URL and credentials you set up earlier
            String url = "jdbc:mysql://localhost:3306/payroll_db";  // MySQL URL
            String username = "root";  // MySQL username
            String password = "Dolphin1005";  // MySQL root password
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add employee to the database
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO Employee (id, name, position, base_salary, hourly_rate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getPosition());
            pstmt.setDouble(4, employee.getBaseSalary());
            pstmt.setDouble(5, employee.getHourlyRate());
            pstmt.executeUpdate();
            System.out.println("Employee added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve employee by ID
    public Employee getEmployee(int id) {
        String sql = "SELECT * FROM Employee WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String position = rs.getString("position");
                double baseSalary = rs.getDouble("base_salary");
                double hourlyRate = rs.getDouble("hourly_rate");
                return new Employee(id, name, position, baseSalary, hourlyRate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
