package by.epam.lukyanau.rentService.controller.command;

public class MessageAttribute {
    //Session attribute
    public static final String LANGUAGE = "language";
    public static final String USER = "user";
    public static final String ALL_USERS = "allUsers";
    public static final String ALL_CARS = "allCars";
    public static final String USER_ROLE = "userRole";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String FILTER_CARS_ERROR_MESSAGE = "userCatalogErrorMessage";
    public static final String MAX_QUANTITY_PAGES = "quantityPages";
    public static final String ORDERS = "orders";
    public static final String SHOW_COMMAND = "showCommand";
    public static final String PERSONAL_ORDERS = "personalOrders";
    public static final String PAYMENT_ERROR_MESSAGE = "paymentErrorMessage";
    public static final String PAYMENT_CARD_DATA = "paymentCardData";

    //Notification
    public static final String ADD_CAR = "addCar";
    public static final String BAN_ACCOUNT = "banAccount";
    public static final String UNBAN_ACCOUNT = "unbanAccount";
    public static final String BANNED_ACCOUNT = "bannedAccount";
    public static final String ACTIVATE_CAR = "activateCar";
    public static final String DEACTIVATE_CAR = "deactivateCar";
    public static final String DELETE_CAR = "deleteCar";
    public static final String ORDER_CAR = "orderCar";
    public static final String APPROVE_ORDER_ID = "approveOrderId";
    public static final String REJECT_ORDER_ID = "rejectOrderId";
    public static final String PAYMENT_MESSAGE = "paymentMessage";
    public static final String DEPOSIT_MESSAGE = "depositMessage";


    private MessageAttribute() {

    }
}
