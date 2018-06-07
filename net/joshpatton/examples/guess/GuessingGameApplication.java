/*
 * Driver for GuessingGameEngine
 * created at 4:37pm June 7th, 2018 by Josh Patton
 */
package net.joshpatton.examples.guess;

import java.util.Scanner;

public class GuessingGameApplication {
    static Scanner scanner;
    static int playCount = 0;
    static int winCount = 0;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("Guessing Game by Josh Patton");
        System.out.println("Created to demonstrate basic Java skills");
        System.out.println();
        boolean keepPlaying = true;
        while(keepPlaying) {
            playCount++;
            playOneGame();
            System.out.print("Press enter to continue, 'n' to quit");
            if(!scanner.nextLine().equals("")) {
                quitApplication();
            }
        }
    }

    public static void playOneGame() {
        System.out.println("\n*Beginning new game*\n");

        System.out.print("What number do you want to guess up to: ");

        int max = GuessingGameEngine.DEFAULT_MAXIMUM_NUMBER;
        try {
            max = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("That is not a number. Ending game");
            quitApplication();
        }

        System.out.println("Guess a number 1 - 10");
        System.out.println("Input 'quit' to exit game");
        
        GuessingGameEngine engine = new GuessingGameEngine(max);

        while(!engine.isGameOver()) {
            System.out.print("1 - 10: ");
            String input = scanner.nextLine().trim();
            try {
                int guess = Integer.parseInt(input);
                if(!engine.inputAndGuess(guess)) {
                    System.out.println("Incorrect guess");
                }
            } catch (NumberFormatException e) {
                System.out.println("That is not a number. Ending game");
                quitApplication();
            }
        }
        if(engine.isGameWon()) {
            System.out.println("Congratulations on winning the game!");
            winCount++;
        } else {
            System.out.println("You have lost the game.");
            System.out.println("The number was " + engine.getTargetNumber());
        }
        System.out.println("You made " + engine.getGuessesMade() + " guesses");
        
    }

    public static void quitApplication() {
        //TODO: show stats
        System.out.println("\n\n=====");
        System.out.println("You played " + playCount + " times and won " + winCount + " times");
        System.out.println("Goodbye");
        System.exit(0);
    }

}