package Chapter5;

import java.util.concurrent.Semaphore;

public class PrintingJobUsingSemaphore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintQueue printQueue = new PrintQueue();
		Thread thread[] = new Thread[5];
		for (int index = 0; index < 5; index++) {
			thread[index] = new Thread(new PrintingJob(printQueue), "Thread " + index);
		}
		for (int index = 0; index < 5; index++) {
			thread[index].start();
		}
	}

}

class PrintQueue {
	Semaphore semaphore;

	PrintQueue() {
		semaphore = new Semaphore(1);
	}

	public void printJob() {
		try {
			semaphore.acquire();
			Long duration = (long) (Math.random() * 10000);
			System.out.println("Thread Name :" + Thread.currentThread().getName() + " Printing ");
			Thread.sleep(duration);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.printf("The document has been printed\n" + Thread.currentThread().getName());
			semaphore.release();
		}
	}
}

class PrintingJob implements Runnable {
	PrintQueue printQueue;

	public PrintingJob(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}

	public void run() {
		System.out.println("Going to Print The Job by Thread : " + Thread.currentThread().getName());
		printQueue.printJob();
	}
}
