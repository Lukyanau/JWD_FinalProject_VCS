package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.service.exception.*;
import by.epam.lukyanau.rentService.service.impl.UserServiceImpl;
import by.epam.lukyanau.rentService.service.util.RequestParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignUp implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    public UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            String name = request.getParameter(RequestParameterName.NAME);
            String surname = request.getParameter(RequestParameterName.SURNAME);
            String login = request.getParameter(RequestParameterName.LOGIN);
            String email = request.getParameter(RequestParameterName.EMAIL);
            String phoneNumber = request.getParameter(RequestParameterName.PHONE_NUMBER);
            String password = request.getParameter(RequestParameterName.PASSWORD);
            String confirmPassword = request.getParameter(RequestParameterName.PASSWORD_CONFIRMATION);
            userService.signUpUser(name, surname, login, email, phoneNumber, password, confirmPassword);
            router = new Router(PagePath.SIGN_IN);
        } catch (ServiceException exp) {
            LOGGER.error(exp);
            router = new Router(PagePath.ERROR_500);
        } catch (LoginNotUniqueException | PasswordNotConfirmedException | IncorrectRegisterParametersException exp) {
            router = new Router(PagePath.PASSING_REGISTRATION);
            request.setAttribute(MessageAttribute.ERROR_MESSAGE, exp.getMessage());
        }
        return router;
    }
}
