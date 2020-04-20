package Chapter6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author amitg Executor LifeCycle Methods
 *
 */
public class ExecutorServiceLifeCycle {
	private final ExecutorService executor = Executors.newCachedThreadPool();

	public void start() throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while (!executor.isShutdown()) {
			try {
			final Socket connection = socket.accept();
			executor.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					handleRequest(connection);
				}

			});
		}catch (RejectedExecutionException e) {
			// TODO: handle exception
			if(!executor.isShutdown()) {
				System.out.println(" Task has Rejected");
			}
		}
		}
	}
   public void handleRequest(Socket connection) {
	   Request req=new Request();
	   if(shutDownRequest()) {
		   stop();
	   }else {
		   dispatchRequest(req);
	   }
   }
   
   public void dispatchRequest(Request req) {
	   
   }
   
   public void stop() {
	   executor.shutdown();
   }
   
   public boolean shutDownRequest() {
	   return true;
   }
}

class Request{
	
}
