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
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;

public class SortOrders implements Command {
    private OrderServiceImpl orderService = OrderServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();

        try {
            String sortType = request.getParameter(ORDER_SORT_TYPE);
            List<Order> currentPersonalOrders = (List<Order>) session.getAttribute(MessageAttribute.PERSONAL_ORDERS);
            List<Order> newSortedPersonalOrders = orderService.sortByParameter(currentPersonalOrders, sortType);
            session.setAttribute(MessageAttribute.PERSONAL_ORDERS, newSortedPersonalOrders);
            router = new Router(PagePath.USER_PROFILE);

        } catch (ServiceException exp) {
            LOGGER.error(exp);
            router = new Router(PagePath.ERROR_500);
        }
        return router;
    }
}

