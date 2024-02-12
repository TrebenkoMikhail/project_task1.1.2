package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserDaoHibernateImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserDaoHibernateImpl.createUsersTable();
        UserDaoHibernateImpl.saveUser("John", "Wick", (byte) 34);
        UserDaoHibernateImpl.saveUser("Neo", "Matrix", (byte) 33);
        UserDaoHibernateImpl.saveUser("Tony", "Jha", (byte) 24);
        UserDaoHibernateImpl.saveUser("Jacky", "Chan", (byte) 48);

        System.out.println("The List of All users: ");

        List<User> users = UserDaoHibernateImpl.getAllUsers();
        assert users != null;
        users.forEach(System.out::println);
        UserDaoHibernateImpl.removeUserById(3);
        UserDaoHibernateImpl.cleanUsersTable();
        UserDaoHibernateImpl.dropUsersTable();

    }
}
