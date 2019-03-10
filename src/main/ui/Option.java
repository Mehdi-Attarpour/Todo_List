package main.ui;

import main.data.Status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Hold all options and validator methods
 */
public class Option {
    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");

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
        return this.integerValidator(1,6);
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
        return this.integerValidator(1,6);
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
        return this.integerValidator(1,5);
    }

    /**
     * Check if the user enters a valid number within the specified range.
     * @param min The lower bond for user scanner.
     * @param max The upper bond for user scanner.
     * @return The valid number within the range.
     */
    public int integerValidator(int min, int max){
        int number;
        do {
            System.out.printf("hint: Please choose a number in range %d to %d\n", min, max);
            while (!scanner.hasNextInt()) {
                System.out.printf("NOT a number! PLEASE choose a number in range %d to %d \n", min, max);
                scanner.next();
            }
            number = scanner.nextInt();

        } while(number > max || number < min);
        return number;
    }

    /**
     * Check if user enters the valid status.
     * @return The valid status.
     */

    public Status statusValidator(){
        Boolean isValid = false;
        Status validStatus = Status.Not_Done;
        System.out.println("hint: Please enter 'Done' or 'Not_Done'");
        do {
            try {
                validStatus = Status.valueOf(scanner.next());
                isValid = true;
            } catch (Exception e){
                System.out.println("PLEASE enter the EXACT word 'Done' or 'Not_Done;");
            }
        }
        while(isValid == false);
        return validStatus;
    }

    /**
     * Check if the user enters a valid date.
     * @return The valid date.
     */
    public LocalDate dateValidator(){
        Boolean isValid = false;
        LocalDate validDate = LocalDate.now();
        System.out.println("Please Enter date in this format! yyyy-MM-dd");
        do {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                validDate = LocalDate.parse(scanner.next(), formatter);
                isValid = true;

            } catch (Exception e) {
                System.out.println("PLEASE Enter date in correct format! like 2019-04-13");
            }
        }
        while (isValid == false);
        return validDate;
    }

    /**
     * Check if user insert valid string (not empty)
     * @return The valid string.
     */
    public String stringValidator(){
        Boolean isValid = false;
        String validString;
        do {
                validString = scanner.next();
                if (validString.trim().length() != 0){
                    isValid = true;
                } else {
                    System.out.println("PLEASE USE your keyboard to TYPE!!!");
                }
        }
        while (isValid == false);
        return validString;
    }
}
