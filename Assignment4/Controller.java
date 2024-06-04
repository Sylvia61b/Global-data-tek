import java.util.Scanner;
import java.util.InputMismatchException;

public class Controller {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService();
        UserService userService = new UserService(taskService);

        boolean loggedIn = false;


        while (true) {

            if (!loggedIn) {
                System.out.println("\nLogin:");
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                loggedIn = userService.loginUser(username, password);

                if (!loggedIn) {
                    System.out.println("Invalid username or password. Please try again.");
                    continue;
                }
            }

            System.out.println("\nTodo Manager Menu:");
            System.out.println("0. Exit");
            System.out.println("1. Show Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Delete Task by Name");
            System.out.println("4. Delete Task by ID");
            System.out.println("5. Search Task");
            System.out.println("6. Update A Task");
            System.out.println("7. Assign Task to a user");
            System.out.println("8. Show Assigned Tasks");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }



            switch (choice) {
                case 0:
                    System.out.println("Exiting Todo Manager.");
                    scanner.close();
                    System.exit(0);
                    break;
                case 1:
                    userService.showTasks();
                    break;
                case 2:
                    userService.addTask();
                    break;
                case 3:
                    userService.deleteTaskByName();
                    break;
                case 4:
                    userService.deleteTaskByID();
                    break;
                case 5:
                    userService.searchTask();
                    break;
                case 6:
                    userService.updateTaskByID();
                    break;
                case 7:
                    userService.assignTaskToUser();
                    break;
                case 8:
                    userService.showTasksForVisitor();
                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }
    }
}
