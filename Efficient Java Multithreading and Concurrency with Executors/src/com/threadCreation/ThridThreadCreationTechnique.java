package com.threadCreation;

public class ThridThreadCreationTechnique {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ThreadExample();
	}

}
//Task Definition
class ThridThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 10; i > 0; i--) {
			System.out.println(" Second Thread Created " + i);
		}
	}

	public ThridThread() {
		new Thread(this).start();
	}
}
