/**
 * Class defining a few characteristics of a dog.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 * 
 */

public class Dog extends Animal {

    /**
     * Construct a new dog with the given weight
     * 
     * @param weight the initial weight of the dog
     */
    public Dog(double weight) {

        this.weight = weight;
    }

    /**
     * Return the species: "dog"
     * 
     * @return the species "dog"
     */
    @Override
    public String species() {

        return "dog";
    }

    /**
     * Return the number of legs that dogs have (4).
     * 
     * @return 4 for the number of legs a dog has
     */
    @Override
    public int numLegs() {

        return 4;
    }

    /**
     * Bark!
     */
    public void bark() {
        if (weight < 20) {
            System.out.println("Yip!");
        } else if (weight < 50) {
            System.out.println("Bark!");
        } else {
            System.out.println("Woof!");
        }
    }

    /**
     * Build and return a String representation of the dog.
     * 
     * @return String representation of the dog
     */
    public String toString() {

        return "Dog weighs " + weight + " pounds.";
    }

}
