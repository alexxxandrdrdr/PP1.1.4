package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String tableName = "USERS";
    private Connection conn = Util.getConnection();
    private String sqlQuery = "";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        sqlQuery ="CREATE TABLE IF NOT EXISTS " + tableName + " ( ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(64) NOT NULL, LAST_NAME VARCHAR(64) NOT NULL, AGE INT NOT NULL)";
        try(Statement stmt = conn.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        sqlQuery = "DROP TABLE IF EXISTS " + tableName;
        try (Statement stmt = conn.createStatement()){
            stmt.execute(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        sqlQuery = "INSERT INTO "+ tableName +"(NAME, LAST_NAME, AGE) VALUES (?, ?, ?)";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        sqlQuery ="DELETE FROM " + tableName + " WHERE ID = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        sqlQuery ="SELECT * FROM " + tableName;

        List<User> users = new ArrayList<User>();
        try (Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery(sqlQuery)) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                User user = new User(rs.getString("NAME"), rs.getString("LAST_NAME"), (byte) rs.getInt("AGE"));
                user.setId((long) id);
                users.add(user);
                //можно было бы и переделать конструктор
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
    sqlQuery = "DELETE FROM " + tableName;
    try (Statement stmt = conn.createStatement()){
        stmt.execute(sqlQuery);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
}
