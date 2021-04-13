package by.epam.lukyanau.rentService.controller.command.impl;

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
import java.util.Optional;

import static by.epam.lukyanau.rentService.service.util.RequestParameterName.ORDER_ID;

public class ApproveOrder implements Command {

    private OrderServiceImpl orderService = OrderServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        try {
            String idOrder = request.getParameter(ORDER_ID);
            Optional<Order> foundOrder = orderService.findOrderById(idOrder);
            if (foundOrder.isPresent() && foundOrder.get().getStatus() == Order.Status.WAITING) {
                orderService.updateOrderStatus(idOrder, Order.Status.PAYMENT.getNameStatus());
                request.setAttribute(MessageAttribute.APPROVE_ORDER_ID, idOrder);
                router = new Router(PagePath.NOTIFICATION);
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
