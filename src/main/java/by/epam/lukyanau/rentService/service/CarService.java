package by.epam.lukyanau.rentService.service;

import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.service.exception.IncorrectAddCarException;
import by.epam.lukyanau.rentService.service.exception.ServiceException;

import java.util.List;

public interface CarService {
    List<Car> findAllCars() throws ServiceException;
    Car addCar(String color, String mark, String model, Integer price, String description) throws IncorrectAddCarException, ServiceException;
}
