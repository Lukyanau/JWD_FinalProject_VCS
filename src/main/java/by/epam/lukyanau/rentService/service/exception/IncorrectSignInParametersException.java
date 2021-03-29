package by.epam.lukyanau.rentService.service.exception;

public class IncorrectSignInParametersException extends Exception{
    public IncorrectSignInParametersException() {
        super();
    }

    public IncorrectSignInParametersException(String message) {
        super(message);
    }

    public IncorrectSignInParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectSignInParametersException(Throwable cause) {
        super(cause);
    }
}
