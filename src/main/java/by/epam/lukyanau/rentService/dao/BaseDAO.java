package by.epam.lukyanau.rentService.dao;

import by.epam.lukyanau.rentService.entity.Entity;

import java.util.List;

public interface BaseDAO<T extends Entity> {
    T add(T t) throws DAOException;

    T remove(int id) throws DAOException;

    List<T> findAll() throws DAOException;
}
