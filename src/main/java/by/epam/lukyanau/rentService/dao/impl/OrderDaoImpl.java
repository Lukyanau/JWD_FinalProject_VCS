package by.epam.lukyanau.rentService.dao.impl;

import by.epam.lukyanau.rentService.connection.ConnectionPool;
import by.epam.lukyanau.rentService.dao.DaoException;
import by.epam.lukyanau.rentService.dao.OrderDao;
import by.epam.lukyanau.rentService.dao.SqlQuery;
import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.entity.Order;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.creator.CarCreator;
import by.epam.lukyanau.rentService.service.creator.OrderCreator;
import by.epam.lukyanau.rentService.service.creator.UserCreator;
import by.epam.lukyanau.rentService.service.util.DateUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static final OrderDaoImpl instance = new OrderDaoImpl();

    private OrderDaoImpl() {

    }

    public static OrderDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Order add(Order order) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_ORDER)) {
            statement.setLong(1, DateUtil.parseDateToMilliseconds(order.getArrivalDate()));
            statement.setLong(2, DateUtil.parseDateToMilliseconds(order.getDepartureDate()));
            statement.setString(3, order.getStatus().getNameStatus());
            statement.setInt(4, findAccountIdByUserLogin(order.getUser().getLogin()));
            statement.setInt(5, order.getCar().getCarId());
            statement.setDouble(6, order.getTotalPrice());
            statement.executeUpdate();
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return order;
    }

    @Override
    public Order remove(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        UserCreator userCreator = UserCreator.getInstance();
        CarCreator carCreator = CarCreator.getInstance();
        OrderCreator orderCreator = OrderCreator.getInstance();
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_ORDERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("users.id");
                String userName = resultSet.getString("users.name");
                String userSurname = resultSet.getString("users.surname");
                String userLogin = resultSet.getString("users.login");
                String userEmail = resultSet.getString("users.email");
                String userPhoneNumber = resultSet.getString("users.phone");
                User currentUser = userCreator.createUser(userId, userName, userSurname, userLogin, userEmail, userPhoneNumber);

                int currentCarId = resultSet.getInt("cars.id");
                String carModel = resultSet.getString("cars.model");
                String carColor = resultSet.getString("cars.color");
                String carMark = findCarMarkById(resultSet.getInt("cars.mark"));
                BigDecimal price = resultSet.getBigDecimal("cars.price");
                Car currentCar = carCreator.createCar(currentCarId, carColor, carModel, price, carMark);


                int orderId = resultSet.getInt("orders.id");
                long orderDateFrom = resultSet.getLong("orders.rentalDateStart");
                long orderDateTo = resultSet.getLong("orders.rentalDateFinish");
                String orderStatus = resultSet.getString("orders.order_status");
                BigDecimal orderCost = resultSet.getBigDecimal("orders.cost");

                Optional<Order.Status> statusOptional = Order.Status.getStatusByValue(orderStatus);
                Order.Status statusOrder = statusOptional.orElseThrow(DaoException::new);
                Date dateFrom = DateUtil.parseMillisecondsToDate(orderDateFrom);
                Date dateTo = DateUtil.parseMillisecondsToDate(orderDateTo);

                Order currentOrder = orderCreator.createOrder(orderId, currentUser, currentCar, dateFrom, dateTo, statusOrder, orderCost.doubleValue());
                orders.add(currentOrder);
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return orders;
    }

    @Override
    public List<Order> findByStatus(String status) throws DaoException {
        UserCreator userCreator = UserCreator.getInstance();
        CarCreator carCreator = CarCreator.getInstance();
        OrderCreator orderCreator = OrderCreator.getInstance();
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_ORDERS_BY_STATUS)) {
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("users.id");
                String userName = resultSet.getString("users.name");
                String userSurname = resultSet.getString("users.surname");
                String userLogin = resultSet.getString("users.login");
                String userEmail = resultSet.getString("users.email");
                String userPhoneNumber = resultSet.getString("users.phone");
                User currentUser = userCreator.createUser(userId, userName, userSurname, userLogin, userEmail, userPhoneNumber);

                int currentCarId = resultSet.getInt("cars.id");
                String carModel = resultSet.getString("cars.model");
                String carColor = resultSet.getString("cars.color");
                String carMark = findCarMarkById(resultSet.getInt("cars.mark"));
                BigDecimal price = resultSet.getBigDecimal("cars.price");
                Car currentCar = carCreator.createCar(currentCarId, carColor, carModel, price, carMark);


                int orderId = resultSet.getInt("orders.id");
                long orderDateFrom = resultSet.getLong("orders.rentalDateStart");
                long orderDateTo = resultSet.getLong("orders.rentalDateFinish");
                String orderStatus = resultSet.getString("orders.order_status");
                BigDecimal orderCost = resultSet.getBigDecimal("orders.cost");

                Optional<Order.Status> statusOptional = Order.Status.getStatusByValue(orderStatus);
                Order.Status statusOrder = statusOptional.orElseThrow(DaoException::new);
                Date dateFrom = DateUtil.parseMillisecondsToDate(orderDateFrom);
                Date dateTo = DateUtil.parseMillisecondsToDate(orderDateTo);

                Order currentOrder = orderCreator.createOrder(orderId, currentUser, currentCar, dateFrom, dateTo, statusOrder, orderCost.doubleValue());
                orders.add(currentOrder);
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return orders;
    }

    @Override
    public List<Order> findAllByUserLogin(String login) throws DaoException {
        UserCreator userCreator = UserCreator.getInstance();
        CarCreator carCreator = CarCreator.getInstance();
        OrderCreator orderCreator = OrderCreator.getInstance();
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_ORDERS_BY_ACCOUNT_ID)) {
            int accountId = findAccountIdByUserLogin(login);
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("users.id");
                String userName = resultSet.getString("users.name");
                String userSurname = resultSet.getString("users.surname");
                String userLogin = resultSet.getString("users.login");
                String userEmail = resultSet.getString("users.email");
                String userPhoneNumber = resultSet.getString("users.phone");
                User currentUser = userCreator.createUser(userId, userName, userSurname, userLogin, userEmail, userPhoneNumber);

                int currentCarId = resultSet.getInt("cars.id");
                String carModel = resultSet.getString("cars.model");
                String carColor = resultSet.getString("cars.color");
                String carMark = findCarMarkById(resultSet.getInt("cars.mark"));
                BigDecimal price = resultSet.getBigDecimal("cars.price");
                Car currentCar = carCreator.createCar(currentCarId, carColor, carModel, price, carMark);


                int orderId = resultSet.getInt("orders.id");
                long orderDateFrom = resultSet.getLong("orders.rentalDateStart");
                long orderDateTo = resultSet.getLong("orders.rentalDateFinish");
                String orderStatus = resultSet.getString("orders.order_status");
                BigDecimal orderCost = resultSet.getBigDecimal("orders.cost");

                Optional<Order.Status> statusOptional = Order.Status.getStatusByValue(orderStatus);
                Order.Status statusOrder = statusOptional.orElseThrow(DaoException::new);
                Date dateFrom = DateUtil.parseMillisecondsToDate(orderDateFrom);
                Date dateTo = DateUtil.parseMillisecondsToDate(orderDateTo);

                Order currentOrder = orderCreator.createOrder(orderId, currentUser, currentCar, dateFrom, dateTo, statusOrder, orderCost.doubleValue());
                orders.add(currentOrder);
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return orders;
    }

    @Override
    public boolean updateStatusById(int id, String status) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_STATUS_ORDER)) {
            statement.setString(1, status);
            statement.setInt(2, id);
            isUpdate = statement.executeUpdate() > 0;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return isUpdate;
    }

    @Override
    public Optional<Order> findById(int id) throws DaoException {
        UserCreator userCreator = UserCreator.getInstance();
        CarCreator carCreator = CarCreator.getInstance();
        OrderCreator orderCreator = OrderCreator.getInstance();
        Optional<Order> order = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ORDER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("users.id");
                String userName = resultSet.getString("users.name");
                String userSurname = resultSet.getString("users.surname");
                String userLogin = resultSet.getString("users.login");
                String userEmail = resultSet.getString("users.email");
                String userPhoneNumber = resultSet.getString("users.phone");
                User currentUser = userCreator.createUser(userId, userName, userSurname, userLogin, userEmail, userPhoneNumber);

                int currentCarId = resultSet.getInt("cars.id");
                String carModel = resultSet.getString("cars.model");
                String carColor = resultSet.getString("cars.color");
                String carMark = findCarMarkById(resultSet.getInt("cars.mark"));
                BigDecimal price = resultSet.getBigDecimal("cars.price");
                Car currentCar = carCreator.createCar(currentCarId, carColor, carModel, price, carMark);


                int orderId = resultSet.getInt("orders.id");
                long orderDateFrom = resultSet.getLong("orders.rentalDateStart");
                long orderDateTo = resultSet.getLong("orders.rentalDateFinish");
                String orderStatus = resultSet.getString("orders.order_status");
                BigDecimal orderCost = resultSet.getBigDecimal("orders.cost");

                Optional<Order.Status> statusOptional = Order.Status.getStatusByValue(orderStatus);
                Order.Status statusOrder = statusOptional.orElseThrow(DaoException::new);
                Date dateFrom = DateUtil.parseMillisecondsToDate(orderDateFrom);
                Date dateTo = DateUtil.parseMillisecondsToDate(orderDateTo);

                order = Optional.of(orderCreator.createOrder(orderId, currentUser, currentCar, dateFrom, dateTo, statusOrder, orderCost.doubleValue()));

            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return order;
    }


    private int findAccountIdByUserLogin(String login) throws DaoException {
        int account_id = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ACCOUNT_ID_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account_id = resultSet.getInt("id");
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return account_id;

    }


    private String findCarMarkById(int markId) throws DaoException {
        String mark = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_MARK_BY_MARK_ID)) {
            statement.setInt(1, markId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                mark = resultSet.getString("mark_name");
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return mark;
    }
}
