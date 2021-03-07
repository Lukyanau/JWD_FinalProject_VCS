package by.epam.lukyanau.rentService.creator;

import by.epam.lukyanau.rentService.entity.User;

import java.math.BigDecimal;

public class UserCreator {
    public static UserCreator instance = new UserCreator();

    private UserCreator() {

    }

    public static UserCreator getInstance() {
        return instance;
    }

    public User createUser(String name, String surname, String login, String email, String phoneNumber, int roleId) {
        return new User(name,surname,login,email,phoneNumber,roleId);
    }

    public User createUser(BigDecimal balance, byte status, String name, String surname, String login,
                           String email, String phoneNumber, int roleId) {
        return new User(balance, status, name, surname, login, email, phoneNumber, roleId);
    }

    public User createUser(BigDecimal balance, int userId, byte status, String name, String surname, String login,
                            String email, String phoneNumber, int roleId) {
        return new User(balance, userId, status, name, surname, login, email,phoneNumber, roleId);
    }


}
