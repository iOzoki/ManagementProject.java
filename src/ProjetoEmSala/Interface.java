package ProjetoEmSala;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;


public class Interface extends JFrame {
    private ManagementProject managementProject;

    public Interface() {
        super("Interface");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Menu");
        this.getContentPane().setBackground(Color.BLACK);
        this.managementProject = new ManagementProject();

        this.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        JLabel presentationLabel = new JLabel("Welcome to Management Project, create projects, tasks and edit them");
        presentationLabel.setFont(new Font("Arial", Font.BOLD, 18));
        presentationLabel.setForeground(Color.WHITE);
        northPanel.setBackground(Color.BLACK);
        northPanel.add(presentationLabel);
        add(northPanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton buttonOne = new JButton("Create a project.");
        JButton buttonTwo = new JButton("Add a task to a project");
        JButton buttonThree = new JButton("Update status of a task");
        JButton buttonFour = new JButton("List all project with your selected task");
        JButton buttonFive = new JButton("Search project by ID");
        JButton buttonSix = new JButton("Delete a project");
        JButton buttonSeven = new JButton("Exit");
        JButton buttonEight = new JButton("Save project in bin");

        buttonOne.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonTwo.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonThree.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonFour.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonFive.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSix.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSeven.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonEight.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(buttonOne);
        buttonPanel.add(buttonTwo);
        buttonPanel.add(buttonThree);
        buttonPanel.add(buttonFour);
        buttonPanel.add(buttonFive);
        buttonPanel.add(buttonSix);
        buttonPanel.add(buttonSeven);
        buttonPanel.add(buttonEight);
        buttonPanel.add(Box.createVerticalGlue());

        buttonOne.addActionListener(e -> {
            String projectName = JOptionPane.showInputDialog(this, "Enter project name:");
            String projectDescription = JOptionPane.showInputDialog(this, "Enter project description:");

            if (projectName != null && projectDescription != null) {
                managementProject.createProject(projectName, projectDescription);
                int projectId = managementProject.getCurrentProjectId() - 1;
                JOptionPane.showMessageDialog(null, "Project created successfully with ID: " + projectId);
            } else {
                JOptionPane.showMessageDialog(this, "Project creation canceled.");
            }
        });
        buttonTwo.addActionListener(e -> {
            try {
                String projectIdInput = JOptionPane.showInputDialog(this, "Enter the project ID:");

                if (projectIdInput == null || projectIdInput.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Project ID is required!");
                    return;
                }

                int projectId = Integer.parseInt(projectIdInput);

                Project project = managementProject.searchProjectByID(projectId);

                if (project == null) {
                    JOptionPane.showMessageDialog(this, "Project with ID " + projectId + " not found!");
                    return;
                }

                String taskName = JOptionPane.showInputDialog(this, "Enter task name:");

                if (taskName == null || taskName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Task name is required!");
                    return;
                }

                String taskDescription = JOptionPane.showInputDialog(this, "Enter task description:");

                if (taskDescription == null || taskDescription.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Task description is required!");
                    return;
                }

                String[] options = {"PENDING", "IN_PROCESS", "COMPLETED"};
                int statusOption = JOptionPane.showOptionDialog(this, "Choose task status:",
                        "Task Status", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);

                Status status;
                switch (statusOption) {
                    case 1:
                        status = Status.IN_PROCESS;
                        break;
                    case 2:
                        status = Status.COMPLETED;
                        break;
                    default:
                        status = Status.PENDING;
                        break;
                }

                String startDate = JOptionPane.showInputDialog(this, "Enter start date (YYYY-MM-DD):");

                if (startDate == null || startDate.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Start date is required!");
                    return;
                }

                String endDate = JOptionPane.showInputDialog(this, "Enter end date (YYYY-MM-DD):");

                if (endDate == null || endDate.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "End date is required!");
                    return;
                }

                managementProject.addTask(projectId, taskName, taskDescription, status, startDate, endDate);

                int taskId = managementProject.getCurrentTaskId() - 1;
                JOptionPane.showMessageDialog(this, "Task added successfully with ID: " + taskId);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid project ID format! Please enter a valid number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        buttonThree.addActionListener(e -> {
            try {
                String projectIdInput = JOptionPane.showInputDialog(this, "Enter the project ID:");
                if (projectIdInput == null || projectIdInput.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Project ID is required!");
                    return;
                }

                int projectId = Integer.parseInt(projectIdInput);

                Project project = managementProject.searchProjectByID(projectId);
                if (project == null) {
                    JOptionPane.showMessageDialog(this, "Project with ID " + projectId + " not found!");
                    return;
                }

                String taskIdInput = JOptionPane.showInputDialog(this, "Enter the task ID to update:");
                if (taskIdInput == null || taskIdInput.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Task ID is required!");
                    return;
                }

                int taskId = Integer.parseInt(taskIdInput);
                Task taskToUpdate = null;

                for (Task task : project.getTaskList()) {
                    if (task.getTaskID() == taskId) {
                        taskToUpdate = task;
                        break;
                    }
                }

                if (taskToUpdate != null) {
                    String[] options = {"PENDING", "IN_PROCESS", "COMPLETED"};
                    int statusOption = JOptionPane.showOptionDialog(this, "Choose the new task status:",
                            "Task Status", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                    Status newStatus;
                    switch (statusOption) {
                        case 1:
                            newStatus = Status.IN_PROCESS;
                            break;
                        case 2:
                            newStatus = Status.COMPLETED;
                            break;
                        default:
                            newStatus = Status.PENDING;
                            break;
                    }

                    taskToUpdate.setTaskStatus(newStatus.toString());
                    JOptionPane.showMessageDialog(this, "Task status updated successfully!");

                } else {
                    JOptionPane.showMessageDialog(this, "Task not found!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format! Please enter a valid number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        buttonFour.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();

            Map<Integer, Project> projects = managementProject.getProjectList();

            if (projects.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No projects found.");
            } else {
                for (Map.Entry<Integer, Project> entry : projects.entrySet()) {
                    Project project = entry.getValue();
                    sb.append("Project ID: ").append(project.getID()).append("\n");
                    sb.append("Project Name: ").append(project.getName()).append("\n");
                    sb.append("Project Description: ").append(project.getDescription()).append("\n");

                    List<Task> taskList = project.getTaskList();
                    if (taskList.isEmpty()) {
                        sb.append("  No tasks found.\n");
                    } else {
                        for (Task task : taskList) {
                            sb.append("  Task ID: ").append(task.getTaskID()).append("\n");
                            sb.append("  Task Name: ").append(task.getTaskName()).append("\n");
                            sb.append("  Task Description: ").append(task.getTaskDescription()).append("\n");
                            sb.append("  Task Status: ").append(task.getTaskStatus()).append("\n");
                            sb.append("  Start Date: ").append(task.getStartDate()).append("\n");
                            sb.append("  End Date: ").append(task.getEndDate()).append("\n");
                            sb.append("  ------------------------------\n");
                        }
                    }
                    sb.append("-----------------------------------\n");
                }
                JOptionPane.showMessageDialog(this, sb.toString());
            }
        });
        buttonFive.addActionListener(e -> {
            try {
                String projectIdInput = JOptionPane.showInputDialog(this, "Enter the project ID to search:");
                if (projectIdInput == null || projectIdInput.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Project ID is required!");
                    return;
                }

                int projectId = Integer.parseInt(projectIdInput);
                Project project = managementProject.searchProjectByID(projectId);

                if (project != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Project ID: ").append(project.getID()).append("\n");
                    sb.append("Project Name: ").append(project.getName()).append("\n");
                    sb.append("Project Description: ").append(project.getDescription()).append("\n");
                    JOptionPane.showMessageDialog(this, sb.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "Project not found!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format! Please enter a valid number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        buttonSix.addActionListener(e -> {
            try {
                String projectIdInput = JOptionPane.showInputDialog(this, "Enter the project ID to delete:");
                if (projectIdInput == null || projectIdInput.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Project ID is required!");
                    return;
                }

                int projectId = Integer.parseInt(projectIdInput);
                Project removedProject = managementProject.searchProjectByID(projectId);

                if (removedProject != null) {
                    managementProject.deleteProject(projectId);
                    JOptionPane.showMessageDialog(this, "Project deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Project not found!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format! Please enter a valid number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        buttonSeven.addActionListener(e -> {
            System.exit(0);
        });
        buttonEight.addActionListener(e -> {
            managementProject.saveProjectInBin();
        });

        add(northPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }







}
