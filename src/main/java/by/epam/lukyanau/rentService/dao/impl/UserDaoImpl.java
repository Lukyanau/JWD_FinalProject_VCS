package by.epam.lukyanau.rentService.dao.impl;

import by.epam.lukyanau.rentService.connection.ConnectionPool;
import by.epam.lukyanau.rentService.service.creator.UserCreator;
import by.epam.lukyanau.rentService.dao.DaoException;
import by.epam.lukyanau.rentService.dao.SqlQuery;
import by.epam.lukyanau.rentService.dao.UserDao;
import by.epam.lukyanau.rentService.entity.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final UserDaoImpl instance = new UserDaoImpl();

    private final UserCreator userCreator = UserCreator.getInstance();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        Optional<User> user = Optional.empty();
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
                BigDecimal accountBalance = findAccountBalanceByUserId(userId);
                boolean userStatus = checkStatus(resultSet.getInt("id"));
                user = Optional.of(userCreator.createUser(accountBalance, userId, userName, userSurname, userLogin, userEmail, userPhoneNumber, roleId, userStatus));
            }

        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return user;
    }

    @Override
    public String findPasswordByLogin(String login) throws DaoException {
        String userPassword = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_PASSWORD_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userPassword = resultSet.getString("password");
            }

        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return userPassword;
    }

    @Override
    public User add(User user) throws DaoException {
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
            throw new DaoException(exp);
        }
        return user;
    }

    @Override
    public User remove(int id) throws DaoException {
        return null;
    }

    @Override
    public List<User> findAll() throws DaoException {
        UserCreator userCreator = UserCreator.getInstance();
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_USERS)) {
            statement.setInt(1, User.Role.USER.getRoleId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String userName = resultSet.getString("name");
                String userSurname = resultSet.getString("surname");
                String userLogin = resultSet.getString("login");
                String userEmail = resultSet.getString("email");
                String userPhone = resultSet.getString("phone");
                boolean userStatus = checkStatus(resultSet.getInt("id"));
                User currentUser = userCreator.createUser(userStatus, userName, userSurname, userLogin, userEmail, userPhone);
                allUsers.add(currentUser);
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return allUsers;
    }

    public boolean updateBalanceByLogin(String login, double sum) throws DaoException {
        boolean isUpdate = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ACCOUNT_BALANCE_BY_ID)) {
            statement.setBigDecimal(1, BigDecimal.valueOf(sum));
            statement.setInt(2, findUserIdByLogin(login).get());
            isUpdate = statement.executeUpdate() > 0;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return isUpdate;
    }

    public Optional<User> findById(int id) throws DaoException {
        UserCreator userCreator = UserCreator.getInstance();
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                String userSurname = resultSet.getString("surname");
                String userLogin = resultSet.getString("login");
                String userEmail = resultSet.getString("email");
                String userPhone = resultSet.getString("phone");
                BigDecimal accountBalance = findAccountBalanceByUserId(userId);
                user = Optional.of(userCreator.createUser(accountBalance,userId,userName,userSurname,userLogin,userEmail,userPhone));
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return user;
    }

    public boolean banAccount(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.BAN_ACCOUNT_BY_LOGIN)) {
            Optional<Integer> optionalUserId = findUserIdByLogin(login);
            if (optionalUserId.isEmpty()) {
                return false;
            }
            statement.setInt(1, optionalUserId.get());
            return statement.executeUpdate() > 0;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
    }

    public boolean unbanAccount(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UNBAN_ACCOUNT_BY_LOGIN)) {
            Optional<Integer> optionalUserId = findUserIdByLogin(login);
            if (optionalUserId.isEmpty()) {
                return false;
            }
            statement.setInt(1, optionalUserId.get());
            return statement.executeUpdate() > 0;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
    }

    public void updatePasswordByLogin(String login, String password) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PASSWORD_BY_LOGIN)) {
            statement.setString(1, password);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
    }

    /*public void checkAccount(User user) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHECK_ACCOUNT)) {
            statement.setInt(1, user.getUserId());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                createAccount(user);
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
    }*/

    private BigDecimal findAccountBalanceByUserId(int userId) throws DaoException {
        BigDecimal accountBalance = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement =
                connection.prepareStatement(SqlQuery.FIND_ACCOUNT_BALANCE_BY_USER_ID)){
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                accountBalance = resultSet.getBigDecimal("balance");
            }

        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return accountBalance;
    }

    public void createAccount(User user) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CREATE_ACCOUNT)) {
            statement.setBigDecimal(1, new BigDecimal(0));
            statement.setInt(2, 1);
            statement.setInt(3, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
    }

    private boolean checkStatus(int userId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHECK_STATUS_BY_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("id_status") == 1) {
                    return false;
                }
            }
            return true;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

    }

    private Optional<Integer> findUserIdByLogin(String login) throws DaoException {
        Optional<Integer> userId = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_ID_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = Optional.of(resultSet.getInt("id"));
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return userId;
    }

}
