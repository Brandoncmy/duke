package duke;

import duke.taskBase.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Storage {
    private String filePath;
    private File file;
    private Scanner input;

    public Storage(String filePath) {
        try {
            this.filePath = filePath;
            this.file = new File(filePath);
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            createFileAndDirectory();
        }
    }


    public void createFileAndDirectory() {
        try {
            file.createNewFile();
            throw new IOException();
        } catch (IOException e) {
            System.out.println("Error creating file.");
        }
    }


    public void save() throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < TaskList.TaskLength(); ++i) {
                String taskType = "";
                String date = "";
                int isDone = 0;
                Task task = TaskList.getTaskName(i);

                if (task instanceof Todo) {
                    taskType = "T";
                } else if (task instanceof Event) {
                    taskType = "E";
                    date = dateToString(((Event) task).at); //casting
                } else if (task instanceof Deadline) {
                    taskType = "D";
                    date = dateToString(((Deadline) task).by);
                }

                if (task.isDone) {
                    isDone = 1;
                } else {
                    isDone = 0;
                }

                if (date.equals("")) {
                    fw.write(taskType + " | " + Integer.toString(isDone) + " | " + TaskList.getTaskName(i).description + System.lineSeparator());
                } else {
                    fw.write(taskType + " | " + Integer.toString(isDone) + " | " + TaskList.getTaskName(i).description + " | " + date + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException ioe) {
            System.out.println("Error in saving file.");
        }
    }


    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        if (!file.exists() || !file.isFile()) {
            return taskList;
        }

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] splitLine = line.split(" \\| ");
            switch (splitLine[0]) {
                case "T":
                    Todo newTodo = new Todo(splitLine[2]);
                    if (splitLine[1].equals("1")) {
                        newTodo.markAsDone();
                    }
                    taskList.add(newTodo);
                case "E":
                    Event newEvent = new Event(splitLine[1], stringToDate(splitLine[2]));
                    if (splitLine[1].equals("1")) {
                        newEvent.markAsDone();
                    }
                    taskList.add(newEvent);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(splitLine[2], stringToDate(splitLine[3]));
                    if (splitLine[1].equals("1")) {
                        newDeadline.markAsDone();
                    }
                    taskList.add(newDeadline);
                    break;
            }
        }
        return taskList;
    }


    public LocalDateTime stringToDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The date loaded from the file is invalid.");
        }
    }


    public String dateToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }
}