package Chapter5;

public class InterruptMethodTestWithPropogation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" Main Thread Execution Start");
		Myclass thread=new Myclass();
		thread.start();
		try {
		thread.interrupt();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" Exception Handled in Main class");
		}
		System.out.println(" Main Thread Execution Ends");

	}

}

class Myclass extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
		for(int i=0;i<5;i++) {
			System.out.println("Printing Value of i ="+i);
		
				Thread.sleep(1000);
			
	}} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		throw new RuntimeException(" Exception Hanled in MyClass");
	}
}
}
