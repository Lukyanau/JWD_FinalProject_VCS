package by.epam.lukyanau.rentService.dao.impl;

import by.epam.lukyanau.rentService.connection.ConnectionPool;
import by.epam.lukyanau.rentService.dao.CarDao;
import by.epam.lukyanau.rentService.dao.DaoException;
import by.epam.lukyanau.rentService.dao.SqlQuery;
import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.service.creator.CarCreator;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {
    private static final CarDaoImpl instance = new CarDaoImpl();

    private CarDaoImpl() {
    }

    public static CarDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Car add(Car car) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_CAR)) {
            statement.setString(1, car.getModel());
            statement.setString(2, car.getColor());
            statement.setInt(3, car.getMarkId());
            statement.setString(4, car.getDescription());
            statement.setBigDecimal(5, car.getPrice());
            statement.executeUpdate();
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return car;
    }

    @Override
    public Car remove(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Car> findAll() throws DaoException {
        List<Car> allCars = new ArrayList<>();
        CarCreator carCreator = CarCreator.getInstance();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_CARS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int carId = resultSet.getInt("id");
                String carModel = resultSet.getString("model");
                String carColor = resultSet.getString("color");
                String carMark = findMarkById(resultSet.getInt("mark"));
                String carDescription = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                boolean status = checkCarStatus(carId);
                Car currentCar = carCreator.createCar(carId, carColor, carModel, price, carDescription, carMark, status);
                allCars.add(currentCar);

            }

        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return allCars;
    }

    @Override
    public Optional<Car> findById(int carId) throws DaoException {
        Optional<Car> car = Optional.empty();
        CarCreator carCreator = CarCreator.getInstance();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_CAR_BY_ID)) {
            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int currentCarId = resultSet.getInt("id");
                String carModel = resultSet.getString("model");
                String carColor = resultSet.getString("color");
                String carMark = findMarkById(resultSet.getInt("mark"));
                String carDescription = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                boolean status = checkCarStatus(carId);
                car = Optional.of(carCreator.createCar(carId, carColor, carModel, price, carDescription, carMark, status));

            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return car;
    }

    @Override
    public List<Car> findFree(String mark, long from, long to) throws DaoException {
        List<Car> freeCars = new ArrayList<>();
        CarCreator carCreator = CarCreator.getInstance();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FREE_CARS)) {
            statement.setString(1, mark);
            statement.setLong(2, from);
            statement.setLong(3, to);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int carId = resultSet.getInt("id");
                String carModel = resultSet.getString("model");
                String carColor = resultSet.getString("color");
                String carMark = mark;
                BigDecimal price = resultSet.getBigDecimal("price");
                Car car = carCreator.createCar(carId,carColor,carModel,price,carMark);
                freeCars.add(car);
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return freeCars;
    }

    @Override
    public boolean deleteCar(int carId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_CAR_BY_ID)) {
            statement.setInt(1, carId);
            return statement.executeUpdate() > 0;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
    }

    @Override
    public boolean activateCar(int carId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ACTIVATE_CAR_BY_ID)) {
            statement.setInt(1, carId);
            return statement.executeUpdate() > 0;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
    }

    @Override
    public boolean deactivateCar(int carId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DEACTIVATE_CAR_BY_ID)) {
            statement.setInt(1, carId);
            return statement.executeUpdate() > 0;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
    }

    @Override
    public int findMarkId(String mark) throws DaoException {
        int markId = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_MARK_ID_BY_MARK)) {
            statement.setString(1, mark);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                markId = resultSet.getInt("id");
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return markId;
    }

    @Override
    public String findMarkById(int markId) throws DaoException {
        String mark = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_MARK_BY_MARK_ID)) {
            statement.setInt(1, markId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                mark = resultSet.getString("mark_name");
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return mark;
    }

    @Override
    public boolean checkCarStatus(int carId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHECK_CAR_STATUS_BY_ID)) {
            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("status") == 1) {
                    return true;
                }
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return false;
    }
}
