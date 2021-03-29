package by.epam.lukyanau.rentService.controller.filter;

import by.epam.lukyanau.rentService.controller.command.PagePath;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageRedirectSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.sendRedirect(httpRequest.getContextPath()+ PagePath.INDEX);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
