package by.epam.lukyanau.rentService.entity;

import by.epam.lukyanau.rentService.service.util.DateUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

public class Order extends Entity{

    public enum Status{
        WAITING("waiting"),

        PAYMENT("payment"),

        ACTIVE("active"),

        REJECTED("rejected");

        private final String nameStatus;

        Status(String nameStatus) {
            this.nameStatus = nameStatus;
        }

        public String getNameStatus() {
            return nameStatus;
        }

        public static Optional<Order.Status> getStatusByValue(String value) {
            return Arrays.stream(Order.Status.values()).
                    filter(o -> o.getNameStatus().equals(value)).findAny();
        }
    }

    private int orderId;
    private User user;
    private Car car;
    private Date arrivalDate;
    private Date departureDate;
    private Status status;
    private double totalPrice;

    public Order(int orderId, User user, Car car, Date arrivalDate, Date departureDate, Status status, double totalPrice) {
        this.orderId = orderId;
        this.user = user;
        this.car = car;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Order(User user, Car car, Date arrivalDate, Date departureDate, Status status, double totalPrice) {
        this.user = user;
        this.car = car;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getArrivalDateString() {
        String dateString = DateUtil.parseDateToStringFormat(this.arrivalDate);
        return dateString;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public String getDepartureDateString() {
        String dateString = DateUtil.parseDateToStringFormat(this.departureDate);
        return dateString;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (Double.compare(order.totalPrice, totalPrice) != 0) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (car != null ? !car.equals(order.car) : order.car != null) return false;
        if (arrivalDate != null ? !arrivalDate.equals(order.arrivalDate) : order.arrivalDate != null) return false;
        if (departureDate != null ? !departureDate.equals(order.departureDate) : order.departureDate != null)
            return false;
        return status == order.status;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", car=" + car +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
