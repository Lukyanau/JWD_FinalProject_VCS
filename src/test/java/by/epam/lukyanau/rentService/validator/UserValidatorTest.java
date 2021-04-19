package by.epam.lukyanau.rentService.validator;

import by.epam.lukyanau.rentService.service.validator.UserValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserValidatorTest {

    @DataProvider(name = "userValidatorCorrectParameters")
    public Object[][] createUserValidatorCorrectParameters(){
        return new Object[][] {
                {"Ivan", "Lukyanau", "luki4", "111","luk@mail.ru", "+375(44)759-23-96"},
                {"Alina", "Lukyanava", "buba", "111","chuk@mail.ru", "+375(44)666-23-96"},
                {"Anton", "Zuravel", "alfa", "111","luk@mail.ru", "+375(44)555-23-96"},
        };
    }

    @Test(dataProvider = "userValidatorCorrectParameters")
    public void checkSingUpParametersShouldReturnTrue(String name, String surname, String login, String password,
                                                      String email, String phoneNumber) {
        boolean condition = UserValidator.checkSingUpParameters(name, surname, login, password, email, phoneNumber);
        assertTrue(condition);
    }

    @DataProvider(name = "userValidatorIncorrectParameters")
    public Object[][] createUserValidatorIncorrectParameters(){
        return new Object[][] {
                {"Ivan", "Lukyanau", "luki4", "111","mail.ru", "+375(44)759-23-96"},
                {"", "Lukyanava", "buba", "111","chuk@mail.ru", "+375(44)666-23-96"},
                {"Anton", "Zuravel", "alfa", "111","luk@mail.ru", "+375(44)555"},
        };
    }

    @Test(dataProvider = "userValidatorIncorrectParameters")
    public void checkSingUpParametersShouldReturnFalse(String name, String surname, String login, String password,
                                                      String email, String phoneNumber) {
        boolean condition = UserValidator.checkSingUpParameters(name, surname, login, password, email, phoneNumber);
        assertFalse(condition);
    }
}
