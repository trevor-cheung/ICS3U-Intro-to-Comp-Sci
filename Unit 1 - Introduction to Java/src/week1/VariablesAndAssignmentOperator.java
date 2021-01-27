package week1;

/**
 * VariablesAndAssignmentOperator
 */
public class VariablesAndAssignmentOperator {
    public static void main(String[] args) {
        exampleOne();
        exampleTwo();
    }

    private static void exampleTwo() {
        double x = 2.3; 
        double y = 3.1;
        double sum = x + y;
        System.out.println(sum);
    }

    private static void exampleOne() {
        // variables are used to store data / information
        int numberOne; // declare a variable called numberOne that refers to a primitve int
        numberOne = 10; // assignment operator
        numberOne = numberOne + 1; 
        System.out.println(numberOne);
    }
}