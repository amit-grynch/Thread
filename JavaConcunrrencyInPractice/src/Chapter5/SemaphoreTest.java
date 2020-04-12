package Chapter5;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyCustomThread thread1 = new MyCustomThread(" A");
		thread1.start();
		MyCustomThread thread2 = new MyCustomThread(" B");
		thread2.start();
		MyCustomThread thread3 = new MyCustomThread(" C");
		thread3.start();
		MyCustomThread thread4 = new MyCustomThread(" D");
		thread4.start();
		MyCustomThread thread5 = new MyCustomThread(" E");
		thread5.start();
		MyCustomThread thread6 = new MyCustomThread("F");
		thread6.start();
		MyCustomThread thread7 = new MyCustomThread(" G");
		thread7.start();
		MyCustomThread thread8 = new MyCustomThread("H");
		thread8.start();

	}

}

class MyCustomThread extends Thread {
	Semaphore semaphore = new Semaphore(5);
	String name;

	public MyCustomThread(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread " + name + " is Acquiring Permit From Semaphore ");
		System.out.println(" Total number of Permit available before aquire  :" + semaphore.availablePermits());
		try {
			semaphore.acquire();
			System.out.println("Thread " + name + " Permit Acruired From Semaphore");
			System.out.println(" Total number of Permit available after aquire:" + semaphore.availablePermits());
			try {
				for (int i = 1; i <= 5; i++) {

					System.out.println(name + " : is performing operation " + i + ", available Semaphore permits : "
							+ semaphore.availablePermits());

					// sleep 1 second
					Thread.sleep(10000);

				}
			} finally {
				System.out.println(name + " : releasing lock...");
				semaphore.release();
				System.out.println(name + " : available Semaphore permits now: " + semaphore.availablePermits());
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
