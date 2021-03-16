package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.exception.IncorrectSignInParametersException;
import by.epam.lukyanau.rentService.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements Command {

    public UserServiceImpl userService = UserServiceImpl.getInstance();

    public final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = null;
        HttpSession session = request.getSession();
        try {
            String login = request.getParameter("username");
            String password = request.getParameter("password");
            User currentUser = userService.signInUser(login, password);
            if (currentUser != null) {
                User.Role currentRole = currentUser.getRole();
                session.setAttribute("sessionUser", currentUser);
                session.setAttribute("sessionRole", currentRole);
                page = PagePath.PASSING_HOME;
            }
        } catch (ServiceException exp) {
            LOGGER.error(exp);
            page = PagePath.ERROR;
        } catch (IncorrectSignInParametersException exp) {
            page = PagePath.SIGN_IN;
            request.setAttribute("errorMessage", "Incorrect login or password");
        }
        return page;
    }
}
