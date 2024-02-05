package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

import static jm.task.core.jdbc.model.User.users;

public interface UserService {
    static void createUsersTable() {

    }

    void dropUsersTable() throws SQLException;

    static void saveUser(String name, String lastName, String age) throws SQLException {
    }

    static void removeUserById(long id) {

    }

    static List<User> getAllUsers() throws SQLException {
        return users;
    }

    void cleanUsersTable() throws SQLException;
}
