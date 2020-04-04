
/*
 *  Using Instance Confinement with Proper Locking mechanism to ensure Thread Safety of class
 *  which contains a state variables which are not Thread Safe.
 */

package Chapter4;

import java.util.HashSet;
import java.util.Set;

/**
 * @author amitg
 *  
 */
public class InstanceConfinement {

	private final Set<Person> mySet=new HashSet<Person>();
	
	public synchronized void addPerson(Person p) {
		mySet.add(p);
	}
	public synchronized boolean containsPerson(Person p) {
		return mySet.contains(p);
	}
}

/**
 * @author amitg
 * if  class is not Thread Safe then can make it Thread safe or ca use lock on Perosn object while
 * performing any operation on the Person Object
 */
class Person{
	
}
