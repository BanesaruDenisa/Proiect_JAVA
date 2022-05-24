package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public DBConnection() {
    }

    public DBConnection(String url, String root, String pass) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, root, pass);
    }

    public static Connection getInstance() throws SQLException {

        if(connection == null){
            String url = "jdbc:mysql://localhost:3306/store";
            String username = "root";
            String password = "bananaverde.ro20";
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        if(connection.isClosed()){

        }
        return connection.prepareStatement(query);
    }

    public void close() throws SQLException{
        connection.close();
    }
}
