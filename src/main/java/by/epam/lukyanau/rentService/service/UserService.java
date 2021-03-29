package by.epam.lukyanau.rentService.service;

import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.exception.*;

import java.util.List;

public interface UserService {
    List<User> findAllUsers() throws ServiceException;
    User signInUser(String login, String password) throws ServiceException, IncorrectSignInParametersException;
    User signUpUser(String name,String surname,String login,String email,String phoneNumber,String password,
                       String confirmPassword) throws ServiceException, LoginNotUniqueException, PasswordNotConfirmedException,
            IncorrectSignInParametersException, IncorrectRegisterParametersException;

}
