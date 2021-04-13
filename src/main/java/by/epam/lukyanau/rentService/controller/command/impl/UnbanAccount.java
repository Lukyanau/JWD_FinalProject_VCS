package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.service.exception.NullUserException;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.UserServiceImpl;
import by.epam.lukyanau.rentService.service.util.RequestParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UnbanAccount implements Command {
    UserServiceImpl userService = UserServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String login = request.getParameter(RequestParameterName.LOGIN);
        try {
            userService.unbanAccount(login);
            request.setAttribute(MessageAttribute.UNBAN_ACCOUNT, login);
            router = new Router(PagePath.NOTIFICATION);
        } catch (ServiceException | NullUserException exp) {
            LOGGER.error(exp);
            router = new Router(PagePath.ERROR_500);
        }
        return router;
    }
}
