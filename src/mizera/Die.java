/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Autumn Mizer
 * Last Updated: 9/11/2024
 */
package mizera;

import java.util.Random;

/**
 * Die class
 */
public class Die {
    /**
     * Minimum sides of die
     */
    public static final int MIN_SIDES = 2;
    /**
     * Maximum sides of die
     */
    public static final int MAX_SIDES = 100;

    private int currentValue;
    private final int numSides;
    private final Random random;


    /**
     * Default die class
     * @param numSides of die
     * @throws IllegalArgumentException if die sides are not within boundary
     */
    public Die(int numSides){
        if(numSides < MIN_SIDES || numSides > MAX_SIDES){
            throw new IllegalArgumentException("Illegal, not within boundary");
        } else{
            this.numSides = numSides;
        }
        this.random = new Random();
    }

    /**
     * get current value of die
     * @return current value
     * @throws DieNotRolledException if die not in expected range
     */
    public int getCurrentValue(){
        if(currentValue < 1 || currentValue > numSides){
            throw new DieNotRolledException();
        }
        return currentValue;
    }

    /**
     * Rolls a number between MIN_SIDES and MAX_SIDES, and then
     * attaches the value to 'currentValue'
     */
    public void roll(){
        currentValue = random.nextInt(numSides) + 1;
    }


}