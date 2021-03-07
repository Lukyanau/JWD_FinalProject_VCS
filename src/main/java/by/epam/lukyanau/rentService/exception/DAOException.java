package by.epam.lukyanau.rentService.exception;

public class DAOException extends Exception {
    private static final long serialVersionUID = 5579627996438039829L;

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
