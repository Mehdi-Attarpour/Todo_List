package main.ui;

import main.data.Project;
import main.data.Task;
import main.data.TaskList;
import main.print.Print;

import java.time.LocalDate;
import java.util.Scanner;


public class UI {

    private TaskList list;
    private Option opt;
    private static Scanner input = new Scanner(System.in).useDelimiter("\n");

    /**
     * Constructor for UI.
     * Assign new Option instance to opt field
     */
    public UI(){
        this.list = new TaskList();
        opt = new Option();
    }

    /**
     * Start the program with message welcome
     * and also a summary of data saved before.
     */
    public void welcome() {
        System.out.println("\n>> Welcome to TODO-List APP");
        System.out.println(list.summary() + "\n");
        this.firsCommand();
    }

    /**
     * Show first menu options
     * and invoke right method regarding user's choice
     */
    public void firsCommand() {
        switch (opt.firstOptions()) {

            case 1: runShowTaskCommand(); break;
            case 2 : runAddTaskCommand(); break;
//            case 3 : runEditCommand(); break;
//            case 4 : runLoadCommand(); break;
//            case 5 : runSaveCommand(); break;
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
                getProjectNameCommand();
                Print.printList(this.list.taskByProject(input.next()));
                this.runShowTaskCommand();
                break;
            }
            case 5: {
                Print.printList(this.list.taskByDueDate(getDueDate()));
                this.runShowTaskCommand();
                break;
            }
            case 6: {
                this.firsCommand();
            }
        }
    }

    /**
     * Add task if it is not already in task list.
     */
    private void runAddTaskCommand() {
        System.out.println("Please insert the Title: ");
        String title = input.next();
        System.out.println("Please insert the due date: ");
        LocalDate dueDate = opt.dateValidator();
        System.out.println("Please insert the project name");
        Project project = new Project(input.next());
        if(this.list.addTask(title, dueDate, project)){
            System.out.println("Task added successfully!!!");
            System.out.println("\n\t\tNote\n\t\tThe Task status is set as 'Not_Done'.\n\t\tIf you want to change it please choose edit option.\n");
            this.firsCommand();
        } else {
            System.out.println("\nYou already added the same task. Please add NEW task.\n");
            this.runAddTaskCommand();
        }
    }

    /**
     * Ask Which project from user.
     */
    public void getProjectNameCommand() {
        System.out.println("Which project?");
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
     * Quit the program.
     */
    private void runQuitCommand() {
        System.exit(0);
    }
}
