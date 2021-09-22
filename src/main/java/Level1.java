import java.util.Scanner;

public class Level1 {

    public static void main(String[] args) {

        String greet = "_________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "_________________________________________________\n";

        String exit = "_________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "_________________________________________________\n";

        System.out.println(greet);



        while (!echo.equals(echoStop)) {

            System.out.println("_________________________________________________\n"
                    + echo + "\n"
                    + "_________________________________________________\n");
            echo = in.nextLine();
        }
        System.out.println(exit);
    }
}
