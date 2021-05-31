package com.TerminatingThread;

import java.util.concurrent.TimeUnit;

public class LoopTaskA implements Runnable {
	
	private static int count = 0;
	private int id;
	
	@Override
	public void run() {
		System.out.println("##### <TASK-" + id + "> STARTING #####");
		
		for (int i=10; i>0; i--) {
			System.out.println("<" + id + ">TICK TICK - " + i);
			
			try {
				TimeUnit.MILLISECONDS.sleep((long)(Math.random() * 1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
		
		System.out.println("***** <TASK-" + id + "> COMPLETED *****");
	}
	
	public LoopTaskA() {
		this.id = ++count;
	}
}