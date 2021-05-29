package com.executorFramework;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ReturningValueFromExecutors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mainThreadName = Thread.currentThread().getName();
		System.out.println(" Main Thread <" + mainThreadName + " > Starting Execution ......");
		ExecutorService executorService = Executors.newCachedThreadPool(new ThreadPoolNamingFactory());
		   Future<Integer> result1 = executorService.submit(new ValueReturningExecutorA(10,20,1000));
		   Future<Integer> result2 = executorService.submit(new ValueReturningExecutorA(100,200,500));
		   Future<Integer> result3 = executorService.submit(new ValueReturningExecutorA(1000,2000,250));
		   try {
			System.out.println(" Getting Result Form Future 1:"+result1.get());
			System.out.println(" Getting Result Form Future 2:"+result2.get());
			System.out.println(" Getting Result Form Future 3:"+result3.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   executorService.shutdown();
		System.out.println(" Main Thread " + mainThreadName + " Ending  Execution ......");
	}

}

class ThreadPoolNamingFactory implements ThreadFactory {
	private static int count = 0;
	private String PoolWorkerThreadName="PoolWorkerThread-";

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread th = new Thread(r, PoolWorkerThreadName+ ++count);
		return th;
	}

}

class ValueReturningExecutorA implements Callable<Integer> {
	private int num1;
	private int num2;
	private int sum;
	private long sleepTime;
	private static int count;
	private int instanceNumber;
	private String taskId;


	public ValueReturningExecutorA(int num1, int num2, long sleepTime) {
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.sleepTime = sleepTime;
		this.instanceNumber = ++count;
		this.taskId = "ValueReturningExecutorA-" + instanceNumber;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("#### ThreadPool Worker Thread--> < " + currentThreadName + "> has Started The Task <" + taskId + ">");
		sum = num1 + num2;
		TimeUnit.MILLISECONDS.sleep(sleepTime);
		System.out.println("#### Thread--> < " + currentThreadName + "> has Completed The Task <" + taskId + ">");
       return sum;
	}


}