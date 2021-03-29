package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.service.exception.IncorrectAddCarException;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.CarServiceImpl;
import by.epam.lukyanau.rentService.service.util.RequestParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddCar implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    public CarServiceImpl carService = CarServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String carColor = request.getParameter(RequestParameterName.CAR_COLOR);
        String carMark = request.getParameter(RequestParameterName.CAR_MARK);
        String carModel = request.getParameter(RequestParameterName.CAR_MODEL);
        Integer carPrice = Integer.parseInt(request.getParameter(RequestParameterName.CAR_PRICE));
        String carDescription = request.getParameter(RequestParameterName.CAR_DESCRIPTION);
        try {
            carService.addCar(carColor, carMark, carModel, carPrice, carDescription);
            request.setAttribute(MessageAttribute.ADD_CAR, carMark + " " + carModel);
            router = new Router(PagePath.NOTIFICATION);
        } catch (IncorrectAddCarException exp) {
            router = new Router(PagePath.PASSING_ADMIN_CAR);
            request.setAttribute(MessageAttribute.ERROR_MESSAGE, exp.getMessage());
        } catch (ServiceException exp) {
            LOGGER.error(exp);
            router = new Router(PagePath.ERROR_500);
        }
        return router;
    }
}
