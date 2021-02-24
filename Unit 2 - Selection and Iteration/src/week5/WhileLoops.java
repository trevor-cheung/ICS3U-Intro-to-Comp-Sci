package week5;

import java.util.Scanner;

public class WhileLoops {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int x = sum(1, 10);
        System.out.println(x);

        exampleTwo(in);

        System.out.println("Please enter a sentence: ");
        String sentence = in.nextLine();
        int numVowels = countVowels(sentence);
        System.out.println("There are " + numVowels + "vowels in this sentence.");
    }

    private static int countVowels(String text) {
        int numVowels = 0;
        int index = 0;

        while (index < text.length()) {
            String letter = text.substring(index, index + 1);
            if("AEIOUaeiou".indexOf(letter) >= 0) {
                numVowels++;
            }
            index++;
        }
        return numVowels;
    }

    private static void exampleTwo(Scanner in) {
        String mysteryColour = "red";
        String colour = "";

        while (!mysteryColour.equalsIgnoreCase(colour)) {
            System.out.print("What is the mystery colour? ");
            colour = in.nextLine();
        }
    }

    /**
     * gets the sum of the numbers from to start to end, inclusive
     * 
     * @param start
     * @param end
     * @return the sum of the numbers from start to end
     */
    private static int sum(int start, int end) {
        /**
         * loops allow us to repeat code
         * repetition / iteration
         * we can iterate through the characters of a string
         */

        int result = 0;
        int i = start;

        while (i <= end) {
            result++;
            i++;
        }

        return result;
    }
}
