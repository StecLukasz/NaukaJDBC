package pl.sdacademy.main;

import pl.sdacademy.database.jdbc.utils.JdbcUtils;

import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {

//        insertTest();
//        deleteById(1);
//        updateRun(2, "Bieg Johnego", 2000);
        printAllData();


    }

    private static void insertTest() throws SQLException {
        Statement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .createStatement();

        statement.executeUpdate("INSERT INTO runs(id, name, members_limit) VALUES (2, 'Nowy bieg 2', 100)");
        statement.executeUpdate("INSERT INTO runs(id, name, members_limit) VALUES (3, 'Nowy bieg 3', 100)");


        statement.close();
    }

    private static void deleteById(int id) throws SQLException {
        Statement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .createStatement();

        statement.executeUpdate("DELETE FROM RUNS WHERE ID =" + id);
        statement.close();
    }

    private static void updateRun(int id, String name, int membersLimit) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection(); // najpierw musisz polaczyc później przygotować
        PreparedStatement statement = connection
                .prepareStatement("UPDATE runs SET name=?, members_limit=? WHERE id=?");

        statement.setString(1, name);
        statement.setInt(2, membersLimit);
        statement.setInt(3, id);

        statement.executeUpdate();
        statement.close();
    }


    private static void printAllData() throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM runs");

        while (resultSet.next()) {
            System.out.printf("id=%d, name=%s, members_limit=%d \n",
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("members_limit"));
        }
        statement.close();
    }


}

