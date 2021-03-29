package by.epam.lukyanau.rentService.service.impl;

import by.epam.lukyanau.rentService.dao.DAOException;
import by.epam.lukyanau.rentService.dao.impl.CarDaoImpl;
import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.service.CarService;
import by.epam.lukyanau.rentService.service.creator.CarCreator;
import by.epam.lukyanau.rentService.service.exception.IncorrectAddCarException;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.validator.CarValidator;

public class CarServiceImpl implements CarService {

    private static final CarServiceImpl instance = new CarServiceImpl();

    private CarServiceImpl() {

    }

    public static CarServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Car addCar(String color, String mark, String model, Integer price, String description) throws IncorrectAddCarException, ServiceException {
        CarCreator carCreator = CarCreator.getInstance();
        CarDaoImpl carDao = CarDaoImpl.getInstance();
        if (!CarValidator.CheckAddCarParameters(color, model, price.toString(), description)) {
            throw new IncorrectAddCarException("Add car parameters is incorrect!!!");
        }
        try {

            int markId = findMarkId(mark);
            Car createdCar = carCreator.createCar(color, markId, model, price, description);
            Car addedCar = carDao.add(createdCar);
            return addedCar;
        } catch (DAOException exp) {
            throw new ServiceException(exp);
        }

    }

    private int findMarkId(String mark) throws ServiceException {
        CarDaoImpl carDao = CarDaoImpl.getInstance();

        try {
            return carDao.findMarkId(mark);
        } catch (DAOException exp) {
            throw new ServiceException(exp);
        }
    }
}
