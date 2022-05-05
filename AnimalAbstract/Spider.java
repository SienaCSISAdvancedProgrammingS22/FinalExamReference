/**
 * Class defining a few characteristics of a spider.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 * 
 */

public class Spider extends Animal {

    /**
     * Construct a new spider with the given weight
     * 
     * @param weight the initial weight of the spider
     */
    public Spider(double weight) {

        this.weight = weight;
    }

    /**
     * Return the species: "spider"
     * 
     * @return the species "spider"
     */
    public String species() {

        return "spider";
    }

    /**
     * Return the number of legs that spiders have (8).
     * 
     * @return 8 for the number of legs a spider has
     */
    public int numLegs() {

        return 8;
    }

    /**
     * Build and return a String representation of the spider.
     * 
     * @return String representation of the spider
     */
    public String toString() {

        return "Spider weighs " + weight + " pounds.";
    }

}
