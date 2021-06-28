package by.epam.lukyanau.rentService.dao;

import by.epam.lukyanau.rentService.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends BaseDao<Order>{
    List<Order> findByStatus(String status) throws DaoException;
    List<Order> findAllByUserLogin(String login) throws DaoException;
    boolean updateStatusById(int id, String status) throws DaoException;
    Optional<Order> findById(int id) throws DaoException;
}
