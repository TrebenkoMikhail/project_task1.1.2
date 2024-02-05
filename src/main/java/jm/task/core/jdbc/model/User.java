package jm.task.core.jdbc.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table
public class User {
    @Id
    private static Long id;

    @Column
    private static String name;

    @Column
    private static String lastName;

    @Column
    private static Byte age;

    public static List<User> users = new ArrayList<>();
    public User() {

    }

    public User(String name, String lastName, Byte age) {
        User.name = name;
        User.lastName = lastName;
        User.age = age;
    }

    public static Long getId(long id) {
        return User.id;
    }

    public void setId(Long id) {
        User.id = id;
    }

    public static String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        User.name = name;
    }

    public static String getLastName(String lastName) {
        return lastName;
    }

    public void setLastName(String lastName) {
        User.lastName = lastName;
    }

    public static Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        User.age = age;
    }

    public static List<User> getAllUsers() {
        return users;
    }
}
