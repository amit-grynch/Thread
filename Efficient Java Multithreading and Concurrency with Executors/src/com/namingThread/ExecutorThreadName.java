package com.namingThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ExecutorThreadName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(Thread.currentThread().getName() + " Main Thread Started ");
		ExecutorService executor = Executors.newCachedThreadPool(new NamedThreadFactory());
		for (int i = 0; i < 3; i++) {
			executor.submit(new ExecutorTask());
		}
		executor.shutdown();
		System.out.println(Thread.currentThread().getName() + " Main Thread Ended ");
	}

}

class ExecutorTask implements Runnable {
	int id = 0;
	static int count = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("  #### Task with Id " + id + " has Started  by Thread " + Thread.currentThread().getName());
		for (int i = 0; i < 10; i++) {
			System.out.println("Task is runnig by Thread  " + Thread.currentThread().getName());
			try {
				TimeUnit.MICROSECONDS.sleep((long) Math.random() * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("  #### Task with Id " + id + " has Ended  by Thread" + Thread.currentThread().getName());
	}

	public ExecutorTask() {
		this.id = ++count;
	}
}

class NamedThreadFactory implements ThreadFactory {
	private static int count = 0;
	private String name = "PollWorker-";

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r, name + ++count);
		return t;
	}

}
