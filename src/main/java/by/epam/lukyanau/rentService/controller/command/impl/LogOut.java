package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LogOut implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new Router(PagePath.SIGN_IN);
    }
}
