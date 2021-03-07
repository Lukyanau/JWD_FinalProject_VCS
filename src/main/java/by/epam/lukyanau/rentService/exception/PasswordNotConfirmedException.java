package by.epam.lukyanau.rentService.exception;

public class PasswordNotConfirmedException extends Exception{
    public PasswordNotConfirmedException() {
        super();
    }

    public PasswordNotConfirmedException(String message) {
        super(message);
    }

    public PasswordNotConfirmedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotConfirmedException(Throwable cause) {
        super(cause);
    }
}
