package Chapter5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueOperation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(19);
		Producer prodcuer = new Producer(queue);
		Consumer consumer=new Consumer(queue);
		prodcuer.start();
		Thread.sleep(1000);
		//prodcuer.interrupt();
		consumer.start();
		System.out.println("BlockingQueue contains " + prodcuer.queue);

	}

}

class Producer extends Thread {

	BlockingQueue<String> queue = null;

	Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("putting element in Queue");
			queue.put("1");
			Thread.sleep(10);
			queue.put("3");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(" Interrupting the Thread");
			// e.printStackTrace();
		}

	}
}

class Consumer extends  Thread {
	BlockingQueue<String> queue=null;
	Consumer(BlockingQueue<String> queue){
		this.queue=queue;
	}
	public void run() {
		try {
		System.out.println("Removing The Element From Queue :" +queue.take());
		System.out.println("Removing The Element From Queue :" +queue.take());
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
