package by.epam.lukyanau.rentService.service;

import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.exception.*;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers() throws ServiceException;
    Optional<User> signInUser(String login, String password) throws ServiceException, IncorrectSignInParametersException;
    User signUpUser(String name,String surname,String login,String email,String phoneNumber,String password,
                       String confirmPassword) throws ServiceException, LoginNotUniqueException, PasswordNotConfirmedException,
            IncorrectSignInParametersException, IncorrectRegisterParametersException;
    boolean depositMoney(String login, double sum) throws ServiceException;
    boolean paymentOrdering(User user, double orderingPrice) throws ServiceException;
    Optional<User> findUserById(int id) throws ServiceException;
    List<User> sortUsers(List<User> allUsers, String sortType);
    boolean banAccount(String login) throws ServiceException, NullUserException;
    boolean unbanAccount(String login) throws ServiceException, NullUserException;
}
