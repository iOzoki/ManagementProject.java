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


    public int getID() {
        return ID;
    }
    public String getName() {
        return Name;
    }
    public String getDescription() {
        return Description;
    }
    public List<Task> getTaskList() {
        return taskList;
    }
}


