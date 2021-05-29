package com.executorFramework;

public class ValueGetterFromNormalThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("< " + Thread.currentThread().getName() + " >  Main Thread Started");

		ValueReturningTaskA task1 = new ValueReturningTaskA(3, 4, 3000);
		ValueReturningTaskA task2 = new ValueReturningTaskA(5, 6, 3000);
		ValueReturningTaskA task3 = new ValueReturningTaskA(10, 20, 3000);
		Thread thread1 = new Thread(task1, "Thread-1");
		Thread thread2 = new Thread(task2, "Thread-2");
		Thread thread3 = new Thread(task3, "Thread-3");
		thread1.start();
		thread2.start();
		thread3.start();
		System.out.println("Getting Result From TaskThread 1 : " + task1.getSum());
		System.out.println("Getting Result From TaskThread 2 : " + task2.getSum());
		System.out.println("Getting Result From TaskThread 3 : " + task3.getSum());
		System.out.println("< " + Thread.currentThread().getName() + ">  Main Thread Completed");
	}

}

class ValueReturningTaskA implements Runnable {
	private int num1;
	private int num2;
	private int sum;
	private long sleepTime;
	private int count;
	private int instanceNumber;
	private String taskId;
	private boolean isTaskDone = false;

	public ValueReturningTaskA(int num1, int num2, long sleepTime) {
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.sleepTime = sleepTime;
		this.instanceNumber = ++count;
		this.taskId = "ValueRetuningTask" + instanceNumber;
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
		isTaskDone = true;
		synchronized (this) {
			this.notify();
		}
		System.out.println("#### Thread--> < " + currentThreadName + "> has Completed The Task <" + taskId + ">");

	}

	public int getSum() {
		if (!isTaskDone) {
			synchronized (this) {
				try {
					System.out.println("Thread < "+ Thread.currentThread().getName()+"> is waiting ");
					this.wait();
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return sum;
	}
}
