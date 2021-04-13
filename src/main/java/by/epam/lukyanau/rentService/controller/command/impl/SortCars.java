package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.service.impl.CarServiceImpl;
import by.epam.lukyanau.rentService.service.util.RequestParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;

public class SortCars implements Command {

    CarServiceImpl carService = CarServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;


        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        String sortType = request.getParameter(RequestParameterName.CAR_SORT_TYPE);
        List<Car> allCars;
        String attributeName;
        if (currentPage.equals(PagePath.CATALOG)) {
            allCars = (List<Car>) session.getAttribute(PAGINATION_CARS);
            attributeName = PAGINATION_CARS;
        } else {
            allCars = (List<Car>) session.getAttribute(CARS);
            attributeName = CARS;
        }
        List<Car> sortedCars = carService.sortByParameter(allCars, sortType);
        session.setAttribute(attributeName, sortedCars);
        router = new Router(currentPage);
        return router;

    }
}
