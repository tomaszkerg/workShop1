package pl.coderslab;

public class Task {
    String taskName;
    String date;
    Boolean isImportant;

    public Task() {
    }

    public Task(String taskName, String date, Boolean isImportant) {
        this.taskName = taskName;
        this.date = date;
        this.isImportant = isImportant;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getImportant() {
        return isImportant;
    }

    public void setImportant(Boolean important) {
        isImportant = important;
    }
}
