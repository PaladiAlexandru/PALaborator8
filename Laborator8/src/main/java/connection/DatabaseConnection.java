package connection;

import java.sql.*;

/**
 * Singleton class
 */
public class DatabaseConnection {

    private static Connection con = null;

    static {

    }

    private DatabaseConnection() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "password");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if(con == null){
            new DatabaseConnection();
        }
        return con;
    }
}
