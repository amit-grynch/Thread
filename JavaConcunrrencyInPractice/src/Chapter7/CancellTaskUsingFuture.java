package Chapter7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CancellTaskUsingFuture {
	 private final static ExecutorService executor=Executors.newFixedThreadPool(1);
	public void timedRun(Runnable r, long timeout,TimeUnit unit) throws InterruptedException {
		  Future<?> task=executor.submit(r);
		    try {
				task.get(timeout,unit);
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				task.cancel(true);			}
	}

}
