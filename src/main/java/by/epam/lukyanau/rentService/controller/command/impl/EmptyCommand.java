package by.epam.lukyanau.rentService.controller.command.impl;


import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;

import javax.servlet.http.HttpServletRequest;


public class EmptyCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request){
        return null;
    }
}
