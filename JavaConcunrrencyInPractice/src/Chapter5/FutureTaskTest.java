package Chapter5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomRunnable runnable=new CustomRunnable(1000);
		FutureTask<String> futureTask=new FutureTask<String>(runnable,"Task Completed");
		ExecutorService executor=Executors.newFixedThreadPool(1);
		executor.submit(futureTask);
		while(true) {
			if(futureTask.isDone()) {
				System.out.println(" FutureTask Complete"); 
                executor.shutdown(); 
                return;
			}
			try {
				String result=futureTask.get();
				System.out.println(" Task Completed "+ result);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class CustomRunnable implements Runnable{
	private final long waitTime; 
	  
    public CustomRunnable(int timeInMillis) 
    { 
        this.waitTime = timeInMillis; 
    } 

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(waitTime);
			System.out.println("Current Thread Detail "+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
