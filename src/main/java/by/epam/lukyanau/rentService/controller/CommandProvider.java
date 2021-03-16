package by.epam.lukyanau.rentService.controller;

import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.CommandName;
import by.epam.lukyanau.rentService.controller.command.impl.EmptyCommand;

public class CommandProvider {
    private CommandProvider() {
    }

    public static Command provideCommand(String command) {
        Command currentCommand;

        if (command == null || command.isEmpty()) {
            return new EmptyCommand();
        }
        try {
            currentCommand = CommandName.valueOf(command.toUpperCase()).getCommand();
        } catch (IllegalArgumentException exp) {
            currentCommand = new EmptyCommand();
        }
        return currentCommand;
    }
}
