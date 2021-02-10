package week3;

public class FunctionsHomework {
    public static void main(String[] args) {
        double questionOne = getTotalCost(985, 5.5);
        double questionTwoArea = findArea(4.5, 2.3);
        double questionTwoPerimeter = findPerimeter(4.5, 2.3);
        int questionThree = findMinutesInAYear(365);
        double questionFour = lightBeamDistance(1.0);
        double questionFive = getWinningPercentage(110, 44);
        double questionSix = getMomentum(10.0, 12.0);
        double questionSeven = convertFahrenheitToCelsius(98.0);
        double questionEightSquare = getSquare(2.0);
        double questionEightSqrt = getSquareRoot(2.0);
        double questionNine = findPay(20);
        double questionTenArea = findArea(10.0, 10.0);
        double questionTenPerimeter = findPerimeter(10.0, 10.0);
        double questionEleven = findKineticEnergy(10.0, 5.0);

        System.out.println("The total cost of the computer is $" + questionOne);
        System.out.println("The area of this rectangle is " + questionTwoArea + " sq feet");
        System.out.println("The perimeter of this rectangle is " + questionTwoPerimeter + " feet");
        System.out.println("There are " + questionThree + " minutes in a year");
        System.out.println("A light beam would travel " + questionFour + " meters in one year");
        System.out.println("The winning percentage of the 1927 NYY was " + questionFive);
        System.out.println("The momentum of this object is " + questionSix + " kg*m/s");
        System.out.println("This temperature in Fahrenheit is " + questionSeven + " degrees Celsius");
        System.out.println("The square of this number is " + questionEightSquare);
        System.out.println("The square root of this number is " + questionEightSqrt);
        System.out.println("This salesperson is due $" + questionNine);
        System.out.println("The area of this rectangle is " + questionTenArea + " units");
        System.out.println("The perimeter of this rectangle is " + questionTenPerimeter + " units");
        System.out.println("The kinetic energy of this object is " + questionEleven + " joules");
    }

    private static double findKineticEnergy(double mass, double speed) {
        return (1.0 / 2.0) * mass * Math.pow(speed, 2);
    }

    private static double findPay(int items) {
        return 0.27 * (double) items;
    }

    private static double getSquareRoot(double n) {
        return Math.sqrt(n);
    }

    private static double getSquare(double n) {
        return Math.pow(n, 2);
    }

    private static double convertFahrenheitToCelsius(double degrees) {
        return (degrees - 32) * (5.0 / 9.0);
    }

    private static double getMomentum(double mass, double velocity) {
        return mass * velocity;
    }

    private static double getWinningPercentage(int wins, int losses) {
        return ((double) wins / (wins + losses));
    }

    private static double lightBeamDistance(double years) {
        return (3 * (Math.pow(10, 8))) * 31536000 * years;
    }

    private static int findMinutesInAYear(int days) {
        return days * 24 * 60;
    }

    private static double findPerimeter(double length, double width) {
        return Math.round(2 * (length + width) * 10) / 10.0;
    }

    private static double findArea(double length, double width) {
        return Math.round(length * width * 10) / 10.0;
    }

    private static double getTotalCost(double price, double tax) {
        return price + (price * tax / 100);
    }
}
