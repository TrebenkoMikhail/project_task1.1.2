package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao, UserService {
    @Override
    public void createUsersTable() {
        try (Session session = Util.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastname VARCHAR(255) NOT NULL," +
                    "age INT NOT NULL)").executeUpdate();
            transaction.commit();
            System.out.println("Table 'users' created or already exists");
        } catch (Exception e) {
            System.err.println("Error creating users table: " + e.getMessage());
        }
    }
    @Override
    public void dropUsersTable() {
       try (Session session = Util.sessionFactory.openSession()) {
           Transaction transaction = session.beginTransaction();
           session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
           transaction.commit();
           System.out.println("Table 'users' dropped or not exist.");
       } catch (Exception e) {
           System.err.println("Error dropping users table: " + e.getMessage());
       }
   }
   @Override
   public void saveUser(String name, String lastname, int age) {
       Transaction transaction = null;
       try (Session session = Util.sessionFactory.openSession()) {
           transaction = session.beginTransaction();
           User user = new User(name, lastname, age);
           session.save(user);
           transaction.commit();
           System.out.println("User saved to database: " + name + " " + lastname);
       } catch (Exception e) {
           if (transaction != null) {
               transaction.rollback();
           }
       }
   }
   @Override
   public void removeUserById(long id){
       try (Session session = Util.sessionFactory.openSession()) {
           Transaction transaction = session.beginTransaction();
           User user = session.get(User.class, id);
                if (user != null) {
                    session.delete(user);
                    transaction.commit();
                    System.out.println("User with id " + id + "removed from database");
                } else {
                    System.out.println("User with id " + id + "not found in database");
                }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   @Override
   public List<User> getAllUsers() {
       List<User> users = new ArrayList<>();
       try (Session session = Util.sessionFactory.openSession()) {
           CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
           CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
           query.from(User.class);
           users = session.createQuery(query).getResultList();
           return users;
       } catch (Exception e) {
           e.printStackTrace();

       }
       return users;
   }

   @Override
   public void cleanUsersTable() {
       Transaction transaction = null;
       try (Session session = Util.sessionFactory.openSession()) {
           transaction = session.beginTransaction();
           session.createQuery("DELETE FROM User").executeUpdate();
           transaction.commit();
           System.out.println("Table 'users' cleaned successfully.");
       } catch (Exception e) {
           if (transaction != null) {
               transaction.rollback();
           }
       }
   }
}



