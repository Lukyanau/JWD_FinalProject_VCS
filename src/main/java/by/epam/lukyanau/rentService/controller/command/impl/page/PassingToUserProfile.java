package by.epam.lukyanau.rentService.controller.command.impl.page;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.dao.impl.OrderDaoImpl;
import by.epam.lukyanau.rentService.entity.Order;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.OrderServiceImpl;
import by.epam.lukyanau.rentService.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;

public class PassingToUserProfile implements Command {
    private OrderServiceImpl orderService = OrderServiceImpl.getInstance();
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;
        try {
            User currentUser = (User) session.getAttribute(MessageAttribute.USER);
            int userId = currentUser.getUserId();
            Optional<User> updatedUserOptional = userService.findUserById(userId);
            if (updatedUserOptional.isPresent()) {
                User updatedUser = updatedUserOptional.get();
                session.setAttribute(MessageAttribute.USER, updatedUser);
                List<Order> personalOrders = orderService.findOrdersByUserLogin(updatedUser.getLogin());
                session.setAttribute(MessageAttribute.PERSONAL_ORDERS, personalOrders);
                session.setAttribute(LOGIN, updatedUser.getLogin());
                session.setAttribute(BALANCE, updatedUser.getBalance());
                router = new Router(PagePath.USER_PROFILE);
            } else {
                router = new Router(PagePath.ERROR_404);
            }
        } catch (ServiceException exp) {
            LOGGER.error(exp);
            router = new Router(PagePath.ERROR_500);
        }
        return router;
    }
}
