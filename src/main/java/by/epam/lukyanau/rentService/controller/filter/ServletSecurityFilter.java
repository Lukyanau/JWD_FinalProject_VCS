package by.epam.lukyanau.rentService.controller.filter;

import by.epam.lukyanau.rentService.controller.command.*;
import by.epam.lukyanau.rentService.controller.command.impl.EmptyCommand;
import by.epam.lukyanau.rentService.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

import static by.epam.lukyanau.rentService.controller.command.CommandName.EMPTY_COMMAND;
import static by.epam.lukyanau.rentService.controller.command.CommandName.SIGN_IN;
import static by.epam.lukyanau.rentService.service.util.RequestParameterName.COMMAND;

@WebFilter(filterName = "ServletSecurityFilter")
public class ServletSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String commandName = request.getParameter(COMMAND);
        Command currentCommand = CommandProvider.provideCommand(commandName);
        CommandName currentCommandName = EMPTY_COMMAND;
        if (commandName == null) {
            currentCommandName = SIGN_IN;
        }
        if (currentCommand.getClass() != EmptyCommand.class) {
            currentCommandName = CommandName.valueOf(commandName.toUpperCase());
        }
        User.Role userRole = (User.Role) session.getAttribute(MessageAttribute.USER_ROLE);
        if (userRole == null) {
            userRole = User.Role.GUEST;
        }
        Set<CommandName> commandNameSet;
        switch (userRole) {
            case USER -> commandNameSet = RolePermission.USER.getCommandNames();
            case ADMIN -> commandNameSet = RolePermission.ADMIN.getCommandNames();
            default -> commandNameSet = RolePermission.GUEST.getCommandNames();
        }
        if (!commandNameSet.contains(currentCommandName)) {
            request.getRequestDispatcher(PagePath.ERROR_404).forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
