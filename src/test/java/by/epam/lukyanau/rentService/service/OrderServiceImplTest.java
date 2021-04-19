package by.epam.lukyanau.rentService.service;

import by.epam.lukyanau.rentService.dao.DaoException;
import by.epam.lukyanau.rentService.dao.impl.OrderDaoImpl;
import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.entity.Order;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.creator.OrderCreator;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.OrderServiceImpl;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class OrderServiceImplTest {
    OrderDaoImpl daoMock;
    OrderServiceImpl orderService;
    List<Order> allOrders;

    @BeforeClass
    public void beforeClass() {
        daoMock = mock(OrderDaoImpl.class);
        orderService = OrderServiceImpl.getInstance();
        Whitebox.setInternalState(OrderDaoImpl.class, "instance", daoMock);
    }

    @DataProvider(name = "updateOrderStatusParameters")
    public Object[][] createUpdateOrderStatusParameters() {
        return new Object[][]{
                {"1", "waiting"},
                {"2", "waiting"},
                {"3", "waiting"}
        };
    }

    @Test(dataProvider = "updateOrderStatusParameters")
    public void updateOrderStatusShouldReturnTrue(String orderId, String status) {
        try {
            when(daoMock.updateStatusById(any(Integer.class), any(String.class))).thenReturn(true);
            boolean condition = orderService.updateOrderStatus(orderId, status);
            assertTrue(condition);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "updateOrderStatusParameters", expectedExceptions = ServiceException.class)
    public void updateOrderStatusShouldThrowException(String orderId, String status) throws DaoException, ServiceException {
        when(daoMock.updateStatusById(any(Integer.class), any(String.class))).thenThrow(DaoException.class);
        orderService.updateOrderStatus(orderId, status);
    }

    @DataProvider(name = "orderId")
    public Object[] createOrderId() {
        return new Object[]{
                "1",
                "2",
                "3"
        };
    }

    @Test(dataProvider = "orderId")
    public void findOrderByIdShouldReturnOrder(String id) {
        OrderCreator orderCreator = OrderCreator.getInstance();
        Optional<Order> createdOrder = Optional.of(orderCreator.createOrder(new User("Andrey", "Burov",
                        "BBB", "bur@mail.ru", "+375(44)6992396", 1),
                new Car(22, "Black", "x6", BigDecimal.valueOf(350), "bmw"),
                Date.valueOf("2021-10-25"), Date.valueOf("2021-10-28"), 500));
        try {
            when(daoMock.findById(any(Integer.class))).thenReturn(createdOrder);
            Optional<Order> currentOrder = orderService.findOrderById(id);
            assertEquals(currentOrder, createdOrder);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "orderId", expectedExceptions = ServiceException.class)
    public void findOrderByIdShouldThrowException(String id) throws DaoException, ServiceException {
        when(daoMock.findById(Integer.parseInt(id))).thenThrow(DaoException.class);
        orderService.findOrderById(id);
    }

    @BeforeMethod
    public void setUpAllOrders() {
        allOrders = new ArrayList<>();
        allOrders.add(new Order(new User("Andrey", "Burov",
                "BBB", "bur@mail.ru", "+375(44)6992396", 1),
                new Car(22, "Black", "x6", BigDecimal.valueOf(350), "bmw"),
                Date.valueOf("2021-10-25"), Date.valueOf("2021-10-28"), Order.Status.WAITING, 500));
        allOrders.add(new Order(new User("Andrey", "Burov",
                "BBB", "bur@mail.ru", "+375(44)6992396", 1),
                new Car(22, "Black", "x6", BigDecimal.valueOf(350), "bmw"),
                Date.valueOf("2021-11-25"), Date.valueOf("2021-11-28"), Order.Status.WAITING, 500));
    }

    @Test
    public void findAllOrderShouldReturnCorrectList() {
        try {
            when(daoMock.findAll()).thenReturn(allOrders);
            List<Order> currentOrders = orderService.findAllOrders();
            assertEquals(currentOrders, allOrders);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findAllOrderShouldThrowException() throws DaoException, ServiceException {
        when(daoMock.findAll()).thenThrow(DaoException.class);
        orderService.findAllOrders();
    }

    @DataProvider(name = "orderLogin")
    public Object[] createOrderLogin() {
        return new Object[]{
                "buba",
                "alfa"
        };
    }

    @Test(dataProvider = "orderLogin")
    public void findOrdersByUserLoginShouldReturnCorrectList(String login) {
        try {
            when(daoMock.findAllByUserLogin(any(String.class))).thenReturn(allOrders);
            List<Order> currentOrdersByLogin = orderService.findOrdersByUserLogin(login);
            assertEquals(currentOrdersByLogin, allOrders);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "orderLogin", expectedExceptions = ServiceException.class)
    public void findOrdersByUserLoginShouldThrowException(String login) throws DaoException, ServiceException {
        when(daoMock.findAllByUserLogin(any(String.class))).thenThrow(DaoException.class);
        orderService.findOrdersByUserLogin(login);
    }

    @AfterMethod
    public void tearDownAllOrders() {
        allOrders = null;
    }

    @DataProvider(name = "makeOrderCorrectParameters")
    public Object[][] createMakeOrderCorrectParameters() {
        return new Object[][]{
                {"17-04-2021", "25-04-2021", new User("Andrey", "Burov", "BBB",
                        "bur@mail.ru", "+375(44)6992396", 1), new Car(22, "Black",
                        "x6", BigDecimal.valueOf(350), "bmw")},
                {"26-04-2021", "29-04-2021", new User("Andrey", "Burov", "BBB",
                        "bur@mail.ru", "+375(44)6992396", 1), new Car(22, "Black",
                        "x6", BigDecimal.valueOf(350), "bmw")},
                {"01-05-2021", "10-05-2021", new User("Andrey", "Burov", "BBB",
                        "bur@mail.ru", "+375(44)6992396", 1), new Car(22, "Black",
                        "x6", BigDecimal.valueOf(350), "bmw")},
        };
    }

    @Test(dataProvider = "makeOrderCorrectParameters")
    public void makeOrderShouldReturnCorrectOrder(String arrivalDateString, String departureDateString, User user, Car car) {
        OrderCreator orderCreator = OrderCreator.getInstance();
        Order createdOrder = orderCreator.createOrder(new User("Andrey", "Burov",
                        "BBB", "bur@mail.ru", "+375(44)6992396", 1),
                new Car(22, "Black", "x6", BigDecimal.valueOf(350), "bmw"),
                Date.valueOf("2021-10-25"), Date.valueOf("2021-10-28"), 500);
        try {
            when(daoMock.add(any(Order.class))).thenReturn(createdOrder);
            Order currentOrder = orderService.makeOrder(arrivalDateString, departureDateString, user, car);
            assertEquals(currentOrder, createdOrder);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "makeOrderCorrectParameters", expectedExceptions = ServiceException.class)
    public void makeOrderShouldThrowException(String arrivalDateString, String departureDateString, User user, Car car)
            throws DaoException, ServiceException {
            when(daoMock.add(any(Order.class))).thenThrow(DaoException.class);
            orderService.makeOrder(arrivalDateString, departureDateString, user, car);
    }

    @AfterClass
    public void afterClass() {
        daoMock = null;
        orderService = null;
    }
}
