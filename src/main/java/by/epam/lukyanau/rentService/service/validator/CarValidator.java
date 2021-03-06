package by.epam.lukyanau.rentService.service.validator;

public class CarValidator {
    private static final String COLOR_REGEX = "^\\w{3,15}$";
    private static final String MODEL_REGEX = "^[\\w\\d]{2,15}";
    private static final String PRICE_REGEX = "^\\d+\\.?\\d+$";
    private static final String DESCRIPTION_REGEX = "^[\\w ]{5,25}$";
    private static final int MIN_CAR_PRICE = 100;
    private static final int MAX_CAR_PRICE = 1000;

    public static boolean checkAddCarParameters(String color, String model, String price, String description) {
        return checkColor(color) && checkModel(model) && checkPrice(price) && checkDescription(description);
    }

    private static boolean checkColor(String color) {
        return isEmptyOrNull(color) && color.matches(COLOR_REGEX);
    }

    private static boolean checkModel(String model) {
        return isEmptyOrNull(model) && model.matches(MODEL_REGEX);
    }

    private static boolean checkPrice(String price) {
        if (isEmptyOrNull(price) && price.matches(PRICE_REGEX)) {
            int currentPrice = Integer.parseInt(price);
            if (MIN_CAR_PRICE < currentPrice && currentPrice < MAX_CAR_PRICE) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDescription(String description) {
        return isEmptyOrNull(description) && description.matches(DESCRIPTION_REGEX);
    }

    private static boolean isEmptyOrNull(String str) {
        return str != null && !str.isEmpty();
    }
}
