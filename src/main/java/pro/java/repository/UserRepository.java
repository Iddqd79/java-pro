package pro.java.repository;

import com.zaxxer.hikari.pool.HikariPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.java.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserRepository {
    private final HikariPool hikariPool;

    @Autowired
    public UserRepository(HikariPool hikariPool) {
        this.hikariPool = hikariPool;

    }

    public User create(Long id, String userName) {
        User user = new User(id, userName);
        try (Connection connection = hikariPool.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(PERSON_DB_STATEMENTS.CREATE_USER_STATEMENT.getSql());
            prepareStatement.setLong(1, id);
            prepareStatement.setString(2, userName);
            int result = prepareStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User update(Long id, String userName) {
        User user = new User(id, userName);
        try (
                Connection connection = hikariPool.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(PERSON_DB_STATEMENTS.UPDATE_USER_STATEMENT.getSql());
            prepareStatement.setString(1, userName);
            prepareStatement.setLong(2, id);
            int result = prepareStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        try (Connection connection = hikariPool.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(PERSON_DB_STATEMENTS.DELETE_USER_STATEMENT.getSql());
            prepareStatement.setLong(1, id);
            int result = prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(Long id) {
        try (Connection connection = hikariPool.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(PERSON_DB_STATEMENTS.GET_USER_STATEMENT.getSql());
            prepareStatement.setLong(1, id);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getLong("id"), resultSet.getString("name"));
                }
                throw new RuntimeException("Absent user with id = " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<User> getAllUsers() {
        try (Connection connection = hikariPool.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(PERSON_DB_STATEMENTS.GET_ALL_USER_STATEMENT.getSql());
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                final Collection<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(new User(resultSet.getLong("id"), resultSet.getString("name")));
                }
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private enum PERSON_DB_STATEMENTS {
        CREATE_USER_STATEMENT("INSERT INTO users (id, name) VALUES (?, ?);"),
        DELETE_USER_STATEMENT("DELETE FROM users WHERE id = ?;"),
        UPDATE_USER_STATEMENT("UPDATE users SET name = ? WHERE id = ?;"),
        GET_USER_STATEMENT("SELECT * FROM users WHERE id = ?;"),
        GET_ALL_USER_STATEMENT("SELECT * FROM users;");

        private final String sql;

        PERSON_DB_STATEMENTS(String sql) {
            this.sql = sql;
        }

        public String getSql() {
            return sql;
        }
    }
}
