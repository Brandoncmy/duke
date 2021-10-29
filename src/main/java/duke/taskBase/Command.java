package duke.taskBase;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

public class Command {
    boolean isExit = false;
    protected String description;

    public Command(boolean isExit, String description) {
        this.isExit = isExit;
        this.description = description;
    }

    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        throw new DukeException("No such function yet.");
    }
}
