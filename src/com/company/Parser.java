package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Parser {

    private CommandWords commands;

    public Parser() {
        commands = new CommandWords();
    }

    public Command getCommand() {
        String inputLine = "";
        String word1;
        String word2;

        System.out.print("> ");

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = reader.readLine();
        } catch (java.io.IOException exc) {
            System.out.println("There was an error during reading: "
                    + exc.getMessage());
        }

        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        if (tokenizer.hasMoreTokens())
            word1 = tokenizer.nextToken();      // get first word
        else
            word1 = null;
        if (tokenizer.hasMoreTokens())
            word2 = tokenizer.nextToken();      // get second word
        else
            word2 = null;

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        if (commands.isCommand(word1))
            return new Command(word1, word2);
        else
            return new Command(null, word2);
    }

    /**
     * Print out a list of valid command words.
     */
    public void showCommands() {
        commands.showAll();
    }
}
