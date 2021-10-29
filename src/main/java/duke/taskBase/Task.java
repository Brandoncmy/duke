package duke.taskBase;

import java.time.LocalDateTime;

public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true; // mark done task with "tick"
    }

    public String getStatusIcon() {

        return (isDone ? "\u2713" : "\u2718"); // mark done task with "tick", not doen with "X"
    }

    public String getDescription(){

        return "[" + getStatusIcon() + "]" + this.description;
    }

    public LocalDateTime getDate() {
        return null;
    }
}
