import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuditManager {
    private Connection conn;

    public AuditManager(Connection conn) {
        this.conn = conn;
    }

    // Method to log changes to the payroll system
    public void logAudit(String action, int employeeId) {
        String sql = "INSERT INTO AuditLog (employee_id, action, timestamp) VALUES (?, ?, NOW())";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            pstmt.setString(2, action);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
