package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

import static jm.task.core.jdbc.model.User.users;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

    }

    public void dropUsersTable() throws SQLException{

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException{

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() throws SQLException{
        return users;
    }

    public void cleanUsersTable() {

    }
}
