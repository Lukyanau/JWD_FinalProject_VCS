package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.CommandName;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.exception.IncorrectSignInParametersException;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.UserServiceImpl;
import by.epam.lukyanau.rentService.service.util.RequestParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SignIn implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    public UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        try {
            String login = request.getParameter(RequestParameterName.USERNAME);
            String password = request.getParameter(RequestParameterName.PASSWORD);

            User currentUser = userService.signInUser(login, password);
            if (currentUser != null) {
                User.Role currentRole = currentUser.getRole();
                session.setAttribute(MessageAttribute.USER, currentUser);
                session.setAttribute(MessageAttribute.USER_ROLE, currentRole);
                String redirectURL = createRedirectURL(request, CommandName.PASSING_HOME.toString().toLowerCase());
                router.setRedirect();
                router.setCurrentPage(redirectURL);
            }
        } catch (ServiceException exp) {
            LOGGER.error(exp);
            router = new Router(PagePath.ERROR_500);
        } catch (IncorrectSignInParametersException exp) {
            router = new Router(PagePath.SIGN_IN);
            request.setAttribute(MessageAttribute.ERROR_MESSAGE, exp.getMessage());
        }
        return router;
    }
}
