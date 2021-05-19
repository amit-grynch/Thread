package com.namingThread;

import java.util.concurrent.TimeUnit;

public class NameThreadInTask {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String currentRunningThread = Thread.currentThread().getName();
		System.out.println("[" + currentRunningThread + "] Main Thread Started ");
		new Thread(new ThreadTask()).start();
		;
		Thread thread2 = new Thread(new ThreadTask());
		thread2.start();
		System.out.println("[" + currentRunningThread + "] Main Thread Ended  ");
	}

}

class ThreadTask implements Runnable {
	int id = 0;
	static int count = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String currentRunningThread = Thread.currentThread().getName();
		System.out.println("  #### Task with Id " + id + " has Started  by Thread " + currentRunningThread);
		for (int i = 0; i < 10; i++) {
			System.out.println("Task is runnig by Thread  " + currentRunningThread);
			try {
				TimeUnit.MICROSECONDS.sleep((long) Math.random() * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("  #### Task with Id " + id + " has Ended  by Thread" + currentRunningThread);
	}

	public ThreadTask() {
		this.id = ++count;
	}
}
