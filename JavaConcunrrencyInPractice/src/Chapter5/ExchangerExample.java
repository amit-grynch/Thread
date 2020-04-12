package Chapter5;

import java.util.concurrent.Exchanger;

public class ExchangerExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Exchanger<Object> exchanger = new Exchanger<Object>();
		ExchangeRunnable exchange1 = new ExchangeRunnable(exchanger, "A");
		ExchangeRunnable exchange2 = new ExchangeRunnable(exchanger, "B");
		new Thread(exchange1).start();
		new Thread(exchange2).start();
	}

}

class ExchangeRunnable implements Runnable {
	Exchanger<Object> exchanger;
	Object object;

	public ExchangeRunnable(Exchanger<Object> exchanger, Object object) {
		this.exchanger = exchanger;
		this.object = object;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Object previous = this.object;
		try {
			this.object = this.exchanger.exchange(this.object);
			System.out.println("Thread " + Thread.currentThread().getName() + " is exchanging Object " + previous
					+ " for " + this.object);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
