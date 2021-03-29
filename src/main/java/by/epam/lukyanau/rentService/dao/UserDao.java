package by.epam.lukyanau.rentService.dao;

import by.epam.lukyanau.rentService.entity.User;

public interface UserDao extends BaseDAO<User> {
    User findByLogin(String login) throws DAOException;
    String findPasswordByLogin(String login) throws DAOException;

}
