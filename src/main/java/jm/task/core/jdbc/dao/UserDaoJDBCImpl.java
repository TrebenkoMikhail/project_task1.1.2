package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException{

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() throws SQLException{
        return User.getAllUsers();
    }

    public void cleanUsersTable() {

    }
}
