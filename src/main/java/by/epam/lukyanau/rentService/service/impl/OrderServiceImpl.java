package by.epam.lukyanau.rentService.service.impl;

import by.epam.lukyanau.rentService.connection.ConnectionPool;
import by.epam.lukyanau.rentService.dao.DaoException;
import by.epam.lukyanau.rentService.dao.impl.OrderDaoImpl;
import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.entity.Order;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.OrderService;
import by.epam.lukyanau.rentService.service.creator.OrderCreator;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.util.DateUtil;
import by.epam.lukyanau.rentService.service.util.comparator.OrderingComparator;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private static final OrderServiceImpl instance = new OrderServiceImpl();

    private OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Order makeOrder(String arrivalDateString, String departureDateString, User user, Car car) throws ServiceException {
        OrderDaoImpl bookingDao = OrderDaoImpl.getInstance();
        OrderCreator orderCreator = OrderCreator.getInstance();
        Order newOrder = null;

        Optional<Date> arrivalDateOptional = DateUtil.parseStringToDateFormat(arrivalDateString);
        Optional<Date> departureDateOptional = DateUtil.parseStringToDateFormat(departureDateString);
        try {
            if (arrivalDateOptional.isPresent() && departureDateOptional.isPresent()) {
                Date arrivalDate = arrivalDateOptional.get();
                Date departureDate = departureDateOptional.get();
                Order order = orderCreator.createOrder(user, car, arrivalDate, departureDate, car.getPrice().doubleValue());
                newOrder = bookingDao.add(order);
            }
        } catch (DaoException exp) {
            throw new ServiceException("Error during make order", exp);
        }
        return newOrder;
    }

    @Override
    public List<Order> findOrdersByStatus(String status) throws ServiceException {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        List<Order> orders;
        try {
            orders = orderDao.findByStatus(status);
        } catch (DaoException exp) {
            throw new ServiceException("Error during find order by status", exp);
        }
        return orders;
    }

    public List<Order> sortByParameter(List<Order> orders, String sortType) throws ServiceException {
        try {
            Comparator<Order> currentComparator = OrderingComparator.valueOf(sortType.toUpperCase()).getComparator();
            List<Order> sortedList = orders.stream().sorted(currentComparator).collect(Collectors.toList());
            return sortedList;
        } catch (IllegalArgumentException exp) {
            throw new ServiceException("Unknown type of comparator", exp);
        }
    }

    public List<Order> findOrdersByUserLogin(String login) throws ServiceException {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        List<Order> orders;
        try {
            orders = orderDao.findAllByUserLogin(login);
        } catch (DaoException exp) {
            throw new ServiceException("Error during find user`s orders", exp);
        }
        return orders;
    }

    public List<Order> findAllOrders() throws ServiceException {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        List<Order> orders;
        try {
            orders = orderDao.findAll();
        } catch (DaoException exp) {
            throw new ServiceException("Error while during find all orders", exp);
        }
        return orders;
    }

    public Optional<Order> findOrderById(String id) throws ServiceException {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        Optional<Order> foundOrder;
        try {
            int orderId = Integer.parseInt(id);
            foundOrder = orderDao.findById(orderId);
        } catch (DaoException exp) {
            throw new ServiceException("Error during find booking by status", exp);
        }
        return foundOrder;
    }

    public boolean updateOrderStatus(String id, String status) throws ServiceException {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        try {
            int orderId = Integer.parseInt(id);
            Optional<Order.Status> optionalStatus = Order.Status.getStatusByValue(status);
            if (optionalStatus.isPresent()) {
                return orderDao.updateStatusById(orderId, status);
            }
        } catch (DaoException exp) {
            throw new ServiceException("Error during update booking status", exp);
        }
        return false;
    }

}
