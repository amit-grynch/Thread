package Chapter5;

public class IntrruptionFlagTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestInterruptingThread t1 = new TestInterruptingThread();
		TestInterruptingThread t2 = new TestInterruptingThread();

		t1.start();
	    t1.interrupt();
		t2.start();
	}

}

class TestInterruptingThread extends Thread {

	public void run() {
		for (int i = 1; i <= 2; i++) {
			if (Thread.interrupted()) {
				System.out.println("code for interrupted thread " +i);
			} else {
				System.out.println("code for normal thread " + i);
			}

		} // end of for loop
	}

}
