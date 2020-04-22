package Chapter7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author amitg
 *  Demonstrate About How Thread/Task can be Cancelled up on Cancellation Request
 */
public class PrimeGeneratorMain {
  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 PrimeGenerator primeGenerator=new PrimeGenerator();
		 ExecutorService executor =Executors.newFixedThreadPool(2);
		 executor.submit(primeGenerator);
		 try {
			 Thread.sleep(1000);
		 } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			primeGenerator.cancel();
		}
        System.out.println(primeGenerator.get());   
	}

}

class PrimeGenerator implements Runnable{
   private final List<BigInteger> primeNumberList=new ArrayList<BigInteger>();
   private volatile boolean cancellationRequest;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		BigInteger p=BigInteger.ONE;
		while(!cancellationRequest) {
			p=p.nextProbablePrime();
			synchronized (p) {
				primeNumberList.add(p);
			}
		}
		 if(cancellationRequest) {
			 System.out.println("Cancellation Request Came");
		 }
	}
	
	public void cancel() {
		cancellationRequest=true;
	}
	public synchronized List<BigInteger> get(){
		 return new ArrayList<BigInteger>(primeNumberList);
	}
}
