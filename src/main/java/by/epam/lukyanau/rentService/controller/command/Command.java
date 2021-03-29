package by.epam.lukyanau.rentService.controller.command;

import by.epam.lukyanau.rentService.controller.Router;


import javax.servlet.http.HttpServletRequest;


import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;

public interface Command {

    /**
     * The constant REDIRECT_QUESTION_MARK.
     */
    String REDIRECT_QUESTION_MARK = "?";
    /**
     * The constant REDIRECT_EQUAL_SIGN.
     */
    String REDIRECT_EQUAL_SIGN = "=";

    /**
     * Execute router.
     *
     * @param request the request
     * @return the router
     */
    Router execute(HttpServletRequest request);

    /**
     * Create redirect url string.
     *
     * @param request     the request
     * @param commandName the command name
     * @return the string
     */
    default String createRedirectURL(HttpServletRequest request, String commandName) {
        String redirectUrl = request.getContextPath() + request.getServletPath()
                + REDIRECT_QUESTION_MARK + COMMAND + REDIRECT_EQUAL_SIGN + commandName;
        return redirectUrl;
    }
}
