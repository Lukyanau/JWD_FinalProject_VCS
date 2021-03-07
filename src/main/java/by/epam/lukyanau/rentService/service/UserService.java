package by.epam.lukyanau.rentService.service;

import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.exception.IncorrectSignInParametersException;
import by.epam.lukyanau.rentService.exception.LoginNotUniqueException;
import by.epam.lukyanau.rentService.exception.PasswordNotConfirmedException;
import by.epam.lukyanau.rentService.exception.ServiceException;

public interface UserService {
    User signInUser(String login, String password) throws ServiceException, IncorrectSignInParametersException;
    User signUpUser(String name,String surname,String login,String email,String phoneNumber,String password,
                       String confirmPassword) throws ServiceException, LoginNotUniqueException, PasswordNotConfirmedException;

}
