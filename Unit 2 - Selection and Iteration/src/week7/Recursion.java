package week7;

public class Recursion {
    public static void main(String[] args) {
        int x = factorial(7);
        System.out.println(x);
        x = factorial2(7);
        System.out.println(x);

        x = fibonacci(7);
        System.out.println(x);

        x = fibonacci2(7);
        System.out.println(x);
    }

    private static int fibonacci2(int n) {
        int result = 1;
        int temp = 1;
        for (int i = 3; i <= n; i++) {            
            result = result + temp;  
            temp = result - temp;          
        }
        return result;
    }

    /**
     * obtains the nth number of the fibonacci sequence
     * @param n the number in the sequence to obtain
     * @return the nth number o fthe fibonacci sequence
     */
    private static int fibonacci(int n) {
        if (n == 1 || n == 2)
            return 1;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static int factorial2(int n) {
        int result = 1;

        for (int i = 1; i <= n; i++) {
            result *= i;
        }

        return result;
    }
    /**
     * obtains factorial given an integer
     * @param n an int representing the number we would like to obtain the factorial of
     * @return n!
     */
    private static int factorial(int n) {
        if (n == 1 || n == 0)
            return 1;
        else
            return n * factorial(n - 1);
    }
}
