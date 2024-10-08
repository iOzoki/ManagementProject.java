package ProjetoEmSala;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ManagementProject {
    private Map<Integer, Project> projectList;
    private static int id = 0;
    private static int taskID = 0;

    ManagementProject() {
        projectList = new HashMap<Integer, Project>();
    }
//--------------------------------------------------------------------------------
//Para criar a interface
    public Map<Integer, Project> getProjectList() {
        return projectList;
    }
    public int getCurrentProjectId() {
        return id;
    }
    public int getCurrentTaskId() {
        return taskID;
    }
//--------------------------------------------------------------------------------
    public void menu() {
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("Choose a option: ");
            System.out.println("1 - Create a project.");
            System.out.println("2 - Add a task to a project.");
            System.out.println("3 - Update status of a task.");
            System.out.println("4 - List all project with your selected task.");
            System.out.println("5 - Search project by ID.");
            System.out.println("6 - Delete a project.");
            System.out.println("7 - Exit");
            System.out.println("8 - Save project in bin");
            System.out.print(">>> ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Type the name of the project you would like to create: ");
                    String name = sc.nextLine();
                    System.out.println("Type the description of the project you would like to create: ");
                    String description = sc.nextLine();
                    createProject(name, description);
                    System.out.printf("------------------------------\n%s created successfully of ID: %d.\n------------------------------\n", name, id - 1);
                    break;
                case 2:
                    System.out.println("Type the ID of the project you would like to add a task to: ");
                    int projectId = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Type the name of the task: ");
                    String taskName = sc.nextLine();

                    System.out.println("Type the description of the task: ");
                    String taskDescription = sc.nextLine();

                    System.out.println("Choose the status of the task: ");
                    System.out.println("1 - PENDING");
                    System.out.println("2 - IN_PROCESS");
                    System.out.println("3 - COMPLETED");
                    int statusOption = sc.nextInt();
                    sc.nextLine();

                    Status status;
                    switch (statusOption) {
                        case 1:
                            status = Status.PENDING;
                            break;
                        case 2:
                            status = Status.IN_PROCESS;
                            break;
                        case 3:
                            status = Status.COMPLETED;
                            break;
                        default:
                            status = Status.PENDING;
                            System.out.println("Invalid option, defaulting to PENDING.");
                            break;
                    }

                    System.out.println("Type the start date of the task (ex: 2024-08-30): ");
                    String startDate = sc.nextLine();

                    System.out.println("Type the end date of the task (ex: 2024-09-05): ");
                    String endDate = sc.nextLine();

                    addTask(projectId, taskName, taskDescription, status, startDate, endDate);
                    System.out.printf("------------------------------\ntask %s created successfully of ID: %d.\n------------------------------\n", taskName, id - 1);
                    break;

                case 3:
                    updateStatus();
                    break;
                case 4:
                    listAllProjectsWithTask();
                    break;
                case 5:
                    System.out.println("Type the id of the project you would like to find: ");
                    int idProcurado = sc.nextInt();
                    sc.nextLine();
                    Project projeto = searchProjectByID(idProcurado);
                    if (projeto != null) {
                        System.out.println("Project found: " + projeto.getName());
                    } else {
                        System.out.println("Project not found.");
                    }
                    break;
                case 6:
                    System.out.println("Type the ID of the project you want to delete: ");
                    int projectIdToDelete = sc.nextInt();
                    sc.nextLine();
                    deleteProject(projectIdToDelete);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                case 8:
                    saveProjectInBin();
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;

            }
        } while (opcao != 7);
        System.out.println("Thanks for use!");
    }

    public void createProject(String name, String description) {
        Project newProject = new Project(id, name, description);
        projectList.put(id, newProject);
        id++;
    }

    public void saveProjectInBin() {
        try (FileOutputStream fileOut = new FileOutputStream("projects.bin");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(projectList);
            System.out.println("Projects successfully saved in binary.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error when saving projects in binary.");
        }
    }

    public void addTask(int projectId, String taskName, String taskDescription, Status status, String startDate, String endDate) {
        Project project = projectList.get(projectId);

        if (project != null) {
            String taskStatus = status.toString();

            Task newTask = new Task(taskID, taskName, taskDescription, taskStatus, startDate, endDate);
            project.addTask(newTask);

            taskID++;

            System.out.println("Task added successfully!");
        } else {
            System.out.println("Project not found!");
        }
    }

    public void updateStatus() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Type the ID of the project containing the task: ");
        int projectId = sc.nextInt();
        sc.nextLine();

        Project project = projectList.get(projectId);

        if (project != null) {
            System.out.println("Type the ID of the task you want to update: ");
            int taskId = sc.nextInt();
            sc.nextLine();

            Task taskToUpdate = null;
            for (Task task : project.getTaskList()) {
                if (task.getTaskID() == taskId) {
                    taskToUpdate = task;
                    break;
                }
            }

            if (taskToUpdate != null) {
                System.out.println("Choose the new status of the task: ");
                System.out.println("1 - PENDING");
                System.out.println("2 - IN_PROCESS");
                System.out.println("3 - COMPLETED");
                int statusOption = sc.nextInt();
                sc.nextLine();

                Status newStatus;
                switch (statusOption) {
                    case 1:
                        newStatus = Status.PENDING;
                        break;
                    case 2:
                        newStatus = Status.IN_PROCESS;
                        break;
                    case 3:
                        newStatus = Status.COMPLETED;
                        break;
                    default:
                        newStatus = Status.PENDING;
                        System.out.println("Invalid option, defaulting to PENDING.");
                        break;
                }

                taskToUpdate.setTaskStatus(newStatus.toString());
                System.out.println("Task status updated successfully!");
            } else {
                System.out.println("Task not found!");
            }
        } else {
            System.out.println("Project not found!");
        }
    }

    public void listAllProjectsWithTask() {
        if (projectList.isEmpty()) {
            System.out.println("No projects found.");
        } else {
            for (Map.Entry<Integer, Project> entry : projectList.entrySet()) {
                Project project = entry.getValue();
                System.out.println("Project ID: " + project.getID());
                System.out.println("Project Name: " + project.getName());
                System.out.println("Project Description: " + project.getDescription());
                System.out.println("Tasks:");
                if (project.getTaskList().isEmpty()) {
                    System.out.println("  No tasks found in this project.");
                } else {
                    for (Task task : project.getTaskList()) {
                        System.out.println("  Task ID: " + task.getTaskID());
                        System.out.println("  Task Name: " + task.getTaskName());
                        System.out.println("  Task Description: " + task.getTaskDescription());
                        System.out.println("  Task Status: " + task.getTaskStatus());
                        System.out.println("  Start Date: " + task.getStartDate());
                        System.out.println("  End Date: " + task.getEndDate());
                        System.out.println("  ------------------------------");
                    }
                }
                System.out.println("-----------------------------------");
            }
        }
    }

    public Project searchProjectByID(int idProcurado) {
        return projectList.get(idProcurado);
    }

    public void deleteProject(int projectId) {
        Project removedProject = projectList.remove(projectId);

        if (removedProject != null) {
            System.out.println("Project deleted successfully!");
        } else {
            System.out.println("Project not found!");
        }
    }
}




