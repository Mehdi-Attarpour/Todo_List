package main.data;

import main.comparator.DateComparator;
import main.comparator.TitleComparator;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class to create a collection for tasks.
 * It inherits from Arraylist interface so it also can
 * use all ArrayList methods.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Remove task by its title.
     * @param taskTitle Task's title which will be removed
     */
    public void removeTask(String taskTitle){
        this.removeIf(x -> x.getTitle().equalsIgnoreCase(taskTitle));
    }

    /**
     * Filter the all tasks with 'Done' status
     * @return An ArrayList of tasks with 'Done' status
     */
    public ArrayList<Task> doneTasks() {
        return (ArrayList<Task>)
                this.stream()
                        .filter(x -> x.getStatus() == Status.Done)
                        .collect(Collectors.toList());
    }

    /**
     * Filter all tasks with 'Not_Done' status.
     * @return An ArrayList of tasks with 'Not_Done' status.
     */
    public ArrayList<Task> toDoTasks() {
        return (ArrayList<Task>)
                this.stream()
                        .filter(x -> x.getStatus() == Status.Not_Done)
                        .collect(Collectors.toList());
    }

    /**
     * Filter all tasks which assigned to specific project.
     * @param projectName The name of project.
     * @return An ArrayList of all tasks assigned to the given project.
     */
    public ArrayList<Task> taskByProject(String projectName) {
        return (ArrayList<Task>)
                this.stream()
                        .filter(x -> x.getProject().getTitle().equalsIgnoreCase(projectName))
                        .collect(Collectors.toList());
    }

    /**
     * Filter all tasks whit specific due date.
     * @param dueDate The due date.
     * @return An ArrayList of all tasks with due date equals to given date.
     */
    public ArrayList<Task> taskByDueDate(LocalDate dueDate) {
        return (ArrayList<Task>)
                this.stream()
                        .filter(x -> x.getDueDate().equals(dueDate))
                        .collect(Collectors.toList());
    }

    /**
     * Sort the tasks according to their titles.
     * @return An ArrayList of sorted task by title.
     * @see TitleComparator
     */

    public ArrayList<Task> sortTaskListByTitle(){
        ArrayList<Task> taskListSortedByTitle = (ArrayList) this.clone();
        Collections.sort(taskListSortedByTitle, new TitleComparator());
        return taskListSortedByTitle;
    }

    /**
     * Sort all tasks according to their due dates.
     * @return An ArrayList of sorted task by due date
     * @see TitleComparator
     */
    public ArrayList<Task> sortTaskListByDueDate(){
        ArrayList<Task> taskListSortedByDate = (ArrayList) this.clone();
        Collections.sort(taskListSortedByDate, new DateComparator());
        return taskListSortedByDate;
    }

    /**
     * Grouped all tasks according to their projects.
     * @return A Map of all project as key and List of their tasks as value.
     */
    public Map<Project, List<Task>> groupedByProject(){
        return
                this.stream()
                        .collect(Collectors.groupingBy(Task::getProject));
    }

    /**
     * Provide a summery of all tasks and their status.
     * @return A string showing number of 'Done' and 'Not-Done' tasks.
     */

    public String summary() {
        long numberOfToDoTask =
                this.stream()
                        .filter(x -> x.getStatus() == Status.Not_Done)
                        .count();

        long numberOfDoneTask =
                this.stream()
                        .filter(x -> x.getStatus() == Status.Done)
                        .count();

        return String.format(">> You have %d Tasks todo and %d Tasks are done.", numberOfToDoTask, numberOfDoneTask);

    }

}
