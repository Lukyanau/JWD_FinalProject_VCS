package by.epam.lukyanau.rentService.service.exception;

public class IncorrectAddCarException extends Exception {
    public IncorrectAddCarException() {
    }

    public IncorrectAddCarException(String message) {
        super(message);
    }

    public IncorrectAddCarException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectAddCarException(Throwable cause) {
        super(cause);
    }
}
