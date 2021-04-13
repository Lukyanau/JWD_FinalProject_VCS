package by.epam.lukyanau.rentService.service.util.comparator;

import by.epam.lukyanau.rentService.entity.User;

import java.util.Comparator;

public class UserLoginComparator implements Comparator<User> {

    private static final UserLoginComparator instance = new UserLoginComparator();

    private UserLoginComparator() {

    }

    public static UserLoginComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(User user1, User user2) {
        return user1.getLogin().compareTo(user2.getLogin());
    }
}
