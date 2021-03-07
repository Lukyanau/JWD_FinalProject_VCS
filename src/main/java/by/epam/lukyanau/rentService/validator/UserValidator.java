package by.epam.lukyanau.rentService.validator;

import by.epam.lukyanau.rentService.exception.ServiceException;

import static by.epam.lukyanau.rentService.exception.ErrorCode.INCORRECT_REGISTER_PARAMETERS;

public class UserValidator {
    private UserValidator() {

    }

    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_]{3,20}$";
    private static final String PASSWORD_REGEX = "^.{3,20}$";
    private static final String NAME_REGEX = "^[a-zA-Z]{3,20}$";
    private static final String SURNAME_REGEX = "^[a-zA-Z]{3,20}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z]{3,20}$";
    private static final String PHONE_REGEX = "^(\\+375\\([\\d]{2}\\)[\\d]{3}\\-[\\d]{2}\\-[\\d]{2})$";

    public static boolean checkLoginInput(String str) {
        return isEmptyOrNull(str) && str.matches(LOGIN_REGEX);
    }

    public static boolean checkPasswordInput(String str) {
        return isEmptyOrNull(str) && str.matches(PASSWORD_REGEX);
    }

    public static boolean checkNameInput(String str) {
        return isEmptyOrNull(str) && str.matches(NAME_REGEX);
    }

    public static boolean checkSurnameInput(String str) {
        return isEmptyOrNull(str) && str.matches(SURNAME_REGEX);
    }

    public static boolean checkEmailInput(String str) {
        return isEmptyOrNull(str) && str.matches(EMAIL_REGEX);
    }

    public static boolean checkPhoneNumberInput(String str) {
        return isEmptyOrNull(str) && str.matches(PHONE_REGEX);
    }

    public static void checkSingUpParameters(String name, String surname, String login, String password,
                                             String email, String phoneNumber) throws ServiceException {
        if (!checkNameInput(name) || !checkSurnameInput(surname)
                || !checkLoginInput(login) || !checkPasswordInput(password) || !checkEmailInput(email)
                || !checkPhoneNumberInput(phoneNumber)) {
            throw new ServiceException(INCORRECT_REGISTER_PARAMETERS);
        }
    }

    private static boolean isEmptyOrNull(String str) {
        return str != null && !str.isEmpty();
    }


}
