package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

import static jm.task.core.jdbc.model.User.users;


public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {

    }
    @Override
    public void dropUsersTable() {

    }
    @Override
    public void saveUser(String name, String lastname, int age) {

    }
    @Override
    public void removeUserById(long id) {

    }
    @Override
    public List<User> getAllUsers() {
        return users;
    }

    public void cleanUsersTable() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
