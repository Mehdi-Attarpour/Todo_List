package main.ui;

import main.data.Status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Validator {
    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");

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
        while(!isValid);
        return validStatus;
    }

    /**
     * Check if the user enters a valid date.
     * @param oldestValidDate Shows date must be after which date, useful when the date must be future.
     * @return The valid date.
     */
    public LocalDate dateValidator(LocalDate oldestValidDate){
        Boolean isValid = false;
        LocalDate validDate = LocalDate.now();
        System.out.println("Please Enter date in this format! yyyy-MM-dd");
        do {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                validDate = LocalDate.parse(scanner.next(), formatter);
                if(validDate.compareTo(oldestValidDate) > 0) {
                    isValid = true;
                } else {
                    System.out.println("Check your calendar, you entered a past date, Sorry dude you may missed the deadline." +
                                        "\nEnter a future date PLEASE!!!");
                }
            } catch (Exception e) {
                System.out.println("PLEASE Enter date in correct format! like 2019-04-13");
            }
        } while (!isValid);
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
        while (!isValid);
        return validString;
    }
}
