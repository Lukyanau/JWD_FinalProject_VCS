package by.epam.lukyanau.rentService.controller;

import by.epam.lukyanau.rentService.connection.ConnectionPool;
import by.epam.lukyanau.rentService.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandProvider.provideCommand(request.getParameter("command"));
        String page = command.execute(request,response);
        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.INSTANCE.destroyPool();
    }
}
