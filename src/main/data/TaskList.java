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
     * Add task to the task list only if there is no task with same fields.
     * if the project has the same name as any other previous tasks there is
     * no need to create a new Project otherwise new Project will be created.
     *
     * @param title   The title of new task.
     * @param dueDate The due date of new task.
     * @param project The project assigned to new task.
     * @return True if the adding new task is done.
     * @see Task for task equality conditions.
     * @see Project for projects equality conditions
     */
     public boolean addTask(String title, LocalDate dueDate, Project project, Status status) {
         boolean addSucceed = true;
         Task newTask = new Task(title, dueDate, project, status);
         for (Task task : this) {
             if (task.equals(newTask)) {
                 addSucceed = false;
                 System.out.print("\nYou already have the same task. Please try again\n");
                 return addSucceed;
             }
         }
         // This part prevents to create a new project if the project has been already created by previous task or tasks.
         for (Task task : this) {
             if (task.getProject().equals(project)) {
                 newTask.setProject(task.getProject());
             }
         }
         this.add(newTask);
         return addSucceed;
     }

    /**
     * Remove task by its title.
     * @param taskTitle Task's title which will be removed
     */
    public void removeAllTaskByTitle(String taskTitle) {
        this.removeIf(x -> x.getTitle().equalsIgnoreCase(taskTitle));
    }

    /**
     * Filter the all tasks regarding to status.
     * @return An ArrayList of tasks with desire status.
     */
    public ArrayList<Task> taskByStatus(Status status){
        return (ArrayList<Task>)
                this.stream()
                        .filter(x -> x.getStatus() == status)
                        .collect(Collectors.toList());
    }

    /**
     * Filter all tasks which assigned to specific project.
     *
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
     *
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
     * Sort the tasks according to given criteria.
     *
     * @return An ArrayList of sorted task by title.
     * @see TitleComparator
     * @see DateComparator
     */
    public ArrayList<Task> sortList(Comparator<Task> comparator){
        ArrayList<Task> sortedList = (ArrayList) this.clone();
        Collections.sort(sortedList, comparator);
        return sortedList;
    }

    /**
     * Grouped all tasks according to their projects.
     *
     * @return A Map of all project as key and List of their tasks as value.
     */
    public Map<Project, List<Task>> groupedByProject() {
        return
                this.stream()
                        .collect(Collectors.groupingBy(Task::getProject));
    }

    /**
     * Provide a summery of all tasks and their status.
     *
     * @return A string showing number of 'Done' and 'Not-Done' tasks.
     */
    public String summary() {
        int numberOfToDoTask = this.taskByStatus(Status.Not_Done).size();
        int numberOfDoneTask = this.taskByStatus(Status.Done).size();

        return String.format(">> You have %d Tasks todo and %d Tasks are done.", numberOfToDoTask, numberOfDoneTask);
    }

    @Override
    public boolean equals(Object o) {
        boolean equals = true;
        try {
            // Check if o is TaskList with the same size of this.
            if (!(o instanceof TaskList) || ((TaskList) o).size() != this.size()) {
                return false;
            } else {
                // Check if both TaskList contains the same Tasks.
                TaskList otherTaskList = (TaskList) o;
                for (Task task: this){
                    if(!otherTaskList.contains(task)){
                        equals = false;
                    }
                }
            }
            return equals;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
