package Chapter5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerProblemUsingBlockingQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(20);
		new Thread(new ProducerThread(queue)).start();
		new Thread(new ConsumerThread(queue)).start();

	}

}


class ProducerThread implements Runnable{
	
	private final BlockingQueue<Integer> queue;
    public ProducerThread(BlockingQueue<Integer> queue) {
    	this.queue=queue;
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int index=0;index<10;index++) {
			try {
				System.out.println("Producer is putting Value  : "+index);
				queue.put(index);
				System.out.println("Current Capacity After Putting Value In Queue By producer :"+queue.size());
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

class ConsumerThread implements Runnable{
	private final BlockingQueue<Integer> queue;
	public ConsumerThread(BlockingQueue<Integer> queue) {
		this.queue=queue;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
			System.out.println("Consumer is taking Value : "+queue.take());
			System.out.println("Current Capacity After Taking Value From Queue By Consumer :"+queue.size());
			Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}