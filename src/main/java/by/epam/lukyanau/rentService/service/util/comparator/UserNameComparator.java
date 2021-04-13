package by.epam.lukyanau.rentService.service.util.comparator;

import by.epam.lukyanau.rentService.entity.User;

import java.util.Comparator;

public class UserNameComparator implements Comparator<User> {

    private static final UserNameComparator instance = new UserNameComparator();

    private UserNameComparator() {

    }

    public static UserNameComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(User user1, User user2) {
        return user1.getName().compareTo(user2.getName());
    }
}
