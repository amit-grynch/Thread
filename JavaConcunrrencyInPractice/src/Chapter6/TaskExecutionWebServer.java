package Chapter6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author amitg
 *  Handling  Task Submission and Execution By Thread Pool Using Executor
 */
public class TaskExecutionWebServer {
   private static final int NTHREAD=100;
   private static final Executor exec=Executors.newFixedThreadPool(NTHREAD);
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Establish the COnnection with Port 80
		ServerSocket socket=new ServerSocket(80);
		while(true) {
			final Socket connection=socket.accept();
			Runnable task=new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					handleRequest(connection);
					
				}
				
			};
			//Below code is for Task Execution
			exec.execute(task);
		}
         
	}
  public static void handleRequest(Socket Connection) {
	   //Code for submitting request task
  }
}
