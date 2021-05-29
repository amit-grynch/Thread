package com.executorFramework;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class CompletionServiceExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mainThreadName = Thread.currentThread().getName();
		System.out.println(" Main Thread <" + mainThreadName + " > Starting Execution ......");
		ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactoryImpl());
		CompletionService<TaskResult <String,Integer>> completionService = new ExecutorCompletionService<TaskResult <String,Integer>>(executorService);
		completionService.submit(new ValueReturningExecutorB(10, 20, 450));
		completionService.submit(new ValueReturningExecutorB(100, 200, 450));
		completionService.submit(new ValueReturningExecutorB(1000, 2000, 450));
		//completionService.submit(new ValueReturningTaskA(11, 22, 450), new TaskResult <String,Integer>());
		executorService.shutdown();
		for(int i=0;i<4;i++) {
			try {
				System.out.println(" Result is :"+completionService.take().get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(" Main Thread " + mainThreadName + " Ending  Execution ......");
	}

}

class ThreadFactoryImpl implements ThreadFactory {
	private static int count = 0;
	private String PoolWorkerThreadName = "PoolWorkerThread-";

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread th = new Thread(r, PoolWorkerThreadName + ++count);
		return th;
	}

}

class ValueReturningExecutorB implements Callable<TaskResult <String,Integer>> {
	private int num1;
	private int num2;
	private long sleepTime;
	private static int count;
	private int instanceNumber;
	private String taskId;

	public ValueReturningExecutorB(int num1, int num2, long sleepTime) {
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.sleepTime = sleepTime;
		this.instanceNumber = ++count;
		this.taskId = "ValueReturningExecutorA-" + instanceNumber;
	}

	@Override
	public TaskResult<String, Integer> call() throws Exception {
		// TODO Auto-generated method stub
		String currentThreadName = Thread.currentThread().getName();
		System.out.println(
				"#### ThreadPool Worker Thread--> < " + currentThreadName + "> has Started The Task <" + taskId + ">");
		TimeUnit.MILLISECONDS.sleep(sleepTime);
		System.out.println("#### Thread--> < " + currentThreadName + "> has Completed The Task <" + taskId + ">");
		return new TaskResult<String, Integer>(taskId, num1+num2);
	}

}