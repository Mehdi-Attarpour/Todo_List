package main.print;

import main.data.Project;
import main.data.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Print {

    private Print(){

    }

    /**
     * Print task.
     * @param task Task to be prited.
     */
    public static void printTask(Task task){
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println(task);
        System.out.println("---------------------------------------------------------------------------------------------------------------\n");
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
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.println("There is NOTHING to show!!!!");
            System.out.println("---------------------------------------------------------------------------------------------------------------");
        }
    }

    /**
     * Print the given map if it is not empty
     * otherwise print there is nothing to show!
     * @param map A map of tasks grouped by project to print.
     */

    public  static void printMap(Map<Project, List<Task>> map){
        if(map != null && map.size() != 0 ) {
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            for (Map.Entry<Project, List<Task>> entry : map.entrySet()) {
                System.out.println("Project: " + entry.getKey().getTitle());

                List<Task> listOfTaskInProject = entry.getValue();

                for (Task task : listOfTaskInProject) {
                    System.out.println(task);
                }
                System.out.println("---------------------------------------------------------------------------------------------------------------");
            }
        } else {
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.println("There is NOTHING to show!!!!");
            System.out.println("---------------------------------------------------------------------------------------------------------------");
        }
    }
}
