package week12;

// The parent class of the box is a rectangle
public class Box extends Rectangle {
    private double height;

    public Box(double length, double width, double height) {
        super(length, width);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVolume() {
        return super.getArea() * height;
    }

    public double getArea() {
        return 2 * (getLength() * height + getWidth() * height * super.getArea());
    }

    public double getPerimeter() {
        return 2 * (super.getPerimeter() + 2 * height);
    }

    public boolean isSquare() {
        return false;
    }

    public boolean isCube() {
        return super.isSquare() && height == getLength();
    }
}
