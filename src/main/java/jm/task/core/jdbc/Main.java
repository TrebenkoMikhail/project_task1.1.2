package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.List;

import static jm.task.core.jdbc.service.UserService.removeUserById;
import static jm.task.core.jdbc.service.UserServiceImpl.*;

public class Main {
    public static void main(String[] args) {
        try {
            UserServiceImpl.createUsersTable(connection);

            saveUser("John", "Braun", 34);
            saveUser("Alice", "Catcher",  25);
            saveUser("Bob", "Macquensly", 28);
            saveUser("Eve", "Apple",  18);

            List<User> users = getAllUsers(connection);
            users.forEach(System.out::println);
            UserServiceImpl.removeUserById(2);
            UserServiceImpl.cleanUsersTable(connection);
            dropUsersTable(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
