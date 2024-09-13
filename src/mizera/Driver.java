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
        int[] holder = new int[diceInput[1] * diceInput[0] - 1];

        for(int i = 0; i < diceInput[0] + 1; i++){
            dice[i].roll();
            for(int j = 0; i < holder.length + 1; j++){
                if(dice[i].getCurrentValue() == j + 1){
                    holder[j] += 1;
                }
            }
        }
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
                        System.out.println(STR."Bad die creation: Illegal number of sides: \{holder[1]}");
                    }
                } else{
                    System.out.println(STR."Expected 3 values but only recieved \{numberArray.length}");
                }

                if(holder[0] <= MAX_DICE && holder[0] >= MIN_DICE){
                    if(holder[1] >= MIN_SIDES && holder[1] <= MAX_SIDES){
                        isValid = true;
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
}