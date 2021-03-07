package by.epam.lukyanau.rentService.exception;

public class IncorrectRegisterParametersException extends Exception{
    public IncorrectRegisterParametersException() {
        super();
    }

    public IncorrectRegisterParametersException(String message) {
        super(message);
    }

    public IncorrectRegisterParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectRegisterParametersException(Throwable cause) {
        super(cause);
    }
}
