public class DeluxeMeal extends Meal {
    private String sideName;
    private String drinkName;

    public DeluxeMeal(String name, String sideName, String drinkName, double cost) {
        super(name, cost + 3);
        this.sideName = sideName;
        this.drinkName = drinkName;
    }

    public String toString() {
        return "deluxe " + super.toString();
    }
}
