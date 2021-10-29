package duke.commandBase;

import duke.taskBase.Command;
import duke.taskBase.Task;
import duke.taskBase.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String FIND_MSG = "Here are the tasks in your list:";
    public static final String FIND_MSG2 = "You have no matched task.";


    public FindCommand(boolean isExit, String description) {
        super(isExit, description);
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        boolean isMatch = false;
        List<String> matchedTasksList = new ArrayList<>();

        if (description.substring(4).equals("")) {
            throw new DukeException("The task description of find cannot be empty.");
        }
        description = description.substring(4);
        for (int i = 0; i < taskList.TaskLength(); i++) {
            Task task = taskList.getTaskName(i);
            if (task.getDescription().contains(description)) {
                matchedTasksList.add(task.getDescription());
                isMatch = true;
            }
        }
        if (isMatch) {
            ui.showOutputToUser(FIND_MSG + getIndexedList(matchedTasksList));
        } else {
            ui.showOutputToUser(FIND_MSG2);
        }
    }


    public static String getTaskString(int indexToShow, String task){
        return String.format( "%1$d. %2$s", indexToShow, task);
    }


    public static String getIndexedList(List<String> tasks){
        String output = "";
        int index = 1;
        for (String task : tasks){
            output += "\n\t" + getTaskString(index, task);
            index++;
        }
        return output;
    }
}
