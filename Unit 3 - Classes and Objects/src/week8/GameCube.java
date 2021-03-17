package week8;

/**
 * The GameCube will model a simple die that could be used for a board game, a dice game, or any game that requires a random number
 * 
 * The class will contain:
 * State:      the current state of the GameCube  
 * Behaviour:  the things that we can do with the GameCube (public)
 */
public class GameCube {
    private int topSide; // attributes of a GameCube
    private int numSides; // instance variables (used to identify the current state of the object)

    /**
     * method to create a GameCube - create a constructor
     * The purpose of the constructor is to create an instance of the class
     * The constructor is a method with the same name as the class
     * Purpose of the constructor is to set the initial state of the object
     * 
     * Return type of constructor is missing and is not needed
     * when the method is done, the user will receive an instance of a GameCube
     * 
     * The constructor's job is to initialise the state of the object and return an
     * instance of that class.
     */

    public GameCube(int numberOfSides) {
        numSides = numberOfSides;
        roll();
    }

    /**
     * overloaded the constructor
     * the no argument constructor is sometimes called the default constructor
     */
    public GameCube() {
        numSides = 6;
        topSide = (int) Math.random() * numSides + 1;
    }

    /**
     * when you roll a GameCube you are just setting the top side
     */
    public void roll(){
        topSide = (int) (Math.random() * numSides + 1);
    }

    /**
     * @return the value of the top side for the GameCube
     */
    public int getTopSide() {
        return topSide;
    }
}