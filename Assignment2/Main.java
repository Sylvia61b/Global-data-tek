import java.util.Scanner;
import java.util.InputMismatchException;




public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        User user = new User(userName);


        while (true) {
            System.out.println("\nTodo Manager Menu:");
            System.out.println("0. Exit");
            System.out.println("1. Print User Name");
            System.out.println("2. Show Tasks");
            System.out.println("3. Add Task");
            System.out.println("4. Delete Task by Name");
            System.out.println("5. Delete Task by ID");
            System.out.println("6. Search Task");
            System.out.println("7. Show Repeated Tasks");
            System.out.println("8. Show Tasks in Increasing Order");
            System.out.println("9. Show Tasks in Decreasing Order");
            System.out.println("10. Update A Task");
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
                    user.printName();
                    break;
                case 2:
                    user.showTasks();
                    break;
                case 3:
                    System.out.println("Please enter the number of tasks you would like to add: ");
                    int count = 0;
                    try {
                        count = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.nextLine();
                        break;
                    }
                    while (count > 0) {
                        System.out.print("Enter task to add: ");
                        String taskToAdd = scanner.nextLine();
                        user.addTask(taskToAdd);
                        count--;
                    }
                    break;
                case 4:
                    System.out.print("Enter name of task to delete: ");
                    String taskNameToDelete = scanner.nextLine();
                    user.deleteTaskByName(taskNameToDelete);
                    break;
                case 5:
                    System.out.print("Enter index of task to delete: ");
                    int indexToDelete = scanner.nextInt();
                    scanner.nextLine();
                    user.deleteTaskById(indexToDelete);
                    break;
                case 6:
                    System.out.print("Enter task to search: ");
                    String taskToSearch = scanner.nextLine();
                    user.searchTask(taskToSearch);
                    break;
                case 7:
                    user.showRepeatedTasks();
                    break;
                case 8:
                    user.showIncreasingOrder();
                    break;
                case 9:
                    user.showDecreasingOrder();
                    break;
                case 10:
                    System.out.print("Enter the index to update: ");
                    int indexToUpdate = scanner.nextInt();
                    System.out.println("Enter new task name to update: ");
                    String newTask = scanner.nextLine();
                    user.updateTask(indexToUpdate, newTask);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}