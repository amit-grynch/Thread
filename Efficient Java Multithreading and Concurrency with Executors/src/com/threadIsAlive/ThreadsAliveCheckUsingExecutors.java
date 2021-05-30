package com.threadIsAlive;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ThreadsAliveCheckUsingExecutors {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here...");

		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadsFactory());

		Future<?> f1 = execService.submit(new LoopTaskC());
		Future<Integer> f2 = execService.submit(new CalculationTaskA(3, 4, 700));

		FutureTask<?> ft3 = new FutureTask<Void>(new LoopTaskC(), null);
		FutureTask<Integer> ft4 = new FutureTask<Integer>(new LoopTaskC(), 999);
		FutureTask<Integer> ft5 = new FutureTask<Integer>(new CalculationTaskA(4, 5, 500));

		execService.execute(ft3);
		execService.execute(ft4);
		execService.execute(ft5);

		execService.shutdown();

		for (int i = 1; i <= 5; i++) {
			TimeUnit.MILLISECONDS.sleep(300);

			System.out.println("[" + currentThreadName + "] ITR-" + i + " -> Is 'LoopTaskC-1' done = " + f1.isDone());
			System.out.println("[" + currentThreadName + "] ITR-" + i + " -> Is 'CalcTaskA-1' done = " + f2.isDone());
			System.out.println("[" + currentThreadName + "] ITR-" + i + " -> Is 'LoopTaskC-2' done = " + ft3.isDone());
			System.out.println("[" + currentThreadName + "] ITR-" + i + " -> Is 'LoopTaskC-3' done = " + ft4.isDone());
			System.out.println("[" + currentThreadName + "] ITR-" + i + " -> Is 'CalcTaskA-2' done = " + ft5.isDone());
		}

		System.out.println("\n$$$$$ [" + currentThreadName + "] Retrieving results now ... $$$$$");

		System.out.println("[" + currentThreadName + "] 'LoopTaskC-1' result = " + f1.get());
		System.out.println("[" + currentThreadName + "] 'CalcTaskA-1' result = " + f2.get());

		System.out.println("[" + currentThreadName + "] 'LoopTaskC-2' result = " + ft3.get());
		System.out.println("[" + currentThreadName + "] 'LoopTaskC-3' result = " + ft4.get());
		System.out.println("[" + currentThreadName + "] 'CalcTaskA-2' result = " + ft5.get());

		System.out.println("[" + currentThreadName + "] Main thread ends here...");
	}
}
