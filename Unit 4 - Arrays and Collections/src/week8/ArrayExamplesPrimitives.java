package week8;

public class ArrayExamplesPrimitives {
    public static void main(String[] args) {
        exampleOne();
        exampleTwo();
    }

    private static void exampleTwo() {
        int[] nums = {7, 2, 27, -98, 113};

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        System.out.println(sum);

        sum = 0;
        for (int num : nums) {
            sum += num;
        }
        System.out.println(sum);
    }

    private static void exampleOne() {
        int[] numbers = new int[5]; // 1. we have declared a reference to an int array
                                    // 2. assigned the variable to an int array of size/length 5

        // arrays have no methods, it is not a class

        numbers[0] = 7;
        numbers[1] = 2;
        numbers[2] = 12;
        numbers[3] = -87;
    }
}