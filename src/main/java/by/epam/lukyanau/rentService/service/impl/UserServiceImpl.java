package by.epam.lukyanau.rentService.service.impl;

import by.epam.lukyanau.rentService.dao.DAOException;
import by.epam.lukyanau.rentService.service.creator.UserCreator;
import by.epam.lukyanau.rentService.service.exception.*;
import by.epam.lukyanau.rentService.dao.impl.UserDaoImpl;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.UserService;
import by.epam.lukyanau.rentService.service.validator.UserValidator;

import java.util.List;


public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        List<User> allUsers;
        try {
             allUsers = userDao.findAll();
        } catch (DAOException exp) {
            throw new ServiceException(exp);
        }
        return allUsers;
    }

    @Override
    public User signInUser(String login, String password) throws ServiceException, IncorrectSignInParametersException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            User signInUser = userDao.findByLogin(login);
            String userPassword = userDao.findPasswordByLogin(login);
            if (password.equals(userPassword)) {
                checkAccount(signInUser);
                return signInUser;
            } else {
                throw new IncorrectSignInParametersException("Incorrect sign in parameters");
            }
        } catch (DAOException exp) {
            throw new ServiceException(exp);
        }
    }


    @Override
    public User signUpUser(String name, String surname, String login, String email,
                           String phoneNumber, String password, String confirmPassword) throws ServiceException,
            LoginNotUniqueException, PasswordNotConfirmedException, IncorrectRegisterParametersException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        UserCreator userCreator = UserCreator.getInstance();
        try {
            if (!UserValidator.checkSingUpParameters(name, surname, login, password, email, phoneNumber)) {
                throw new IncorrectRegisterParametersException("Incorrect SignUp Parameters");
            }
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

    public boolean banAccount(String login) throws ServiceException, NullUserException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            boolean isBanned = userDao.banAccount(login);
            if (!isBanned){
                throw new NullUserException("There isn't user with this login");
            }
            return true;
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
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            User foundUser = userDao.findByLogin(login);
            if (foundUser != null) {
                throw new LoginNotUniqueException("Login is already in use.");
            }
        } catch (DAOException exp) {
            throw new ServiceException(exp);
        }
    }


    private void checkAccount(User user) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            if (user.getRole().getRoleId() == 2) {
                userDao.checkAccount(user);
            }
        } catch (DAOException exp) {
            throw new ServiceException(exp);
        }
    }
}
