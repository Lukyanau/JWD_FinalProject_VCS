package by.epam.lukyanau.rentService.service;

import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.entity.Order;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    Order makeOrder(String arrivalDateString, String departureDateString, User user, Car car) throws ServiceException;
    List<Order> findOrdersByStatus(String status) throws ServiceException;

}
