package Chapter6;

import java.util.concurrent.Executor;

/**
 * @author amitg
 *  New Thread is Handling  New Request Implementing Executor
 */
public class TaskPerThreadWebServer implements Executor{

	@Override
	public void execute(Runnable task) {
		// Creating New Thread and Executing Task
		new Thread(task).start();
		//Handling Task Synchronously
		 task.run();
		
	};

}
