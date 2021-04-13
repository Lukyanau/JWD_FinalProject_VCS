package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.controller.command.PropertiesMessageKey;
import by.epam.lukyanau.rentService.controller.command.impl.util.CommandUtil;
import by.epam.lukyanau.rentService.entity.Order;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.OrderServiceImpl;
import by.epam.lukyanau.rentService.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;

public class Payment implements Command {
    private OrderServiceImpl orderService = OrderServiceImpl.getInstance();
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;

        try {
            User currentUser = (User) session.getAttribute(MessageAttribute.USER);
            String idOrder = request.getParameter(ORDER_ID);
            Optional<Order> foundOrder = orderService.findOrderById(idOrder);
            if (foundOrder.isPresent() && foundOrder.get().getStatus() == Order.Status.PAYMENT) {
                Order order = foundOrder.get();
                double totalPriceOrder = order.getTotalPrice();
                if (userService.paymentBooking(currentUser, totalPriceOrder)) {
                    orderService.updateOrderStatus(idOrder, Order.Status.ACTIVE.getNameStatus());
                    request.setAttribute(MessageAttribute.PAYMENT_MESSAGE, order.getOrderId());
                    router = new Router(PagePath.NOTIFICATION);
                } else {
                    String locale = (String) session.getAttribute(MessageAttribute.LANGUAGE);
                    String messageWithLocale = CommandUtil.makePartWithLocale(locale, PropertiesMessageKey.PAYMENT_ERROR_MESSAGE);
                    request.setAttribute(MessageAttribute.PAYMENT_ERROR_MESSAGE, messageWithLocale);
                    router = new Router(PagePath.USER_PROFILE);
                }
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
