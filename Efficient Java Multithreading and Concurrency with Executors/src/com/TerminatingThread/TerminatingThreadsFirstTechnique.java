package com.TerminatingThread;

import java.util.concurrent.TimeUnit;

public class TerminatingThreadsFirstTechnique {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here...");

		LoopTaskE task1 = new LoopTaskE();
		LoopTaskE task2 = new LoopTaskE();
		LoopTaskE task3 = new LoopTaskE();

		new Thread(task1, "MyThread-1").start();
		new Thread(task2, "MyThread-2").start();
		new Thread(task3, "MyThread-3").start();

		TimeUnit.MILLISECONDS.sleep(5000);
		task1.cancel();
		task2.cancel();
		task3.cancel();

		System.out.println("[" + currentThreadName + "] Main thread ends here...");
	}
}
