package by.epam.lukyanau.rentService.service;

import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.service.exception.IncorrectAddCarException;
import by.epam.lukyanau.rentService.service.exception.ServiceException;

public interface CarService {
    Car addCar(String color, String mark, String model, Integer price, String description) throws IncorrectAddCarException, ServiceException;
}
