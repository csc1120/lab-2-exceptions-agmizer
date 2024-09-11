/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Autumn Mizer
 * Last Updated: 9/11/2024
 */
package mizera;

/**
 * Die not rolled exception class
 */
public class DieNotRolledException extends RuntimeException {
    /**
     * Get generic message for exception
     * @return exception message
     */
    @Override
    public String getMessage(){
        return "Die not rolled in expected range";
    }
}
