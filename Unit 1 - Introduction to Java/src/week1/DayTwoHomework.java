package week1;

public class DayTwoHomework {
    public static void main(String[] args) {
        questionOne();
        questionTwo();
        questionThree();
        questionFour();
        questionFive();
        questionSix();
    }

    private static void questionSix() {
        double a = -1;
        double b = 2;
        double c = 3;
        double posRoot = (-b + Math.sqrt(Math.pow(b, 2) - 4*a*c))/(2*a);
        double negRoot = (-b - Math.sqrt(Math.pow(b, 2) - 4*a*c))/(2*a);
        System.out.println("6. Using the equation from #3, the roots are (" + posRoot + ", 0) and (" + negRoot + ", 0).");
    }

    private static void questionFive() {
        int numPennies = 1;
        int numNickels = 2;
        int numDimes = 3;
        int numQuarters = 4;
        int numLoonies = 5;
        int numToonies = 6;
        double money = (0.01*numPennies) + (0.05*numNickels) + (0.1*numDimes) + (0.25*numQuarters) + numLoonies + (2*numToonies);
        System.out.println("5. The user has " + money + " dollars.");
    }

    private static void questionFour() {
        double x1 = 0;
        double y1 = 0;
        double x2 = 3;
        double y2 = -1;
        double rise = y2-y1;
        double run = x2-x1;
        double slope = rise/run;
        System.out.println("4. The slope of the line between the two coordinates (" + x1 + ", " + y1 + ") and ("+ x2 + ", " + y2 + ") is " + slope);
    }

    private static void questionThree() {
        double a = -1;
        double b = 2;
        double c = 3;
        double x = 4;
        double y = (a * Math.pow(x, 2)) + (b * x) + c;
        System.out.println("3. In this equation, y = " + y);
    }

    private static void questionTwo() {
        double radius = 5;
        double volume = 4/3 * Math.PI * Math.pow(radius, 3);
        System.out.println("2. The volume of a sphere with a radius of " + radius + " is " + volume);
    }

    private static void questionOne() {
        double radius = 5;
        double area = Math.PI * radius * radius;
        System.out.println("1. The area of a circle with a radius of " + radius + " is " + area);
    }
}
