package pl.sdacademy.database.jdbc.daoimpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.database.jdbc.entity.Run;
import pl.sdacademy.database.jdbc.utils.JdbcUtils;

import javax.swing.plaf.nimbus.State;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunDaoImplTest {
    private RunDaoImpl runDao = new RunDaoImpl();

    @BeforeEach
    void clearTable()throws SQLException{
        Statement statement = JdbcUtils.getInstance().getConnection().createStatement();
        statement.executeUpdate("DELETE FROM runs");
        statement.close();

    }

    @Test
    void save() {
        Run run = new Run(1, "Testowy Bieg", 99);
        try {
            runDao.save(run);
            Run saved = runDao.findById(run.getId());

            assertNotNull(saved);
            assertEquals(run.getId(), saved.getId());
            assertEquals(run.getName(), saved.getName());
            assertEquals(run.getMembersLimit(), saved.getMembersLimit());
        }catch (SQLException e){
            fail(e);
        }
    }

    @Test
    void findAll() {
        try {
            Run run1 = new Run(100, "Bieg numer 100", 99);
            Run run2 = new Run(105, "inny bieg", 20);

            runDao.save(run1);
            runDao.save(run2);

            List<Run> runList = runDao.findAll();
            assertNotNull(runList);
            assertEquals(2, runList.size());

            Run testRun1 = null;
            if (runList.get(0).getId() == run1.getId()) {
                testRun1 = runList.get(0);
            }else if (runList.get(1).getId() == run1.getId()){
                testRun1= runList.get(1);
            }

            assertNotNull(testRun1);
            assertEquals(run1.getId(), testRun1.getId());
            assertEquals(run1.getName(), testRun1.getName());
            assertEquals(run1.getMembersLimit(), testRun1.getMembersLimit());

        }catch (SQLException e){
            fail(e);
        }
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
        Run run = new Run(1, "Bieg testowy przed zmiana",50);
        try {
            runDao.save(run);
            run.setMembersLimit(20);
            run.setName("Inna nazwa");

            runDao.update(run);

            Run updated = runDao.findById(run.getId());

            assertNotNull(updated);
            assertEquals(run.getMembersLimit(), updated.getMembersLimit());
            assertEquals(run.getName(),updated.getName());

        }catch (SQLException e){
            fail(e);
        }
    }

    @Test
    void deleteById() {
        Run run = new Run(1, "Bieg do usunicia", 100);
        try{
            runDao.save(run);
            runDao.deleteById(run.getId());
            Run deleted = runDao.findById(run.getId());


            assertNull(deleted);


        }catch (SQLException e){
            fail(e);
        }
    }
}