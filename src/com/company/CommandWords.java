package com.company;

public class CommandWords {

    private static final String[] validCommands = {
            "go",
            "quit",
            "help",
            "inventory",
            "take",
            "drop",
            "look",
            "attack"
    };

    public boolean isCommand(String aString) {
        for (String validCommand : validCommands) {
            if (validCommand.equals(aString))
                return true;
        }
        return false;
    }

    public void printAllCommands() {
        for (String validCommand : validCommands) {
            System.out.print(validCommand + "  ");
        }
        System.out.println();
    }
}
