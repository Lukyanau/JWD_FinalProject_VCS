package by.epam.lukyanau.rentService.service.validator;

import by.epam.lukyanau.rentService.service.util.DateUtil;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Date validator.
 */
public class DateValidator {
    private static final int DATE_YEAR_INDEX = 0;
    private static final int DATE_MONTH_INDEX = 1;
    private static final int DATE_DAY_INDEX = 2;
    private static final String DATE_REGEX = "^\\d{4}\\-\\d{2}\\-\\d{2}$";
    private static final String DATE_REGEX_DELIMITER = "-";
    private static final int LOW_BORDER_MONTH_AND_DAY = 1;
    private static final int LOW_BORDER_YEAR = 2020;
    private static final int HIGH_BORDER_YEAR = 2050;
    private static final int HIGH_BORDER_MONTH = 12;
    private static final int HIGH_BORDER_DAY = 31;

    private DateValidator() {
    }

    public static boolean checkDate(String arrivalDate, String departureDate) {
        boolean isCorrect = false;
        Date currentDay = DateUtil.takeCurrentDateFormat();
        if (isEmptyOrNull(arrivalDate) && isEmptyOrNull(departureDate) &&
                isStringMatches(arrivalDate, DATE_REGEX) && isStringMatches(departureDate, DATE_REGEX)) {
            if (validateDateValues(arrivalDate) && validateDateValues(departureDate)) {
                Optional<Date> arrivalDateFormatOptional = DateUtil.parseStringToDateFormat(arrivalDate);
                Optional<Date> departureDateFormatOptional = DateUtil.parseStringToDateFormat(departureDate);
                if (arrivalDateFormatOptional.isPresent() && departureDateFormatOptional.isPresent()) {
                    Date arrivalDateFormat = arrivalDateFormatOptional.get();
                    Date departureDateFormat = departureDateFormatOptional.get();
                    if (compareDates(currentDay, arrivalDateFormat, departureDateFormat)) {
                        isCorrect = true;
                    }
                }
            }
        }
        return isCorrect;
    }

    private static boolean compareDates(Date currentDate, Date arrivalDate, Date departureDate) {
        boolean isCorrect = false;
        if ((currentDate.before(arrivalDate) || currentDate.equals(arrivalDate))
                && currentDate.before(departureDate) && arrivalDate.before(departureDate)) {
            isCorrect = true;
        }
        return isCorrect;
    }

    private static boolean validateDateValues(String date) {
        String[] partsDate = date.split(DATE_REGEX_DELIMITER);
        int year = Integer.parseInt(partsDate[DATE_YEAR_INDEX]);
        int month = Integer.parseInt(partsDate[DATE_MONTH_INDEX]);
        int day = Integer.parseInt(partsDate[DATE_DAY_INDEX]);

        return isYearCorrect(year) && isMonthCorrect(month) && isDayCorrect(day);
    }

    public static boolean isYearCorrect(int year) {
        return LOW_BORDER_YEAR <= year && year <= HIGH_BORDER_YEAR;
    }

    public static boolean isMonthCorrect(int month) {
        return LOW_BORDER_MONTH_AND_DAY <= month && month <= HIGH_BORDER_MONTH;
    }

    public static boolean isDayCorrect(int day) {
        return LOW_BORDER_MONTH_AND_DAY <= day && day <= HIGH_BORDER_DAY;
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
