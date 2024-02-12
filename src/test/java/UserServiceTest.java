import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static jm.task.core.jdbc.model.User.getAllUsers;
import static org.junit.Assert.assertEquals;

public class UserServiceTest {

    @Test
    public void testCreateAndDropUsersTable() {
        UserDaoHibernateImpl.createUsersTable();
        List<User> users = getAllUsers();
        assertEquals(0, users.size());
        UserDaoHibernateImpl.dropUsersTable();
        users = getAllUsers();
        assertEquals(0, users.size());
    }

    @Test
    public void testSaveAndRemoveUser() throws SQLException {
        UserDaoHibernateImpl.createUsersTable();
        UserDaoHibernateImpl.saveUser("John", "Wick", (byte) 34);
        UserDaoHibernateImpl.saveUser("Jame", "Smith", (byte) 36);
        UserDaoHibernateImpl.getAllUsers();
        assertEquals(2, UserDaoHibernateImpl.getAllUsers().size());
        User userToRemove = UserDaoHibernateImpl.getAllUsers().get(0);

        UserDaoHibernateImpl.removeUserById(userToRemove.getId());
        UserDaoHibernateImpl.getAllUsers();
        assertEquals(1, UserDaoHibernateImpl.getAllUsers().size());
        UserDaoHibernateImpl.cleanUsersTable();
        UserDaoHibernateImpl.dropUsersTable();
    }
}
