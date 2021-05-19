package com.threadCreation;

import java.util.concurrent.TimeUnit;

public class FirstThreadCreationTechnique {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" Before Custom Thread Creation ");
		new ThreadExample();
		System.out.println(" After Custom Thread Creation ");
	}

}

class ThreadExample extends Thread {
	@Override
	public void run() {

		for (int i = 0; i < 10; i++) {
			System.out.println(" First Thread creation Technique--" + i);
		}

		try {
			TimeUnit.MICROSECONDS.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ThreadExample() {
		this.start();
	}
}
