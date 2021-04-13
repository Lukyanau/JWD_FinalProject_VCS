package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.service.exception.NullCarException;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.CarServiceImpl;
import by.epam.lukyanau.rentService.service.util.RequestParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteCar implements Command {
    CarServiceImpl carService = CarServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int carId = Integer.parseInt(request.getParameter(RequestParameterName.CAR_ID));
        try {
            carService.deleteCar(carId);
            request.setAttribute(MessageAttribute.DELETE_CAR, carId);
            router = new Router(PagePath.NOTIFICATION);
        } catch (ServiceException | NullCarException exp) {
            LOGGER.error(exp);
            router = new Router(PagePath.ERROR_500);
        }
        return router;
    }
}
