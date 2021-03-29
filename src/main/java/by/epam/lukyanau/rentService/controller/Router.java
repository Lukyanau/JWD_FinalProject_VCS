package by.epam.lukyanau.rentService.controller;

import by.epam.lukyanau.rentService.controller.command.PagePath;

public class Router {

    public enum Type {
        FORWARD,
        REDIRECT
    }

    private Type currentType = Type.FORWARD;

    private String currentPage = PagePath.SIGN_IN;

    public Router() {

    }

    public Router(String currentPage) {
        this.currentPage = currentPage;
    }

    public Router(Type currentType, String currentPage) {
        this.currentType = currentType;
        this.currentPage = currentPage;
    }

    public Type getCurrentType() {
        return currentType;
    }

    public void setRedirect() {
        this.currentType = Type.REDIRECT;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}
