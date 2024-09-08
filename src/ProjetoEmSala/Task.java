package ProjetoEmSala;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private int taskID;
    private String taskName;
    private String taskDescription;
    private String taskStatus;
    private String startDate;
    private String endDate;

    public Task(int taskID, String taskName, String taskDescription, String taskStatus, String startDate, String endDate) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getTaskID() {
        return taskID;
    }
    public String getTaskName() {
        return taskName;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public String getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
