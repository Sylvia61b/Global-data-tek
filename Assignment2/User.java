import java.util.*;


class User{
    String name;
    ArrayList<String> taskArray;
    public User(String name){
        this.name = name;
        taskArray = new ArrayList<>();
    }

    public User(String name, ArrayList<String> taskArray) {
        this.name = name;
        this.taskArray = taskArray;
    }

    // Method to print the user's name
    public void printName(){
        System.out.println("User Name :" + name);
    }

    // Method to display all tasks
    public void showTasks(){
        if(taskArray.isEmpty()){
            System.out.println("No tasks found");
        }else {
            for (String task : taskArray) {
                System.out.println(task);
            }
        }
    }

    public void addTask(String task){
        taskArray.add(task);
        System.out.println("Task added: " + task);
    }

    public void deleteTaskByName(String taskName) {
        if (taskArray.remove(taskName)) {
            System.out.println("Task removed: " + taskName);
        } else {
            System.out.println("Task not found.");
        }
    }

    public void deleteTaskById(int index){
        if (index >= 0 && index < taskArray.size()) {
            String removedTask = taskArray.remove(index);
            System.out.println("Task: " + removedTask+ " was removed.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void updateTask(int index, String newTask) {
        if (index >= 0 && index < taskArray.size()) {
            taskArray.set(index, newTask);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void searchTask(String task) {
        if (taskArray.contains(task)) {
            System.out.println("Task found: " + task);
        } else {
            System.out.println("Task not found.");
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
        if (taskArray.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            ArrayList<String> sortedTasks = new ArrayList<>(taskArray);
            Collections.sort(sortedTasks);
            System.out.println("Tasks in increasing order:");
            for (String task : sortedTasks) {
                System.out.println(task);
            }
        }
    }
    public void showDecreasingOrder(){
        if (taskArray.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            ArrayList<String> sortedTasks = new ArrayList<>(taskArray);
            sortedTasks.sort(Comparator.reverseOrder());
            System.out.println("Tasks in decreasing order:");
            for (String task : sortedTasks) {
                System.out.println(task);
            }
        }

    }
