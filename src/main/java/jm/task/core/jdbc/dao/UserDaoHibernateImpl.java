package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                "(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(100) NOT NULL," +
                "lastName VARCHAR(100) NOT NULL," +
                "age TINYINT NOT NULL)").addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
        Util.closeFactory();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users ").addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
        Util.closeFactory();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = Util.getSessionFactory().getCurrentSession();
        User user1 = new User(name, lastName, age);
        session.beginTransaction();
        session.save(user1);
        session.getTransaction().commit();
        Util.closeFactory();
        System.out.println("User с именем " + name + " добавлен в базу данных");

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        Util.closeFactory();
    }

    @Override
    public List<User> getAllUsers() {

        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<User> list = session.createQuery("FROM User").getResultList();
        session.getTransaction().commit();
        Util.closeFactory();
        return list;

    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE User").executeUpdate();
        session.getTransaction().commit();
        Util.closeFactory();
    }

}
