package Chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author amitg
 *  Using CompletionService to computation of Multiplication of integer and There are multiple tasks
 */
public class CompletionServiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CallableTask callableTask1 = new CallableTask(10, 20, "Task1", 20001);
		CallableTask callableTask2 = new CallableTask(30, 40, "Task2", 40001);
		CallableTask callableTask3 = new CallableTask(50, 60, "Task3", 60001);
		CallableTask callableTask4 = new CallableTask(70, 80, "Task4", 80001);
		ExecutorService executor = Executors.newFixedThreadPool(4);
		CompletionService<Integer> executorCompletionService = new ExecutorCompletionService<Integer>(executor);
		List<Future<Integer>> futuresList = new ArrayList<Future<Integer>>();
		futuresList.add(executorCompletionService.submit(callableTask1));
		futuresList.add(executorCompletionService.submit(callableTask2));
		futuresList.add(executorCompletionService.submit(callableTask3));
		futuresList.add(executorCompletionService.submit(callableTask4));
		for (int index = 0; index < futuresList.size(); index++) {
			try {
				System.out.println("-----Getting The Result -----");
				Integer result = executorCompletionService.take().get();
				System.out.println("Result :" + index + " " + result);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class CallableTask implements Callable<Integer> {
	int a, b;
	String taskName;
	long sleepTime;

	public CallableTask(int a, int b, String taskName, long sleepTime) {
		super();
		this.a = a;
		this.b = b;
		this.taskName = taskName;
		this.sleepTime = sleepTime;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Starting The Task :" + taskName);
		int result = a * b;
		Thread.sleep(1000);
		System.out.println("Ending The Task :" + taskName);
		return result;
	}

}