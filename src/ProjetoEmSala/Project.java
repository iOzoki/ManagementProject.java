package ProjetoEmSala;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {
    private static final long serialVersionUID = 1L; // Identificador de versão para serialização
    private int ID;
    private String Name;
    private String Description;
    private List<Task> taskList;

    public Project(int ID, String Name, String Description) {
        this.ID = ID;
        this.Name = Name;
        this.Description = Description;
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void addTask(int taskID, String taskName, String taskDescription, String taskStatus, String startDate, String endDate) {
        taskList.add(new Task(taskID, taskName, taskDescription, taskStatus, startDate, endDate));
    }

    // Getters e Setters
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }
    public List<Task> getTaskList() {
        return taskList;
    }
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}


