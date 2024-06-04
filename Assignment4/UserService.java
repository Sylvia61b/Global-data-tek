import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;
    private TaskService taskService;
    private UserDAO userDAO;
    private User loggedInUser;

    public UserService(TaskService taskService) {
        this.users = new ArrayList<>();
        this.taskService = taskService;
        this.userDAO = new UserDAO();
    }

    // register
    public void registerUser(String username, String password) {
        users.add(new User(username, password, "Client"));
    }

    //login
    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            }
        }
        return false; // User not found or incorrect password
    }

    public void logoutUser() {
        loggedInUser = null;
    }

    private boolean isLoggedInWithRole(String role) {
        return loggedInUser != null && loggedInUser.getRole().equals(role);
    }

    public void showTasks(){
        if (isLoggedInWithRole("Client")) {
            taskService.showTasks();
        } else {
            System.out.println("Only clients can show tasks.");
        }
    }

    public void addTask() {
        if (isLoggedInWithRole("Client")) {
            taskService.addTask();
        } else {
            System.out.println("Only clients can add tasks.");
        }
    }

    public void updateTaskByID() {
        if (isLoggedInWithRole("Client")) {
            taskService.updateTaskByID();
        }else{
            System.out.println("Only clients can update tasks.");
        }
    }

    public void deleteTaskByID() {
        if (isLoggedInWithRole("Client")) {
            taskService.deleteTaskByID();
        } else {
            System.out.println("Only clients can delete tasks.");
        }
    }

    public void deleteTaskByName(){
        if (isLoggedInWithRole("Client")) {
            taskService.deleteTaskByName();
        } else {
            System.out.println("Only clients can delete tasks.");
        }
    }

    public void searchTask(){
        if (isLoggedInWithRole("Client")) {
            taskService.searchTask();
        } else {
            System.out.println("Only clients can search tasks.");
        }
    }

    public void assignTaskToUser(){
        if (isLoggedInWithRole("Client")) {
            String visitorName = taskService.assignTaskToUser();
            User visitorUser = userDAO.getUserByUsername(visitorName);
            if (visitorUser == null) {
                // If the visitor user doesn't exist, create one

                visitorUser = new User(visitorName, "visitorPassword", "Visitor");
                userDAO.addUser(visitorUser);
            }
            // Assign the task to the visitor user
            taskService.assignTaskToUser(visitorUser);

        } else {
            System.out.println("Only clients can search tasks.");
        }
    }

    public ArrayList<Task> getTasksForVisitor() {
        if (isLoggedInWithRole("Visitor")) {
            ArrayList<Task> visitorTasks = new ArrayList<>();
            for (Task task : taskService.getTaskArray()) {
                if (task.getAssignedTo().equals(loggedInUser.getUsername())) {
                    visitorTasks.add(task);
                }
            }
            return visitorTasks;
        } else {
            System.out.println("Only visitors can view their tasks.");
            return null;
        }
    }

    public void showTasksForVisitor(){
        taskService.showTasks(getTasksForVisitor());
    }





}
