package by.epam.lukyanau.rentService.controller.command.impl.page;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.entity.Order;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PassingToAdminOrders implements Command {
    private OrderServiceImpl orderService = OrderServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;

        try {
            Order.Status status = Order.Status.WAITING;
            List<Order> waitingOrders = orderService.findOrdersByStatus(status.getNameStatus());
            session.setAttribute(MessageAttribute.ORDERS, waitingOrders);
            router = new Router(PagePath.PASSING_ADMIN_ORDERS);
        } catch (ServiceException exp) {
            LOGGER.error(exp);
            router = new Router(PagePath.ERROR_500);
        }
        return router;
    }
}
