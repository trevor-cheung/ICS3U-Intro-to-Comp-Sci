package week8;

public class Card {
    private String face;
    private String suit;

    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
    }

    public String getFace() {
        return face;
    }

    public String getSuit() {
        return suit;
    }

    public int compareTo(Card c) {
        return getValue(this) - getValue(c);
    }

    public static int compare(Card c1, Card c2) {
        int c1value;
        try {
            int temp = Integer.parseInt(c1.face);
            c1value = temp;
        } catch (NumberFormatException x) {
            if (c1.face.equals("J"))
                c1value = 11;
            else if (c1.face.equals("Q"))
                c1value = 12;
            else if (c1.face.equals("K"))
                c1value = 13;
            else
                c1value = 14;
        }
        int c2value;
        try {
            int temp2 = Integer.parseInt(c2.face);
            c2value = temp2;
        } catch (NumberFormatException x) {
            if (c2.face.equals("J"))
                c2value = 11;
            else if (c2.face.equals("Q"))
                c2value = 12;
            else if (c2.face.equals("K"))
                c2value = 13;
            else
                c2value = 14;
        }
        return c1value - c2value;
    }

    /**
     * helper method because it is only accessible by the class and allows another method to work. It is
     * helping another method in the class.
     */
    private int getValue(Card c) {
        String face = c.face;

        try {
            int temp = Integer.parseInt(face);
            return temp;
        } catch (NumberFormatException x) {
            if (face.equals("J"))
                return 11;
            else if (face.equals("Q"))
                return 12;
            else if (face.equals("K"))
                return 13;
            else
                return 14;
        }
    }

    /**
     * The word static means that a method or attribute in a class BELONGS to the class. And you would call or 
     * activate the method or attribute through the class name.
     * 
     * Non-static methods belong to an object (card1 and card2 were objects), Card is the class
     * card1.getSuit();
     */
}
