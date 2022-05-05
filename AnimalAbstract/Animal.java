/**
 * Abstract class defining the required functionality of an Animal.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

abstract public class Animal {

   // the variable that was in all classes is moved into this abstract class
   protected double weight;

   /**
    * Return the species of the type of animal.
    * 
    * @return the species of the type of animal.
    */
   abstract public String species();

   /**
    * Return the number of legs that members of this
    * species should have.
    * 
    * @return the number of legs that members of this
    *         species should have
    */
   abstract public int numLegs();

   /**
    * Set the weight of this animal
    * 
    * @param newWeight the new weight of the animal
    */
   public void setWeight(double newWeight) {

      weight = newWeight;
   }

   /**
    * Get the weight of the animal
    * 
    * @return the weight of the animal
    */
   public double getWeight() {

      return weight;
   }
}
