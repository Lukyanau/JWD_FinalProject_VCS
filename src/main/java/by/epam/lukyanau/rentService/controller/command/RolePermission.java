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
            LOG_OUT

    )),

    ADMIN(EnumSet.of(
            PASSING_ADMIN_CARS,
            PASSING_HOME,
            CHANGE_LANGUAGE,
            PASSING_ERROR,
            ADD_CAR,
            PASSING_USERS_ADMIN,
            BAN_ACCOUNT,
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