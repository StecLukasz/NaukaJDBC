package pl.sdacademy.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {

        String connectionString = "jdbc:mysql://localhost:3306/runcenter";
        Properties prop = new Properties();
        prop.put("password", "plyta1");
        prop.put("user", "root");
        prop.put("serverTimezone", "Europe/Warsaw");

        Connection connection = DriverManager.getConnection(connectionString, prop);


    }
}
