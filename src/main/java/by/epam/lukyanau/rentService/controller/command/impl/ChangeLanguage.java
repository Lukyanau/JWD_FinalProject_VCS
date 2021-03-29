package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;

public class ChangeLanguage implements Command {

    private static final String EN_LANGUAGE = "en";
    private static final String RU_LANGUAGE = "ru";

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;

        String givenLanguage = request.getParameter(LANGUAGE);

        if (givenLanguage.equals(EN_LANGUAGE) || givenLanguage.equals(RU_LANGUAGE)) {
            session.setAttribute(MessageAttribute.LANGUAGE, givenLanguage);
            String currentPage = (String) session.getAttribute(CURRENT_PAGE);
            router = new Router(currentPage);
        } else {
            router = new Router(PagePath.ERROR_500);
        }
        return router;
    }
}
