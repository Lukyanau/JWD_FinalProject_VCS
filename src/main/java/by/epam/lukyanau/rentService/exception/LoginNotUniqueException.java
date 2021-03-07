package by.epam.lukyanau.rentService.exception;

public class LoginNotUniqueException extends Exception{
    public LoginNotUniqueException() {
        super();
    }

    public LoginNotUniqueException(String message) {
        super(message);
    }

    public LoginNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginNotUniqueException(Throwable cause) {
        super(cause);
    }
}
