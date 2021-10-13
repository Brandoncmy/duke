package scale;

import java.util.Scanner;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Duke {                                                                         // Week 8 - level 7 - done//
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void printTaskList(ArrayList<Task> item){
        System.out.println("_________________________________________________");
        System.out.println("Here are the tasks in your list:");

        int i = 0;
        for (Task list : taskList){
            System.out.println(i+1 + "." + "[" + taskList.get(i).getType() + "]" + taskList.get(i).getStatusIcon() + taskList.get(i).description);
            i++;
        }
        System.out.println("_________________________________________________\n");
    }

    public static void printDeleteTask(int index){
        System.out.println("_________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + taskList.get(index).getType() + taskList.get(index).getStatusIcon() + taskList.get(index).description);
        System.out.println("Now you have " + (taskList.size() - 1) + " tasks in the list.");
        System.out.println("_________________________________________________\n");
    }

    private static void appendToFile(String filePath, ArrayList<Task> item) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode

        int i = 0;

        for (Task list : taskList){
            fw.write(taskList.get(i).getType() + " | " + taskList.get(i).isDone + " | " + taskList.get(i).description + '\n');
            i++;
        }

        fw.close();
    }

    public static void main(String[] args) throws IOException {

        // create file //
        File duke = new File("duke.txt");
        checkFile(duke);


        printGreet(); // start //

        Scanner in = new Scanner(System.in);
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
                printTaskList(taskList);
            }else if (firstInput.equalsIgnoreCase("delete")){                                                     // print "delete"
                String deleteNum = input.split(" ")[1];
                int d_num = Integer.parseInt(deleteNum) - 1;
                printDeleteTask(d_num);
                taskList.remove(d_num);

            }else if (firstInput.equalsIgnoreCase("done")){                                                       // make "done"
                String taskNum = input.split(" ")[1];
                int num = Integer.parseInt(taskNum) - 1;

                if (taskList.get(num).description !=  null){
                    taskList.get(num).markAsDone();                                                                            //list : mark as done
                    System.out.println("_________________________________________________");
                    System.out.println("Nice! I've marked this task as done: \n" + taskList.get(num).getStatusIcon() + taskList.get(num).description);
                    System.out.println("_________________________________________________\n");
                }else{
                    System.out.println("There is no task #" + num);
                }

            } else {
                if (checkError(input)) {continue;}

                String taskDescription = input.substring(firstInput.length(), input.length());                              // keep task description

                    switch (firstInput) {
                        case "todo":
                            Todo t = new Todo(taskDescription);
                            t.type = "todo";
                            taskList.add(t);
                            break;
                        case "deadline":
                            Deadline d = new Deadline(taskDescription);
                            d.type = "deadline";
                            taskList.add(d);
                            break;
                        case "event":
                            Event e = new Event(taskDescription);
                            e.type = "event";
                            taskList.add(e);
                            break;
                    }

                    System.out.println("_________________________________________________\n"
                            + "Got it. I've added this task: \n"
                            + "\t" + "[" + taskList.get(taskCount).getType() + "]" + taskList.get(taskCount).getStatusIcon() + taskList.get(taskCount).description + "\n"
                            + "Now you have " + (taskCount + 1) + " tasks in the list." + "\n"
                            + "_________________________________________________\n");

                    taskCount++;
            }
        }
        appendToFile(duke.getAbsolutePath(), taskList);
    }

    public static boolean checkFile (File duke){
        try {
            if (duke.createNewFile()) {
                System.out.println("File created: " + duke.getName());
            } else {
                System.out.println("File already exists.");
            }
            System.out.println("File path: " + duke.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return true;
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
            if (description.contains("/by"))
            this.description = description.replace("/by", "(by:") + ")";
        }
    }

    public static class Event extends Task {

        protected String taskDesc;

        public Event(String description) {
            super(description);
            if (description.contains("/at")){
                this.description = description.replace("/at", "(at:") + ")";
            }
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

        public boolean markAsDone() {
            this.isDone = true;
            return true; // mark done task with X
        }

        public String getStatusIcon() {
            if (!isDone){
                return ("[ ] ");
            }
            return (isDone ? "[X] " : " "); // mark done task with X
        }

        public String getType(){

            String taskType = this.type;

            switch (taskType){
                case "todo" :
                    type = "T";
                    break;
                case "deadline" :
                    type = "D";
                    break;
                case "event" :
                    type = "E";
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
}
