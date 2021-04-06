package week12;

public class Driver {
    public static void main(String[] args) {
        /*Rectangle rect1 = new Rectangle(5, 6);

        System.out.println(rect1.getArea());
        System.out.println(rect1.getPerimeter());
        System.out.println(rect1.isSquare());

        Rectangle rect2 = new Rectangle(5, 5);
    
        System.out.println(rect2.getArea());
        System.out.println(rect2.getPerimeter());
        System.out.println(rect2.isSquare());

        // Java will only create a default constructor if one is not already supplied
        // Rectangle rect3 = new Rectangle();

        // By default, Java will supply a no argument constructor that does nothing (BUT call your parent's no argument constructor)
        // Driver d = new Driver();
        */

        Box box1 = new Box(1, 2, 3);
        System.out.println(box1.getArea());
        System.out.println(box1.isCube());
        System.out.println(box1.getVolume());
        System.out.println(box1.getPerimeter());
    }
}
