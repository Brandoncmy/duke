package duke.commandBase;

import duke.taskBase.Command;
import duke.taskBase.TaskList;
import duke.Storage;
import duke.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ByeCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }
}
