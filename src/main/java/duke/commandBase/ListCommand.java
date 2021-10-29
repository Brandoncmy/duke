package duke.commandBase;

import duke.taskBase.Command;
import duke.taskBase.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;


import java.util.List;
import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String LIST_MSG = "Here are the tasks in your list:";

    public ListCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    public static String getTaskString(int indexToShow, String task){
        return String.format( "%1$d. %2$s", indexToShow, task);
    }


    public static String getIndexedList(List<String> tasks){
        int index = 1;
        String output = "";
        for (String task : tasks){
            output += "\n\t" + getTaskString(index, task);
            index++;
        }
        return output;
    }


    public String getTaskList(TaskList taskList) {
        List<String> indexedList = new ArrayList<>();
        for (int i = 0; i < taskList.TaskLength(); i++) {
            indexedList.add(taskList.getTaskName(i).getDescription());
        }
        return getIndexedList(indexedList);
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.TaskLength() == 0) {
            throw new DukeException("The task list is empty!");
        }
        ui.showOutputToUser(LIST_MSG + getTaskList(taskList));
    }
}
