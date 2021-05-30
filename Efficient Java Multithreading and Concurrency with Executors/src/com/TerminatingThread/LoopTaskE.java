package com.TerminatingThread;

import java.util.concurrent.TimeUnit;

public class LoopTaskE implements Runnable {
	
	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	
	private volatile boolean shutdown = false;
	
	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("##### [" + currentThreadName + "] <" + taskId + 
				"> STARTING #####");
		
		for (int i=1;; i++) {
			System.out.println("[" + currentThreadName + "] <" + taskId + 
					"> TICK TICK - " + i);
			
			try {
				TimeUnit.MILLISECONDS.sleep((long)(Math.random() * 3000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized(this) {
				if (shutdown) {
					break;
				}
			}
		}
		
		System.out.println("***** [" + currentThreadName + "] <" + taskId + 
				"> COMPLETED *****");
	}
	
	public void cancel() {
		System.out.println("***** [" + Thread.currentThread().getName() + "] <" + taskId + "> Shutting down *****");
		
		synchronized (this) {
			this.shutdown = true;
		}
	}
	
	public LoopTaskE() {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskE" + instanceNumber;
	}
}