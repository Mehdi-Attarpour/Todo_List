package main.ui;

import main.FileHandler;
import main.comparator.DateComparator;
import main.comparator.TitleComparator;
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
    private Validator validator;

    /**
     * Constructor for UI.
     * Assign new Option instance to opt field
     */
    public UI(){
        this.list = new TaskList();
        opt = new Option();
        fileHandler = new FileHandler("file");
        validator = new Validator();
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
        this.runQuitCommand();
    }

    /**
     * Show first menu options
     * and invoke right method regarding user's choice
     */
    public void firstCommand() {
        boolean running = true;
        while (running) {
            switch (opt.firstOptions()) {
                case 1: runShowTaskCommand(); break;
                case 2: runAddTaskCommand(); break;
                case 3: runEditCommand(); break;
                case 4: runLoadCommand(); break;
                case 5: runSaveCommand(); break;
                case 6: running = false;
            }
        }
    }

    /**
     * Show the menu for how user wants to see the tasks
     * and invoke right method regarding user's choice
     */
    public void runShowTaskCommand() {
        boolean running = true;
        while (running) {
            switch (opt.showTaskOptions()) {
                case 1: System.out.println("\n" + list.summary()); break;
                case 2: Print.printList(this.list.taskByStatus(Status.Done)); break;
                case 3: Print.printList(this.list.taskByStatus(Status.Not_Done)); break;
                case 4: Print.printList(this.list.sortList(new TitleComparator())); break;
                case 5: Print.printList(this.list.sortList(new DateComparator())); break;
                case 6: Print.printMap(this.list.groupedByProject()); break;
                case 7: Print.printList(this.list.taskByProject(getTaskDetail("Which project?"))); break;
                case 8: Print.printList(this.list.taskByDueDate(getDate("Which date?", LocalDate.parse("0001-01-01")))); break;
                case 9: running = false;
            }
        }
    }

    /**
     * Add task if it is not already in task list.
     */
    private void runAddTaskCommand() {
        String title = getTaskDetail("Please insert the Title: ");
        LocalDate dueDate = getDate("Please insert the due date: ", LocalDate.now());
        Project project = new Project(getTaskDetail("Please insert the project name"));
        if(this.list.addTask(title, dueDate, project, Status.Not_Done)){
            System.out.println("Task Added successfully!!!");
            Print.printTask(list.get(list.size()-1));
            System.out.println("\t\tNote\n\t\tThe Task status is set as 'Not_Done'.\n\t\tIf you want to change it please choose edit option.\n");
        } else {
            System.out.println("Something is wrong please try again.");;
        }
    }

    /**
     * Edit and update task if it won't be a duplicate task.
     */
    public void runEditCommand(){
        if(this.list.size() == 0){
            Print.divider();
            System.out.println("There is nothing to edit. Please add Task first.");
            Print.divider();
        } else {
            Print.printList(list );
            Task taskToEdit = this.getOneTaskToEdit();
            switch (opt.showEditTaskOption()){
                case 1 : this.editTitle(taskToEdit); break;
                case 2 : this.editDueDate(taskToEdit); break;
                case 3 : this.editProject(taskToEdit); break;
                case 4 : this.editStatus(taskToEdit); break;
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
        int id = validator.integerValidator(1, list.size());
        return list.get(id - 1);
    }

    /**
     * Get valid task title or project title from user.
     * @param question Shows what user must enter.
     * @return The valid title for task or project.
     */
    public String getTaskDetail(String question){
        System.out.println(question);
        return validator.stringValidator();
    }
    /**
     * Get a date from user.
     * @param question Show the Question that why Date is needed.
     * @param oldestValidDate Shows that date must be future or any date is valid.
     * @return The valid date.
     */
    public LocalDate getDate(String question, LocalDate oldestValidDate){
        System.out.println(question);
        return validator.dateValidator(oldestValidDate);
    }

    /**
     * Update task's title.
     * @param taskToEdit Task to be updated.
     */
    public void editTitle(Task taskToEdit){
        String title = getTaskDetail("Please enter new title to update the task:");
        LocalDate dueDate = taskToEdit.getDueDate();
        Project project = taskToEdit.getProject();
        Status status = taskToEdit.getStatus();
        updateTask(taskToEdit, title, dueDate, project, status);
    }

    /**
     * Update task's due date.
     * @param taskToEdit Task to be updated.
     */
    public void editDueDate(Task taskToEdit){
        LocalDate dueDate = getDate("Please enter new due date to update the task", LocalDate.parse("0000-01-01"));
        String title = taskToEdit.getTitle();
        Project project = taskToEdit.getProject();
        Status status = taskToEdit.getStatus();
        updateTask(taskToEdit, title, dueDate, project, status);
    }

    /**
     * Update task's project.
     * @param taskToEdit Task to be updated.
     */
    public void editProject(Task taskToEdit){
        Project project = new Project(getTaskDetail("Please enter new project to update the task"));
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
        return validator.statusValidator();
    }

    /**
     * Update task's status.
     * @param taskToEdit Task to be updated.
     */
    private void editStatus(Task taskToEdit){
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
    private void removeTask(Task taskToRemove){
        if(list.remove(taskToRemove)){
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Something is wrong pleas try again.");
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
    private void updateTask(Task task,String title, LocalDate dueDate, Project project, Status status){
        if(list.addTask(title, dueDate, project, status)){
            list.remove(task);
            System.out.println();
            System.out.println("Task is updated successfully.");
            Print.printTask(list.get(list.size()-1));
        } else {
            System.out.println("Something is wrong pleas try again.");
        }
    }

    /**
     * Save the task list a file.
     */
    private void runSaveCommand(){
        fileHandler.save(list);
    }

    /**
     * Load the saved task list.
     */
    private void runLoadCommand(){
        this.list = fileHandler.load();
    }

    /**
     * Quit the program.
     */
    private void runQuitCommand() {
        System.exit(0);
    }
}
