package by.epam.lukyanau.rentService.controller;

import by.epam.lukyanau.rentService.connection.ConnectionPool;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;

@WebServlet("/controller")
public class ServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;//yagni - you ain't gonna need it

    public ServletController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandProvider.provideCommand(request.getParameter("command"));

        Router router = command.execute(request);
        String currentPage = router.getCurrentPage();
        HttpSession session = request.getSession();

        session.setAttribute(CURRENT_PAGE, currentPage);

        if (router.getCurrentType().equals(Router.Type.FORWARD)) {
            request.getRequestDispatcher(currentPage).forward(request, response);
        } else {
            response.sendRedirect(currentPage);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.INSTANCE.destroyPool();
    }
}
