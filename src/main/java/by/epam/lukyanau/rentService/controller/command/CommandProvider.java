package by.epam.lukyanau.rentService.controller.command;

import by.epam.lukyanau.rentService.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.REGISTRATION, new PassingToRegistration());
        commands.put(CommandName.SAVE_NEW_USER, new SignUp());
        commands.put(CommandName.GO_TO_SIGN_IN_PAGE,new PassingToSignIn());
        commands.put(CommandName.GO_TO_ERROR_PAGE,new PassingToErrorPage());
        commands.put(CommandName.GO_TO_HOME_PAGE,new SignIn());
        commands.put(CommandName.HOME_PAGE,new PassingToHomePage());
        commands.put(CommandName.LOG_OUT,new LogOut());
    }

    public Command takeCommand(String name) {
        CommandName commandName;

        commandName = CommandName.valueOf(name.toUpperCase());

        return commands.get(commandName);
    }
}
