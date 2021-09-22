import java.util.Scanner;
import java.util.Arrays;

public class Level3 {

    public static void main(String[] args) {
        printGreet();

        Scanner in = new Scanner(System.in);

        String[] listOfItem = new String [100];
        int n = 0;

        String item = in.nextLine();
        String listStop = "bye";

        while (item != null) {
            if (item.equals(listStop)){
                printExit();
                break;
            }else if (item.equals("list")){
                System.out.println("_________________________________________________\n");
                printListArray(listOfItem);
                System.out.println("_________________________________________________\n");
            }else {
                listOfItem[n] = item;
                System.out.println("_________________________________________________\n"
                        + "added: " + listOfItem[n] + "\n"
                        + "_________________________________________________\n");
                n++;
            }
            item = in.nextLine();
        }

    }

    public static void printGreet(){

        String greet
                = "_________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "_________________________________________________\n";

        System.out.println(greet);
    }

    public static void printExit(){

        String exit = "_________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "_________________________________________________\n";

        System.out.println(exit);
    }

    public static void printListArray(String[] item){
        int i = 0;

        while(item[i] != null){
            System.out.println(i+1 + ". " + item[i]);
            i++;
        }
    }


}
