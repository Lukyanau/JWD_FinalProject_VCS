package by.epam.lukyanau.rentService.dao.impl;

import by.epam.lukyanau.rentService.connection.ConnectionPool;
import by.epam.lukyanau.rentService.dao.CarDAO;
import by.epam.lukyanau.rentService.dao.DAOException;
import by.epam.lukyanau.rentService.dao.SqlQuery;
import by.epam.lukyanau.rentService.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarDaoImpl implements CarDAO {
    private static final CarDaoImpl instance = new CarDaoImpl();

    private CarDaoImpl() {
    }

    public static CarDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Car add(Car car) throws DAOException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_CAR)) {
            statement.setString(1,car.getModel());
            statement.setString(2,car.getColor());
            statement.setInt(3,car.getMarkId());
            statement.setString(4,car.getDescription());
            statement.setBigDecimal(5,car.getPrice());
            statement.executeUpdate();
        } catch (SQLException exp) {
            throw new DAOException(exp);
        }
        return car;
    }

    @Override
    public Car remove(int id) throws DAOException {
        return null;
    }

    @Override
    public List<Car> findAll() throws DAOException {
        return null;
    }

    public int findMarkId(String mark) throws DAOException {
        int markId = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_MARK_ID_BY_MARK)) {
            statement.setString(1, mark);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                markId = resultSet.getInt("id");
            }
        } catch (SQLException exp) {
            throw new DAOException(exp);
        }
        return markId;
    }
}
