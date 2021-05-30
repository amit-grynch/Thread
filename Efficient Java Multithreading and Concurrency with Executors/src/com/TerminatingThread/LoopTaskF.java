package com.TerminatingThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LoopTaskF implements Runnable {

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	private final int DATASET_SIZE = 100000;

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + "] <" + taskId + "> STARTING #####");

		for (int i = 1;; i++) {
			System.out.println("[" + currentThreadName + "] <" + taskId + "> TICK TICK - " + i);

			doComplexCalculation();

			if (Thread.interrupted()) {
				System.out.println("[" + currentThreadName + "] <" + taskId + "> Interrupted. Cancelling ...");
				break;
			}
		}

		System.out.println("[" + currentThreadName + "] <" + taskId + "> Retrieving 'INTERRUPTED' status again : "
				+ Thread.interrupted());

		System.out.println("***** [" + currentThreadName + "] <" + taskId + "> COMPLETED *****");
	}

	private void doComplexCalculation() {
		for (int i = 0; i < 2; i++) {
			Collections.sort(generateDataSet());
		}
	}

	private List<Integer> generateDataSet() {
		List<Integer> intList = new ArrayList<>();
		Random randomGenerator = new Random();

		for (int i = 0; i < DATASET_SIZE; i++) {
			intList.add(randomGenerator.nextInt(DATASET_SIZE));
		}

		return intList;
	}

	public LoopTaskF() {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskF" + instanceNumber;
	}
}