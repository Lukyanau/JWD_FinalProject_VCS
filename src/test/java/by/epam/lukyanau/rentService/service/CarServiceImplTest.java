package by.epam.lukyanau.rentService.service;

import by.epam.lukyanau.rentService.dao.DaoException;
import by.epam.lukyanau.rentService.dao.impl.CarDaoImpl;
import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.service.creator.CarCreator;
import by.epam.lukyanau.rentService.service.exception.IncorrectAddCarException;
import by.epam.lukyanau.rentService.service.exception.NullCarException;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.CarServiceImpl;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class CarServiceImplTest {

    CarDaoImpl daoMock;
    CarServiceImpl carService;
    List<Car> sortedCarsModel;
    List<Car> sortedCarsPrice;
    List<Car> freeCars;
    List<Car> allCars;

    @BeforeClass
    public void beforeClass() {
        daoMock = mock(CarDaoImpl.class);
        carService = CarServiceImpl.getInstance();
        Whitebox.setInternalState(CarDaoImpl.class, "instance", daoMock);
    }

    @DataProvider(name = "currentCars")
    public Object[] createCurrentCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(22, "Black", "x6", BigDecimal.valueOf(350), "bmw"));
        cars.add(new Car(20, "Yellow", "x5", BigDecimal.valueOf(250), "bmw"));
        return new Object[]{cars};
    }

    @BeforeMethod
    public void setUpPrice() {
        sortedCarsPrice = new ArrayList<>();
        sortedCarsPrice.add(new Car(20, "Yellow", "x5", BigDecimal.valueOf(250), "bmw"));
        sortedCarsPrice.add(new Car(22, "Black", "x6", BigDecimal.valueOf(350), "bmw"));
    }

    @Test(dataProvider = "currentCars")
    public void sortByParameterShouldReturnCorrectList(List<Car> cars) {
        List<Car> currentCars = carService.sortByParameter(cars, "price");
        assertEquals(currentCars, sortedCarsPrice);
    }

    @AfterMethod
    public void tearDownPrice() {
        sortedCarsPrice = null;
    }

    @BeforeMethod
    public void setUpModel() {
        sortedCarsModel = new ArrayList<>();
        sortedCarsModel.add(new Car(22, "Black", "x6", BigDecimal.valueOf(350), "bmw"));
        sortedCarsModel.add(new Car(20, "Yellow", "x5", BigDecimal.valueOf(250), "bmw"));
    }

    @Test(dataProvider = "currentCars")
    public void sortByParameterShouldReturnIncorrectList(List<Car> cars) {
        List<Car> currentCars = carService.sortByParameter(cars, "model");
        assertNotEquals(currentCars, sortedCarsModel);
    }

    @AfterMethod
    public void tearDownModel() {
        sortedCarsModel = null;
    }

    @DataProvider(name = "deactivateCarId")
    public Object[] createDeactivateCarId() {
        return new Object[]{1, 2, 3, 4};
    }

    @Test(dataProvider = "deactivateCarId")
    public void deactivateCarShouldReturnTrue(int carId) {
        try {
            when(daoMock.deactivateCar(any(Integer.class))).thenReturn(true);
            boolean actual = carService.deactivateCar(carId);
            assertTrue(actual);
        } catch (DaoException | ServiceException | NullCarException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "deactivateCarId", expectedExceptions = NullCarException.class)
    public void deactivateCarShouldThrowNullCarException(int carId) throws DaoException, NullCarException, ServiceException {
        when(daoMock.deactivateCar(any(Integer.class))).thenReturn(false);
        carService.deactivateCar(carId);
    }

    @DataProvider(name = "activateCarId")
    public Object[] createActivateCarId() {
        return new Object[]{1, 2, 3, 4};
    }

    @Test(dataProvider = "activateCarId")
    public void activateCarShouldReturnTrue(int carId) {
        try {
            when(daoMock.activateCar(any(Integer.class))).thenReturn(true);
            boolean actual = carService.activateCar(carId);
            assertTrue(actual);
        } catch (DaoException | ServiceException | NullCarException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "activateCarId", expectedExceptions = NullCarException.class)
    public void activateCarShouldThrowNullCarException(int carId) throws DaoException, NullCarException, ServiceException {
        when(daoMock.activateCar(any(Integer.class))).thenReturn(false);
        carService.activateCar(carId);
    }

    @DataProvider(name = "deleteCarId")
    public Object[] createDeleteCarId() {
        return new Object[]{1, 2, 3, 4};
    }

    @Test(dataProvider = "deleteCarId")
    public void deleteCarShouldReturnTrue(int carId) {
        try {
            when(daoMock.deleteCar(any(Integer.class))).thenReturn(true);
            boolean actual = carService.deleteCar(carId);
            assertTrue(actual);
        } catch (DaoException | ServiceException | NullCarException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "deleteCarId", expectedExceptions = NullCarException.class)
    public void deleteCarShouldThrowNullCarException(int carId) throws DaoException, NullCarException, ServiceException {
        when(daoMock.deleteCar(any(Integer.class))).thenReturn(false);
        carService.deleteCar(carId);
    }

    @DataProvider(name = "dateFromTo")
    public Object[][] createDateFromTo() {
        return new Object[][]{
                {"bmw", "17-04-2021", "25-04-2021"},
                {"bmw", "26-04-2021", "30-04-2021"}
        };
    }

    @BeforeMethod
    public void setUpFreeCars() {
        freeCars = new ArrayList<>();
        freeCars.add(new Car(22, "Black", "x6", BigDecimal.valueOf(350), "bmw"));
        freeCars.add(new Car(20, "Yellow", "x5", BigDecimal.valueOf(250), "bmw"));
    }

    @Test(dataProvider = "dateFromTo")
    public void findFreeCarsByParametersShouldReturnCorrectList(String mark, String dateFrom, String dateTo) {
        try {
            when(daoMock.findFree(any(String.class), any(Long.class), any(Long.class))).thenReturn(freeCars);
            List<Car> currentCars = carService.findFreeCarsByParameters(mark, dateFrom, dateTo);
            assertEquals(currentCars, freeCars);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @AfterMethod
    public void tearDownFreeCars() {
        freeCars = null;
    }

    @Test(dataProvider = "dateFromTo", expectedExceptions = ServiceException.class)
    public void findFreeCarsByParametersShouldThrowException(String mark, String dateFrom, String dateTo)
            throws DaoException, ServiceException {
        when(daoMock.findFree(any(String.class), any(Long.class), any(Long.class))).thenThrow(DaoException.class);
        carService.findFreeCarsByParameters(mark, dateFrom, dateTo);
    }

    @DataProvider(name = "carIdForSearching")
    public Object[] createCarIdForSearching() {
        return new Object[]{"1", "2", "3", "4"};
    }

    @Test(dataProvider = "carIdForSearching")
    public void findByIdShouldReturnCorrectCar(String carId) {
        Optional<Car> currentCar = Optional.of
                (new Car(1, "Black", "x6", BigDecimal.valueOf(350), "bmw"));
        try {
            when(daoMock.findById(any(Integer.class))).thenReturn(currentCar);
            Optional<Car> actualCar = carService.findById(carId);
            assertEquals(actualCar, currentCar);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "carIdForSearching", expectedExceptions = ServiceException.class)
    public void findByIdShouldThrowException(String carId) throws DaoException, ServiceException {
        when(daoMock.findById(Integer.parseInt(carId))).thenThrow(DaoException.class);
        carService.findById(carId);
    }

    @DataProvider(name = "carParametersAdd")
    public Object[][] createCarParametersAdd() {
        return new Object[][]{
                {"black", "bmw", "i8", 200, "Good Car", 1},
                {"red", "mercedes", "amg", 250, "Good Car", 2},
                {"white", "ford", "mustang", 225, "Good Car", 3}
        };
    }

    @Test(dataProvider = "carParametersAdd")
    public void addCarShouldReturnCorrectCar(String color, String mark, String model, Integer price, String description, int markId) {
        CarCreator carCreator = CarCreator.getInstance();
        Car createdCar = carCreator.createCar(color, markId, model, price, description);
        try {
            when(daoMock.add(any(Car.class))).thenReturn(createdCar);
            Car currentCar = carService.addCar(color, mark, model, price, description);
            assertEquals(currentCar, createdCar);
        } catch (DaoException | IncorrectAddCarException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "carParametersAdd", expectedExceptions = ServiceException.class)
    public void addCarShouldThrowException(String color, String mark, String model, Integer price, String description, int markId)
            throws DaoException, IncorrectAddCarException, ServiceException {
        when(daoMock.add(any(Car.class))).thenThrow(DaoException.class);
        carService.addCar(color, mark, model, price, description);
    }

    @DataProvider(name = "carIncorrectParametersAdd")
    public Object[][] createCarIncorrectParametersAdd() {
        return new Object[][]{
                {"black", "bmw", "i8", 20, "Good Car", 1},
                {"red", "mercedes", "amg", 25, "Good Car", 2},
                {"white", "ford", "mustang", 22, "Good Car", 3}
        };
    }

    @Test(dataProvider = "carIncorrectParametersAdd", expectedExceptions = IncorrectAddCarException.class)
    public void addCarShouldThrowIncorrectParametersException(String color, String mark, String model, Integer price,
                                                              String description, int markId)
            throws DaoException, IncorrectAddCarException, ServiceException {
        CarCreator carCreator = CarCreator.getInstance();
        Car createdCar = carCreator.createCar(color, markId, model, price, description);

        when(daoMock.add(any(Car.class))).thenReturn(createdCar);
        carService.addCar(color, mark, model, price, description);

    }

    @BeforeMethod
    public void setUpAllCars() {
        allCars = new ArrayList<>();
        allCars.add(new Car(22, "Black", "x6", BigDecimal.valueOf(350), "bmw"));
        allCars.add(new Car(20, "Yellow", "x5", BigDecimal.valueOf(250), "bmw"));
    }

    @Test
    public void findAllCarsShouldReturnCorrectList() {
        try {
            when(daoMock.findAll()).thenReturn(allCars);
            List<Car> currentCars = carService.findAllCars();
            assertEquals(currentCars, allCars);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findAllCarsShouldThrowException() throws DaoException, ServiceException {
            when(daoMock.findAll()).thenThrow(DaoException.class);
            carService.findAllCars();
    }

    @AfterMethod
    public void tearDownAllCars() {
        allCars = null;
    }


    @AfterClass
    public void afterClass() {
        daoMock = null;
        carService = null;
    }
}
