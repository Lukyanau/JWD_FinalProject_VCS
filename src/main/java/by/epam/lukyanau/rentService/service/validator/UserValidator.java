package by.epam.lukyanau.rentService.service.validator;

public class UserValidator {
    private UserValidator() {

    }

    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_]{3,20}$";
    private static final String PASSWORD_REGEX = "^.{3,20}$";
    private static final String NAME_REGEX = "^[a-zA-Z]{3,20}$";
    private static final String SURNAME_REGEX = "^[a-zA-Z]{3,20}$";
    private static final String EMAIL_REGEX = "^[a-zA-z0-9_.-]{1,35}@[a-zA-z0-9_-]{2,15}\\.[a-z]{2,5}$";
    private static final String PHONE_REGEX = "^(\\+375\\([\\d]{2}\\)[\\d]{3}\\-[\\d]{2}\\-[\\d]{2})$";


    public static boolean checkSingUpParameters(String name, String surname, String login, String password,
                                                String email, String phoneNumber) {
        return checkNameInput(name) && checkSurnameInput(surname)
                && checkLoginInput(login) && checkPasswordInput(password) && checkEmailInput(email)
                && checkPhoneNumberInput(phoneNumber);
    }


    private static boolean checkLoginInput(String str) {
        return isEmptyOrNull(str) && str.matches(LOGIN_REGEX);
    }

    private static boolean checkPasswordInput(String str) {
        return isEmptyOrNull(str) && str.matches(PASSWORD_REGEX);
    }

    private static boolean checkNameInput(String str) {
        return isEmptyOrNull(str) && str.matches(NAME_REGEX);
    }

    private static boolean checkSurnameInput(String str) {
        return isEmptyOrNull(str) && str.matches(SURNAME_REGEX);
    }

    private static boolean checkEmailInput(String str) {
        return isEmptyOrNull(str) && str.matches(EMAIL_REGEX);
    }

    private static boolean checkPhoneNumberInput(String str) {
        return isEmptyOrNull(str) && str.matches(PHONE_REGEX);
    }

    private static boolean isEmptyOrNull(String str) {
        return str != null && !str.isEmpty();
    }
}
