package com.TerminatingThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;



public class TerminatingBlockedExecutorTasks {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here...");
		
		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadsFactory());
		
		Future<?> f1 = execService.submit(new LoopTaskA());
		Future<?> f2 = execService.submit(new LoopTaskG());
		Future<Long> f3 = execService.submit(new FactorialTaskB(30, 500));
		
		execService.shutdown();
		
		TimeUnit.MILLISECONDS.sleep(2000);
		
		System.out.println("[" + currentThreadName + "] Invoking cancel() on all the tasks ...");
		f1.cancel(true);
		f2.cancel(true);
		f3.cancel(true);
		
		System.out.println("[" + currentThreadName + "] Main thread ends here...");
	}
}
