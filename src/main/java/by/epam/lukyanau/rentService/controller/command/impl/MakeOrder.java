package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.CarServiceImpl;
import by.epam.lukyanau.rentService.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;

public class MakeOrder implements Command {
    private CarServiceImpl carService = CarServiceImpl.getInstance();
    private OrderServiceImpl orderService = OrderServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;

        try {
            String dateFrom = (String) session.getAttribute(CAR_DATE_FROM);
            String dateTo = (String) session.getAttribute(CAR_DATE_TO);
            User currentUser = (User) session.getAttribute(MessageAttribute.USER);
            String carId = request.getParameter(CAR_ID);
            Optional<Car> foundCar = carService.findById(carId);
            if (foundCar.isPresent() && foundCar.get().isStatus()) {
                Car car = foundCar.get();
                orderService.makeOrder(dateFrom, dateTo, currentUser, car);
                session.removeAttribute(CAR_DATE_FROM);
                session.removeAttribute(CAR_DATE_TO);
                session.removeAttribute(CARS);
                request.setAttribute(MessageAttribute.ORDER_CAR, car.getModel());
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
