import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static jm.task.core.jdbc.service.UserService.*;
import static jm.task.core.jdbc.service.UserServiceImpl.*;
import static org.junit.Assert.fail;

public class UserServiceTest {
    long testId = 1L;
    String testName = "Nick";
    String testLastName = "Martin";
    byte testAge = 15;


    @Test
    public void testDropUsersTable() {
        try {
            dropUsersTable(UserServiceImpl.connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateUsersTable() {
        try {
            UserServiceImpl.createUsersTable(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveUser() throws Exception {
        try {
            UserServiceImpl.saveUser(testName, testLastName, testAge);
            if (!testName.equals(User.getUserName())
                    && !testLastName.equals(User.getLastName(testLastName))
                    && testAge != User.getAge()
            ) {
                throw new Exception("User was incorrectly removed to the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveUserById() {
        try {
            UserServiceImpl.removeUserById(testId);
            if (testName.equals(User.getUserName())
                    && testLastName.equals(User.getLastName(testLastName))
                    && testAge == User.getAge()
            ) {
                throw new Exception("User was incorrectly removed to the database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllUsers() {
        try {
                List<User> users = UserServiceImpl.getAllUsers(connection);
            if (users.isEmpty()) {
                throw new Exception("Check if the save/delete or create table method works correctly");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCleanUsersTable() {
        try {
            cleanUsersTable(UserServiceImpl.connection);
        } catch (Exception e) {
            fail("The method of clearing the user table is implemented incorrectly or An exception occurred while testing clearing the users table\n" + e);
        }
    }
}
