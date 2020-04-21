package Chapter6;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;

/**
 * @author amitg
 * Using CompletionService to render page elements as they become available
 */
public abstract class RenderImage {
	private final ExecutorService executorService;
	public RenderImage(ExecutorService executorService ) {
		this.executorService=executorService;
	}
	void renderImage(CharSequence s) {
		List<ImageInfo> imageList=scanForImageInfo(s);
		CompletionService<ImageData> executorCompletionService=new ExecutorCompletionService<ImageData>(executorService);
		  for(ImageInfo info:imageList) {
			  executorCompletionService.submit(new Callable<ImageData>() {

				@Override
				public ImageData call() throws Exception {
					// TODO Auto-generated method stub
					return info.downloadImage();
				}
				  
			  });
		  }
		      for(int index=0;index<imageList.size();index++) {
		    	  try {
					ImageData imageData=executorCompletionService.take().get();
					renderImage(imageData);
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }

}
interface ImageData {
}

interface ImageInfo {
    ImageData downloadImage();
}

abstract void renderText(CharSequence s);

abstract List<ImageInfo> scanForImageInfo(CharSequence s);

abstract void renderImage(ImageData i);

}
