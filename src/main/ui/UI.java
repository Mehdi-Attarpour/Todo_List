package main.ui;

import main.FileHandler;
import main.data.Project;
import main.data.Status;
import main.data.Task;
import main.data.TaskList;
import main.print.Print;

import java.time.LocalDate;


public class UI {

    private TaskList list;
    private Option opt;
    private FileHandler fileHandler;

    /**
     * Constructor for UI.
     * Assign new Option instance to opt field
     */
    public UI(){
        this.list = new TaskList();
        opt = new Option();
        fileHandler = new FileHandler();
    }

    /**
     * Start the program with message welcome
     * and also a summary of data saved before.
     */
    public void welcome() {
        this.list = fileHandler.load();
        System.out.println("\n>> Welcome to TODO-List APP");
        System.out.println(list.summary() + "\n");
        this.firstCommand();
    }

    /**
     * Show first menu options
     * and invoke right method regarding user's choice
     */
    public void firstCommand() {
        switch (opt.firstOptions()) {

            case 1 : runShowTaskCommand(); break;
            case 2 : runAddTaskCommand(); break;
            case 3 : runEditCommand(); break;
            case 4 : runLoadCommand(); break;
            case 5 : runSaveCommand(); break;
            case 6 : runQuitCommand(); break;
        }
    }

    /**
     * Show the menu for how user wants to see the tasks
     * and invoke right method regarding user's choice
     */
    public void runShowTaskCommand() {

        switch (opt.showTaskOptions()) {
            case 1: {
                Print.printList(this.list.sortTaskListByTitle());
                this.runShowTaskCommand();
                break;
            }
            case 2: {
                Print.printList(this.list.sortTaskListByDueDate());
                this.runShowTaskCommand();
                break;
            }
            case 3: {
                Print.printMap(this.list.groupedByProject());
                this.runShowTaskCommand();
                break;
            }
            case 4: {
                Print.printList(this.list.taskByProject(getProjectNameCommand()));
                this.runShowTaskCommand();
                break;
            }
            case 5: {
                Print.printList(this.list.taskByDueDate(getDueDate()));
                this.runShowTaskCommand();
                break;
            }
            case 6: {
                this.firstCommand();
            }
        }
    }

    /**
     * Add task if it is not already in task list.
     */
    private void runAddTaskCommand() {
        System.out.println("Please insert the Title: ");
        String title = opt.stringValidator();
        System.out.println("Please insert the due date: ");
        LocalDate dueDate = opt.dateValidator();
        System.out.println("Please insert the project name");
        Project project = new Project(opt.stringValidator());
        if(this.list.addTask(title, dueDate, project, Status.Not_Done)){
            System.out.println("Task Added successfully!!!");
            Print.printTask(list.get(list.size()-1));
            System.out.println("\t\tNote\n\t\tThe Task status is set as 'Not_Done'.\n\t\tIf you want to change it please choose edit option.\n");
            this.firstCommand();
        } else {
            this.runAddTaskCommand();
        }
    }

    /**
     * Edit and update task if it won't be a duplicate task.
     */
    public void runEditCommand(){
        if(this.list.size() == 0){
            System.out.println("---------------------------------------------------------");
            System.out.println("\nThere is nothing to edit. Please add Task first.");
            System.out.println("---------------------------------------------------------");
        } else {
            Print.printList(list );
            Task taskToEdit = this.getOneTaskToEdit();
            switch (opt.showEditTaskOption()){
                case 1 : this.editTitle(taskToEdit);
                case 2 : this.editDueDate(taskToEdit);
                case 3 : this.editProject(taskToEdit);
                case 4 : this.editStatus(taskToEdit);
                case 5 : this.removeTask(taskToEdit);
            }
        }

    }

    /**
     * Get access to the task for editing.
     * @return The task to edit.
     */
    public Task getOneTaskToEdit(){
        System.out.println("\nPlease confirm which task do you want to edit (Enter the number of task)");
        int id = opt.integerValidator(1, list.size());
        return list.get(id - 1);
    }

    /**
     * Ask Which project from user.
     */
    public String getProjectNameCommand() {
        System.out.println("Which project?");
        return opt.stringValidator();
    }

    /**
     * Get a date from user.
     * @return The valid date.
     */
    public LocalDate getDueDate() {
        System.out.println("Which date?");
        return opt.dateValidator();
    }

    /**
     * Get valid task title.
     * @return The valid task title.
     */
    public String getNewTaskTitle(){
        System.out.println("Please enter new title to update the task:");
        return opt.stringValidator();
    }

    /**
     * Update task's title.
     * @param taskToEdit Task to be updated.
     */
    public void editTitle(Task taskToEdit){
        String title = getNewTaskTitle();
        LocalDate dueDate = taskToEdit.getDueDate();
        Project project = taskToEdit.getProject();
        Status status = taskToEdit.getStatus();
        updateTask(taskToEdit, title, dueDate, project, status);
    }

    /**
     * Get valid date.
     * @return The valid date.
     */
    private LocalDate getNewDueDate() {
        System.out.println("Please enter new due date to update the task");
        return opt.dateValidator();
    }

    /**
     * Update task's due date.
     * @param taskToEdit Task to be updated.
     */
    public void editDueDate(Task taskToEdit){
        LocalDate dueDate = getNewDueDate();
        String title = taskToEdit.getTitle();
        Project project = taskToEdit.getProject();
        Status status = taskToEdit.getStatus();
        updateTask(taskToEdit, title, dueDate, project, status);
    }

    /**
     * Get valid project title.
     * @return The valid project's title.
     */
    private Project getNewProject() {
        System.out.println("Please enter new project to update the task");
        Project newProject = new Project(opt.stringValidator());
        return newProject;
    }

    /**
     * Update task's project.
     * @param taskToEdit Task to be updated.
     */
    public void editProject(Task taskToEdit){
        Project project = getNewProject();
        String title = taskToEdit.getTitle();
        LocalDate dueDate = taskToEdit.getDueDate();
        Status status = taskToEdit.getStatus();
        updateTask(taskToEdit, title, dueDate, project, status);
    }

    /**
     * Get new valid status.
     * @return The valid Status.
     */
    private Status getNewStatus() {
        System.out.println("Please enter new Status to update the task");
        return opt.statusValidator();
    }

    /**
     * Update task's status.
     * @param taskToEdit Task to be updated.
     */
    public void editStatus(Task taskToEdit){
        String title = taskToEdit.getTitle();
        LocalDate dueDate = taskToEdit.getDueDate();
        Project project = taskToEdit.getProject();
        Status status = getNewStatus();
        updateTask(taskToEdit, title, dueDate, project, status);
    }

    /**
     * Delete task.
     * @param taskToRemove Task to be removed.
     */
    public void removeTask(Task taskToRemove){
        if(list.remove(taskToRemove)){
            System.out.println("Task removed successfully.");
            firstCommand();
        } else {
            System.out.println("Something is wrong pleas try again.");
            firstCommand();
        }
    }

    /**
     * Update Task if there won't be duplicate task
     * if the task can be updated with new fields it remove the task and create a new
     * task with updated values.
     * @param task Task to be updated.
     * @param title New title.
     * @param dueDate New due date.
     * @param project New project.
     * @param status New status.
     */
    public void updateTask(Task task,String title, LocalDate dueDate, Project project, Status status){
        if(list.addTask(title, dueDate, project, status)){
            list.remove(task);
            System.out.println();
            System.out.println("Task is updated successfully.");
            Print.printTask(list.get(list.size()-1));
            firstCommand();
        } else {
            System.out.println("Something is wrong pleas try again.");
            firstCommand();
        }
    }

    /**
     * Save the task list a file.
     */

    public void runSaveCommand(){
        if(fileHandler.save(list)){
            System.out.println("Saved Successfully");
            firstCommand();
        }
    }

    /**
     * Load the saved task list.
     */
    public void runLoadCommand(){
        this.list = fileHandler.load();
            firstCommand();
    }

    /**
     * Quit the program.
     */
    private void runQuitCommand() {
        System.exit(0);
    }
}
