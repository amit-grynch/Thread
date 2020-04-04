package Chapter4;

/**
 * @author amitg
 *  Benefit of private lock is that any client code can not acquire.
 */
public class JavaMonitirPattern {
     private final Object myLock=new Object();
     Widget widget;
     void accessMethod() {
    	 synchronized(myLock) {
    		 // code for  controlling access to Widget
    	 }
     }
}

class Widget {
	
}
