package by.epam.lukyanau.rentService.controller.command;

import by.epam.lukyanau.rentService.controller.command.impl.*;

public enum CommandName {

    PASSING_REGISTRATION(new PassingToRegistration(), "passing_registration"),
    SIGN_UP(new SignUp(), "save_new_user"),
    PASSING_SIGN_IN(new PassingToSignIn(), "passing_sign_in"),
    GO_TO_ERROR_PAGE(new PassingToErrorPage(), "passing_error"),
    GO_TO_HOME_PAGE(new SignIn(), "passing_home"),
    HOME_PAGE(new PassingToHomePage(), "home_page"),
    LOG_OUT(new LogOut(), "log_out"),
    CHANGE_LANGUAGE(new ChangeLanguage(), "change_language");

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
