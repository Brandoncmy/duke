package duke.commandBase;

import duke.taskBase.Command;
import duke.taskBase.Event;
import duke.taskBase.TaskList;
import duke.DukeException;
import duke.Storage;

import duke.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String EVENT_MSG = "Got it. I've added this task:\n";
    public static final String EVENT_MSG1 = "\nNow you have ";
    public static final String EVENT_MSG2 = " in task list.";
    protected Event event;

    public AddEventCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (description.substring(5).equals("")) {
            throw new DukeException("The description of event cannot be empty.");
        } else if (!description.contains("at")) {
            throw new DukeException("The event date cannot be empty.");
        }
        String taskDescription = description.substring(6, description.indexOf("at") - 1);
        String taskDate = description.substring(description.indexOf("at") + 3);
        event = new Event(taskDescription, stringToDate(taskDate));
        TaskList.addTaskList(event);
        assert taskList.TaskLength() > 0; //taskList size should > 0 after adding new task
        ui.showOutputToUser(EVENT_MSG + event.getDescription() + EVENT_MSG1 + TaskList.TaskLength() + EVENT_MSG2);
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