package duke.commandBase;

import duke.taskBase.Command;
import duke.taskBase.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String DELETE_MSG = "Noted. I've removed this task:\n";
    public static final String DELETE_MSG1 = "\nNow you have ";
    public static final String DELETE_MSG2 = " in task list.";


    public DeleteCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int index = 0;
        if (description.substring(7).equals("")) {
            throw new DukeException("Which task to delete?");
        }
        index = Integer.parseInt(description.substring(7)) - 1;
        ui.showOutputToUser(DELETE_MSG + "\t" + taskList.getTaskName(index).getDescription());
        taskList.DeleteTask(index);
        //ui.showOutputToUser(DELETE_MSG1 + taskList.length() + DELETE_MSG2);
        storage.save();
    }
}
