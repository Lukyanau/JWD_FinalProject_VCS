package by.epam.lukyanau.rentService.dao;

public class SqlQuery {
    //User Table
    public static final String FIND_ALL_USERS = "SELECT * FROM users WHERE role = ?";
    public static final String FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String FIND_USER_ID_BY_LOGIN = "SELECT id FROM users WHERE login = ?";
    public static final String FIND_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE login = ?";
    public static final String UPDATE_PASSWORD_BY_LOGIN = "UPDATE users SET password = ? WHERE login = ?";
    public static final String ADD_USER = "INSERT INTO users(login,name,surname,email,phone,role) VALUES (?,?,?,?,?,?)";
    public static final String FIND_USER_BY_ID = "SELECT id, login, email, name, surname, " +
            "phone FROM users WHERE id = ?";
    public static final String FIND_ACCOUNT_BALANCE_BY_USER_ID = "SELECT balance FROM accounts WHERE user_id = ?";



    //Car Table
    public static final String ADD_CAR = "INSERT INTO cars(model,color,mark,description,price,status) VALUES (?,?,?,?,?,true)";
    public static final String FIND_MARK_ID_BY_MARK = "SELECT id FROM marks WHERE mark_name = ?";
    public static final String FIND_MARK_BY_MARK_ID = "SELECT mark_name FROM marks WHERE id = ?";
    public static final String FIND_ALL_CARS = "SELECT * FROM cars";
    public static final String FIND_CAR_BY_ID = "SELECT * FROM cars WHERE id = ?";
    public static final String CHECK_CAR_STATUS_BY_ID = "SELECT status FROM cars WHERE id = ?";
    public static final String DELETE_CAR_BY_ID = "DELETE FROM cars WHERE id = ?";
    public static final String ACTIVATE_CAR_BY_ID = "UPDATE cars SET status = (SELECT id FROM car_status " +
            "WHERE status = 'activated') WHERE id = ?";
    public static final String DEACTIVATE_CAR_BY_ID = "UPDATE cars SET status = (SELECT id FROM car_status " +
            "WHERE status = 'deactivated') WHERE id = ?";
    public static final String FIND_FREE_CARS = "SELECT id,model,color,price FROM cars WHERE mark = (SELECT id FROM marks WHERE mark_name = ?)" +
            " AND status = (SELECT id FROM car_status WHERE status = 'activated') AND NOT EXISTS (SELECT id, " +
            "rentalDateStart, rentalDateFinish, order_status, carId, account_id FROM orders WHERE order_status != " +
            "'rejected' AND id = carId AND ((? BETWEEN rentalDateStart AND rentalDateFinish) OR " +
            "(? BETWEEN rentalDateStart AND rentalDateFinish)))";

    //Account Table
    public static final String FIND_ACCOUNT_ID_BY_LOGIN = "SELECT id FROM accounts WHERE user_id = (SELECT id FROM users WHERE login = ?)";
    public static final String CHECK_ACCOUNT = "SELECT * FROM accounts WHERE user_id = ?";
    public static final String CREATE_ACCOUNT = "INSERT INTO accounts(balance,id_status,user_id) VALUES (?,?,?)";
    public static final String CHECK_STATUS_BY_ID = "SELECT id_status FROM accounts where user_id = ?";
    public static final String BAN_ACCOUNT_BY_LOGIN = "UPDATE accounts SET id_status = (SELECT id FROM account_status " +
            "WHERE status = 'banned') WHERE user_id = ?";
    public static final String UNBAN_ACCOUNT_BY_LOGIN = "UPDATE accounts SET id_status = (SELECT id FROM account_status " +
            "WHERE status = 'unbanned') WHERE user_id = ?";
    public static final String UPDATE_ACCOUNT_BALANCE_BY_ID = "UPDATE accounts SET balance = ? WHERE user_id = ?";

    //Order Table
    public static final String ADD_ORDER = "INSERT INTO orders (rentalDateStart, rentalDateFinish, order_status, " +
            "account_id, carId, cost) VALUES(?,?,?,?,?,?)";
    public static final String FIND_ALL_ORDERS = "SELECT users.id, users.login, users.email, users.name, users.surname, " +
            "users.phone, cars.id, cars.model, cars.color, cars.price, cars.mark, orders.id, orders.rentalDateStart, " +
            "orders.rentalDateFinish, orders.order_status, orders.cost FROM rentcar.orders JOIN rentcar.users " +
            "ON users.id = orders.account_id JOIN rentcar.cars ON cars.id = orders.carId";
    public static final String FIND_ALL_ORDERS_BY_STATUS = "SELECT users.id, users.login, users.email, users.name, users.surname, " +
            "users.phone, cars.id, cars.model, cars.color, cars.price, cars.mark, orders.id, orders.rentalDateStart, " +
            "orders.rentalDateFinish, orders.order_status, orders.cost FROM rentcar.orders JOIN rentcar.users " +
            "ON users.id = orders.account_id JOIN rentcar.cars ON cars.id = orders.carId WHERE orders.order_status = ?";
    public static final String FIND_ORDER_BY_ID = "SELECT users.id, users.login, users.email, users.name, users.surname, " +
            "users.phone, cars.id, cars.model, cars.color, cars.price, cars.mark, orders.id, orders.rentalDateStart, " +
            "orders.rentalDateFinish, orders.order_status, orders.cost FROM rentcar.orders JOIN rentcar.users " +
            "ON users.id = orders.account_id JOIN rentcar.cars ON cars.id = orders.carId WHERE orders.id = ?";
    public static final String UPDATE_STATUS_ORDER = "UPDATE orders SET order_status = ? WHERE id = ?";
    public static final String FIND_ALL_ORDERS_BY_ACCOUNT_ID = "SELECT users.id, users.login, users.email, users.name, users.surname, " +
            "users.phone, cars.id, cars.model, cars.color, cars.price, cars.mark, orders.id, orders.rentalDateStart, " +
            "orders.rentalDateFinish, orders.order_status, orders.cost FROM rentcar.orders JOIN rentcar.users " +
            "ON users.id = orders.account_id JOIN rentcar.cars ON cars.id = orders.carId WHERE orders.account_id = ?";

    private SqlQuery() {

    }
}
