import java.util.*;


class User{
    String name;
    String[] taskArray = new String[5];
    public User(String name){
        this.name = name;
    }

    public User(String name, String[] taskArray) {
        this.name = name;
        this.taskArray = taskArray;
    }

    // Method to print the user's name
    public void printName(){
        System.out.println("User Name :" + name);
    }

    // Method to display all tasks
    public void showTasks(){
        System.out.println("Tasks :" + Arrays.toString(taskArray));
    }

    public boolean addTask(String tasks){
        String[] taskSplit= tasks.split(",");
        if (taskSplit.length != 5) {
            System.out.println("Error: Please enter exactly 5 tasks separated by commas.");
            return false;
        } else {
            taskArray = taskSplit;
            return true;
        }
    }
    public void showRepeatedTasks(){
        HashSet<String> taskSet = new HashSet<String>();
        boolean hasRepeats = false;
        System.out.println("Repeated Tasks :");
        for(String task : taskArray){
            if(taskSet.contains(task)){
                System.out.println(task);
                hasRepeats = true;
            }else{
                taskSet.add(task);
            }
        }
        if (!hasRepeats) {
            System.out.println("No repeated tasks found.");
        }
    }

    public void showIncreasingOrder(){
        String[] tasksCopy = taskArray.clone();
        Arrays.sort(tasksCopy);
        System.out.println("IncreasingOrder");
        for(String task : tasksCopy){
            System.out.println(task);
        }
    }
    public void showDecreasingOrder(){
        String[] tasksCopy = taskArray.clone();
        Arrays.sort(tasksCopy, Collections.reverseOrder());
        System.out.println("DecreasingOrder");
        for(String task : tasksCopy){
            System.out.println(task);
        }

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name of the user: ");

        String name = sc.nextLine();
        User user = new User(name);
        System.out.println("User is created!");

        // Input tasks ensuring exactly 5 tasks are entered
        String inputs = "";
        boolean done = false;
        while (!done) {
            System.out.println("Please enter 5 tasks separated by commas: ");
            inputs = sc.nextLine();
            done = user.addTask(inputs);
        }

        // Choose order of displaying tasks
        System.out.println("Please choose to see all the tasks: 1: increasing 2 : decreasing:");
        int taskChoice = sc.nextInt();
        switch(taskChoice){
            case 1:
                user.showIncreasingOrder();
                break;
            case 2:
                user.showDecreasingOrder();
                break;
            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
                break;
        }

        //Show the user info
        System.out.println("User Info: ");
        user.printName();
        user.showTasks();
        user.showRepeatedTasks();

    }
}

