package week2;

public class StringExamples {
    public static void main(String[] args) {
        exampleOne();
        exampleTwo();
        exampleThree();
        exampleFour();
    }

    private static void exampleFour() {
    }

    private static void exampleThree() {
        String s1 = new String("Steve"); // going to build a new String everytime
        String s2 = new String("Steve");
        String s3 = "Steve"; //if you declare a String like we did for s3 or s4 using String a = "blah blah" (we don't use the constructor) it reuses the existing Steve if it is already there
        String s4 = "Steve"; //String literal - Java stores all the String literals in a table and reuses them

        System.out.println(s1 == s2); // == equality operator (never use == to compare strings)
        System.out.println(s1.equals(s2)); // to check if 2 strings are equal, use the equals method
        System.out.println(s3 == s4); //use == to compare String literals returns true
    }

    /**
     * Add other String methods with output
     */
    private static void exampleTwo() {
        String courseCode = "ICS3U AP";
        int x = courseCode.length();
        //String sub = courseCode.substring(2, 5); //"S3U" (starts at index 2 and ends at index 4) [2, 5) 2 <= x < 5
        //String sub2 = courseCode.substring(2); // "S3U AP" the parameter refers to the starting index. The substring will begin at 2 and go to the end.

        System.out.println("The length of \"" + courseCode + "\" is: " + x);
    }

    private static void exampleOne() {
        String simpleText = "This is a String."; //Strings are NOT primitive data, they are reference/object types
        int number = 7;
        System.out.println(simpleText);
        System.out.println(number);
    }
}
