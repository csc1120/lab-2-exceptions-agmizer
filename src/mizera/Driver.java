/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Autumn Mizer
 * Last Updated: 9/11/2024
 */
package mizera;

import java.util.Scanner;

/**
 * Driver class for dice
 */
public class Driver {

    /**
     * Minimum amount of dice
     */
    public static final int MIN_DICE = 2;

    /**
     * Maximum amount of dice
     */
    public static final int MAX_DICE = 10;
    /**
     * Minimum sides of die
     */
    public static final int MIN_SIDES = 2;
    /**
     * Maximum sides of die
     */
    public static final int MAX_SIDES = 100;
    public static void main(String[] args) {
        int[] diceInput = getInput();
        Die[] dice = createDice(diceInput[0], diceInput[1]);
        int[] rolls = rollDice(dice, diceInput[1], diceInput[2]);
        int max = findMax(rolls);

        report(diceInput[0], rolls, max);
    }

    private static int[] getInput(){
        Scanner in = new Scanner(System.in);
        int[] holder = new int[3];
        boolean isValid = false;

        System.out.println("""
                        Please enter the number of dice to roll, how many sides the dice have,
                        and how many rolls to complete, separating the values by a space.
                        Example: "2 6 1000\"""");
        while(!isValid){
            try{
                System.out.print("\nEnter configuration: ");
                String numbers = in.nextLine();
                String[] numberArray = numbers.split(" ", 3);

                if(numberArray.length == 3){
                    for(int i = 0; i < numberArray.length; i++){
                        holder[i] = Integer.parseInt(numberArray[i]);
                    }

                    if(holder[0] > MAX_DICE || holder[0] < MIN_DICE){
                        System.out.println("Invalid Input: Not within the dice boundary");
                    } else if(holder[1] < MIN_SIDES || holder[1] > MAX_SIDES){
                        System.out.println("Bad die creation: Illegal number of sides: "
                                           + holder[1]);
                    } else if (holder[2] <= 0){
                        System.out.println("Cannot roll 0 or less times");
                    }
                } else{
                    System.out.println("Expected 3 values but only recieved "
                                       + numberArray.length);
                }

                if(holder[0] <= MAX_DICE && holder[0] >= MIN_DICE){
                    if(holder[1] >= MIN_SIDES && holder[1] <= MAX_SIDES){
                        if(holder[2] > 0){
                            isValid = true;
                        }
                    }
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid Input: All values must be whole numbers\n");
            }
        }

        return holder;
    }

    private static Die[] createDice(int numDice, int numSides){
        Die[] dieArray = new Die[numDice];
        for(int i = 0; i < numDice; i++){
            dieArray[i] = new Die(numSides);
        }
        return dieArray;
    }

    private static int[] rollDice(Die[] dice, int numSides, int numRolls){
        int[] holder = new int[(numSides * dice.length) - dice.length + 1];
        int value = 0;
        for(int i = 0; i < numRolls; i++){
            for (Die die : dice) {
                die.roll();
                value += die.getCurrentValue();
            }
            for(int j = 0; j < holder.length + 1; j++){
                if(value == j + dice.length){
                    holder[j] += 1;
                }
            }
            value = 0;
        }
        return holder;
    }

    private static int findMax(int[] rolls){
        int max = rolls[0];
        for (int roll : rolls) {
            if(roll > max){
                max = roll;
            }
        }
        return max;
    }

    private static void report(int numDice, int[] rolls, int max){
        final int ten = 10;
        int numStars;
        double scale = (double)max / ten;

        for(int i = 0; i < rolls.length; i++){
            if((i + numDice) >= ten){
                System.out.printf("%2d:%-9d", i + numDice, rolls[i]);
            } else{
                System.out.printf("%d :%-9d", i + numDice, rolls[i]);
            }
            numStars = (int)(rolls[i] / scale);

            for(int j = 0; j < numStars; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}