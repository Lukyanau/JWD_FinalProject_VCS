package by.epam.lukyanau.rentService.service.util.comparator;

import by.epam.lukyanau.rentService.entity.Car;

import java.util.Comparator;

public class CarModelComparator implements Comparator<Car> {
    private static final CarModelComparator instance = new CarModelComparator();

    private CarModelComparator() {

    }

    public static CarModelComparator getInstance() {
        return instance;
    }
    @Override
    public int compare(Car car1, Car car2) {
        return car1.getModel().compareTo(car2.getModel());
    }
}
