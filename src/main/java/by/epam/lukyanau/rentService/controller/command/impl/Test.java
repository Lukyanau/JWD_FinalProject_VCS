package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.exception.DAOException;
import by.epam.lukyanau.rentService.dao.impl.UserDaoImpl;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.validator.UserValidator;

public class Test {
    public static void main(String[] args) throws DAOException {
        System.out.println(!UserValidator.checkLoginInput("luki4"));
        System.out.println(!UserValidator.checkPasswordInput("111"));
    }
}
