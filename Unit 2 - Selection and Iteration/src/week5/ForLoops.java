package week5;

import java.util.Scanner;

public class ForLoops {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int x = sum(1, 10);
        System.out.println(x);

        exampleTwo(in);

        System.out.println("Please enter a sentence. ");
        String sentence = in.nextLine();
        int numVowels = countVowels(sentence);
        System.out.println("There are " + numVowels + " vowel(s) in this sentence.");
        
        drawRectangle(4, 6, "*");
        ecoo2010(3, 4, 1, 2);
    }

    private static void ecoo2010(int m, int n, int p, int q) {
        // top of frame
        for (int i = 0; i < q; i++) {
            for (int a = 0; a < n + 2 * p + 2 * q; a++) {
                System.out.print("#");
            }
            System.out.println();
        }

        // top of matting
        for (int b = 0; b < p; b++) {
            // left side of frame
            for (int a = 0; a < q; a++) {
                System.out.print("#");
            }
            // matting
            for (int a = 0; a < n + 2 * p; a++) {
                System.out.print("+");
            }
            // right side of frame
            for (int a = 0; a < q; a++) {
                System.out.print("#");
            }
            System.out.println();
        }

        // picture
        for (int c = 0; c < m; c++) {
            // left side of frame
            for (int a = 0; a < q; a++) {
                System.out.print("#");
            }
            // left side of matting
            for (int a = 0; a < p; a++) {
                System.out.print("+");
            }
            // picture
            for (int a = 0; a < n; a++) {
                System.out.print("*");
            }
            // right side of matting
            for (int a = 0; a < p; a++) {
                System.out.print("+");
            }
            // right side of frame
            for (int a = 0; a < q; a++) {
                System.out.print("#");
            }
            System.out.println();
        }

        // bottom of matting
        for (int b = 0; b < p; b++) {
            // left side of frame
            for (int a = 0; a < q; a++) {
                System.out.print("#");
            }
            // matting
            for (int a = 0; a < n + 2 * p; a++) {
                System.out.print("+");
            }
            // right side of frame
            for (int a = 0; a < q; a++) {
                System.out.print("#");
            }
            System.out.println();
        }

        // bottom frame
        for (int i = 0; i < q; i++) {
            for (int a = 0; a < n + 2 * p + 2 * q; a++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }

    private static void drawRectangle(int width, int length, String symbol) {
        String result = "";
        for (int i = 0; i < width; i++) {
            result += symbol + " ";
        }
        for (int i = 0; i < length; i++) {
            System.out.println(result);
        }
    }

    private static int countVowels(String text) {
        int result = 0;
        int index = 0;
        String letter = text.substring(index, index + 1);
        for (index = 0; index < text.length(); index++) {
            if ("AEIOUaeiou".indexOf(letter) >= 0) {
                result++;
            }
        }
        return result;
    }

    private static void exampleTwo(Scanner in) {
        String mysteryColour = "red";
        String colour = "";

        for (;!mysteryColour.equalsIgnoreCase(colour);) {
            System.out.print("What is the mystery colour? ");
            colour = in.nextLine();
        }
    }

    private static int sum(int start, int end) {
        int result = 0;

        for (int i = start; i <= end; i++) {
            result++;
        }
        return result;
    }
}
