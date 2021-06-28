package by.epam.lukyanau.rentService.dao;

import by.epam.lukyanau.rentService.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {
    Optional<User> findByLogin(String login) throws DaoException;
    String findPasswordByLogin(String login) throws DaoException;
    boolean updateBalanceByLogin(String login, double sum) throws DaoException;
    Optional<User> findById(int id) throws DaoException;
    boolean banAccount(String login) throws DaoException;
    boolean unbanAccount(String login) throws DaoException;
    void updatePasswordByLogin(String login, String password) throws DaoException;
    void createAccount(User user) throws DaoException;
}
