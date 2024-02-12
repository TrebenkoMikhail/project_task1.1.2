package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import java.sql.SQLException;
import java.util.List;

import static jm.task.core.jdbc.model.User.users;

public interface UserDao {
    static void createUsersTable() {

    }

    static void dropUsersTable() {

    }

    static void saveUser(String name, String lastName, byte age) throws SQLException {

    }

    static void removeUserById(long id) {

    }

    static List<User> getAllUsers() throws SQLException {
        return users;
    }

    static void cleanUsersTable() {

    }
}
