package com.TerminatingThread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialTaskA implements Callable<Long> {
	
	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	
	private volatile boolean shutdown = false;
	
	private long a;
	private long sleepTime;
	private long factorial;
	
	@Override
	public Long call() throws Exception {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("##### [" + currentThreadName + "] <" + taskId +	"> STARTING #####");
		
		factorial = 1L;
		
		for (long i=1; i <= a; i++) {
			factorial *= i;
			
			System.out.println("[" + currentThreadName + "] <" + taskId + "> Iteration - " + i + ". Intermediate " +
					"Result = " + factorial);
			
			TimeUnit.MILLISECONDS.sleep(sleepTime);
			
			synchronized(this) {
				if (shutdown) {
					factorial = -1;
					break;
				}
			}
		}
		
		System.out.println("***** [" + currentThreadName + "] <" + taskId + "> COMPLETED *****");
		
		return factorial;
	}
	
	public void cancel() {
		System.out.println("***** [" + Thread.currentThread().getName() + "] <" + taskId + "> Shutting down *****");
		
		synchronized(this) {
			this.shutdown = true;
		}
	}
	
	public FactorialTaskA(long a, long sleepTime) {
		this.a = a;
		this.sleepTime = sleepTime;
		
		this.instanceNumber = ++count;
		this.taskId = "FactorialTaskA" + instanceNumber;
	}
}