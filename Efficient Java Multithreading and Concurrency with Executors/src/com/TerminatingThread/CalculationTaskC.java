package com.TerminatingThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class CalculationTaskC implements Callable<Long> {
	
	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	
	private final int DATASET_SIZE = 100000;
	
	private boolean isThreadInterrupted = false;
	
	@Override
	public Long call() throws Exception {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("##### [" + currentThreadName + "] <" + taskId + "> STARTING #####");
		
		long totalTimeTakenInMillis = 0;
		
		for (int i=0; i < 1000; i++) {
			System.out.println("[" + currentThreadName + "] <" + taskId + "> CURRENT RUNNING AVERAGE = " + 
					(i == 0 ? 0 : totalTimeTakenInMillis / (2 * i)));
			
			long timeTakenInMillis = doComplexCalculation();
			totalTimeTakenInMillis += timeTakenInMillis;
			
			if (Thread.interrupted()) {
				System.out.println("[" + currentThreadName + "] <" + taskId + "> Interrupted. Cancelling ...");
				isThreadInterrupted = true;
				break;
			}
		}
		
		System.out.println("[" + currentThreadName + "] <" + taskId + "> Retrieving 'INTERRUPTED' status again : " +
				Thread.interrupted());
		
		System.out.println("***** [" + currentThreadName + "] <" + taskId +	"> COMPLETED *****");
		
		return isThreadInterrupted ? -1 : totalTimeTakenInMillis / (2 * 1000);
	}
	
	private long doComplexCalculation() {
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < 2; i++) {
			Collections.sort(generateDataSet());
		}
		
		return System.currentTimeMillis() - startTime;
	}
	
	private List<Integer> generateDataSet() {
		List<Integer> intList = new ArrayList<>();
		Random randomGenerator = new Random();
		
		for (int i = 0; i < DATASET_SIZE; i++) {
			intList.add(randomGenerator.nextInt(DATASET_SIZE));
		}
		
		return intList;
	}
	
	public CalculationTaskC() {
		this.instanceNumber = ++count;
		this.taskId = "CalculationTaskC" + instanceNumber;
	}
}