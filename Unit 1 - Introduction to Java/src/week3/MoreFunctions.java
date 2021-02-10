package week3;

public class MoreFunctions {
    public static void main(String[] args) {
        int number = 34529;

        int sum = getSum(number);
        System.out.println(sum);

        number = 3781;
        sum = getSumVersion2(number);
        System.out.println(sum);

        String timeString = "5:34.221";
        double timeInSeconds = convertToSeconds(timeString);
        System.out.println(timeInSeconds);
    }

    /**
     * converts time into a double (seconds)
     * "5:34.221" => 334.221
     * 
     * @param timeString time in the format "mm:ss.sss"
     * @return converts time into seconds
     */
    private static double convertToSeconds(String timeString) {
        int colon = timeString.indexOf(":");
        int minutesAsSeconds = Integer.parseInt(timeString.substring(0, colon)) * 60;
        double seconds = Double.parseDouble(timeString.substring(colon + 1));
        return minutesAsSeconds + seconds;
    }

    /**
     * getSumVersion2 has been overloaded
     * overloaded means there is more than 1 version of the function (with that name)
     * the difference is the argument/parameter list
     * 
     * @param number
     * @return
     */
    public static int getSumVersion2(String number) {
        String numberAsString = number;
        int digit1 = Integer.parseInt(numberAsString.substring(0, 1));
        int digit2 = Integer.parseInt(numberAsString.substring(1, 2));
        int digit3 = Integer.parseInt(numberAsString.substring(2, 3));
        int digit4 = Integer.parseInt(numberAsString.substring(3, 4));
        
        return digit1 + digit2 + digit3 + digit4;
    }
    
    public static int getSumVersion2(int number) {
        String numberAsString = "" + number;
        
        return getSumVersion2(numberAsString);
    }

    /**
     * 
     * The method will return the sum of a 5 digit number Ex. 12346 => 16
     * 
     * @param number
     * @return The sum of the individual digits of number
     */
    public static int getSum(int number) {
        int digit1 = number / 10000;
        int digit2 = number / 1000 % 10;
        int digit3 = number / 100 % 10;
        int digit4 = number / 10 % 10;
        int digit5 = number % 10;
        
        return digit1 + digit2 + digit3 + digit4 + digit5;  
    }
}
