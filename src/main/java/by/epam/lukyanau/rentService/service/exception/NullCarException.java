package by.epam.lukyanau.rentService.service.exception;

public class NullCarException extends Exception{
    public NullCarException() {
    }

    public NullCarException(String message) {
        super(message);
    }

    public NullCarException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullCarException(Throwable cause) {
        super(cause);
    }
}
