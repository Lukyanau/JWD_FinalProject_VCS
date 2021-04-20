package by.epam.lukyanau.rentService.service.impl;

import by.epam.lukyanau.rentService.dao.DaoException;
import by.epam.lukyanau.rentService.service.creator.UserCreator;
import by.epam.lukyanau.rentService.service.exception.*;
import by.epam.lukyanau.rentService.dao.impl.UserDaoImpl;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.UserService;
import by.epam.lukyanau.rentService.service.util.comparator.UserLoginComparator;
import by.epam.lukyanau.rentService.service.util.comparator.UserNameComparator;
import by.epam.lukyanau.rentService.service.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
        return allUsers;
    }

    @Override
    public Optional<User> signInUser(String login, String password) throws ServiceException, IncorrectSignInParametersException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            Optional<User> signInUser = userDao.findByLogin(login);
            String userPassword = userDao.findPasswordByLogin(login);
            if (password.equals(userPassword) && signInUser.isPresent()) {
                return signInUser;
            } else {
                throw new IncorrectSignInParametersException("Incorrect sign in parameters");
            }
        } catch (DaoException exp) {
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
            Optional<User> signUnUser = userDao.findByLogin(login);
            checkAccount(signUnUser.get());
            userDao.updatePasswordByLogin(login, password);
            return registeredUser;
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }

    }

    public boolean depositMoney(String login, double sum) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean isMadeDeposit = false;
        try {
            Optional<User> userOptional = userDao.findByLogin(login);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                double currentBalance = user.getBalance().doubleValue();
                double newBalance = currentBalance + sum;
                isMadeDeposit = userDao.updateBalanceByLogin(login, newBalance);
            }
        } catch (DaoException exp) {
            throw new ServiceException("Error during deposit money", exp);
        }
        return isMadeDeposit;
    }

    public boolean paymentOrdering(User user, double orderingPrice) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        double userBalance = user.getBalance().doubleValue();
        boolean isPayment = false;
        try {
            if (Double.compare(userBalance, orderingPrice) >= 0) {
                double resultBalance = userBalance - orderingPrice;
                isPayment = userDao.updateBalanceByLogin(user.getLogin(), resultBalance);
            }
        } catch (DaoException exp) {
            throw new ServiceException("Error during payment booking", exp);
        }
        return isPayment;
    }

    public Optional<User> findUserById(int id) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findById(id);
        } catch (DaoException exp) {
            throw new ServiceException("Error during find user by id", exp);
        }
    }

    public List<User> sortUsers(List<User> allUsers, String sortType) {
        UserNameComparator nameComparator = UserNameComparator.getInstance();
        UserLoginComparator loginComparator = UserLoginComparator.getInstance();
        List<User> sortedUsers = new ArrayList<>(allUsers);
        switch (sortType) {
            case "name" -> sortedUsers.sort(nameComparator);
            case "login" -> sortedUsers.sort(loginComparator);
        }
        return sortedUsers;
    }

    public boolean banAccount(String login) throws ServiceException, NullUserException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            boolean isBanned = userDao.banAccount(login);
            if (!isBanned) {
                throw new NullUserException("There isn't user with this login");
            }
            return true;
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
    }

    public boolean unbanAccount(String login) throws ServiceException, NullUserException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            boolean isBanned = userDao.unbanAccount(login);
            if (!isBanned) {
                throw new NullUserException("There isn't user with this login");
            }
            return true;
        } catch (DaoException exp) {
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
            Optional<User> foundUser = userDao.findByLogin(login);
            if (foundUser.isPresent()) {
                throw new LoginNotUniqueException("Login is already in use.");
            }
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
    }


    private void checkAccount(User user) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            userDao.createAccount(user);
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
    }
}
