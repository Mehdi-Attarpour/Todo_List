package main.print;

import main.data.Project;
import main.data.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Print {

    private Print(){
    }

    public static void divider(){
        System.out.println("---------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Print task.
     * @param task Task to be prited.
     */
    public static void printTask(Task task){
        Print.divider();
        System.out.println(task);
        Print.divider();
        System.out.println();
    }

    /**
     * whenever a list asked by user is empty it will show this message.
     */
    public static void printEmpty(){
        Print.divider();
        System.out.println("There is NOTHING to show!!!!");
        Print.divider();
    }

    /**
     * Print all task in given list if the given list is not empty
     * otherwise prints there is nothing to show!
     * @param list An ArrayList of tasks to print one by one
     */
    public static void printList(ArrayList<Task> list){
        if(list != null && list.size() != 0 ){
            for(Task task: list){
                System.out.println("\t\t(" + Integer.toString(list.indexOf(task) + 1) + ")" + task);
            }
        } else {
            Print.printEmpty();
        }
    }

    /**
     * Print the given map if it is not empty
     * otherwise print there is nothing to show!
     * @param map A map of tasks grouped by project to print.
     */
    public  static void printMap(Map<Project, List<Task>> map){
        if(map != null && map.size() != 0 ) {
            Print.divider();
            for (Map.Entry<Project, List<Task>> entry : map.entrySet()) {
                System.out.println("Project: " + entry.getKey().getTitle());

                List<Task> listOfTaskInProject = entry.getValue();

                for (Task task : listOfTaskInProject) {
                    System.out.println(task);
                }
                Print.divider();
            }
        } else {
            Print.printEmpty();
        }
    }
}
