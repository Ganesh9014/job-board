package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jobboard", "root", "Ganesh54@"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
