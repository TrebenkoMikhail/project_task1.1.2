package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl implements UserService {


    @Override
    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," + "lastname VARCHAR(255) NOT NULL," + "age INT NOT NULL)";

            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        System.out.println("Table 'users' created successfully.");
    }

    @Override
    public void saveUser(String name, String lastname, int age) {
        int rowsAffected;
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("INSERT INTO users (name, lastname, age) VALUES(?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setInt(3, age);
            rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User saved to database: " + name + " " + lastname);
            }
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Table 'users' dropped successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Users with id " + id + " removed from database");
            } else {
                System.out.println("User with id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM users");
            System.out.println("Table 'users' cleaned successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("name"));
                user.setLastname(resultSet.getString("lastname"));
                user.setAge((byte) resultSet.getInt("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Getting all users from database");
        return users;
    }
}
