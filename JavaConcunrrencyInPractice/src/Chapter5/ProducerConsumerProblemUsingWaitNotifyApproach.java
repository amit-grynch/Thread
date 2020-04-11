package Chapter5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author amitg
 *
 */
public class ProducerConsumerProblemUsingWaitNotifyApproach {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> queue = new LinkedList<Integer>();
		new CustomCounsumerThread(queue, 10).start();
		new CustomProducerThread(queue, 5).start();
	}

}

/**
 * @author amitg
 *
 */
class CustomProducerThread extends Thread {
	private Queue<Integer> queue;
	private int size;
	int i;

	/**
	 * @param queue
	 * @param size
	 */
	public CustomProducerThread(Queue<Integer> queue, int size) {
		this.queue = queue;
		this.size = size;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (queue) {
				while (queue.size() == size) {
					try {
						System.out.println("Queue is full and waiting the Consumer to consume :");
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Random randomInt = new Random();
				i = randomInt.nextInt(10);
				System.out.println("Producer is Adding Number in Queue : " + i);
				queue.add(i);
				queue.notifyAll();
			}
		}
	}
}

/**
 * @author amitg
 *
 */
class CustomCounsumerThread extends Thread {
	int size;
	int i;
	Queue<Integer> queue;

	/**
	 * @param queue
	 * @param size
	 */
	CustomCounsumerThread(Queue<Integer> queue, int size) {
		this.queue = queue;
		this.size = size;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (queue) {
				while (queue.size() == 0) {
					System.out.println("Queue is Empty and waiting the Producer  to Produce :");
					try {
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				i = queue.remove();
				System.out.println("Consumer Has Removed The Number From Queue :" + i);
				queue.notifyAll();
			}
		}
	}
}