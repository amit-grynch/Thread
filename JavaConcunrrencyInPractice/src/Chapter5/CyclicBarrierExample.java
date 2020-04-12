package Chapter5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable barrierAction1 = new Runnable() {
			// This is the task which take place after reaching Barrier Point
			public void run() {
				System.out.println("Barrier Action 1");
			};
		};
		Runnable barrierAction2 = new Runnable() {
			public void run() {
				System.out.println("Barrier Action 2");
			};
		};
		CyclicBarrier cb1 = new CyclicBarrier(2, barrierAction1);
		CyclicBarrier cb2 = new CyclicBarrier(2, barrierAction2);
		new Thread(new CyclicBarrierRunnanle(cb1, cb2)).start();
		new Thread(new CyclicBarrierRunnanle(cb1, cb2)).start();

	}

}

class CyclicBarrierRunnanle implements Runnable {
	CyclicBarrier barrier1;
	CyclicBarrier barrier2;

	CyclicBarrierRunnanle(CyclicBarrier barrier1, CyclicBarrier barrier2) {
		this.barrier1 = barrier1;
		this.barrier2 = barrier2;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			System.out.println(" Thread : " + Thread.currentThread().getName() + " waiting Barrier1");
			barrier1.await();
			Thread.sleep(1000);
			System.out.println(" Thread : " + Thread.currentThread().getName() + " waiting Barrier2");
			barrier2.await();
			System.out.println("Thread " + Thread.currentThread().getName() + " Done");
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}