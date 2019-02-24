package main.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Hold all options and validator methods
 */
public class Option {
    static Scanner input = new Scanner(System.in).useDelimiter("\n");

    /**
     * Show the first menu for user to choose.
     * @return The valid option number.
     */
    public int firstOptions(){
        System.out.println( ">> Pick an option:\n" +
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
     * @return the valid option number.
     */
    public int showTaskOptions(){
        System.out.println("----------------------------------------------------------");
        System.out.println( ">>> How do you want to see the Tasks:\n" +
                "\t>>> (1) Show all Tasks sorted by title \n" +
                "\t>>> (2) Show all Tasks sorted by due date \n" +
                "\t>>> (3) Show all Tasks grouped by project \n" +
                "\t>>> (4) Show Task List of a certain project\n" +
                "\t>>> (5) Show Task List with certain due date\n" +
                "\t>>> (6) Back");
        return this.integerValidator(1,6);
    }


    /**
     * Check if the user input value is a number within the specified range.
     * @param min The lower bond for user input.
     * @param max The upper bond for user input.
     * @return The valid number within the range.
     */
    public int integerValidator(int min, int max){
        int number;
        do {
            System.out.printf("\nhint: Please choose a number in range %d to %d\n", min, max);
            while (!input.hasNextInt()) {
                System.out.printf("Not a number! Please choose a number in range %d to %d \n", min, max);
                input.next();
            }
            number = input.nextInt();

        } while(number > max || number < min);
        return number;
    }

    /**
     * Check if the user input string is a valid date.
     * @return The valid date.
     */
    public LocalDate dateValidator(){
        Boolean success = false;
        LocalDate date = LocalDate.now();
        do {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(input.next(), formatter);
                success = true;

            } catch (Exception e) {
                System.out.println("Please Enter date in correct format! like 2019-04-13");
            }
        }
        while (success == false);
        return date;
    }
}
