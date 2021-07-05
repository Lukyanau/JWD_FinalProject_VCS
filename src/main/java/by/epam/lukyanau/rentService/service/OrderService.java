package by.epam.lukyanau.rentService.service;

import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.entity.Order;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order makeOrder(String arrivalDateString, String departureDateString, User user, Car car) throws ServiceException;
    List<Order> findOrdersByStatus(String status) throws ServiceException;
    List<Order> sortByParameter(List<Order> orders, String sortType) throws ServiceException;
    List<Order> findOrdersByUserLogin(String login) throws ServiceException;
    List<Order> findAllOrders() throws ServiceException;
    Optional<Order> findOrderById(String id) throws ServiceException;
    boolean updateOrderStatus(String id, String status) throws ServiceException;
}
