package jm.task.core.jdbc.dao;

import com.mysql.cj.protocol.Resultset;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(100) NOT NULL," +
                    "lastName VARCHAR(100) NOT NULL," +
                    "age TINYINT NOT NULL)");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT into users (name, lastName, age) VALUES (?,?,?)")) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM users WHERE id = ?")) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery("SELECT * FROM users");
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong(1));
                user.setName(res.getString(2));
                user.setLastName(res.getString(3));
                user.setAge(res.getByte(4));
                list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(list);
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
