package week4;

import java.util.Scanner;

public class BooleanExpressions {
    public static void main(String[] args) {
        primitiveBoolean();
        compoundBooleanExpressions();
    }

    private static void compoundBooleanExpressions() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter a colour for the shoes: ");
        String colour = null; // in.nextLine(); //.toLowerCase();

        System.out.print("How many shoes? ");
        int numShoes = Integer.parseInt(in.nextLine());

        System.out.println(colour != null && colour.equalsIgnoreCase("red") && (numShoes >= 4));

        System.out.println(colour != null && colour.equalsIgnoreCase("blue") || (numShoes >= 4));

        in.close();

        /** short circuit expressions
         * 
         * System.out.println(colour.equalsIgnoreCase("red") && (numShoes >= 4));
         * If the colour is not red there is no way both expressions can be true and as a result Java does not look at the numShoes expression
         * 
         * System.out.println(colour.equalsIgnoreCase("blue") || (numShoes >= 4));
         * If the colour is blue then we don't need to look at numShoes expression BECAUSE only 1 of the expressions has to be true for OR
         */

        boolean isResult = false;
        System.out.println(isResult);
        System.out.println(!isResult);
    }

    private static void primitiveBoolean() {
        boolean isYellow = true; //booleans are either true or false
        boolean hasDog = false; // naming convention: normally use is or has to prefix the variable name

        boolean isTrue = (7 + 3) == 10;

        System.out.println(7 != 7);
        System.out.println(7 == 7);
    }
}