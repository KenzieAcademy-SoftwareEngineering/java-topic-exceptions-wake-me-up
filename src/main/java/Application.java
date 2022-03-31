import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

//program that simulates waking up and getting ready in the morning with error handling
public class Application {

    public static void main(String[] args)   {
        //Pseudocode:
        //1. Alarm goes off
        //2. check awake status - if not awake, alarm goes off again
        //3. get breakfast
        //  3a. if unable to get breakfast, continue
        //  3b. Unless there is no coffee -- that is a catastrophic failure!
        //4. get dressed
        //5. Leave or login for work

        try {
            boolean isAwake = false;
            //set this to "OFF" to throw error when afer soundAlarm
            String power = "ON";
            System.out.println("Morning time: It's 6am!");

            try {
                // alarm goes off
                if (soundAlarm(power)) {
                    System.out.println("BEEP!");
                    isAwake = true;
                } else {
                    throw new InterruptedException("Alarm did not go off.");
                }

            } catch (InterruptedException e) {
                //do not do anything
                System.out.println(e.getMessage());
                return;
            } catch (NumberFormatException e) {
                System.out.println("Bad input, but okay to continue");
            } catch (Exception e) {
                System.out.println("Unexpected exception:" + e.getMessage());
            }

            if(isAwake) {
                System.out.println("I'm awake!");
            }

            //getBreakfast
            System.out.println(Arrays.toString(getBreakfast("whats_in_the_kitchen.txt")));
        }
        catch (IOException|NoBreakfastException e) {
            System.out.println("Check the kitchen file. Unable to read.");
        }
    }

    //No exception thrown from method - main handles raising exception
    public static boolean soundAlarm(String power) {
        if(power.equals("ON")) {
            return true;
        }
        else {
            return false;
        }
    }

    //method to check the kitchen file for available food
    //exception is handled with try/catch, but then bubbled up by throwing another exception
    public static String[] getBreakfast (String kitchenFile) throws IOException {
        try {
            Path filePath = Path.of(kitchenFile);
            String kitchenContentsString = Files.readString(filePath);
            String[] kitchenContentsArray = kitchenContentsString.split("\n");
            return kitchenContentsArray;
        }
        catch (IOException e){
            throw new NoBreakfastException(e.getMessage(), e);
        }
    }
}