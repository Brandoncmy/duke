package duke;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "_______________________________________________________\n";
    private final Scanner input;
    private String output;

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream input) {
        this.input = new Scanner(input);
    }

    private static boolean ignoreSpace(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }


    public static void showWelcome() {
        String logo
                = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE +
                "\tHello! I'm \n" + logo +
                "\tWhat can I do for you?\n" +
                "\tHere are the list of menu: \n" +
                "\t 1. todo ... \n" +
                "\t 2. deadline ... by ... \n" +
                "\t 3. event ... at ... \n" +
                "\t 4. list = to show the item in the list \n" +
                "\t 5. done ...(number) \n" +
                "\t 6. delete ...(number) \n" +
                LINE);
    }

    public void showGoodbyeMessage() {
        output = LINE + "Bye. Hope to see you again soon.\n" + LINE;
    }

    public String readCommand() {
        System.out.println("Type: " );
        String user_input = input.nextLine();

        return user_input;
    }


    public String showOutput() {
        return this.output;
    }

    public void showOutputToUser(String output){
        this.output = output;
    }


    public String showLoadingError(String errorMessage) {
        return LINE + ":( OOPS!!! " + errorMessage + "\n" + LINE;
    }
}