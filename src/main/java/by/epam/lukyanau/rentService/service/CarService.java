package by.epam.lukyanau.rentService.service;

import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.service.exception.IncorrectAddCarException;
import by.epam.lukyanau.rentService.service.exception.NullCarException;
import by.epam.lukyanau.rentService.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAllCars() throws ServiceException;
    Car addCar(String color, String mark, String model, Integer price, String description)
            throws IncorrectAddCarException, ServiceException;
    Optional<Car> findById(String carId) throws ServiceException;
    List<Car> findFreeCarsByParameters(String carMark, String dateFromString, String dateToString)
            throws ServiceException;
    boolean deleteCar(int carId) throws ServiceException, NullCarException;
    boolean activateCar(int carId) throws ServiceException, NullCarException;
    boolean deactivateCar(int carId) throws ServiceException, NullCarException;
    List<Car> sortByParameter(List<Car> allCars, String sortType);
}
