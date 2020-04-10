package Chapter5;

public class IntrruptMethodTestWithRestore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" Main Thread Execution Start");
		MyThread myThread=new MyThread();
		Thread thread=new Thread(myThread);
		thread.start();
		thread.interrupt();
		System.out.println(" Main Thread Execution Ends");
	}

}

class MyThread implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<5;i++) {
			System.out.println("Printing Value of i ="+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(" Throwing the Exception");
			    
			}
	}
	}
}
