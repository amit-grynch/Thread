package com.threadCreation;

public class SecondThreadCreationTechnique {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SceondThread().start();
	}

}

//Thread Cum Task Definition
class SceondThread extends Thread {
	int count = 0;
	int id = 0;

	public void run() {

		for (int i = 10; i > 0; i--) {
			System.out.println(" Second Thread Created " + i);
		}
	}

	public SceondThread() {
		// TODO Auto-generated constructor stub
		id = ++count;
	}
}
