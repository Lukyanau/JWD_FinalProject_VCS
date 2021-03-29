package by.epam.lukyanau.rentService.entity;

import java.math.BigDecimal;

public class Car extends Entity {

    private String color;
    private String model;
    private BigDecimal price;
    private String description;
    private int markId;

    public Car(String color, String model, BigDecimal price, String description, int markId) {
        this.color = color;
        this.model = model;
        this.price = price;
        this.description = description;
        this.markId = markId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (markId != car.markId) return false;
        if (color != null ? !color.equals(car.color) : car.color != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (price != null ? !price.equals(car.price) : car.price != null) return false;
        return description != null ? description.equals(car.description) : car.description == null;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + markId;
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", markId=" + markId +
                '}';
    }
}
