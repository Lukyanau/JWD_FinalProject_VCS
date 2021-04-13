package by.epam.lukyanau.rentService.entity;

import java.math.BigDecimal;

public class Car extends Entity {

    private int carId;
    private String color;
    private String model;
    private BigDecimal price;
    private String description;
    private int markId;
    private String mark;
    private boolean status;

    public Car(String color, String model, BigDecimal price, String description, int markId) {
        this.color = color;
        this.model = model;
        this.price = price;
        this.description = description;
        this.markId = markId;
    }

    public Car(int carId, String color, String model, BigDecimal price, String mark) {
        this.carId = carId;
        this.color = color;
        this.model = model;
        this.price = price;
        this.mark = mark;
    }

    public Car(int carId, String color, String model, BigDecimal price, String description, String mark, boolean status) {
        this.carId = carId;
        this.color = color;
        this.model = model;
        this.price = price;
        this.description = description;
        this.mark = mark;
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (carId != car.carId) return false;
        if (markId != car.markId) return false;
        if (status != car.status) return false;
        if (color != null ? !color.equals(car.color) : car.color != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (price != null ? !price.equals(car.price) : car.price != null) return false;
        if (description != null ? !description.equals(car.description) : car.description != null) return false;
        return mark != null ? mark.equals(car.mark) : car.mark == null;
    }

    @Override
    public int hashCode() {
        int result = carId;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + markId;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", color='" + color + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", markId=" + markId +
                ", mark='" + mark + '\'' +
                ", status=" + status +
                '}';
    }
}

