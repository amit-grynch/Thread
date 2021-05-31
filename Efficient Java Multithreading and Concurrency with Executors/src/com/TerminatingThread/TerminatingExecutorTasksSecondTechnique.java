package com.TerminatingThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;



public class TerminatingExecutorTasksSecondTechnique {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here...");
		
//		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadsFactory());
		ExecutorService execService = Executors.newFixedThreadPool(2, new NamedThreadsFactory());
		
		Future<Long> f1 = execService.submit(new CalculationTaskC());
		Future<?> f2 = execService.submit(new LoopTaskF());
		Future<?> f3 = execService.submit(new LoopTaskF());
		
		execService.shutdown();
		
		TimeUnit.MILLISECONDS.sleep(2000);
		
		System.out.println("[" + currentThreadName + "] Interrupting 'CalculationTaskC-1' ....");
		System.out.println("[" + currentThreadName + "] RESULT for f1 cancellation = " + f1.cancel(true));
		
		System.out.println("[" + currentThreadName + "] Interrupting 'LoopTaskF-1' ....");
		System.out.println("[" + currentThreadName + "] RESULT for f2 cancellation = " + f2.cancel(true));
		
		System.out.println("[" + currentThreadName + "] Interrupting 'LoopTaskF-2' ....");
		System.out.println("[" + currentThreadName + "] RESULT for f3 cancellation = " + f3.cancel(true));
		
		System.out.println("[" + currentThreadName + "] Main thread ends here...");
	}
}
