import java.util.ArrayList;
import java.util.List;

/**
 * Example showing the use of the Animal interface to represent the
 * population of an unusual zoo.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

public class Zoo {

	public static void main(String args[]) {

		// Create and populate our zoo!
		// Note that we construct an ArrayList but store
		// as a List since we will be restricting to only
		// List operations and would then be able to
		// change only the object construction if we wanted
		// to switch to a different implementation later.
		List<Animal> animals = new ArrayList<Animal>();

		animals.add(new Snake(5.5));
		animals.add(new Dog(25.2));
		animals.add(new Spider(0.04));
		animals.add(new Spider(0.06));
		animals.add(new Dog(55.9));
		animals.add(new Dog(12.0));
		animals.add(new Spider(0.1));
		animals.add(new Spider(0.11));
		animals.add(new Spider(0.007));
		animals.add(new Snake(2.1));
		animals.add(new Snake(0.4));
		animals.add(new Snake(12.2));
		animals.add(new Dog(95.6));

		// print out using toString
		System.out.println("Our zoo population:");
		System.out.println(animals);

		// we can also run through and count legs
		int numLegs = 0;
		for (Animal a : animals) {
			numLegs += a.numLegs();
		}
		System.out.println("Total legs in the zoo: " + numLegs);

		// If we want to know what the actual class is, in this
		// case, we could call the species method, but we can
		// always check with the instanceof operator
		int numDogs = 0;
		for (Animal a : animals) {
			if (a instanceof Dog) {
				numDogs++;
				// and we can also cast it to a Dog and
				// call Dog-specific methods that are not
				// part of the Animal interface
				Dog d = (Dog) a;
				d.bark();
			}
		}
		System.out.println("Dogs in the zoo: " + numDogs);

	}
}
