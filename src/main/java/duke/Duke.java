package duke;

import duke.taskBase.Command;
import duke.taskBase.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates Duke with specified filepath and initialised with storage, taskList and ui.
     * @param filePath The file path of the storage file.
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke and show output .
     */

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showLoadingError(e.getMessage());
            }finally {
                ui.showOutput();
            }
        }
    }

    /**
     *  The main function is to run Duke.
     * @param   args      The argument values provide by the user to run Duke.
     */

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
