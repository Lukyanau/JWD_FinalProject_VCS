package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.service.exception.NullUserException;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BanAccount implements Command {
    UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String login = request.getParameter("login");
        try {
            userService.banAccount(login);
            request.setAttribute(MessageAttribute.BAN_ACCOUNT, login);
            router = new Router(PagePath.NOTIFICATION);
        } catch (ServiceException | NullUserException exp) {
            router = new Router(PagePath.ERROR_500);
        }
        return router;
    }
}
