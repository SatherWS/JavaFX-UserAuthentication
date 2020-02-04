package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
    Connection connection = null;
    public static Connection connectdb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lifeline_db", "root", "mysql");
            return con;
        }
        catch (Exception x) {
            x.printStackTrace();
            return null;
        }
    }
}