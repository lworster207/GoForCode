/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class RockPaperScissors {

    // class variable declarations
    public static final int ROCK = 1;
    public static final int PAPER = 2;
    public static final int SCISSORS = 3;

    public static void main(String[] args) {
        // This program supports "happy path" inputs only.

        // declarations
        Random randomPick = new Random();
        Scanner userInput = new Scanner(System.in);

        int userPick = 0;         // store the user's pick
        int computerPick = 0;     // store the computer's pick
        int numberOfRounds = 0;
        int userWins = 0;
        int computerWins = 0;
        int ties = 0;

        // used to display the chosen weapons in each round
        String[] weaponOptions = {"Rock", "Paper", "Scissors"};

        // prompt at least once
        do {
            // reset cumulative variables between rounds
            userWins = 0;
            computerWins = 0;
            ties = 0;

            // get the number of rounds to play
            numberOfRounds = getNumberOfRounds(userInput);

            if (numberOfRounds > 0) {
                // play until numberOfRounds == 0
                while (numberOfRounds-- > 0) {
                    userPick = getUserPick(userInput);
                    computerPick = randomPick.nextInt(3) + 1;

                    // show the user the weapons
                    System.out.println("You     Computer");
                    System.out.print(weaponOptions[userPick - 1] + "  " + weaponOptions[computerPick - 1] + " :");

                    // display the result for the round
                    if (userPick == computerPick) {
                        // the round is a tie
                        ties += 1;
                        System.out.println("Tied this round!\n");
                    } else if (didUserWin(userPick, computerPick)) {
                        userWins += 1;
                        System.out.println("You won this round!\n");
                    } else {
                        computerWins += 1;
                        System.out.println("The computer won this round!\n");
                    }

                }

                // display the overall results and winner
                System.out.println("User Wins: " + userWins + "   ComputerWins:" + computerWins + "   Ties: " + ties + "\n");

                if (userWins > computerWins) {
                    // the user won the most roundss
                    System.out.println("You won the most rounds!");
                } else if (userWins < computerWins) {
                    // the computer won the most roundss
                    System.out.println("The computer won the most rounds!");
                } else {
                    // tied number of winss
                    System.out.println("You and the computer tied!");
                }

            } else {
                System.out.println("Invalid number of rounds entered - exiting.");
            }

        } while (playAgain(userInput));
    }

    public static boolean didUserWin(int userPick, int computerPick) {
        // Determine if the user won
        // Return true if the user won, false if not

        /*  Rules of the game:
            Paper wraps Rock to win
            Scissors cut Paper to win
            Rock breaks Scissors to win
         */
        // assume the user will lose - change only when they win
        boolean userWin = false;

        switch (userPick) {
            case ROCK:
                // user picked Rock
                if (computerPick == SCISSORS) {
                    userWin = true;
                }
                break;
            case PAPER:
                // user picked paper
                if (computerPick == ROCK) {
                    userWin = true;
                }
                break;
            case SCISSORS:
                // user picked scissors
                if (computerPick == PAPER) {
                    userWin = true;
                }
                break;
        }
        return (userWin);

    }

    public static boolean playAgain(Scanner sc) {
        // prompt the user to play again
        System.out.print("\nPlay again? ( Y or N ) ");

        // save the response
        String response = sc.nextLine();

        //return true only if the user responsed with a Y or y
        return (response.equals("Y") || response.equals("y"));

    }

    public static int getUserPick(Scanner sc) {
        // prompt the user for their pick
        System.out.print("Please enter:     1 for Rock    2 for Paper    3 for Scissors ");

        int userPick = Integer.parseInt(sc.nextLine());

        return (userPick);

    }

    public static int getNumberOfRounds(Scanner sc) {
        // Prompt for the number of rounds to play
        // If valid, return the number of rounds.
        // Returns 0 if an invalid entry was made

        int numberOfRounds = 0;

        System.out.print("How many Rounds would you like to play?(1-10)");
        numberOfRounds = Integer.parseInt(sc.nextLine());

        // Only 1-10 rounds are valid.
        if (numberOfRounds > 10 || numberOfRounds < 1) {
            return (0);
        } else {
            return (numberOfRounds);
        }

    }
}
