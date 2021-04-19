package by.epam.lukyanau.rentService.service;

import by.epam.lukyanau.rentService.dao.DaoException;
import by.epam.lukyanau.rentService.dao.impl.UserDaoImpl;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.creator.UserCreator;
import by.epam.lukyanau.rentService.service.exception.*;
import by.epam.lukyanau.rentService.service.impl.UserServiceImpl;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class UserServiceImplTest {

    UserDaoImpl daoMock;
    UserServiceImpl userService;
    List<User> sortedUsers;

    @BeforeClass
    public void beforeClass() {
        daoMock = mock(UserDaoImpl.class);
        userService = UserServiceImpl.getInstance();
        Whitebox.setInternalState(UserDaoImpl.class, "instance", daoMock);

        sortedUsers = new ArrayList<>();
        sortedUsers.add(new User("Andrey", "Burov", "BBB", "bur@mail.ru",
                "+375(44)699-23-96", 1));
        sortedUsers.add(new User("Ivan", "Lukyanau", "AAA", "luk@mail.ru",
                "+375(44)759-23-96", 1));

    }

    @DataProvider(name = "banUnbanAccountLogin")
    public Object[] createUnbanAccountLogin() {
        return new Object[]{"luki4", "buba", "alfa"};
    }

    @Test(dataProvider = "banUnbanAccountLogin")
    public void unbanAccountShouldReturnTrue(String login) {
        try {
            when(daoMock.unbanAccount(any(String.class))).thenReturn(true);
            boolean condition = userService.unbanAccount(login);
            assertTrue(condition);
        } catch (DaoException | ServiceException | NullUserException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "banUnbanAccountLogin", expectedExceptions = NullUserException.class)
    public void unbanAccountShouldThrowException(String login) throws DaoException, ServiceException, NullUserException {
        when(daoMock.unbanAccount(any(String.class))).thenReturn(false);
        userService.unbanAccount(login);
    }

    @Test(dataProvider = "banUnbanAccountLogin")
    public void banAccountShouldReturnTrue(String login) {
        try {
            when(daoMock.banAccount(any(String.class))).thenReturn(true);
            boolean condition = userService.banAccount(login);
            assertTrue(condition);
        } catch (DaoException | ServiceException | NullUserException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "banUnbanAccountLogin", expectedExceptions = NullUserException.class)
    public void banAccountShouldThrowException(String login) throws DaoException, ServiceException, NullUserException {
        when(daoMock.banAccount(any(String.class))).thenReturn(false);
        userService.banAccount(login);
    }

    @DataProvider(name = "usersList")
    public Object[] createCorrectUsersList() {
        List<User> users = new ArrayList<>();
        users.add(new User("Ivan", "Lukyanau", "AAA", "luk@mail.ru", "+375(44)759-23-96", 1));
        users.add(new User("Andrey", "Burov", "BBB", "bur@mail.ru", "+375(44)699-23-96", 1));
        return new Object[]{users};
    }

    @Test(dataProvider = "usersList")
    public void sortUsersShouldReturnCorrectUserList(List<User> users) {
        List<User> currentUsers = userService.sortUsers(users, "name");
        assertEquals(currentUsers, sortedUsers);
    }

    @Test(dataProvider = "usersList")
    public void sortUsersShouldReturnIncorrectUserList(List<User> users) {
        List<User> currentUsers = userService.sortUsers(users, "login");
        assertNotEquals(currentUsers, sortedUsers);
    }

    @Test
    public void findUserByIdShouldReturnCorrectUser() {
        Optional<User> expectedUser =
                Optional.of(new User("Fedor", "Lukyanau", "luki44", "luki@mail.ru",
                        "+375(44)777-23-96", 1));
        try {
            when(daoMock.findById(any(Integer.class))).thenReturn(expectedUser);
            Optional<User> currentUser = userService.findUserById(1);
            assertEquals(currentUser, expectedUser);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findUserByIdShouldThrowException() throws DaoException, ServiceException {
        when(daoMock.findById(any(Integer.class))).thenThrow(DaoException.class);
        userService.findUserById(1);
    }

    @DataProvider(name = "paymentCorrectUsers")
    public Object[][] createPaymentCorrectUsers() {
        return new Object[][]{
                {new User(BigDecimal.valueOf(10000), 10, "Ivan", "Lukyanau", "AAA",
                        "luk@mail.ru", "+375(44)759-23-96"), 1000},
                {new User(BigDecimal.valueOf(10000), 20, "Andrey", "Burov", "BBB", "bur@mail.ru",
                        "+375(44)699-23-96"), 1500}};
    }

    @Test(dataProvider = "paymentCorrectUsers")
    public void paymentOrderingShouldReturnTrue(User user, double orderingPrice) {
        try {
            when(daoMock.updateBalanceByLogin(any(String.class), any(Double.class))).thenReturn(true);
            boolean actual = userService.paymentOrdering(user, orderingPrice);
            assertTrue(actual);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "paymentCorrectUsers")
    public void paymentOrderingShouldReturnFalse(User user, double orderingPrice) {
        try {
            when(daoMock.updateBalanceByLogin(any(String.class), any(Double.class))).thenReturn(false);
            boolean actual = userService.paymentOrdering(user, orderingPrice);
            assertFalse(actual);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "paymentCorrectUsers", expectedExceptions = ServiceException.class)
    public void paymentOrderingShouldThrowException(User user, double orderingPrice) throws DaoException, ServiceException {
        when(daoMock.updateBalanceByLogin(any(String.class), any(Double.class))).thenThrow(DaoException.class);
        boolean actual = userService.paymentOrdering(user, orderingPrice);
        assertFalse(actual);
    }

    @DataProvider(name = "depositMoneyLoginAndSum")
    public Object[][] createDepositMoneyLoginAndSum() {
        return new Object[][]{
                {"luki4", 150},
                {"buba", 20000},
                {"alfa", 10000}};
    }

    @Test(dataProvider = "depositMoneyLoginAndSum")
    public void depositMoneyShouldReturnTrue(String login, double sum) {
        try {
            Optional<User> expectedUser =
                    Optional.of(new User(BigDecimal.valueOf(10000), 10, "Ivan", "Lukyanau", "VVV",
                            "luk@mail.ru", "+375(44)759-23-96"));
            when(daoMock.findByLogin(any(String.class))).thenReturn(expectedUser);
            when(daoMock.updateBalanceByLogin(any(String.class), any(Double.class))).thenReturn(true);
            boolean actual = userService.depositMoney(login, sum);
            assertTrue(actual);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "depositMoneyLoginAndSum")
    public void depositMoneyShouldReturnFalse(String login, double sum) {
        try {
            when(daoMock.updateBalanceByLogin(any(String.class), any(Double.class))).thenReturn(false);
            boolean actual = userService.depositMoney(login, sum);
            assertFalse(actual);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(dataProvider = "depositMoneyLoginAndSum", expectedExceptions = ServiceException.class)
    public void depositMoneyShouldThrowException(String login, double sum) throws DaoException, ServiceException {
        Optional<User> expectedUser =
                Optional.of(new User(BigDecimal.valueOf(10000), 10, "Ivan", "Lukyanau", "CCC",
                        "luk@mail.ru", "+375(44)759-23-96"));
        when(daoMock.findByLogin(any(String.class))).thenReturn(expectedUser);
        when(daoMock.updateBalanceByLogin(any(String.class), any(Double.class))).thenThrow(DaoException.class);
        boolean actual = userService.depositMoney(login, sum);
        assertFalse(actual);
    }

    @DataProvider(name = "signUpCorrectParameters")
    public Object[][] createSignUpCorrectParameters() {
        return new Object[][]{
                {"Ivan", "Lukyanau", "filin", "99999@mail.ru", "+375(29)189-57-65", "password", "password"},
                {"Alina", "Lukyanava", "kukushka", "email@mail.ru", "+375(29)188-88-96", "password", "password"}
        };
    }

    @Test(dataProvider = "signUpCorrectParameters")
    public void signUpUserShouldReturnCorrectUser(String name, String surname, String login, String email,
                                                  String phoneNumber, String password, String confirmPassword) {
        UserCreator userCreator = UserCreator.getInstance();
        User currentUser = userCreator.createUser(name, surname, login, email, phoneNumber, User.Role.USER.getRoleId());
        try {
            when(daoMock.findByLogin(any(String.class))).thenReturn(Optional.empty());
            when(daoMock.add(any(User.class))).thenReturn(currentUser);
            User actualUser = userService.signUpUser(name, surname, login, email, phoneNumber, password, confirmPassword);
            assertEquals(actualUser, currentUser);
        } catch (DaoException | ServiceException | LoginNotUniqueException | PasswordNotConfirmedException
                | IncorrectRegisterParametersException exp) {
            fail(exp.getMessage());
        }
    }

    @DataProvider(name = "signUpIncorrectParameters")
    public Object[][] createSignUpIncorrectParameters() {
        return new Object[][]{
                {"I", "Lukyanau", "vorobey", "99999@mail.ru", "+375(29)189-57-65", "password", "password"},
                {"A", "Lukyanava", "sova", "email@mail.ru", "+375(29)188-88-96", "password", "password"}
        };
    }

    @Test(dataProvider = "signUpIncorrectParameters", expectedExceptions = IncorrectRegisterParametersException.class)
    public void signUpUserShouldThrowIncorrectParametersException(String name, String surname, String login, String email,
                                                                  String phoneNumber, String password, String confirmPassword)
            throws DaoException, ServiceException, PasswordNotConfirmedException,
            LoginNotUniqueException, IncorrectRegisterParametersException {
        UserCreator userCreator = UserCreator.getInstance();
        User currentUser = userCreator.createUser(name, surname, login, email, phoneNumber, User.Role.USER.getRoleId());

        when(daoMock.add(any(User.class))).thenReturn(currentUser);
        userService.signUpUser(name, surname, login, email, phoneNumber, password, confirmPassword);
    }

    @DataProvider(name = "signUpNotUniqueLoginParameters")
    public Object[][] createSignUpNotUniqueLoginParameters() {
        return new Object[][]{
                {"Ivan", "Lukyanau", "buba", "99999@mail.ru", "+375(29)189-57-65", "password", "password"},
                {"Alina", "Lukyanava", "alfa", "email@mail.ru", "+375(29)188-88-96", "password", "password"}
        };
    }

    @Test(dataProvider = "signUpNotUniqueLoginParameters", expectedExceptions = LoginNotUniqueException.class)
    public void signUpUserShouldThrowLoginNotUniqueException(String name, String surname, String login, String email,
                                                             String phoneNumber, String password, String confirmPassword)
            throws DaoException, ServiceException, PasswordNotConfirmedException,
            LoginNotUniqueException, IncorrectRegisterParametersException {
        Optional<User> expectedUser =
                Optional.of(new User(BigDecimal.valueOf(10000), 10, "Ivan", "Lukyanau", "AAA",
                        "luk@mail.ru", "+375(44)7592396"));
        UserCreator userCreator = UserCreator.getInstance();
        User currentUser = userCreator.createUser(name, surname, login, email, phoneNumber, User.Role.USER.getRoleId());
        when(daoMock.findByLogin(any(String.class))).thenReturn(expectedUser);
        when(daoMock.add(any(User.class))).thenReturn(currentUser);
        userService.signUpUser(name, surname, login, email, phoneNumber, password, confirmPassword);
    }

    @DataProvider(name = "signUpNotEqualsPasswordsParameters")
    public Object[][] createSignUpNotEqualsPasswordsParameters() {
        return new Object[][]{
                {"Ivan", "Lukyanau", "bravo", "99999@mail.ru", "+375(29)189-57-65", "password", "pass"},
                {"Alina", "Lukyanava", "fox", "email@mail.ru", "+375(29)188-88-96", "password", "pass"}
        };
    }

    @Test(dataProvider = "signUpNotEqualsPasswordsParameters", expectedExceptions = PasswordNotConfirmedException.class)
    public void signUpUserShouldThrowPasswordNotConfirmedException(String name, String surname, String login, String email,
                                                                   String phoneNumber, String password, String confirmPassword)
            throws DaoException, ServiceException, PasswordNotConfirmedException,
            LoginNotUniqueException, IncorrectRegisterParametersException {
        UserCreator userCreator = UserCreator.getInstance();
        User currentUser = userCreator.createUser(name, surname, login, email, phoneNumber, User.Role.USER.getRoleId());
        when(daoMock.findByLogin(any(String.class))).thenReturn(Optional.empty());
        when(daoMock.add(any(User.class))).thenReturn(currentUser);
        userService.signUpUser(name, surname, login, email, phoneNumber, password, confirmPassword);
    }

    @DataProvider(name = "signUpCorrectParametersForDaoException")
    public Object[][] createSignUpCorrectParametersForDaoException() {
        return new Object[][]{
                {"Ivan", "Lukyanau", "milk", "99999@mail.ru", "+375(29)189-57-65", "password", "password"},
                {"Alina", "Lukyanava", "bread", "email@mail.ru", "+375(29)188-88-96", "password", "password"}
        };
    }

    @Test(dataProvider = "signUpCorrectParametersForDaoException", expectedExceptions = ServiceException.class)
    public void signUpUserShouldThrowException(String name, String surname, String login, String email,
                                               String phoneNumber, String password, String confirmPassword)
            throws DaoException, ServiceException, PasswordNotConfirmedException,
            LoginNotUniqueException, IncorrectRegisterParametersException {
        UserCreator userCreator = UserCreator.getInstance();
        userCreator.createUser(name, surname, login, email, phoneNumber, User.Role.USER.getRoleId());
        when(daoMock.findByLogin(any(String.class))).thenReturn(Optional.empty());
        when(daoMock.add(any(User.class))).thenThrow(DaoException.class);
        userService.signUpUser(name, surname, login, email, phoneNumber, password, confirmPassword);
    }

    @DataProvider(name = "signInCorrectParameters")
    public Object[][] createSignInCorrectParameters() {
        return new Object[][]{
                {"buba", "333"},
                {"alfa", "888"}
        };
    }

    @Test(dataProvider = "signInCorrectParameters")
    public void signInUserShouldReturnCorrectUser(String login, String password) {
        Optional<User> expectedUser =
                Optional.of(new User("Fedor", "Lukyanau", "luki444", "luki@mail.ru",
                        "+375(44)7772396", 2));
        try {
            when(daoMock.findByLogin(any(String.class))).thenReturn(expectedUser);
            when(daoMock.findPasswordByLogin(any(String.class))).thenReturn(password);
            Optional<User> currentUser = userService.signInUser(login, password);
            assertEquals(currentUser, expectedUser);
        } catch (DaoException | ServiceException | IncorrectSignInParametersException exp) {
            fail(exp.getMessage());
        }
    }

    @DataProvider(name = "signInIncorrectParameters")
    public Object[][] createSignInIncorrectParameters() {
        return new Object[][]{
                {"buba", "444"},
                {"alfa", "999"}
        };
    }

    @Test(dataProvider = "signInIncorrectParameters", expectedExceptions = IncorrectSignInParametersException.class)
    public void signInUserShouldThrowIncorrectParametersException(String login, String password)
            throws DaoException, ServiceException, IncorrectSignInParametersException {
        Optional<User> expectedUser =
                Optional.of(new User("Fedor", "Lukyanau", "luki444", "luki@mail.ru",
                        "+375(44)7772396", 2));
        when(daoMock.findByLogin(any(String.class))).thenReturn(expectedUser);
        Optional<User> currentUser = userService.signInUser(login, password);
        assertEquals(currentUser, expectedUser);

    }

    @Test
    public void findAllShouldReturnCorrectList() {
        try {
            List<User> users = new ArrayList<>();
            users.add(new User("Ivan", "Lukyanau", "AAA", "luk@mail.ru",
                    "+375(44)7592396", 1));
            users.add(new User("Andrey", "Burov", "BBB", "bur@mail.ru",
                    "+375(44)6992396", 1));
            when(daoMock.findAll()).thenReturn(users);
            List<User> currentUsers = userService.findAllUsers();
            assertEquals(currentUsers, users);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findAllShouldThrowException() throws DaoException, ServiceException {
        when(daoMock.findAll()).thenThrow(DaoException.class);
        userService.findAllUsers();
    }


    @AfterClass
    public void afterClass() {
        daoMock = null;
        userService = null;
        sortedUsers = null;
    }
}
