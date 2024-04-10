package main.webapp.JDBC.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConn {
    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdvJava", "root", "Kartikey2011");

        return conn;
    }
}
