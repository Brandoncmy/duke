package duke.taskBase;

import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }


    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }


    public static void addTaskList(Task description) {
        taskList.add(description);
    }


    public static Task DeleteTask(int description) {
        return taskList.remove(description);
    }


    public static int TaskLength() {
        return taskList.size();
    }


    public static Task getTaskName(int description) {
        return taskList.get(description);
    }

}
