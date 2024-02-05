package jm.task.core.jdbc.service;
import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.List;
import static jm.task.core.jdbc.model.User.users;

public abstract class UserServiceImpl implements UserService {

    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/example_schema", "root", "2522678tkdtmv");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected UserServiceImpl(Connection connection) {
        UserServiceImpl.connection = connection;
    }

    public static void createUsersTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," + "lastname VARCHAR(255) NOT NULL," + "age INT NOT NULL)";
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Table 'users' created successfully.");
    }

    public static void dropUsersTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Table 'users' dropped successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveUser(String name, String lastName, int age) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES(?,?,?)");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setInt(3, age);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("User saved to database: " + name + " " + lastName);
        }
    }

    public static void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
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

    public static List<User> getAllUsers(Connection connection) throws SQLException{
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("name"));
                user.setAge((byte) resultSet.getInt("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Getting all users from database");
        users.forEach(System.out::println);
        return users;
    }

    public static void cleanUsersTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
            System.out.println("Table 'users' cleaned successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
