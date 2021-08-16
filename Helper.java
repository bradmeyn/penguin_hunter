import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * This is the helper/utility class with various static methods that assist with elements within the game.
 * @author Brad Meyn
 * @version ver 1.0.0
 */

public class Helper {


    public Helper()
    {

    }

    /**
    * Method for taking an int value from the user (usually for selecting an option)
    * 
    * @param    displayMessage     String that will be displayed to the user as the prompt
    * @return                      int value entered by the user
    */
    public static int inputInt(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        boolean valid = false;
        int inputValue = 0;
        while(!valid)
        {
            try
            {
                System.out.println(displayMessage);
                inputValue = Integer.parseInt(console.nextLine());
                valid = true;

            }
            catch(NumberFormatException e)
            {
                System.out.println("\n!!!!! You have not entered a valid integer !!!!!\n");
                valid = false;
            }
        }

        return inputValue;
    }

    /**
    * Method for taking a String value from the user 
    * 
    * @param    displayMessage     String that will be displayed to the user as the prompt
    * @return                      String value entered by the user
    */
    public static String inputString(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        String inputValue = console.nextLine();
        // console.close();
        return inputValue;
    }


    /**
    * Method to validate whether entered string meets length requirements
    * 
    * @param    string      String to be assessed
    * @param    min         int value for minimum length
    * @param    max         int value for maximum length
    * @return               boolean representing if string is valid length (true) or not (false)
    */
    public static boolean isValidLength(String string, int min, int max)
    {
        if(string.length() >= min && string.length() <= max){
            return true;
        }
        System.out.println("\n!!!!! Input is outside the length requirements !!!!!\n");
        return false;
    }

    /**
    * Method to validate selected number is within allowed bounds
    * 
    * @param    choice      number being assessed
    * @param    min         int value for minimum num allowed
    * @param    max         int value for maximum num allowed
    * @return               boolean representing if selection is within allowed range (true) or not (false)
    */
    public static boolean isValidNumOption(int choice, int min, int max)
    {
        if(choice >= min && choice <= max)
        {
            return true;
        }
        System.out.println("\n!!!!! That number does not represent one of the options provided !!!!!\n");
        return false;
    }
    

    /**
    * Method to check if string is blank
    * 
    * @param    string      String to be assessed
    * @return               boolean representing if string is blank (true) or not (false)
    */
    public static boolean isBlank(String string)
    {
        if(string.trim().length() == 0)
        {
            System.out.println("\n!!!!!Your input cannot be blank!!!!!\n");
            return true;
        }
        
        return false;
    }

   /**
    * Method to validate whether entered value is an integer
    * 
    * @param    string      String to be assessed
    * @return               boolean representing if string is an integer (true) or not (false)
    */
    public static boolean isInt(String input)
    {
        try
        {
            Integer.parseInt(input);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Input is not a number");
            return false;
        }
        return true;
    }

    /**
    * Method for adding text decoration
    * 
    * @return       String with lines for decoration/space between text
    */
    public static String lines()
    {
        return "==============================================================================================\n" +
        "==============================================================================================\n" +
        "==============================================================================================\n";
    }

    /**
    * Method to hold console up allowing user to read displayed items.
    * 
    */
    public static void proceed()
    {
        boolean invalidInput = true;
        while(invalidInput)
        {
            try
            {
                System.out.println(
                Helper.lines() +
                "\nPress Enter/Return key to continue\n\n");
                Scanner console = new Scanner(System.in);
                console.nextLine();
                invalidInput = false;
            }
            catch(IllegalArgumentException e)
            {
                invalidInput = true;
                System.out.println(Helper.lines() + "\nThat is not the enter/return key :(\n");
            }
        }
    }


    /**
    * Class for text decoration
    * 
    * @param    min       minimum int of range to generate a random number
    * @param    max       maximum int of range to generate a random number
    * @return             random int number between specified ranges
    */
    public static int randomNum(int min, int max)
    {
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }
}
