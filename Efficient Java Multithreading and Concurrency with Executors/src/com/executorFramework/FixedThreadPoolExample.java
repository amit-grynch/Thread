package com.executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService service = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 6; i++) {
			service.submit(new Task());
		}
		service.shutdown();
	}

}

class Task implements Runnable {
	int id = 0;
	static int count = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(" Task with Id " + id + " has Started");
		for (int i = 0; i < 10; i++) {
			System.out.println("Task is runnig by Thread Id " + id);
			try {
				TimeUnit.MICROSECONDS.sleep((long) Math.random() * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(" Task with Id " + id + " has Ended");
	}

	public Task() {
		this.id = ++count;
	}
}
