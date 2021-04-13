package by.epam.lukyanau.rentService.controller.command;

import static by.epam.lukyanau.rentService.controller.command.CommandName.*;

import java.util.EnumSet;
import java.util.Set;

public enum RolePermission {

    GUEST(EnumSet.of(
            SIGN_IN,
            SIGN_UP,
            PASSING_HOME,
            PASSING_SIGN_UP,
            PASSING_SIGN_IN,
            CHANGE_LANGUAGE,
            PASSING_ERROR
    )),

    USER(EnumSet.of(
            PASSING_HOME,
            CHANGE_LANGUAGE,
            PASSING_ERROR,
            PASSING_CATALOG,
            PASSING_USER_PROFILE,
            SORT_CARS,
            SORT_ORDERS,
            FILTER_CARS,
            MAKE_ORDER,
            PAYMENT,
            PASSING_PAYMENT_CARD,
            MAKE_DEPOSIT,
            LOG_OUT

    )),

    ADMIN(EnumSet.of(
            PASSING_ADMIN_CARS,
            PASSING_HOME,
            CHANGE_LANGUAGE,
            PASSING_ERROR,
            ADD_CAR,
            PASSING_USERS_ADMIN,
            PASSING_ADMIN_ORDERS,
            BAN_ACCOUNT,
            UNBAN_ACCOUNT,
            SORT_USERS,
            SORT_CARS,
            ACTIVATE_CAR,
            DEACTIVATE_CAR,
            DELETE_CAR,
            APPROVE_ORDER,
            SHOW_ALL_ORDERS,
            LOG_OUT
    ));

    private final Set<CommandName> commandNames;

    RolePermission(Set<CommandName> commandNames) {
        this.commandNames = commandNames;
    }


    public Set<CommandName> getCommandNames() {
        return commandNames;
    }
}