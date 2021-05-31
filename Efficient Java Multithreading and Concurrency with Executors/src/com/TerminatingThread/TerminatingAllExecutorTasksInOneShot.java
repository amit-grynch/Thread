package com.TerminatingThread;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;



public class TerminatingAllExecutorTasksInOneShot {

	public static void main(String[] args) throws Exception {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here...");
		
		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadsFactory());
		
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskF());
		Future<Long> t3Future = execService.submit(new FactorialTaskB(30, 500));
		Future<Long> t4Future = execService.submit(new CalculationTaskC());
		Future<TaskResult<String, Integer>> t5Future = execService.submit(new CalculationTaskB(2, 3, 9000));
		
		TimeUnit.MILLISECONDS.sleep(1000);
		
		System.out.println("[" + currentThreadName + "] SHUTTING DOWN NOW ...");
		execService.shutdownNow();
		
		System.out.println("[" + currentThreadName + "] ALL THREADS TERMINATED = " + 
				execService.awaitTermination(7000, TimeUnit.MILLISECONDS));
		
		System.out.println("[" + currentThreadName + "] 'FactorialTaskB-1' Result = " + t3Future.get());
		
		System.out.println("[" + currentThreadName + "] 'CalculationTaskC1' Result = " + t4Future.get());
		
		try {
			System.out.println("[" + currentThreadName + "] 'CalculationTaskB1' Result = " + t5Future.get());
		} catch (ExecutionException ee) {
			System.out.println("[" + currentThreadName + "] 'CalculationTaskB1 Result = Got ExecutionException. The cause is : \n");
			
			StringWriter sw = new StringWriter();
			ee.getCause().printStackTrace(new PrintWriter(sw));
			
			System.out.println(sw.toString());
		}
		
		System.out.println("[" + currentThreadName + "] Main thread ends here...");
	}
}
