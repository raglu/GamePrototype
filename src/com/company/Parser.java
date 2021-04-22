package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Parser {

    public static Command getCommand(String caller) {
        String inputLine = "";
        String word1;
        String word2;

        System.out.print(caller + "> ");

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
            word1 = tokenizer.nextToken();
        else
            word1 = "";

        if (tokenizer.hasMoreTokens())
            word2 = tokenizer.nextToken();
        else
            word2 = null;

        while (tokenizer.hasMoreTokens())
            word2 += " "+ tokenizer.nextToken();

        return new Command(word1, word2);
    }

}
