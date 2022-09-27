package bullscows;

import java.util.Scanner;

public class Main {

    static String secret = "";
    static int bull = 0;
    static int cow = 0;
    static int turn = 1;
    static boolean win = false;
    static Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {

        if (!generate()) {
            return;
        }

        while (!win) {
            String guess = scanner.next();

            checkGuess(guess);
            printResult(bull, cow);

            if (!win) {
                System.out.printf("Turn %d:\n", ++turn);
            }
        }

        System.out.println("Congratulations! You guessed the secret code.");
    }

    public static boolean generate() {

        char symbol;

        System.out.println("Input the length of the secret code:");
        int volume = scanner.nextInt();

        if (volume > 36) {
            System.out.println("Error: can't generate a secret number with a length of 37 because there aren't enough unique digits.");
            System.out.println("Please enter a number not greater than 36.");
            return false;
        }

        System.out.println("Input the number of possible symbols in the code:");
        int dictionary = scanner.nextInt();

        if (volume > dictionary) {
            System.out.println("Error: Length must be <= Dictionary");
            return false;
        }

        secret = String.valueOf((int) (Math.random() * 10));

        while (secret.length() != volume) {
            int random = (int) (Math.random() * dictionary);
            if (random < 10) {
                symbol = (char) (48 + random); // počinje sa #48 ASCII = 0 i završava sa #57 ASCII = 9
            } else {
                symbol = (char) (87 + random); // počinje sa #97 ASCII = a i završava sa  #122 ASCII = z
            }
            if (!secret.contains(String.valueOf(symbol))) {
                secret += String.valueOf(symbol);
            }
        }

        System.out.print("The secret is prepared: ");
        for (int i = 0; i < secret.length(); i++) {
            System.out.print("*");
        }

        if (dictionary <= 10) {
            System.out.println(" (0-" + (char) (47 + dictionary) + ")");
        } else {
            System.out.println(" (0-9, a-" + (char) (86 + dictionary) + ")");
        }

        System.out.println("Okay, let's start a game! It's your turn 1:");
        return true;
    }

    public static void checkGuess(String guess) {
        bull = 0;
        cow = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bull++;
            } else if (secret.contains(String.valueOf(guess.charAt(i)))) {
                cow++;
            }
        }
        if (bull == secret.length()) {
            win = true;
        }
    }

    public static void printResult(int bull, int cow) {
        if (bull > 0 && cow > 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s).\n", bull, cow);
        } else if (bull == 0 && cow > 0) {
            System.out.printf("Grade: %d cow(s).\n", cow);
        } else if (bull > 0 && cow == 0) {
            System.out.printf("Grade: %d bull(s).\n", bull);
        } else System.out.println("Grade: 0 bull(s) and 0 cow(s). ");
    }
}
