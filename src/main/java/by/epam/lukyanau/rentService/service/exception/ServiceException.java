package by.epam.lukyanau.rentService.service.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = -2795804103099775537L;

    private String errorCode;

    public ServiceException() {
    }

    public ServiceException(String errorCode) {
        this.errorCode = errorCode;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
