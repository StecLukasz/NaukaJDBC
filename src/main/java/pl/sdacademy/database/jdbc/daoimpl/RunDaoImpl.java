package pl.sdacademy.database.jdbc.daoimpl;

import pl.sdacademy.database.jdbc.dao.RunDao;
import pl.sdacademy.database.jdbc.entity.Run;
import pl.sdacademy.database.jdbc.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RunDaoImpl implements RunDao {


    @Override
    public void save(Run run)throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("INSERT INTO runs(id, name, members_limit) VALUES(?,?,?)");
        statement.setInt(1,run.getId());
        statement.setString(2,run.getName());
        statement.setInt(3,run.getMembersLimit());

        statement.executeUpdate();
        statement.close();

    }

    @Override
    public List<Run> findAll()throws SQLException {
        Statement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM runs");
        List<Run> runList = new ArrayList<>();
        while (resultSet.next()) {
            runList.add(new Run(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("members_limit")));
        }
        statement.close();
        return runList;
    }

    @Override
    public Run findById(int id)throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("SELECT *FROM runs WHERE id=?");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();    // wyciagamy dane tym Query

        Run run = null;
        if (resultSet.next()){
            run = new Run();
            run.setId(resultSet.getInt("id"));
            run.setName(resultSet.getString("name"));
            run.setMembersLimit(resultSet.getInt("members_limit"));
        }




        return run;
    }

    @Override
    public void update(Run run)throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("UPDATE runs SET name=?, members_limit=?, id=?");
        statement.setString(1, run.getName());
        statement.setInt(2, run.getMembersLimit());
        statement.setInt(3,run.getId());

        statement.executeUpdate();
        statement.close();

    }

    @Override
    public void deleteById(int id) throws SQLException{
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("DELETE FROM runs WHERE id=?");

        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();


    }
}

