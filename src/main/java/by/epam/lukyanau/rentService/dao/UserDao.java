package by.epam.lukyanau.rentService.dao;

import by.epam.lukyanau.rentService.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {
    Optional<User> findByLogin(String login) throws DaoException;
    String findPasswordByLogin(String login) throws DaoException;

}
