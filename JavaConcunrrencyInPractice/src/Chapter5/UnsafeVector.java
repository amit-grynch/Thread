package Chapter5;

import java.util.Vector;

public class UnsafeVector {

	public static void main(String[] args) {

	}

	/**
	 * @param vector
	 * @return Not Threadsafe , not make it atomic, need to aquire the lock of
	 *         Synchronized Collection Lock
	 */
	public static Object getLast(Vector vector) {
		synchronized (vector) {
			int size = vector.size() - 1;
			return vector.get(size);
		}
	}

	/**
	 * @param vector
	 *            Not Threadsafe , not make it atomic, need to aquire the lock of
	 *            Synchronized Collection Lock
	 */
	public static void deleteLast(Vector vector) {
		synchronized (vector) {
			int size = vector.size() - 1;
			vector.remove(size);
		}
	}
}
