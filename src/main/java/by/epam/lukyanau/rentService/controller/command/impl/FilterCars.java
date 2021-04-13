package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.controller.command.PropertiesMessageKey;
import by.epam.lukyanau.rentService.controller.command.impl.util.CommandUtil;
import by.epam.lukyanau.rentService.entity.Car;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.CarServiceImpl;
import by.epam.lukyanau.rentService.service.util.PaginationUtil;
import by.epam.lukyanau.rentService.service.validator.DateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;

public class FilterCars implements Command {
    private CarServiceImpl carService = CarServiceImpl.getInstance();
    private static final int DEFAULT_SPLIT_NUMBER = 10;
    private static final int DEFAULT_PAGE_NUMBER = 1;

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String currentPageList = request.getParameter(CAR_LIST_PAGE);
        if (currentPageList == null) {
            try {
                String carMark = request.getParameter(CAR_MARK);
                String dateFrom = request.getParameter(CAR_DATE_FROM);
                String dateTo = request.getParameter(CAR_DATE_TO);

                if (DateValidator.checkDate(dateFrom, dateTo)) {
                    List<Car> foundCars = carService.findFreeCarsByParameters(carMark, dateFrom, dateTo);
                    if(foundCars.size() == 0){
                        String locale = (String) session.getAttribute(MessageAttribute.LANGUAGE);
                        String messageWithLocale = CommandUtil.makePartWithLocale(locale, PropertiesMessageKey.NO_SUCH_CARS);
                        request.setAttribute(MessageAttribute.FILTER_CARS_ERROR_MESSAGE, messageWithLocale);
                        return new Router(PagePath.CATALOG);
                    }
                    int maxQuantityPagesOfList = PaginationUtil.calculateTotalPages(foundCars, DEFAULT_SPLIT_NUMBER);
                    session.setAttribute(MessageAttribute.MAX_QUANTITY_PAGES, maxQuantityPagesOfList);
                    List<Car> definedCrossSectionListByPage = PaginationUtil.takeSplitListByPage(foundCars,
                            DEFAULT_SPLIT_NUMBER, DEFAULT_PAGE_NUMBER);
                    session.setAttribute(CARS, foundCars);
                    session.setAttribute(PAGINATION_CARS, definedCrossSectionListByPage);
                    session.setAttribute(CAR_DATE_FROM, dateFrom);
                    session.setAttribute(CAR_DATE_TO, dateTo);

                } else {
                    String locale = (String) session.getAttribute(MessageAttribute.LANGUAGE);
                    String messageWithLocale = CommandUtil.makePartWithLocale(locale, PropertiesMessageKey.FILTER_CARS_INCORRECT_DATA);
                    request.setAttribute(MessageAttribute.FILTER_CARS_ERROR_MESSAGE, messageWithLocale);
                }
                return new Router(PagePath.CATALOG);
            } catch (ServiceException exp) {
                LOGGER.error(exp);
                return new Router(PagePath.ERROR_500);
            }
        }else{
            int currentPage = Integer.parseInt(currentPageList);
            List<Car> currentFreeRoomsList = (List<Car>) session.getAttribute(CARS);
            List<Car> definedCrossSectionListByPage = PaginationUtil.takeSplitListByPage(currentFreeRoomsList,
                    DEFAULT_SPLIT_NUMBER, currentPage);
            session.setAttribute(PAGINATION_CARS, definedCrossSectionListByPage);
            return new Router(PagePath.CATALOG);
        }
    }
}
