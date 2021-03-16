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

    private static final double ANTE_MIN = 50.0;
    private static final double ANTE_MAX = 100.0;
    private static final double PAIR_PLUS_MIN = 50.0;
    private static final double PAIR_PLUS_MAX = 100.0;

    private static double playerMoney = 1000;
    private static Scanner in = new Scanner(System.in);
    private static double ante = 0.0;
    private static double playWager = 0.0;
    private static double pairPlus = 0.0;

    private static String playerHand = ""; // deals the player's hand
    private static String dealerHand = ""; // deals the dealer's hand

    private static String dealerFace1 = "", dealerFace2 = "", dealerFace3 = "";
    private static String playerFace1 = "", playerFace2 = "", playerFace3 = "";

    private static String faces = "234567891JQKA"; // a string with all the possible faces (substitutes 1 for 10)

    public static void main(String[] args) {
        boolean playAgain = true;

        while (playAgain && playerMoney >= 100) { // the minimum amount of money to play is $100 because the minimum
                                                  // ante is $50, therefore also making the minimum play wager $50 
                                                  // $50 + $50 = $100
            double tempMoney = playerMoney;
            System.out.println("You currently have $" + playerMoney + ".");
            ante = askAnte();
            playWager = ante;
            pairPlus = 0.0; // resets the pair plus wager just in case the player does not put down a pair plus wager
            if (playerMoney >= ante + playWager + PAIR_PLUS_MIN) { // checks if the player has enough money to put down
                                                                   // a pair plus wager
                pairPlus = askPairPlus();
            }

            dealerHand = dealCards();
            playerHand = dealCards();

            System.out.println("Your Hand: " + playerHand);
            if (askFold()) {
                playerMoney -= ante + pairPlus;
                playAgain = playAnother();
                continue;
            } else {
                System.out.println("Dealer's Hand: " + dealerHand);
                System.out.println("Your Hand: " + playerHand);
            }

            dealerFace1 = getIndividualFace(1, dealerHand);
            dealerFace2 = getIndividualFace(2, dealerHand);
            dealerFace3 = getIndividualFace(3, dealerHand);
            playerFace1 = getIndividualFace(1, playerHand);
            playerFace2 = getIndividualFace(2, playerHand);
            playerFace3 = getIndividualFace(3, playerHand);

            int dealerHandType = handType(dealerHand, dealerFace1, dealerFace2, dealerFace3);
            int playerHandType = handType(playerHand, playerFace1, playerFace2, playerFace3);

            int dealerHighCard = getHighCard(dealerHand, dealerFace1, dealerFace2, dealerFace3);
            int playerHighCard = getHighCard(playerHand, playerFace1, playerFace2, playerFace3);

            // if the dealer or player has a pair, the pair becomes the high card
            if (dealerHandType == PAIR)
                dealerHighCard = getPairHighCard(dealerHighCard, dealerFace1, dealerFace2, dealerFace3);
            if (playerHandType == PAIR)
                playerHighCard = getPairHighCard(playerHighCard, playerFace1, playerFace2, playerFace3);

            // checks if the dealer or player's hand is an Ace-low straight
            // if so, the high card becomes 3
            if (checkAceLowStraight(dealerHand))
                dealerHighCard = 3;
            if (checkAceLowStraight(playerHand))
                playerHighCard = 3;

            // determines whether the player loses or gains the ante and/or play wager
            playerMoney = compareHands(dealerHandType, playerHandType, dealerHighCard, playerHighCard);
            playerMoney = pairPlusPayouts(playerHandType);

            // lets the player know after every hand if they won or lost money and by how much
            winOrLose(tempMoney);

            if (playerMoney < 100)
                System.out.println("You do not have enough money to play.");
            playAgain = playAnother();
        }

        in.close();
    }

    /**
     * calculates the pair plus payouts for the player's hand
     * 
     * @param playerHandType the player's hand type
     * @return the player's money after determining the pair plus payouts
     */
    private static double pairPlusPayouts(int playerHandType) {
        if (playerHandType >= PAIR) // if the player's hand has a pair or better, they do not lose their pair plus
                                    // wager and they also get the pair plus payouts
            playerMoney += playerHandType * pairPlus; // pair plus payouts
        else
            playerMoney -= pairPlus;
        return playerMoney;
    }

    /**
     * compares the dealer's and player's hands to determine whether the player
     * loses or gains or keeps the ante and/or play wager
     * 
     * @param dealerHandType the dealer's hand type
     * @param playerHandType the player's hand type
     * @param dealerHighCard the highest card of the dealer's hand
     * @param playerHighCard the highest card of the player's hand
     * @return the player's money after calculating whether the player loses or
     *         gains or keeps the ante and/or play wager
     */
    private static double compareHands(int dealerHandType, int playerHandType, int dealerHighCard, int playerHighCard) {
        if (dealerHandType == HIGH_CARD && dealerHighCard <= 11) // if the dealer has a hand of Jack-high or lower, then
                                                                 // it is treated as if the dealer's hand does not
                                                                 // qualify to play
            playerMoney -= ante;
        else if (dealerHandType > playerHandType)
            playerMoney -= ante + playWager;
        else if (dealerHandType == playerHandType) {
            if (dealerHighCard > playerHighCard)
                playerMoney -= ante + playWager;
            else if (dealerHighCard == playerHighCard) {
                // if the dealer and player have identical pairs, the high card is now the other
                // card
                if (dealerHandType == PAIR && (getFaceValue(dealerFace1) + getFaceValue(dealerFace2)
                        + getFaceValue(dealerFace3)) > (getFaceValue(playerFace1) + getFaceValue(playerFace2)
                                + getFaceValue(playerFace3)))
                    playerMoney -= ante + playWager;
                else if (dealerHandType == PAIR && (getFaceValue(dealerFace1) + getFaceValue(dealerFace2)
                + getFaceValue(dealerFace3)) < (getFaceValue(playerFace1) + getFaceValue(playerFace2)
                        + getFaceValue(playerFace3)))
                    playerMoney += ante + playWager;
                // if the above conditions are false, then it is a tie and the player loses the
                // ante but keeps the play wager
                else
                    playerMoney -= ante;
            } else
                playerMoney += ante + playWager;
        } else {
            // if the player wins and the dealer's hand is Queen-high or better, the player
            // gets back the ante and play wager and gains the ante and play wager again
            // ex. player wins, ante and play wager are 50 each, player gets back 200
            // 50 * 2 + 50 * 2
            playerMoney += ante + playWager;
        }
        return playerMoney;
    }

    /**
     * lets the player know after every hand if they won or lost money and by how
     * much in this case, playerMoney's value is the player's money after the bets
     * are placed and the hands are shown
     * 
     * @param tempMoney the player's money before the bets are placed and the hands are shown
     */
    private static void winOrLose(double tempMoney) {
        if (playerMoney > tempMoney)
            System.out.println("You won $" + (playerMoney - tempMoney) + "!");
        else if (playerMoney < tempMoney)
            System.out.println("You lost $" + (tempMoney - playerMoney) + "!");
        else
            System.out.println("You broke even!");
    }

    /**
     * checks if the dealer or player's hand is an Ace-low straight
     * 
     * @param hand the dealer or player's hand
     * @return whether the dealer or player's hand is an Ace-low straight
     */
    private static boolean checkAceLowStraight(String hand) {
        String aceLowStraight = "23A"; // a straight can be ace, 2, 3, but using the method below, ace will always be
                                       // highest, so this string is equal to a straight of ace, 2, 3, but the ace is
                                       // just moved
        String lowestFace = "-"; // there is a dash here because if I left these strings blank and some faces
                                 // were equal, the "consecutiveFaces" string (below) could be just one face and
                                 // "consecutiveFaces" could be found in the "faces" string
        String middleFace = "-";
        String highestFace = "-";
        int numFaces = 0;
        // iterates through the "faces" string and finds the lowest, middle, and highest
        // faces of the cards in the hand
        // if there are equal faces, only one face will get recognized, but for
        // straights, this doesn't matter
        for (int i = 0; i < faces.length(); i++) {
            if (hand.indexOf(faces.substring(i, i + 1)) >= 0) {
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
        if (aceLowStraight.equals(consecutiveFaces))
            return true;
        else
            return false;
    }

    /**
     * obtains the face of the pair (if the hand has a pair, then the pair becomes
     * the high card)
     * 
     * @param highCard   the highest card of the hand
     * @param firstFace  the first card's face
     * @param secondFace the second card's face
     * @param thirdFace  the third card's face
     * @return the face of the pair
     */
    private static int getPairHighCard(int highCard, String firstFace, String secondFace, String thirdFace) {
        if (firstFace.equals(secondFace))
            return getFaceValue(firstFace);
        else if (secondFace.equals(thirdFace))
            return getFaceValue(secondFace);
        else if (firstFace.equals(thirdFace))
            return getFaceValue(firstFace);
        else
            return highCard;
    }

    /**
     * obtains the face of a selected individual card
     * 
     * @param index indicates which card to get the face of (first, second, third)
     * @param hand  the dealer or player's hand
     * @return the face of the selected individual card
     */
    private static String getIndividualFace(int index, String hand) {
        if (index == 1) {
            if (hand.substring(0, 1).equals("1"))
                return "10";
            else
                return hand.substring(0, 1);
        } else {
            String temp = hand.substring(hand.indexOf(" ") + 1);
            if (index == 2)
                if (temp.substring(0, 1).equals("1"))
                    return "10";
                else
                    return temp.substring(0, 1);
            else if (index == 3)
                if (temp.substring(temp.indexOf(" ") + 1, temp.indexOf(" ") + 2).equals("1"))
                    return "10";
                else
                    return temp.substring(temp.indexOf(" ") + 1, temp.indexOf(" ") + 2);
            else {
                throw new IllegalArgumentException("Only have 3 faces!");
            }
        }
    }

    /**
     * obtains the face of highest card of the hand
     * 
     * @param hand       the dealer or player's hand
     * @param firstFace  the first card's face
     * @param secondFace the second card's face
     * @param thirdFace  the third card's face
     * @return the face of highest card of the hand
     */
    private static int getHighCard(String hand, String firstFace, String secondFace, String thirdFace) {
        if (hand.indexOf("A") >= 0)
            return ACE;
        else if (hand.indexOf("K") >= 0)
            return KING;
        else if (hand.indexOf("Q") >= 0)
            return QUEEN;
        else if (hand.indexOf("J") >= 0)
            return JACK;
        else if (hand.indexOf("1") >= 0)
            return 10;
        else
            return Math.max(Integer.parseInt(firstFace),
                    Math.max(Integer.parseInt(secondFace), Integer.parseInt(thirdFace)));
    }

    /**
     * obtains the face of the card in integer form
     * 
     * @param face the face of the card
     * @return the face of the card in integer form
     */
    private static int getFaceValue(String face) {
        if (face.indexOf("A") >= 0)
            return ACE;
        else if (face.indexOf("K") >= 0)
            return KING;
        else if (face.indexOf("Q") >= 0)
            return QUEEN;
        else if (face.indexOf("J") >= 0)
            return JACK;
        else if (face.indexOf("1") >= 0)
            return 10;
        else
            return Integer.parseInt(face);
    }

    /**
     * asks the player if they want to keep playing
     * 
     * @return whether they want to play again or not
     */
    private static boolean playAnother() {
        if (playerMoney >= 100) {
            String response = "";
            while (!(response.equalsIgnoreCase("Yes") || response.equalsIgnoreCase("Y")
                    || response.equalsIgnoreCase("No") || response.equalsIgnoreCase("N"))) {
                System.out.print("Do you want to play again? (Y/N) ");
                response = in.nextLine();
                response.toLowerCase();
            }
            return response.indexOf("y") >= 0;
        } else
            return false;
    }

    /**
     * asks the player if they want to fold or not if they do not choose to fold,
     * then they automatically place a play wager equal to their ante wager
     * 
     * @return whether the player wants to fold or not
     */
    private static boolean askFold() {
        String response = "";
        boolean fold = false;

        while (!(response.equalsIgnoreCase("Yes") || response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("No")
                || response.equalsIgnoreCase("N"))) {
            System.out.print("Would you like to fold? (Y/N) ");
            response = in.nextLine();
            response.toLowerCase();
        }
        if (response.indexOf("y") >= 0)
            fold = true;

        if (response.indexOf("n") >= 0) {
            System.out.println("You have placed a play wager of $" + ante + ".");
            fold = false;
        }

        return fold;
    }

    /**
     * asks if the player wants to place an optional pair plus wager, and if so,
     * what is their wager (has to be within a certain range, in this case it is
     * $50-$100)
     * 
     * @return the player's pair plus wager (no pair plus wager is 0)
     */
    private static double askPairPlus() {
        String response = "";
        double pairPlus = PAIR_PLUS_MIN;

        while (!(response.equalsIgnoreCase("Yes") || response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("No")
                || response.equalsIgnoreCase("N"))) {
            System.out
                    .print("Would you like to place an optional pair plus wager? The pair plus wager has to be between "
                            + PAIR_PLUS_MIN + " and " + PAIR_PLUS_MAX + " dollars. (Y/N) ");
            response = in.nextLine();
            response.toLowerCase();
        }

        if (response.indexOf("n") >= 0)
            pairPlus = 0;

        if (response.indexOf("y") >= 0) {
            System.out.print(
                    "Please place a pair plus wager between " + PAIR_PLUS_MIN + " and " + PAIR_PLUS_MAX + " dollars. ");
            boolean validInput = false;
            while (!validInput) {
                try {
                    pairPlus = Double.parseDouble(in.nextLine());
                    validInput = true;
                    if (pairPlus < PAIR_PLUS_MIN || pairPlus > PAIR_PLUS_MAX) {
                        System.out.print("Please place a pair plus wager between " + PAIR_PLUS_MIN + " and "
                                + PAIR_PLUS_MAX + " dollars. ");
                        validInput = false;
                    } else if (ante + playWager + pairPlus > playerMoney) { // checks if the player does not have enough
                                                                            // money to place the inputted pair plus 
                                                                            // wager (taking into account the ante and
                                                                            // pair plus wager as well)
                        System.out.print(
                                "You do not have enough money to do that. Please place a pair plus wager while still keeping enough for the play wager. ");
                        validInput = false;
                    }
                } catch (NumberFormatException x) {
                    System.out.print("Please place a pair plus wager between " + PAIR_PLUS_MIN + " and " + PAIR_PLUS_MAX
                            + " dollars. ");
                }
            }
        }
        return pairPlus;
    }

    /**
     * asks the player to place an ante wager which has to be within a certain range
     * (in this case, it is $50-$100)
     * 
     * @return the player's ante wager
     */
    private static double askAnte() {
        System.out.print("Please place an ante wager between " + ANTE_MIN + " and " + ANTE_MAX + " dollars. ");
        boolean validInput = false;
        double ante = ANTE_MIN;
        while (!validInput) {
            try {
                ante = Double.parseDouble(in.nextLine());
                validInput = true;
                if (ante < ANTE_MIN || ante > ANTE_MAX) {
                    System.out.print(
                            "Please place an ante wager between " + ANTE_MIN + " and " + ANTE_MAX + " dollars. ");
                    validInput = false;
                } else if (ante * 2 > playerMoney) { // the player requires enough money for the play wager to play, and
                                                     // the play wager is equal to the ante
                    System.out.print(
                            "You do not have enough money to do that. Please place an ante wager while still keeping enough for the play wager. ");
                    validInput = false;
                }
            } catch (NumberFormatException x) {
                System.out.print("Please place an ante wager between " + ANTE_MIN + " and " + ANTE_MAX + " dollars. ");
            }
        }
        return ante;
    }

    /**
     * figures out the type of the hand (straight flush, three of a kind, straight,
     * flush, pair, or high card)
     * 
     * @param hand       the dealer or player's hand
     * @param firstFace  the first card's face
     * @param secondFace the second card's face
     * @param thirdFace  the third card's face
     * @return the type of the hand
     */
    public static int handType(String hand, String firstFace, String secondFace, String thirdFace) {
        boolean straight = isStraight(hand);
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

    /**
     * checks if the cards in the hand consist of a pair or not
     * 
     * @param firstFace  the first card's face
     * @param secondFace the second card's face
     * @param thirdFace  the third card's face
     * @return whether the cards in the hand consist of a pair or not
     */
    private static boolean isPair(String firstFace, String secondFace, String thirdFace) {
        if (firstFace.equals(secondFace) || secondFace.equals(thirdFace) || firstFace.equals(thirdFace))
            return true;
        else
            return false;
    }

    /**
     * checks if the cards in the hand have 3 same suits or not
     * 
     * @param hand the dealer or player's hand
     * @return whether the cards in the hand have 3 same suits or not
     */
    private static boolean isFlush(String hand) {
        String suits = "HDSC"; // hearts, diamonds, spades, clubs
        String firstSuit = "";
        String secondSuit = "";
        String thirdSuit = "";
        int numSuits = 0;
        for (int i = 0; i < hand.length(); i++) { // iterates through the "hand" string and finds the individual suits
                                                  // of the 3 cards
            if (suits.indexOf(hand.substring(i, i + 1)) >= 0) {
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
     * 
     * @param hand the dealer or player's hand
     * @return whether the cards in the hand have 3 consecutive faces or not
     */
    private static boolean isStraight(String hand) {
        String aceLowStraight = "23A"; // a straight can be ace, 2, 3, but using the method below, ace will always be
                                       // highest, so this string is equal to a straight of ace, 2, 3, but the ace is
                                       // just moved
        String lowestFace = "-"; // there is a dash here because if I left these strings blank and some faces
                                 // were equal, the "consecutiveFaces" string (below) could be just one face and
                                 // "consecutiveFaces" could be found in the "faces" string
        String middleFace = "-";
        String highestFace = "-";
        int numFaces = 0;
        for (int i = 0; i < faces.length(); i++) { // iterates through the "faces" string and finds the lowest, middle,
                                                   // and highest faces of the cards in the hand (if there are equal
                                                   // faces, only one face will get recognized, but for straights, this
                                                   // doesn't matter)
            if (hand.indexOf(faces.substring(i, i + 1)) >= 0) {
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
     * 
     * @param firstFace  the first card's face
     * @param secondFace the second card's face
     * @param thirdFace  the third card's face
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
     * 
     * @param straight whether the hand is a straight
     * @param flush    whether the hand is a flush
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
     * 
     * @param hand the dealer or player's card
     * @param card the selected individual card
     * @return whether the card has appeared in the hand already or not
     */
    public static boolean isUnique(String hand, String card) {
        return hand.indexOf(card) == -1;
    }

    /**
     * deals a hand with 3 different cards
     * 
     * @return 3 random unique cards in a string (the cards have a space between
     *         them)
     */
    private static String dealCards() {
        String cards = "";
        for (int i = 0; i < 3; i++) {
            Boolean hasCard = false;
            while (!hasCard) {
                String card = getCard();
                if (isUnique(cards, card)) {
                    cards += card + " ";
                    hasCard = true;
                }
            }
        }
        return cards;
    }

    /**
     * gets an individual random card
     * 
     * @return a string with the face first and suit second
     */
    private static String getCard() {
        return getFace() + getSuit();
    }

    /**
     * gets a random suit
     * 
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
     * 
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