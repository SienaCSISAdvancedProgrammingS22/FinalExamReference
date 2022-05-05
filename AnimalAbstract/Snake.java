/**
 * Class defining a few characteristics of a snake.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 * 
 */

public class Snake extends Animal {

    /**
     * Construct a new snake with the given weight
     * 
     * @param weight the initial weight of the snake
     */
    public Snake(double weight) {

        this.weight = weight;
    }

    /**
     * Return the species: "snake"
     * 
     * @return the species "snake"
     */
    public String species() {

        return "snake";
    }

    /**
     * Return the number of legs that snakes have (0).
     * 
     * @return 0 for the number of legs a snake has
     */
    public int numLegs() {

        return 0;
    }

    /**
     * Build and return a String representation of the snake.
     * 
     * @return String representation of the snake
     */
    public String toString() {

        return "Snake weighs " + weight + " pounds.";
    }

}
