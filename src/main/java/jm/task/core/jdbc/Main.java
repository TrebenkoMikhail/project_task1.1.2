package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceHibernateImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceHibernateImpl userService = new UserServiceHibernateImpl();

        userService.createUsersTable();
        userService.saveUser("John", "Wick", (byte) 34);
        userService.saveUser("Neo", "Matrix", (byte) 33);
        userService.saveUser("Tony", "Jha", (byte) 24);
        userService.saveUser("Jacky", "Chan", (byte) 48);

        System.out.println("The List of All users: ");

        List<User> users = userService.getAllUsers();
        assert users != null;
        for (User user : users) {
            System.out.println(user.toString());
        }
        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
