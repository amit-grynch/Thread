package com.executorFramework;

public class ValueGetterFromNormalThreadUsingObserverPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" Thread < " + Thread.currentThread().getName() + " >  Main Thread Started");

		ValueReturningTaskB task1 = new ValueReturningTaskB(3, 4, 3000, new ReturningValueListnerImpl("TaskListner-1"));
		ValueReturningTaskB task2 = new ValueReturningTaskB(5, 6, 2000, new ReturningValueListnerImpl("TaskListner-2"));
		ValueReturningTaskB task3 = new ValueReturningTaskB(10, 20, 1000,	new ReturningValueListnerImpl("TaskListner-3"));
		Thread thread1 = new Thread(task1, "Thread-1");
		Thread thread2 = new Thread(task2, "Thread-2");
		Thread thread3 = new Thread(task3, "Thread-3");
		thread1.start();
		thread2.start();
		thread3.start();
		System.out.println("Thread < " + Thread.currentThread().getName() + ">  Main Thread Completed");
	}

}

class ValueReturningTaskB implements Runnable {
	private int num1;
	private int num2;
	private int sum;
	private long sleepTime;
	private static int count;
	private int instanceNumber;
	private String taskId;
	private ReturningValueListnerImpl returningValueListner;

	public ValueReturningTaskB(int num1, int num2, long sleepTime, ReturningValueListnerImpl returningValueListner) {
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.sleepTime = sleepTime;
		this.instanceNumber = ++count;
		this.taskId = "ValueRetuningTask-" + instanceNumber;
		this.returningValueListner = returningValueListner;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("#### Thread--> < " + currentThreadName + "> has Started The Task <" + taskId + ">");
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sum = num1 + num2;
		System.out.println("#### Thread--> < " + currentThreadName + "> has Completed The Task <" + taskId + ">");
		returningValueListner.getResult(sum);
	}

}
