import java.util.Scanner;
import java.lang.Integer;
import java.lang.String;
import java.lang.*;

public class Duke {                                                                         // Week 7 - level 6 //

    public static void main(String[] args) throws DukeException {
        printGreet(); // start //

        Scanner in = new Scanner(System.in);
        Task[] listOfTask = new Task [100];
        int taskCount = 0;

        String input = "User input";
        String firstInput = input.split(" ")[0];
        firstInput.toLowerCase();


        while (input != null) {
            input = in.nextLine();
            firstInput = input.split(" ")[0];
            firstInput.toLowerCase();

            if (firstInput.equalsIgnoreCase("bye")){                                                                      // "exit"
                printExit();   //exit//
                break;
            }else if (firstInput.equalsIgnoreCase("list")){                                                       // print "list"
                System.out.println("_________________________________________________");
                printTaskList(listOfTask);
                System.out.println("_________________________________________________\n");
            }else if (firstInput.equalsIgnoreCase("done")){                                                       // make "done"
                String taskNum = input.split(" ")[1];
                int num = Integer.parseInt(taskNum) - 1;

                if (listOfTask[num].description !=  null){
                    listOfTask[num].markAsDone();                                                                            //list : mark as done
                    System.out.println("_________________________________________________");
                    System.out.println("Nice! I've marked this task as done: \n" + listOfTask[num].getStatusIcon() + listOfTask[num].description);
                    System.out.println("_________________________________________________\n");
                }else{
                    System.out.println("There is no task #" + taskNum+1);
                }

            } else {
                if (checkError(input)) {continue;}

                String taskDescription = input.substring(firstInput.length(), input.length());                              // keep task description

                    switch (firstInput) {
                        case "todo":
                            Todo t = new Todo(taskDescription);
                            t.type = "todo";
                            listOfTask[taskCount] = t;
                            break;
                        case "deadline":
                            Deadline d = new Deadline(taskDescription);
                            d.type = "deadline";
                            listOfTask[taskCount] = d;
                            break;
                        case "event":
                            Event e = new Event(taskDescription);
                            e.type = "event";
                            listOfTask[taskCount] = e;
                            break;
                    }

                    System.out.println("_________________________________________________\n"
                            + "Got it. I've added this task: \n"
                            + "\t" + listOfTask[taskCount].getType() + listOfTask[taskCount].getStatusIcon() + listOfTask[taskCount].description + "\n"
                            + "Now you have " + (taskCount + 1) + " tasks in the list." + "\n"
                            + "_________________________________________________\n");

                    taskCount++;
            }
        }
    }

    public static boolean checkError (String descriptor){
        try{
            checkTask(descriptor);
            return false;
        }catch (IllegalStateException e) {
            System.out.println("______________________________________________________\n"
                    + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                    + "______________________________________________________\n");
        }catch (IllegalCallerException e) {
            System.out.println("______________________________________________________\n"
                    + "☹ OOPS!!! The description of an event cannot be empty.\n"
                    + "______________________________________________________\n");
        }catch (IllegalAccessError e) {
            System.out.println("______________________________________________________\n"
                    + "☹ OOPS!!! The description of a deadline cannot be empty.\n"
                    + "______________________________________________________\n");
        }catch (DukeException e) {
            System.out.println("_______________________________________________________________\n"
                    + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "_______________________________________________________________\n");
        }
        return true;
    }

    public static boolean checkTask(String taskName) throws DukeException {
        String firstInput = taskName.split(" ")[0];
        firstInput.toLowerCase();
        String taskDescription = taskName.substring(firstInput.length(), taskName.length());

        switch (firstInput){
            case "todo" :
                if (taskDescription.trim().isEmpty()){
                    throw new IllegalStateException();
                }else{
                    break;
                }
            case "event" :
                if (taskDescription.trim().isEmpty()){
                    throw new IllegalCallerException();
                }else{
                    break;
                }
            case "deadline" :
                if (taskDescription.trim().isEmpty()){
                    throw new IllegalAccessError();
                }else{
                    break;
                }

            default: throw new DukeException();
        }
        return true;
    }

    public static class DukeException extends Exception {
    }

    public static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }
    }

    public static class Deadline extends Task {

        public Deadline(String description) {
            super(description);
            this.description = description.replace("/by", "(by:") + ")";
        }
    }

    public static class Event extends Task {

        public Event(String description) {
            super(description);
            this.description = description.replace("/at", "(at:") + ")";
        }
    }

    public static class Task {
        protected String description;
        protected String type;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            if (!isDone){
                return ("[ ] ");
            }
            return (isDone ? "[X] " : " "); // mark done task with X
        }

        public String markAsDone() {
            this.isDone = true;
            return (this.getStatusIcon()); // mark done task with X
        }

        public String getType(){

            String taskType = this.type;

            switch (taskType){
                case "todo" :
                    type = "[T]";
                    break;
                case "deadline" :
                    type = "[D]";
                    break;
                case "event" :
                    type = "[E]";
                    break;
            }
            return type;
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
            System.out.println(i+1 + "." + item[i].getType() + item[i].getStatusIcon() + item[i].description);
            i++;
        }
    }
}
