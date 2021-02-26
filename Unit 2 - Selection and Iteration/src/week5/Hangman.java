package week5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hangman {

    private static final int ALLOWED_WRONG_GUESSES = 6;
    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            String sentence = getMessage();
            String usedLetters = "";

            boolean isWinner = false;
            boolean isLoser = false;
        
            int incorrectGuesses = 0;

            String hiddenMsg = encryptMessage(sentence, usedLetters);
            System.out.println(hiddenMsg);

            while (!isWinner && !isLoser) {
                String guess = getLetter(in, usedLetters);
                usedLetters += guess;

                if(sentence.indexOf(guess) < 0) {
                    incorrectGuesses++;
                }

                hiddenMsg = encryptMessage(sentence, usedLetters);
                System.out.println(hiddenMsg);

                drawHangman(incorrectGuesses);

                if(hiddenMsg.indexOf("_") < 0) {
                    System.out.println("YOU WIN!");
                    isWinner = true;
                }else if(incorrectGuesses == ALLOWED_WRONG_GUESSES) {
                    System.out.println("YOU LOSE!");
                    isLoser = true;
                }

            }

            playAgain = playAnother(in);
        } 
        in.close();
    }

    private static void drawHangman(int incorrectGuesses) {
        if (incorrectGuesses == 1) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |     O");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
        } else if (incorrectGuesses == 2) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |     O");
            System.out.println("   |     |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
        } else if (incorrectGuesses == 3) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |     O");
            System.out.println("   |   --|");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
        } else if (incorrectGuesses == 4) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |     O");
            System.out.println("   |   --|--");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
        } else if (incorrectGuesses == 5) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |     O");
            System.out.println("   |   --|--");
            System.out.println("   |    /");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
        } else if (incorrectGuesses == 6) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |     O");
            System.out.println("   |   --|--");
            System.out.println("   |    / \\");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
        }
    }

    private static String getMessage() {
        try {
            Scanner in = new Scanner(new File("src\\week5\\clues.dat"));
            int numberOfClues = Integer.parseInt(in.nextLine());
            int clueNumber = (int) (Math.random() * numberOfClues + 1);
            for(int i = 0; i < clueNumber; i++) {
                in.nextLine();
            }
            return in.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect filename. ");
            System.exit(0);
        }
        return null;
    }

    private static boolean playAnother(Scanner in) {
        String response = "";
        while(!(response.equalsIgnoreCase("Yes") || response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("No") || response.equalsIgnoreCase("N"))) {
            System.out.print("Do you want to play again? (Y/N) ");
            response = in.nextLine();
            response.toLowerCase();
        }
        return response.indexOf("y") >= 0;
    }

    private static String getLetter(Scanner in, String usedLetters) {
        String letter = "";
        boolean validInput = false;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        while(!validInput) {
            System.out.println("Used Letters: " + displayUsedLetters(usedLetters));
            System.out.print("Please input a letter: ");
            letter = in.nextLine();
            if (letter.length() == 1 && alphabet.indexOf(letter) >= 0 && usedLetters.indexOf(letter) < 0 && usedLetters.toLowerCase().indexOf(letter) < 0) {
                validInput = true;
            }else{
                System.out.println("Invalid Guess! Only letters are allowed and you cannot guess a letter that you have already chosen.");
            }
        }
        return letter.toUpperCase();
    }

    private static String displayUsedLetters(String usedLetters) {
        String letter = "";
        for(int i = 0; i < usedLetters.length(); i++) {
            letter += usedLetters.substring(i, i + 1) + " ";
        }
        return letter;
    }

    /**
     * "BASEBALL IS THE BEST SPORT OUT THERE" _ A _ _ _ A _ _ / _ _ / _ _ _ / _ _ _
     * _ / _ _ _ _ _ / _ _ _ / _ _ _ _ _
     * 
     * @param sentence
     * @return
     */
    private static String encryptMessage(String sentence, String usedLetters) {
        String hiddenMsg = "";
        for (int i = 0; i < sentence.length(); i++) {
            String temp = sentence.substring(i, i + 1);
            if(temp.equals(" ")) {
                hiddenMsg += "/ ";
            }else if(usedLetters.indexOf(temp) >= 0) {
                hiddenMsg += temp + " ";
            }else{
                hiddenMsg += "_ ";
            }
        }
        return hiddenMsg;
    }
}
