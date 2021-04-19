package by.epam.lukyanau.rentService.validator;

import by.epam.lukyanau.rentService.service.validator.CarValidator;
import by.epam.lukyanau.rentService.service.validator.UserValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CarValidatorTest {
    @DataProvider(name = "carValidatorCorrectParameters")
    public Object[][] createUserValidatorCorrectParameters(){
        return new Object[][] {
                {"black", "i8", "200", "Good Car"},
                {"black", "amg", "250", "Best Car"},
                {"black", "mustang", "300", "Something"}
        };
    }

    @Test(dataProvider = "carValidatorCorrectParameters")
    public void checkAddCarParametersShouldReturnTrue(String color, String model, String price, String description) {
        boolean condition = CarValidator.checkAddCarParameters(color, model, price, description);
        assertTrue(condition);
    }

    @DataProvider(name = "carValidatorIncorrectParameters")
    public Object[][] createUserValidatorIncorrectParameters(){
        return new Object[][] {
                {"black", "i", "200", "Good Car"},
                {"black", "amg", "20", "Best Car"},
                {"black", "mustang", "300", ""}
        };
    }

    @Test(dataProvider = "carValidatorIncorrectParameters")
    public void checkAddCarParametersShouldReturnFalse(String color, String model, String price, String description) {
        boolean condition = CarValidator.checkAddCarParameters(color, model, price, description);
        assertFalse(condition);
    }
}
