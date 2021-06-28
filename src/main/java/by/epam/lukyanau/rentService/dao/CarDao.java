package by.epam.lukyanau.rentService.dao;

import by.epam.lukyanau.rentService.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao extends BaseDao<Car> {
    Optional<Car> findById(int carId) throws DaoException;
    List<Car> findFree(String mark, long from, long to) throws DaoException;
    boolean deleteCar(int carId) throws DaoException;
    boolean activateCar(int carId) throws DaoException;
    boolean deactivateCar(int carId) throws DaoException;
    int findMarkId(String mark) throws DaoException;
    String findMarkById(int markId) throws DaoException;
    boolean checkCarStatus(int carId) throws DaoException;
}
