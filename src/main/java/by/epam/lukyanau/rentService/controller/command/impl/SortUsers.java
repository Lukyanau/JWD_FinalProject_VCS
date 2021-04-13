package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.impl.UserServiceImpl;
import by.epam.lukyanau.rentService.service.util.RequestParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SortUsers implements Command {
    UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;

        String sortType = request.getParameter(RequestParameterName.USER_SORT_TYPE);
        List<User> allUsers = (List<User>) session.getAttribute(MessageAttribute.ALL_USERS);
        List<User> sortedUsers = userService.sortUsers(allUsers, sortType);
        session.setAttribute(MessageAttribute.ALL_USERS, sortedUsers);
        router = new Router(PagePath.PASSING_ADMIN_USERS);
        return router;
    }
}
