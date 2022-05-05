import java.util.Arrays;
import java.util.Comparator;

/**
 * Example showing the use of the Animal interface to represent the
 * population of an unusual zoo where the Animals can be sorted by
 * weight with an anonymous Comparator.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

// inner class defining one possible Comparator for the
// Animal objects we will be working with
class LegsComparator implements Comparator<Animal> {

	/**
	 * Compare two Animal objects by their number of legs.
	 * 
	 * @param a the first animal
	 * @param b the second animal
	 * @return a negative number of a has fewer legs, 0 if they
	 *         have the same number of legs, and a positive number if b has
	 *         fewer legs
	 */
	public int compare(Animal a, Animal b) {

		return a.numLegs() - b.numLegs();
	}
}

public class ZooArray {

	public static void main(String args[]) {

		// Create and populate our array-based zoo!
		Animal animals[] = { new Snake(5.5),
				new Dog(25.2),
				new Spider(0.04),
				new Spider(0.06),
				new Dog(55.9),
				new Dog(12.0),
				new Spider(0.1),
				new Spider(0.11),
				new Spider(0.007),
				new Snake(2.1),
				new Snake(0.4),
				new Snake(12.2),
				new Dog(95.6)
		};
		// print out using toString
		System.out.println("Our zoo population:");
		System.out.println(Arrays.toString(animals));

		// let's sort the animals by number of legs using the
		// Comparator defined as an inner class
		Arrays.sort(animals, new LegsComparator());
		System.out.println("Our legs-sorted zoo population:");
		System.out.println(Arrays.toString(animals));

		// let's sort the animals by weight using an anonymous Comparator
		Arrays.sort(animals, new Comparator<Animal>() {
			public int compare(Animal a, Animal b) {
				if (a.getWeight() > b.getWeight())
					return 1;
				if (a.getWeight() < b.getWeight())
					return -1;
				return 0;
			}
		});
		System.out.println("Our weight-sorted zoo population:");
		System.out.println(Arrays.toString(animals));

	}
}
