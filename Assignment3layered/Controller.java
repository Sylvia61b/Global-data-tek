import java.util.Scanner;
import java.util.InputMismatchException;

public class Controller {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService();

        while (true) {
            System.out.println("\nTodo Manager Menu:");
            System.out.println("0. Exit");
            System.out.println("1. Show Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Delete Task by Name");
            System.out.println("4. Delete Task by ID");
            System.out.println("5. Search Task");
            System.out.println("6. Update A Task");
            System.out.println("7. Assign Task to a user");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 0:
                    System.out.println("Exiting Todo Manager.");
                    scanner.close();
                    System.exit(0);
                    break;
                case 1:
                    taskService.showTasks();
                    break;
                case 2:
                    taskService.addTask();
                    break;
                case 3:
                    taskService.deleteTaskByName();
                    break;
                case 4:
                    taskService.deleteTaskByID();
                    break;
                case 5:
                    taskService.searchTask();
                    break;
                case 6:
                    taskService.updateTaskByID();
                    break;
                case 7:
                    taskService.assignTaskToUser();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }
    }
}
