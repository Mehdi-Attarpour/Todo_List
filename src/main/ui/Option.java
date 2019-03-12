package main.ui;

/**
 * Hold all options and validator methods
 */
public class Option {
    private Validator validator = new Validator();

    /**
     * Show the first menu for user to choose.
     * @return The valid option number.
     */
    public int firstOptions(){
        System.out.println( "\n>> Pick an option:\n" +
                ">> (1) Show Tasks\n" +
                ">> (2) Add New Task\n" +
                ">> (3) Edit Task (update, mark as done, remove)\n" +
                ">> (4) Load Tasks\n" +
                ">> (5) Save\n" +
                ">> (6) Quit\n");
        return validator.integerValidator(1,6);
    }

    /**
     * Show the menu for display tasks.
     * @return The valid option number.
     */
    public int showTaskOptions(){
        System.out.println( "\n>>> How do you want to see the Tasks:\n" +
                          "\t>>> (1) Show all Tasks sorted by title \n" +
                          "\t>>> (2) Show all Tasks sorted by due date \n" +
                          "\t>>> (3) Show all Tasks grouped by project \n" +
                          "\t>>> (4) Show Task List of a certain project\n" +
                          "\t>>> (5) Show Task List with certain due date\n" +
                          "\t>>> (6) Back");
        return validator.integerValidator(1,6);
    }

    /**
     * Show the menu for edit options
     * @return The valid option number
     */
    public int showEditTaskOption(){
        System.out.println( "\n>>> What do you want to edit?\n" +
                            "\t>>> (1) Title\n" +
                            "\t>>> (2) Due Date\n" +
                            "\t>>> (3) Project\n" +
                            "\t>>> (4) Status\n" +
                            "\t>>> (5) Remove"
        );
        return validator.integerValidator(1,5);
    }
}
