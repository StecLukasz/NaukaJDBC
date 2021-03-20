package pl.sdacademy.database.jdbc.dao;

import pl.sdacademy.database.jdbc.entity.Run;

import java.sql.SQLException;
import java.util.List;

public interface RunDao {
    void save(Run run) throws SQLException;          //C
    List<Run> findAll()throws SQLException;          //R
    Run findById(int id)throws SQLException;         //R
    void update(Run run)throws SQLException;         //U
    void deleteById(int id)throws SQLException;      //D
}
