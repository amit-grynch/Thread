package Chapter7;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author amitg Code is unreliable if We call cancel method because put is
 *         block when queue is full.
 */
public class UnrelaibleCancellationTask {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newFixedThreadPool(2);
		BlockingQueue<BigInteger> primeBlockingQueue = new ArrayBlockingQueue<>(10);
		BlockingQueuePrimeProducer producer = new BlockingQueuePrimeProducer(primeBlockingQueue);
		BlockingQueuePrimeConsumer consumer = new BlockingQueuePrimeConsumer(primeBlockingQueue);
		executor.submit(producer);
		executor.submit(consumer);
		try {
			while (primeBlockingQueue.size() > 0) {
				consumer.run();
			}
		} finally {
			producer.cancel();
		}
	}

}

class BlockingQueuePrimeProducer implements Runnable {
	private final BlockingQueue<BigInteger> primeQueue;
	private volatile boolean cancellationRequest;

	BlockingQueuePrimeProducer(BlockingQueue<BigInteger> primeQueue) {
		this.primeQueue = primeQueue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BigInteger primeNumber = BigInteger.ONE;
		while (!cancellationRequest) {
			try {
				primeQueue.put(primeNumber.nextProbablePrime());
				System.out.println("Produced PrimeNumber = " + primeNumber);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void cancel() {
		cancellationRequest = true;
	}
}

class BlockingQueuePrimeConsumer implements Runnable {
	private final BlockingQueue<BigInteger> primeConsumerQueue;

	BlockingQueuePrimeConsumer(BlockingQueue<BigInteger> primeConsumerQueue) {
		this.primeConsumerQueue = primeConsumerQueue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BigInteger intConsumer = primeConsumerQueue.take();
			System.out.println("Counsumed Prime NUmber is =" + intConsumer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

}
