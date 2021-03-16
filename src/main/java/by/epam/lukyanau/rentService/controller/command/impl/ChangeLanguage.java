package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukyanau.rentService.util.RequestParameterName.*;

public class ChangeLanguage implements Command {
    private static final String EN_LANGUAGE = "en";
    private static final String RU_LANGUAGE = "ru";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession();

        String givenLanguage = request.getParameter(LANGUAGE);
        if (givenLanguage.equals(EN_LANGUAGE) || givenLanguage.equals(RU_LANGUAGE)) {
            session.setAttribute(MessageAttribute.LANGUAGE, givenLanguage);
            page = (String) session.getAttribute(CURRENT_PAGE);
        } else {
            page = PagePath.ERROR;
        }
        return page;
    }
}
