package by.epam.lukyanau.rentService.controller.command;

import by.epam.lukyanau.rentService.controller.command.impl.*;
import by.epam.lukyanau.rentService.controller.command.impl.page.*;

public enum CommandName {
    EMPTY_COMMAND(new EmptyCommand(), "empty_command"),
    CHANGE_LANGUAGE(new ChangeLanguage(), "change_language"),
    SIGN_UP(new SignUp(), "sign_up"),
    SIGN_IN(new SignIn(), "sign_in"),
    PASSING_SIGN_UP(new PassingToRegistration(), "passing_sign_up"),
    PASSING_SIGN_IN(new PassingToSignIn(), "passing_sign_in"),
    PASSING_ADMIN_CARS(new PassingToAdminCars(), "passing_admin_cars"),
    PASSING_ERROR(new PassingToErrorPage(), "passing_error"),
    PASSING_HOME(new PassingToHomePage(), "passing_home"),
    PASSING_USERS_ADMIN(new PassingAdminUsers(), "passing_users_admin"),
    ADD_CAR(new AddCar(), "add_car"),
    BAN_ACCOUNT(new BanAccount(),"ban_account"),
    LOG_OUT(new LogOut(), "log_out");


    private final Command command;
    private final String nameCommand;

    CommandName(Command command, String nameCommand) {
        this.command = command;
        this.nameCommand = nameCommand;
    }

    public Command getCommand() {
        return command;
    }

    public String getNameCommand() {
        return nameCommand;
    }
}
