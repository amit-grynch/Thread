package Chapter5;

import java.util.concurrent.CountDownLatch;

public class TestHarness {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			long timeTask = timeTasks(5);
			System.out.println(" TimeTaken :" + timeTask);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static long timeTasks(int nThreads) throws InterruptedException {
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);

		for (int i = 0; i < nThreads; i++) {
			Thread t = new Thread() {
				public void run() {
					System.out.println(" In Run");
					try {
						System.out.println(" Before  startGate.await()");
						startGate.await();
						try {
							System.out.println("Doing Something");
						} finally {
							System.out.println("countDown");
							endGate.countDown();
						}
					} catch (InterruptedException ignored) {
					}
				}
			};
			t.start();
		}

		long start = System.nanoTime();
		System.out.println("Calculate start Time : " + start + " startGate :" + startGate);
		startGate.countDown();
		System.out.println(" startGate :" + startGate);
		System.out.println(" endGate :" + endGate);
		endGate.await();
		System.out.println(" endGate :" + endGate);
		long end = System.nanoTime();
		System.out.println("Calculate end Time : " + end);
		return end - start;
	}
}
