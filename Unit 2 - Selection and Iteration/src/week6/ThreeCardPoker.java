package week6;

import java.util.Scanner;

public class ThreeCardPoker {

    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;
    private static final int NUM_SUITS = 4;

    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;
    private static final int NUM_FACES = 13;

    private static final int STRAIGHT_FLUSH = 40;
	private static final int THREE_OF_A_KIND = 30;
	private static final int STRAIGHT = 6;
	private static final int FLUSH = 3;
	private static final int PAIR = 1;
    private static final int HIGH_CARD = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double playerMoney = 1000;
        boolean playAgain = true;
        
        while(playAgain) {
            System.out.println("You currently have $" + playerMoney + ".");
            double anteMin = 50.0;
            double anteMax = 100.0;
            double ante = askAnte(in, anteMin, anteMax);
            double pairPlusMin = 50.0;
            double pairPlusMax = 100.0;
            double pairPlus = askPairPlus(in, pairPlusMin, pairPlusMax);

            String playerHand = dealCards(); // deals the player's hand
            String dealerHand = dealCards(); // deals the dealer's hand

            System.out.println("Your Hand: " + playerHand);
            boolean play = askFoldOrPlay(in, ante);
            if (!play) {
                playerMoney -= ante + pairPlus;
                playAgain = playAnother(in);
                continue;
            }else{
                System.out.println("Dealer's Hand: " + dealerHand);
                System.out.println("Your Hand: " + playerHand);
            }
            double playWager = ante;

            int dealerHighCard = getHighCard(dealerHand);
            int playerHighCard = getHighCard(playerHand);
            int dealerHandType = handType(dealerHand);
            int playerHandType = handType(playerHand);
            
            if (dealerHighCard > playerHighCard)
                playerMoney -= ante + playWager;
            //else if (dealerHighCard >= 11 || dealerHighCard == playerHighCard)
            
        }

        in.close();
    }

    /**
     * obtains the highest card of the hand
     * @param hand the dealer or player's hand
     * @return the highest card of the hand
     */
    private static int getHighCard(String hand) {
        if (hand.indexOf("A") >= 0)
            return 14;
        else if (hand.indexOf("K") >= 0)
            return 13;
        else if (hand.indexOf("Q") >= 0)
            return 12;
        else if (hand.indexOf("J") >= 0)
            return 11;
        else if (hand.indexOf("10") >= 0)
            return 10;
        else 
            return Math.max(Integer.parseInt(hand.substring(0, 1)), Math.max(Integer.parseInt(hand.substring(3, 4)), Integer.parseInt(hand.substring(6, 7))));
    }

    /**
     * asks the player if they want to keep playing
     * 
     * @param in passes the scanner into this function
     * @return whether they want to play again or not
     */
    private static boolean playAnother(Scanner in) {
        String response = "";
        while(!(response.equalsIgnoreCase("Yes") || response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("No") || response.equalsIgnoreCase("N"))) {
            System.out.print("Do you want to play again? (Y/N) ");
            response = in.nextLine();
            response.toLowerCase();
        }
        return response.indexOf("y") >= 0;
    }

    /**
     * asks the player if they want to fold or play
     * if they choose to not fold, then they automatically place a play wager equal to their ante wager
     * @param in passes the scanner into this function
     * @param ante the player's ante wager
     * @return whether the player wants to fold or play
     */
    private static boolean askFoldOrPlay(Scanner in, double ante) {
        String response = "";
        boolean play = false;

        while(!(response.equalsIgnoreCase("Yes") || response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("No") || response.equalsIgnoreCase("N"))) {
            System.out.print("Would you like to fold? (Y/N) ");
            response = in.nextLine();
            response.toLowerCase();
        }
        if (response.indexOf("y") >= 0) 
            play = false;

        if (response.indexOf("n") >= 0) {
            System.out.println("You have placed a play wager of $" + ante + ".");
            play = true;
        }

        return play;
    }

    /**
     * asks if the player wants to place an optional pair plus wager, and if so,
     * what is their wager (has to be within a certain range, in this case it is
     * $50-$100)
     * 
     * @param in  passes the scanner into this function
     * @param min the minimum pair plus wager
     * @param max the maximum pair plus wager
     * @return the player's pair plus wager (no pair plus wager is 0)
     */
    private static double askPairPlus(Scanner in, double min, double max) {
        String response = "";
        double pairPlus = min;

        while(!(response.equalsIgnoreCase("Yes") || response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("No") || response.equalsIgnoreCase("N"))) {
            System.out.print("Would you like to place an optional pair plus wager? The pair plus wager has to be between " + min + " and " + max + " dollars. (Y/N) ");
            response = in.nextLine();
            response.toLowerCase();
        }

        if (response.indexOf("n") >= 0) 
            pairPlus = 0;

        if (response.indexOf("y") >= 0) {
            System.out.print("Please place a pair plus wager between " + min + " and " + max + " dollars. ");
            boolean validInput = false;
            while (!validInput) {
                try {
                    pairPlus = Double.parseDouble(in.nextLine());
                    validInput = true;
                    if (pairPlus < min || pairPlus > max) {
                        System.out.print("Please place a pair plus wager between " + min + " and " + max + " dollars. ");
                        validInput = false;
                    }
                } catch (NumberFormatException x) {
                    System.out.print("Please place a pair plus wager between " + min + " and " + max + " dollars. ");
                }
            }
        }
        return pairPlus;
    }

    /**
     * asks the player to place an ante wager which has to be within a certain range (in this case, it is $50-$100)
     * @param in passes the scanner into this function
     * @param min the minimum ante wager
     * @param max the maximum ante wager
     * @return the player's ante wager
     */
    private static double askAnte(Scanner in, double min, double max) {
        System.out.print("Please place an ante wager between " + min + " and " + max + " dollars. ");
        boolean validInput = false;
        double ante = min;
        while (!validInput) {
            try {
                ante = Double.parseDouble(in.nextLine());
                validInput = true;
                if (ante < min || ante > max) {
                    System.out.print("Please place an ante wager between " + min + " and " + max + " dollars. ");
                    validInput = false;
                }
            } catch (NumberFormatException x) {
                System.out.print("Please place an ante wager between " + min + " and " + max + " dollars. ");
            }
        }
        return ante;
    }

    public static int handType(String hand) {
        String faces = "234567891JQKA";
        String firstFace = "";
        String secondFace = "";
        String thirdFace = "";
        int numFaces = 0;
        for (int i = 0; i < hand.length(); i++) {
            if (faces.indexOf(hand.substring(i, i + 1)) >= 0)  {
                if (numFaces == 0)
                    firstFace = hand.substring(i, i + 1);
                else if (numFaces == 1)
                    secondFace = hand.substring(i, i + 1);
                else 
                    thirdFace = hand.substring(i, i + 1);
                numFaces++;
            }
        }

        boolean straight = isStraight(hand, faces);
        boolean flush = isFlush(hand);
		if (isStraightFlush(straight, flush))
			return STRAIGHT_FLUSH;
		else if (isThreeOfAKind(firstFace, secondFace, thirdFace))
			return THREE_OF_A_KIND;
		else if (straight)
			return STRAIGHT;
		else if (flush)
			return FLUSH;
		else if (isPair(firstFace, secondFace, thirdFace))
			return PAIR;
		else
			return HIGH_CARD;
    }
    
    private static boolean isPair(String firstFace, String secondFace, String thirdFace) {
        if (firstFace.equals(secondFace) || secondFace.equals(thirdFace) || firstFace.equals(thirdFace))
            return true;
        else   
            return false;
    }

    /**
     * checks if the cards in the hand have 3 same suits or not
     * @param hand the dealer or player's hand
     * @return whether the cards in the hand have 3 same suits or not
     */
    private static boolean isFlush(String hand) {
        String suits = "HDSC"; // hearts, diamonds, spades, clubs
        String firstSuit = "";
        String secondSuit = "";
        String thirdSuit = "";
        int numSuits = 0;
        for (int i = 0; i < hand.length(); i++) { // iterates through the "hand" string and finds the individual suits of the 3 cards
            if (suits.indexOf(hand.substring(i, i + 1)) >= 0)  {
                if (numSuits == 0)
                    firstSuit = hand.substring(i, i + 1);
                else if (numSuits == 1)
                    secondSuit = hand.substring(i, i + 1);
                else 
                    thirdSuit = hand.substring(i, i + 1);
                numSuits++;
            }
        }
        
        if (firstSuit.equals(secondSuit) && secondSuit.equals(thirdSuit))
            return true;
        else
            return false;
    }

    /**
     * checks if the cards in the hand have 3 consecutive faces or not
     * @param hand the dealer or player's hand
     * @param faces a string with all the possible faces (substitutes 1 for 10)
     * @return whether the cards in the hand have 3 consecutive faces or not
     */
    private static boolean isStraight(String hand, String faces) {
        String aceLowStraight = "23A"; // a straight can be ace, 2, 3, but using the method below, ace will always be highest, so this string is equal to a straight of ace, 2, 3, but the ace is just moved
        String lowestFace = "-"; // there is a dash here because if I left these strings blank and some faces were equal, the "consecutiveFaces" string (below) could be just one face and "consecutiveFaces" could be found in the "faces" string
        String middleFace = "-";
        String highestFace = "-";
        int numFaces = 0;
        for (int i = 0; i < faces.length(); i++) { // iterates through the "faces" string and finds the lowest, middle, and highest faces of the cards in the hand (if there are equal faces, only one face will get recognized, but for straights, this doesn't matter)
            if (hand.indexOf(faces.substring(i, i + 1)) >= 0)  {
                if (numFaces == 0)
                    lowestFace = faces.substring(i, i + 1);
                else if (numFaces == 1)
                    middleFace = faces.substring(i, i + 1);
                else 
                    highestFace = faces.substring(i, i + 1);
                numFaces++;
            }
        }
        String consecutiveFaces = lowestFace + middleFace + highestFace;
        if (faces.indexOf(consecutiveFaces) >= 0 || aceLowStraight.equals(consecutiveFaces)) 
            return true;
        else
            return false;
        
    }

    /**
     * checks if the cards in the hand have 3 same faces or not
     * @param hand the dealer or player's hand
     * @param firstFace the first card's face
     * @param secondFace the second card's face
     * @param thirdFace the third card's face
     * @return whether the cards in the hand have 3 same faces or not
     */
    private static boolean isThreeOfAKind(String firstFace, String secondFace, String thirdFace) {
        if (firstFace.equals(secondFace) && secondFace.equals(thirdFace))
            return true;
        else   
            return false;
    }

    /**
     * checks if the hand is a straight and a flush (straight flush) or not
     * @param straight whether the hand is a straight
     * @param flush whether the hand is a flush
     * @return whether the hand is a straight flush or not
     */
    private static boolean isStraightFlush(boolean straight, boolean flush) {
        if (straight && flush)
            return true;
        else
            return false;
    }

    /**
     * checks if the card has appeared in the hand already or not
     * @param hand the dealer or player's card
     * @param card a random card
     * @return whether the card has appeared in the hand already or not
     */
    public static boolean isUnique(String hand, String card) {
		return hand.indexOf(card) == -1;
    }

    /**
     * deals a hand with 3 different cards
     * @return 3 random unique cards in a string (the cards have a space between them)
     */
    private static String dealCards() {
        String cards = "";
        for (int i = 0; i < 3; i++) {
            Boolean hasCard = false;
            while(!hasCard){
                String card = getCard();
                if (isUnique(cards, card)){
                    cards += card + " ";
                    hasCard= true;
                }
            }
        }
        return cards;
    }

    /**
     * gets an individual random card
     * @return a string with the face first and suit second
     */
    private static String getCard() {
        return getFace() + getSuit();
    }

    /**
     * gets a random suit
     * @return a random suit (hearts, diamonds, spades, or clubs)
     */
    private static String getSuit() {
        int suit = (int) (Math.random() * NUM_SUITS);
        if (suit == HEARTS) {
            return "H"; // hearts
        } else if (suit == DIAMONDS) {
            return "D"; // diamonds
        } else if (suit == SPADES) {
            return "S"; // spades
        } else if (suit == CLUBS) {
            return "C"; // clubs
        } else {
            return null;
        }
    }

    /**
     * gets a random face
     * @return a random face (2-10, jack, queen, king, or ace)
     */
    private static String getFace() {
        int face = (int) (Math.random() * NUM_FACES + 2);
        if (face == JACK) {
            return "A"; // jack
        } else if (face == QUEEN) {
            return "J"; // queen
        } else if (face == KING) {
            return "Q"; // king
        } else if (face == ACE) {
            return "K"; // ace
        } else {
            return "" + face; // number
        }
    }
}