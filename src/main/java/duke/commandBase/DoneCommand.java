package duke.commandBase;

import duke.taskBase.Command;
import duke.taskBase.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;


public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String DONE_MSG = "Nice! I have marked this task as done:\n";


    public DoneCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int index = 0;
        if (description.substring(4).equals("")) {
            throw new DukeException("Which task is done?");
        }
        index = Integer.parseInt(description.substring(5)) - 1;
        (taskList.getTaskName(index)).markAsDone();
        ui.showOutputToUser(DONE_MSG + taskList.getTaskName(index).getDescription());
        storage.save();
    }
}
