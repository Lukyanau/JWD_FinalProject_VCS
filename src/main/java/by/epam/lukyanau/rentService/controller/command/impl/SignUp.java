package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.exception.IncorrectSignInParametersException;
import by.epam.lukyanau.rentService.exception.LoginNotUniqueException;
import by.epam.lukyanau.rentService.exception.PasswordNotConfirmedException;
import by.epam.lukyanau.rentService.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignUp implements Command {
    public UserServiceImpl userService = UserServiceImpl.getInstance();

    public final static Logger LOGGER = LogManager.getLogger();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page;
        try {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String login = request.getParameter("login");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("password_confirmation");
            userService.signUpUser(name, surname, login, email, phoneNumber, password, confirmPassword);
            page = PagePath.SIGN_IN;
        } catch (ServiceException exp) {
            LOGGER.error(exp);
            page = PagePath.ERROR;
        } catch (LoginNotUniqueException | PasswordNotConfirmedException | IncorrectSignInParametersException exp) {
            page = PagePath.PASSING_REGISTRATION;
        }
        return page;
    }
}
