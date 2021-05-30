package com.threadIsAlive;

import java.util.concurrent.ThreadFactory;

public class NamedThreadsFactory implements ThreadFactory {

	private static int count = 0;
	private static String NAME = "PoolWorker-";
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, NAME + ++count);
		return t;
	}

}
