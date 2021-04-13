package by.epam.lukyanau.rentService.controller.command.impl.page;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.CarServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;


public class PassingToAdminCars implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    public CarServiceImpl carService = CarServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;
        try {
            List<Car> allCars = carService.findAllCars();
            session.setAttribute(CARS, allCars);
            router = new Router(PagePath.PASSING_ADMIN_CARS);
        } catch (ServiceException exp) {
            LOGGER.error(exp);
            router = new Router(PagePath.ERROR_500);
        }
        return router;
    }
}
