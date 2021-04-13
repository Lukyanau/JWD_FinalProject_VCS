package by.epam.lukyanau.rentService.dao;

import by.epam.lukyanau.rentService.entity.Order;

import java.util.List;

public interface OrderDao extends BaseDao<Order>{
    List<Order> findByStatus(String status) throws DaoException;
}
