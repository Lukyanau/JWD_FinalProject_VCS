package by.epam.lukyanau.rentService.service.creator;

import by.epam.lukyanau.rentService.entity.User;

import java.math.BigDecimal;

public class UserCreator {
    private static final UserCreator instance = new UserCreator();

    private UserCreator() {
    }

    public static UserCreator getInstance() {
        return instance;
    }

    public User createUser(String name, String surname, String login, String email, String phoneNumber, int roleId) {
        return new User(name, surname, login, email, phoneNumber, roleId);
    }

    public User createUser(int userId, String name, String surname, String login, String email,
                           String phoneNumber){
        return new User(userId, name, surname, login, email, phoneNumber);
    }

    public User createUser(int userId, String name, String surname, String login, String email,
                           String phoneNumber, int roleId, boolean status) {
        return new User(userId, name, surname, login, email, phoneNumber, roleId, status);
    }

    public User createUser(BigDecimal accountBalance, int userId, String name, String surname, String login, String email,
                           String phoneNumber, int roleId, boolean status) {
        return new User(accountBalance, userId, name, surname, login, email, phoneNumber, roleId, status);
    }

    public User createUser(boolean status, String name, String surname, String login,
                           String email, String phoneNumber) {
        return new User(status, name, surname, login, email, phoneNumber);
    }
    public User createUser(BigDecimal balance, boolean status, String name, String surname, String login,
                           String email, String phoneNumber, int roleId) {
        return new User(balance, status, name, surname, login, email, phoneNumber, roleId);
    }

    public User createUser(BigDecimal balance, int userId, String name, String surname, String login,
                           String email, String phoneNumber) {
        return new User(balance, userId, name, surname, login, email, phoneNumber);
    }
}
