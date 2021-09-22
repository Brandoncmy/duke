import java.util.Scanner;
import java.lang.Integer;


public class Level3 {

    public static void main(String[] args) {
        printGreet(); // start //

        Scanner in = new Scanner(System.in);
        Task[] listOfTask = new Task [100];
        int taskCount = 0;

        String item = in.nextLine();        //starting item
        String listStop = "bye";

        while (item != null) {
            if (item.equals(listStop)){
                printExit();   //exit//
                break;
            }else if (item.equals("list")){
                System.out.println("_________________________________________________");
                printTaskList(listOfTask);
                System.out.println("_________________________________________________\n");
            }else if (isInteger(item)){
                System.out.println("isInteger is success");
                String extractNum = item.replaceAll("[^0-9]", "");
                int num = Integer.parseInt(extractNum);                                                  ///stop here -- find out the number in the item "done 2"
                listOfTask[num].markAsDone();
            } else {
                Task t = new Task(item);        //create new task//
                listOfTask[taskCount] = t;
                System.out.println("_________________________________________________\n"
                        + "added: " + listOfTask[taskCount].description + "\n"
                        + "_________________________________________________\n");
                taskCount++;
            }
            item = in.nextLine();
        }

    }

    public static boolean isInteger(String item) {
        if(item.matches(".*\\d.*")){
            return true;
        }else{
            return false;
        }
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            if (!isDone){
                return ("[ ] ");
            }
            return (isDone ? "[X" : " "); // mark done task with X
        }

        public String markAsDone() {
            this.isDone = true;
            return (this.getStatusIcon()); // mark done task with X
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

    public static void printTaskList(Task[] item){

        System.out.println("Here are the tasks in your list:");

        int i = 0;

        while(item[i] != null){
            System.out.println(i+1 + "." + item[i].getStatusIcon() + item[i].description);
            i++;
        }
    }


}
