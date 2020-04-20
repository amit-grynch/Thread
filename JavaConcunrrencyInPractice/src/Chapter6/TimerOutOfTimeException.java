package Chapter6;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author amitg
 *  Class illustrating confusing Timer behavior
 */
public class TimerOutOfTimeException {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 Timer timer = new Timer();
	        timer.schedule(new ThrowTask(), 1);
	        Thread.sleep(1);
	        timer.schedule(new ThrowTask(), 1);
	        Thread.sleep(5);
	}
	static class ThrowTask extends TimerTask {
        public void run() {
            throw new RuntimeException();
        }
    }

}
