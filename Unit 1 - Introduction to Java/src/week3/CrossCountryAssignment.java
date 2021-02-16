package week3;

import java.util.Scanner;

public class CrossCountryAssignment {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        trackRunner(in); // runner 1
        trackRunner(in); // runner 2
        trackRunner(in); // runner 3
        in.close();
    }

    /**
     * all the keyboard inputs, conversions, and calculations as well as printing the run summary condensed into one function 
     * it is easy to repeat and does not clog up the main function
     * 
     * @param in passes the scanner into this function
     */
    private static void trackRunner(Scanner in) {
        // keyboard inputs
        String fullName = getRunnerName(in);
        String timeToFirstMile = getRunTime(in, "the first mile");
        String timeToSecondMile = getRunTime(in, "the second mile");
        String timeToComplete = getRunTime(in, "complete the run");

        // converts inputted times to seconds
        double split1 = convertTimeToSeconds(timeToFirstMile);         
        double timeToMileTwo = convertTimeToSeconds(timeToSecondMile); 
        double timeToMileThree = convertTimeToSeconds(timeToComplete); 

        double split2 = timeToMileTwo - split1;          // subtracts the time to the end of the second mile by the time to the first mile to obtain the second split's time    
        double split3 = timeToMileThree - timeToMileTwo; // subtracts the time to complete the run by the time to the end of the second mile to obtain the third split's time
        
        // converts the split times (in seconds) into a string in the format "mm:ss.sss" that is ready to be printed
        String split1AsString = convertToString(split1); 
        String split2AsString = convertToString(split2);
        String split3AsString = convertToString(split3);

        // prints run summary
        System.out.println(fullName + " Run Summary:\n--------\nTime to Complete: " + timeToComplete + "\nSplit 1: " + split1AsString + "\nSplit 2: " + split2AsString + "\nSplit 3: " + split3AsString + "\n");
    }

    /**
     * converts the split time (in seconds) into a string with minutes and seconds
     * 
     * @param splitTime split time in seconds
     * @return the split time in a string in the format "mm:ss.sss"
     */
    private static String convertToString(double splitTime) {
        int minutes = (int)splitTime / 60;
        double seconds = splitTime % 60;
        return String.format("%d:%06.3f", minutes, seconds);
    }

    /**
     * converts a time into a double (seconds)
     * 
     * @param timeString time in the format "mm:ss.sss"
     * @return time in seconds
     */
    private static double convertTimeToSeconds(String timeString) {
        int colon = timeString.indexOf(":");
        int minutesAsSeconds = Integer.parseInt(timeString.substring(0, colon)) * 60;
        double seconds = Double.parseDouble(timeString.substring(colon + 1));
        return minutesAsSeconds + seconds;
    }

    /**
     * obtains the user's time to a certain distance along the run in the format "mm:ss.sss"
     * 
     * @param in passes the scanner into this function
     * @param runDistance the location/distance where the user recorded their time (ex. the first mile, the second mile, etc.)
     * @return the user's time to a certain distance along the run in the format "mm:ss.sss"
     */
    private static String getRunTime(Scanner in, String runDistance) {
        System.out.print("Please input your time to " + runDistance + " in the format (mm:ss.sss): ");
        String runTime = in.nextLine();
        return runTime;
    }

    /**
     * obtains the user's first and last name
     * 
     * @param in passes the scanner into this function
     * @return the user's first and last name
     */
    private static String getRunnerName(Scanner in) {
        System.out.print("Please input your first name: ");
        String firstName = in.nextLine();

        System.out.print("Please input your last name: ");
        String lastName = in.nextLine();
        return firstName + " " + lastName;
    }
}