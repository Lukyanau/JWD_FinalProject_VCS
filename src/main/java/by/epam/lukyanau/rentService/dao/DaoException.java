package by.epam.lukyanau.rentService.dao;

public class DaoException extends Exception {
    private static final long serialVersionUID = 5579627996438039829L;

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
