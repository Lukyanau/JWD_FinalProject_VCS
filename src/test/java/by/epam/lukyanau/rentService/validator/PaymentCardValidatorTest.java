package by.epam.lukyanau.rentService.validator;

import by.epam.lukyanau.rentService.service.validator.PaymentCardValidator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;
import static org.testng.Assert.*;
import static org.testng.Assert.assertFalse;

public class PaymentCardValidatorTest {
    private Map<String, String>[] correctMaps;
    private Map<String, String>[] incorrectMaps;

    @BeforeClass
    public void setUp() {
        correctMaps = new Map[3];
        Map<String, String> correctMapFirst = new HashMap<>();
        correctMapFirst.put(NUMBER_CARD, "1111 1111 2222 2222");
        correctMapFirst.put(DATE_CARD, "11/22");
        correctMapFirst.put(CV_CODE_CARD, "333");
        correctMapFirst.put(TRANSFER_AMOUNT_CARD, "3000");

        Map<String, String> correctMapSecond = new HashMap<>();
        correctMapSecond.put(NUMBER_CARD, "1234 5678 9012 3456");
        correctMapSecond.put(DATE_CARD, "01/11");
        correctMapSecond.put(CV_CODE_CARD, "543");
        correctMapSecond.put(TRANSFER_AMOUNT_CARD, "300.20");

        Map<String, String> correctMapThird = new HashMap<>();
        correctMapThird.put(NUMBER_CARD, "5463 3453 2357 2221");
        correctMapThird.put(DATE_CARD, "07/02");
        correctMapThird.put(CV_CODE_CARD, "535");
        correctMapThird.put(TRANSFER_AMOUNT_CARD, "12345.0");

        correctMaps[0] = correctMapFirst;
        correctMaps[1] = correctMapSecond;
        correctMaps[2] = correctMapThird;

        incorrectMaps = new Map[3];
        Map<String, String> incorrectMapFirst = new HashMap<>();
        incorrectMapFirst.put(NUMBER_CARD, "111 1111 2222 2222");
        incorrectMapFirst.put(DATE_CARD, "11/22");
        incorrectMapFirst.put(CV_CODE_CARD, "33");
        incorrectMapFirst.put(TRANSFER_AMOUNT_CARD, "");

        Map<String, String> incorrectMapSecond = new HashMap<>();
        incorrectMapSecond.put(NUMBER_CARD, "1234 5678 9012 3456");
        incorrectMapSecond.put(DATE_CARD, "");
        incorrectMapSecond.put(CV_CODE_CARD, "543");
        incorrectMapSecond.put(TRANSFER_AMOUNT_CARD, "300f.20");

        Map<String, String> incorrectMapThird = new HashMap<>();
        incorrectMapThird.put(NUMBER_CARD, "5463 3453 2357 2221");
        incorrectMapThird.put(DATE_CARD, "07/44");
        incorrectMapThird.put(CV_CODE_CARD, "");
        incorrectMapThird.put(TRANSFER_AMOUNT_CARD, "12345.0");

        incorrectMaps[0] = incorrectMapFirst;
        incorrectMaps[1] = incorrectMapSecond;
        incorrectMaps[2] = incorrectMapThird;
    }

    @DataProvider(name = "correctMaps")
    public Object[] returnCorrectMap() {
        return new Object[]{
                correctMaps[0],
                correctMaps[1],
                correctMaps[2],
        };
    }

    @DataProvider(name = "incorrectMaps")
    public Object[] returnIncorrectMap() {
        return new Object[]{
                incorrectMaps[0],
                incorrectMaps[1],
                incorrectMaps[2],
        };
    }

    @DataProvider(name = "correctDataToValidateParameters")
    public Object[][] returnCorrectDataToValidateParameters() {
        return new Object[][]{
                {correctMaps[0], "1111 1111 2222 2222", "11/22", "333", "3000"},
                {correctMaps[1], "1234 5678 9012 3456", "01/11", "543", "300.20"},
                {correctMaps[2], "5463 3453 2357 2221", "07/02", "535", "12345.0"},
        };
    }

    @DataProvider(name = "incorrectDataToValidateParameters")
    public Object[][] returnIncorrectDataToValidateParameters() {
        return new Object[][]{
                {incorrectMaps[0], "111 1111 2222 2222", "11/22", "33", "3000f"},
                {incorrectMaps[1], "1234 5678 9012 3456", "01/11", "543", "300f.20"},
                {incorrectMaps[2], "5463 3453 2357 2221", "07/44", "535", "12345.0"},
        };
    }

    @Test(dataProvider = "correctDataToValidateParameters")
    public void validateParametersCorrectMapsEquals(Map<String, String> correctMap, String number,
                                                    String date, String cvCode, String transferAmount) {
        Map<String, String> actualMap = PaymentCardValidator.validateParameters(number, date, cvCode, transferAmount);
        assertEquals(correctMap, actualMap);
    }

    @Test(dataProvider = "incorrectDataToValidateParameters")
    public void validateParametersIncorrectMapsNotEquals(Map<String, String> correctMap, String number,
                                                         String date, String cvCode, String transferAmount) {
        Map<String, String> actualMap = PaymentCardValidator.validateParameters(number, date, cvCode, transferAmount);
        assertNotEquals(correctMap, actualMap);
    }

    @Test(dataProvider = "correctMaps")
    public void defineIncorrectValuesReturnTrue(Map<String, String> currentMap) {
        boolean condition = PaymentCardValidator.defineIncorrectValues(currentMap);
        assertTrue(condition);

    }

    @Test(dataProvider = "incorrectMaps")
    public void defineIncorrectValuesReturnFalse(Map<String, String> currentMap) {
        boolean condition = PaymentCardValidator.defineIncorrectValues(currentMap);
        assertFalse(condition);
    }

    @AfterClass
    public void tierDown() {
        correctMaps = null;
        incorrectMaps = null;
    }
}
