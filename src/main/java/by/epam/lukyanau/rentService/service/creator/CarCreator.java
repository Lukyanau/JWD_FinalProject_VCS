package by.epam.lukyanau.rentService.service.creator;

import by.epam.lukyanau.rentService.entity.Car;

import java.math.BigDecimal;

public class CarCreator {

    private static final CarCreator instance = new CarCreator();

    private CarCreator() {

    }

    public static CarCreator getInstance() {
        return instance;
    }

    public Car createCar(String color, int markId, String model, Integer price, String description) {
        return new Car(color, model, BigDecimal.valueOf(price), description, markId);
    }

    public Car createCar(int carId, String color, String model, BigDecimal price, String description, String mark,
                         boolean status) {
        return new Car(carId, color, model, price, description, mark, status);
    }

    public Car createCar(int carId, String color, String model, BigDecimal price, String mark) {
        return new Car(carId, color, model, price, mark);
    }

}
