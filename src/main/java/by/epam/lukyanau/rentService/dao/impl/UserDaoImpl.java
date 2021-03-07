package by.epam.lukyanau.rentService.dao.impl;

import by.epam.lukyanau.rentService.connection.ConnectionPool;
import by.epam.lukyanau.rentService.creator.UserCreator;
import by.epam.lukyanau.rentService.exception.DAOException;
import by.epam.lukyanau.rentService.dao.SqlQuery;
import by.epam.lukyanau.rentService.dao.UserDao;
import by.epam.lukyanau.rentService.entity.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final UserDaoImpl instance = new UserDaoImpl();

    private final UserCreator userCreator = UserCreator.getInstance();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public User findByLogin(String login) throws DAOException {
        User user = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                String userSurname = resultSet.getString("surname");
                String userLogin = resultSet.getString("login");
                String userEmail = resultSet.getString("email");
                String userPhoneNumber = resultSet.getString("phone");
                int roleId = resultSet.getInt("role");
                user = userCreator.createUser(userId, userName, userSurname, userLogin, userEmail, userPhoneNumber, roleId);
            }

        } catch (SQLException exp) {
            throw new DAOException(exp);
        }
        return user;
    }

    @Override
    public String findPasswordByLogin(String login) throws DAOException {
        String userPassword = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_PASSWORD_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userPassword = resultSet.getString("password");
            }

        } catch (SQLException exp) {
            throw new DAOException(exp);
        }
        return userPassword;
    }

    public void updatePasswordByLogin(String login, String password) throws DAOException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PASSWORD_BY_LOGIN)) {
            statement.setString(1, password);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException exp) {
            throw new DAOException(exp);
        }
    }


    @Override
    public User add(User user) throws DAOException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhoneNumber());
            statement.setInt(6, user.getRole().getRoleId());
            statement.executeUpdate();
        } catch (SQLException exp) {
            throw new DAOException(exp);
        }
        return user;
    }

    @Override
    public User remove(int id) throws DAOException {
        return null;
    }

    @Override
    public List<User> findAll() throws DAOException {
        return null;
    }

    public void checkAccount(User user) throws DAOException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHECK_ACCOUNT)) {
            statement.setInt(1, user.getUserId());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                createAccount(user);
            }
        } catch (SQLException exp) {
            throw new DAOException(exp);
        }
    }

    private void createAccount(User user) throws DAOException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CREATE_ACCOUNT)) {
            statement.setBigDecimal(1, new BigDecimal(0));
            statement.setInt(2, 1);
            statement.setInt(3, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException exp) {
            throw new DAOException(exp);
        }
    }

}
