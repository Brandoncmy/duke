package duke;

import duke.commandBase.AddTodoCommand;
import duke.commandBase.AddEventCommand;
import duke.commandBase.AddDeadlineCommand;
import duke.commandBase.DoneCommand;
import duke.commandBase.ListCommand;
import duke.commandBase.DeleteCommand;
import duke.commandBase.FindCommand;
import duke.commandBase.ByeCommand;
import duke.taskBase.Command;

public class Parser {

    public static Command parse(String userInput) throws DukeException {
        switch (userInput.split(" ")[0]) {
            case AddTodoCommand.COMMAND_WORD:
                return new AddTodoCommand(false, userInput);
            case AddEventCommand.COMMAND_WORD:
                return new AddEventCommand(false, userInput);
            case AddDeadlineCommand.COMMAND_WORD:
                return new AddDeadlineCommand(false, userInput);
            case DoneCommand.COMMAND_WORD:
                return new DoneCommand(false, userInput);
            case ListCommand.COMMAND_WORD:
                return new ListCommand(false, ""); //just print
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(false, userInput);
            case FindCommand.COMMAND_WORD:
                return new FindCommand(false, userInput);
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand(true, userInput);
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}