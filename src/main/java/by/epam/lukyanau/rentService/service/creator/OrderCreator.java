package by.epam.lukyanau.rentService.service.creator;

import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.entity.Order;
import by.epam.lukyanau.rentService.entity.User;

import java.util.Date;

public class OrderCreator {
    private static final OrderCreator instance = new OrderCreator();

    private OrderCreator(){

    }

    public static OrderCreator getInstance() {
        return instance;
    }

    public Order createOrder(int bookingId, User user, Car car, Date arrivalDate,
                               Date departureDate, Order.Status status, double totalPrice) {
        Order newOrder= new Order(bookingId, user, car, arrivalDate, departureDate, status, totalPrice);
        return newOrder;
    }

    public Order createOrder(User user, Car car, Date arrivalDate,
                                 Date departureDate, double totalPrice) {
        Order newOrder = new Order(user, car, arrivalDate, departureDate,
                Order.Status.WAITING, totalPrice);
        return newOrder;
    }
}
