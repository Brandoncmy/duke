package duke.commandBase;

import duke.taskBase.Command;
import duke.taskBase.Todo;
import duke.taskBase.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;

public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String TODO_MSG = "Got it. I've added this task:\n";
    public static final String TODO_MSG1 = "Now you have ";
    public static final String TODO_MSG2 = " in task list.";
    protected Todo todo;

    public AddTodoCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (description.substring(4).equals("")) {
            throw new DukeException("The description of todo cannot be empty.");
        }
        todo = new Todo(description.substring(5));
        taskList.addTaskList(todo);
        assert taskList.TaskLength() > 0; //taskList size should > 0 after adding new task
        ui.showOutputToUser(TODO_MSG + todo.getDescription() + TODO_MSG1 + taskList.TaskLength() + TODO_MSG2);
        storage.save();
    }
}
