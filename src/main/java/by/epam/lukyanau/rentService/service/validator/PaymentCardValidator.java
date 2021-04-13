package by.epam.lukyanau.rentService.service.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;

public class PaymentCardValidator {
    private static final String NUMBER_CARD_REGEX = "^[\\d]{4}\\s[\\d]{4}\\s[\\d]{4}\\s[\\d]{4}$";
    private static final String DATE_CARD_REGEX = "^\\d{2}\\/\\d{2}$";
    private static final String CV_CARD_CODE_REGEX = "^[\\d]{3}$";
    private static final String TRANSFER_AMOUNT_REGEX = "^\\d+\\.?\\d{1,2}$";
    private static final String DATE_REGEX_DELIMITER = "/";
    private static final String EMPTY_STRING = "";
    private static final double MIN_TRANSFER_AMOUNT = 100;
    private static final double MAX_TRANSFER_AMOUNT = 100000;

    private PaymentCardValidator() {
    }

    public static Map<String, String> validateParameters(String number, String date, String cvCode, String transferAmount) {
        Map<String, String> validateData = new HashMap<>();
        validateData.put(NUMBER_CARD, isNumberCardCorrect(number) ? number : EMPTY_STRING);
        validateData.put(DATE_CARD, isDateCardCorrect(date) ? date : EMPTY_STRING);
        validateData.put(CV_CODE_CARD, isCvCardCodeCorrect(cvCode) ? cvCode : EMPTY_STRING);
        validateData.put(TRANSFER_AMOUNT_CARD, isTransferAmountCorrect(transferAmount)
                ? transferAmount : EMPTY_STRING);
        return validateData;
    }

    private static boolean isNumberCardCorrect(String numberCard) {
        return isEmptyOrNull(numberCard) && isStringMatches(numberCard, NUMBER_CARD_REGEX);
    }

    private static boolean isDateCardCorrect(String dateCard) {
        if (isEmptyOrNull(dateCard) && isStringMatches(dateCard, DATE_CARD_REGEX)) {
            String[] parts = dateCard.split(DATE_REGEX_DELIMITER);
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            if (DateValidator.isMonthCorrect(month) && DateValidator.isDayCorrect(day)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCvCardCodeCorrect(String cvCardCode) {
        return isEmptyOrNull(cvCardCode) && isStringMatches(cvCardCode, CV_CARD_CODE_REGEX);
    }

    private static boolean isTransferAmountCorrect(String transferAmount) {
        if (isStringMatches(transferAmount, TRANSFER_AMOUNT_REGEX)) {
            double value = Double.parseDouble(transferAmount);
            if (MIN_TRANSFER_AMOUNT <= value && value <= MAX_TRANSFER_AMOUNT) {
                return true;
            }
        }
        return false;
    }

    public static boolean defineIncorrectValues(Map<String, String> data) {
        for (String key : data.keySet()) {
            if (data.get(key).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isEmptyOrNull(String string) {
        return string != null && !string.isEmpty();
    }

    private static boolean isStringMatches(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
