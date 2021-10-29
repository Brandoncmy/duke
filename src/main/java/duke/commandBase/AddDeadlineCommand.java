package duke.commandBase;

import duke.taskBase.Command;
import duke.taskBase.Deadline;
import duke.taskBase.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String DEADLINE_MSG = "Got it. I've added this task:\n";
    public static final String DEADLINE_MSG1 = "\nNow you have ";
    public static final String DEADLINE_MSG2 = " in task list.";
    protected Deadline deadline;

    public AddDeadlineCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (description.substring(8).equals("")) {
            throw new DukeException("The description of deadline cannot be empty.");
        } else if (!description.contains("by")) {
            throw new DukeException("The date cannot be empty.");
        }
        String taskDescription = description.substring(9, description.indexOf("by") - 1);
        String taskDate = description.substring(description.indexOf("by") + 3);
        deadline = new Deadline(taskDescription, stringToDate(taskDate));
        taskList.addTaskList(deadline);
        assert taskList.TaskLength() > 0; //taskList size should > 0 after adding new task
        ui.showOutputToUser(DEADLINE_MSG + deadline.getDescription() + DEADLINE_MSG1 + taskList.TaskLength() + DEADLINE_MSG2);
        storage.save();
    }

    public LocalDateTime stringToDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The format of the date and time is not DD/MM/YYYY HHmm");
        }
    }
}
