import java.util.*;


class User {
    String name;
    ArrayList<Task> taskArray;

    public User(String name) {
        this.name = name;
        taskArray = new ArrayList<>();
    }

    public User(String name, ArrayList<Task> taskArray) {
        this.name = name;
        this.taskArray = taskArray;
    }

    // Method to print the user's name
    public void printName() {
        System.out.println("User Name :" + name);
    }

    // Method to display all tasks
    public void showTasks() {
        if (taskArray.isEmpty()) {
            System.out.println("No tasks found");
        } else {
            for (Task task : taskArray) {
                System.out.println(task);
            }
        }
    }

    public void addTask(Task task) {
        taskArray.add(task);
        System.out.println("Task added: " + task);
    }

    public void deleteTaskByName(String taskName) {
        boolean found = false;
        for (Task task : taskArray) {
            if (task.getTaskTitle().equals(taskName)) {
                taskArray.remove(task);
                found = true;
                System.out.println("Task removed: " + taskName);
                break;
            }
        }
        if (!found) {
            System.out.println("Task not found.");
        }

    }

    public void deleteTaskById(int id) {
        boolean found = false;
        for (Task task : taskArray) {
            if (task.getTaskId() == id) {
                taskArray.remove(task);
                found = true;
                System.out.println("Task removed: " + task.getTaskTitle());
                break;
            }
        }
        if (!found) {
            System.out.println("Invalid task id.");
        }
    }

    public void updateTask(int index, String newTask) {
        if (index >= 0 && index < taskArray.size()) {
            for (Task task : taskArray) {
                if (task.getTaskId() == index) {
                    task.setTaskTitle(newTask);
                    System.out.println("Task updated successfully!");
                }
            }
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void searchTask(String taskName) {
        boolean found = false;
        for (Task task : taskArray) {
            if (task.getTaskTitle().equals(taskName)) {
                System.out.println("Task found: " + task);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Task not found.");
        }
    }

    public void showRepeatedTasks() {
        HashSet<String> taskSet = new HashSet<String>();
        boolean hasRepeats = false;
        System.out.println("Repeated Tasks :");
        for (Task task : taskArray) {
            if (taskSet.contains(task.getTaskTitle())) {
                System.out.println(task);
                hasRepeats = true;
            } else {
                taskSet.add(task.getTaskTitle());
            }
        }
        if (!hasRepeats) {
            System.out.println("No repeated tasks found.");
        }
    }

    public void showIncreasingOrder() {
        if (taskArray.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            taskArray.sort(Comparator.comparing(Task::getTaskTitle));

            System.out.println("Tasks in increasing order by Name:");
            for (Task task : taskArray) {
                System.out.println(task.toString());
            }
        }
    }

    public void showDecreasingOrder() {
        if (taskArray.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            taskArray.sort(Comparator.comparing(Task::getTaskTitle).reversed());
            System.out.println("Tasks in decreasing order by Name:");
            for (Task task : taskArray) {
                System.out.println(task.toString());
            }
        }

    }


}



