package by.epam.lukyanau.rentService.service.impl;

import by.epam.lukyanau.rentService.creator.UserCreator;
import by.epam.lukyanau.rentService.exception.*;
import by.epam.lukyanau.rentService.dao.impl.UserDaoImpl;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.UserService;
import by.epam.lukyanau.rentService.validator.UserValidator;

import static by.epam.lukyanau.rentService.exception.ErrorCode.LOGIN_NOT_UNIQUE;
import static by.epam.lukyanau.rentService.exception.ErrorCode.PASSWORD_NOT_SAME;

public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();

    private final UserDaoImpl userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public User signInUser(String login, String password) throws ServiceException, IncorrectSignInParametersException {
        try {
            User signInUser = userDao.findByLogin(login);
            String userPassword = userDao.findPasswordByLogin(login);
            if (password.equals(userPassword)){
                return signInUser;
            }else {
                return null;
            }
        } catch (DAOException exp) {
            throw new ServiceException(exp);
        }
    }

//    @Override
//    public boolean signUpUser(String name, String surname, String login, String email,
//                              String phoneNumber, String password, String confirmPassword) throws ServiceException {
//        boolean isAdded = false;
//        UserCreator userCreator = UserCreator.getInstance();
//        try {
//            if (UserValidator.checkSingUpParameters(name, surname, login, password, email, phoneNumber) &&
//                    checkLoginUnique(login) && verifyPassword(password, confirmPassword)) {
//                User registeredUser = userCreator.createUser(name, surname, login, password, email, phoneNumber, 2);
//                isAdded = userDao.add(registeredUser);
//            }else{
//                throw new ServiceException("Invalid inputs");
//            }
//        } catch (DAOException exp) {
//            throw new ServiceException(exp);
//        }
//        return isAdded;
//    }

    @Override
    public User signUpUser(String name, String surname, String login, String email,
                           String phoneNumber, String password, String confirmPassword) throws ServiceException, LoginNotUniqueException, PasswordNotConfirmedException {
        UserCreator userCreator = UserCreator.getInstance();
        try {
            UserValidator.checkSingUpParameters(name, surname, login, password, email, phoneNumber);
            checkLoginUnique(login);
            verifyPassword(password, confirmPassword);
            User createdUser = userCreator.createUser(name, surname, login, email, phoneNumber, User.Role.USER.getRoleId());
            User registeredUser = userDao.add(createdUser);
            userDao.updatePasswordByLogin(login, password);
            return registeredUser;
        } catch (DAOException exp) {
            throw new ServiceException(exp);
        }

    }


    private void verifyPassword(String password, String confirmPassword) throws PasswordNotConfirmedException {
        if (!password.equals(confirmPassword)) {
            throw new PasswordNotConfirmedException("Password mismatch.");
        }
    }

    private void checkLoginUnique(String login) throws LoginNotUniqueException, ServiceException {
        try {
            User foundUser = userDao.findByLogin(login);
            if (foundUser != null) {
                throw new LoginNotUniqueException("Login is already in use.");
            }
        } catch (DAOException exp) {
            throw new ServiceException(exp);
        }
    }

    public void checkLogin(String login) throws ServiceException {
        try {
            if (userDao.findByLogin(login) != null) {

            }
        } catch (DAOException exp) {
            throw new ServiceException(exp);
        }
    }
}
