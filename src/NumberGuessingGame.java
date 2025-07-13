import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🎮 Welcome to the Number Guessing Game!");
        System.out.println("Try to guess the number between 1 and 100.");

        boolean playAgain = true;
        int totalScore = 0;
        int round = 1;

        while (playAgain) {
            System.out.println("\n--- Round " + round + " ---");
            int pointsThisRound = playGame();
            totalScore += pointsThisRound;
            System.out.println("🎯 Score this round: " + pointsThisRound);
            System.out.println("🏆 Total Score: " + totalScore);

            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next().toLowerCase();
            if (!response.equals("yes")) {
                playAgain = false;
                System.out.println("Thank you for playing! Final Score: " + totalScore);
            }
            round++;
        }

        scanner.close();
    }

    public static int playGame() {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int maxAttempts = 10;
        int attempts = 0;
        int guess = -1;

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess (1 to 100): ");
            try {
                guess = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("⚠️ Please enter a valid number!");
                scanner.next(); // clear the invalid input
                continue;
            }

            attempts++;

            if (guess < 1 || guess > 100) {
                System.out.println("⚠️ Number out of range! Try again.");
            } else if (guess < numberToGuess) {
                System.out.println("Too low! 🔽");
            } else if (guess > numberToGuess) {
                System.out.println("Too high! 🔼");
            } else {
                System.out.println("🎉 Correct! You guessed it in " + attempts + " attempts.");
                return calculateScore(attempts, maxAttempts);
            }
        }

        System.out.println("❌ Out of attempts! The correct number was: " + numberToGuess);
        return 0; // no points if user fails to guess
    }

    public static int calculateScore(int attempts, int maxAttempts) {
        // Higher points for fewer attempts
        return (maxAttempts - attempts + 1) * 10;
    }
}
