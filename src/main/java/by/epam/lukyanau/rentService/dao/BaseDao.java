package by.epam.lukyanau.rentService.dao;

import by.epam.lukyanau.rentService.entity.Entity;

import java.util.List;

public interface BaseDao<T extends Entity> {
    T add(T t) throws DaoException;

    T remove(int id) throws DaoException;

    List<T> findAll() throws DaoException;
}
