package Chapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PreLoader {

	private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {

		@Override
		public ProductInfo call() throws Exception {
			// TODO Auto-generated method stub
			return loadProductInfo();
		}
	});

	private final Thread thread = new Thread(future);

	public void start() {
		thread.start();
	}

	public ProductInfo get() throws Exception {
		try {
			return future.get();
		} catch (Exception e) {
			throw  new Exception();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	ProductInfo loadProductInfo() throws Exception {
		return get();
	}

}

interface ProductInfo {

}

class DataLoadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}