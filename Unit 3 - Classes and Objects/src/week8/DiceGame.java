package week8;

public class DiceGame {
    public static void main(String[] args) {
        GameCube playerOne = new GameCube();
        GameCube playerTwo = new GameCube();
        
        int numRolls = 10;
        int scoreOne = 0;
        int scoreTwo = 0;
        for (int i = 0; i < numRolls; i++ ) {
            playerOne.roll();
            playerTwo.roll();

            System.out.println("Player 1: " + playerOne.getTopSide());
            System.out.println("Player 2: " + playerTwo.getTopSide());
            if (playerOne.getTopSide() > playerTwo.getTopSide()) {
                scoreOne++;
                System.out.println("PLAYER ONE WINS");
            } else if (playerOne.getTopSide() < playerTwo.getTopSide()) {
                scoreTwo++;
                System.out.println("PLAYER TWO WINS");
            } else
                System.out.println("DRAW");
        }

        System.out.println("--------------------FINAL SCORE--------------------");
        System.out.println("Player 1: " + scoreOne);
        System.out.println("Player 2: " + scoreTwo);
    }
}
