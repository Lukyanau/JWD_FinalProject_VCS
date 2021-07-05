package by.epam.lukyanau.rentService.service.impl;

import by.epam.lukyanau.rentService.dao.DaoException;
import by.epam.lukyanau.rentService.dao.impl.CarDaoImpl;
import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.service.CarService;
import by.epam.lukyanau.rentService.service.creator.CarCreator;
import by.epam.lukyanau.rentService.service.exception.IncorrectAddCarException;
import by.epam.lukyanau.rentService.service.exception.NullCarException;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.util.DateUtil;
import by.epam.lukyanau.rentService.service.util.comparator.CarModelComparator;
import by.epam.lukyanau.rentService.service.util.comparator.CarPriceComparator;
import by.epam.lukyanau.rentService.service.validator.CarValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {

    private static final CarServiceImpl instance = new CarServiceImpl();

    private CarServiceImpl() {

    }

    public static CarServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Car> findAllCars() throws ServiceException {
        CarDaoImpl carDao = CarDaoImpl.getInstance();
        List<Car> allCars;
        try {
            allCars = carDao.findAll();
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
        return allCars;
    }

    @Override
    public Car addCar(String color, String mark, String model, Integer price, String description)
            throws IncorrectAddCarException, ServiceException {
        CarCreator carCreator = CarCreator.getInstance();
        CarDaoImpl carDao = CarDaoImpl.getInstance();
        if (!CarValidator.checkAddCarParameters(color, model, price.toString(), description)) {
            throw new IncorrectAddCarException("Add car parameters is incorrect!!!");
        }
        try {
            int markId = findMarkId(mark);
            Car createdCar = carCreator.createCar(color, markId, model, price, description);
            Car addedCar = carDao.add(createdCar);
            return addedCar;
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }

    }

    @Override
    public Optional<Car> findById(String carId) throws ServiceException {
        CarDaoImpl carDao = CarDaoImpl.getInstance();
        try {
            int currentCarId = Integer.parseInt(carId);
            return carDao.findById(currentCarId);
        } catch (DaoException exp) {
            throw new ServiceException("Error while find car by id", exp);
        }
    }

    @Override
    public List<Car> findFreeCarsByParameters(String carMark, String dateFromString, String dateToString) throws ServiceException {
        CarDaoImpl carDao = CarDaoImpl.getInstance();
        List<Car> foundCars;

        try{
            long dateFrom = DateUtil.parseDateStringToMilliseconds(dateFromString);
            long dateTo = DateUtil.parseDateStringToMilliseconds(dateToString);

            foundCars = carDao.findFree(carMark, dateFrom, dateTo);

        } catch (DaoException exp) {
            throw new ServiceException("Error during find free cars by parameters",exp);
        }
        return foundCars;
    }

    @Override
    public boolean deleteCar(int carId) throws ServiceException, NullCarException {
        CarDaoImpl carDao = CarDaoImpl.getInstance();
        try {
            boolean isActivated = carDao.deleteCar(carId);
            if (!isActivated) {
                throw new NullCarException("There isn't car with this id");
            }
            return true;
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
    }

    @Override
    public boolean activateCar(int carId) throws ServiceException, NullCarException {
        CarDaoImpl carDao = CarDaoImpl.getInstance();
        try {
            boolean isActivated = carDao.activateCar(carId);
            if (!isActivated) {
                throw new NullCarException("There isn't car with this id");
            }
            return true;
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
    }

    @Override
    public boolean deactivateCar(int carId) throws ServiceException, NullCarException {
        CarDaoImpl carDao = CarDaoImpl.getInstance();
        try {
            boolean isActivated = carDao.deactivateCar(carId);
            if (!isActivated) {
                throw new NullCarException("There isn't car with this id");
            }
            return true;
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
    }

    @Override
    public List<Car> sortByParameter(List<Car> allCars, String sortType) {
        CarPriceComparator priceComparator = CarPriceComparator.getInstance();
        CarModelComparator modelComparator = CarModelComparator.getInstance();
        List<Car> sortedCars = new ArrayList<>(allCars);
        switch (sortType) {
            case "price" -> sortedCars.sort(priceComparator);
            case "model" -> sortedCars.sort(modelComparator);
        }
        return sortedCars;
    }

    private int findMarkId(String mark) throws ServiceException {
        CarDaoImpl carDao = CarDaoImpl.getInstance();

        try {
            return carDao.findMarkId(mark);
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
    }
}
