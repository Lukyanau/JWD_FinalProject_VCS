package by.epam.lukyanau.rentService.controller.command.impl.page;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class PassingToUserCatalog implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.CATALOG);
    }
}
