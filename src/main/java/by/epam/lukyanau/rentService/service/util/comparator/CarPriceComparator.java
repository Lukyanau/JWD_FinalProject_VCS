package by.epam.lukyanau.rentService.service.util.comparator;

import by.epam.lukyanau.rentService.entity.Car;

import java.util.Comparator;

public class CarPriceComparator implements Comparator<Car> {

    private static final CarPriceComparator instance = new CarPriceComparator();

    private CarPriceComparator() {

    }

    public static CarPriceComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(Car car1, Car car2) {
        return car1.getPrice().compareTo(car2.getPrice());
    }
}
