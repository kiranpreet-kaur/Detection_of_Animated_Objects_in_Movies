import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

public class ObjectDetection {
	
	private boolean[][] myPixels;

	/**
     * Constructor takes in the file that contains the image
     * and converts it to a BufferedImage. This image is stored
     * in an instance variable in the form of an integer array.
     *
     *       
     * 
     * @param file, file that contains the image
     */
	
	public ObjectDetection(File file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (Exception e) {
            System.out.println("Incorrect File ");
            return;
        }
        
            myPixels = imageToPixels(img);
               
    }
    
	
	 public boolean[][] getPixels() {
	        return myPixels;
	    }
	 
	 /**
	     * Converts a BufferedImage to a two-dimensional array of image data.
	     *
	     * @param image the source BufferedImage
	     *
	     * @return  a two-dimensional array of image data representing the
	     *          source image
	     *
	     * 
	     *
	     * This returns a true bitmap where each element in the grid is either a 0 or a
	     * 1. A 1 means the pixel is white and a 0 means the pixel is black.
	     * 
	     * comparing each pixels in order to detect the object in an image
	     * 
	     * If the incoming image doesn't have any pixels in it then this method returns
	     * null;
	     * 
	     * 
	     * 
	     * @param image
	     * @return
	     */
	 
	 
	    public static boolean[][] imageToPixels(BufferedImage image) 
	    {
	    	if (image == null || image.getWidth() == 0 || image.getHeight() == 0)
				return null;
	              

	    	long bgColor = image.getRGB(0,0);
	    	boolean[][] result = new boolean[image.getWidth()][image.getHeight()];
	    	for (int x = 0; x < image.getWidth(); x++) {
	    	  for (int y = 0; y < image.getHeight(); y++) {
	    		
	    		 result[x][y] = image.getRGB(x,y) != bgColor;
	    	  }
	    	}
	    	return result;
	    }
	   


		/**
	     * Converts a two-dimensional array of image data to a BufferedImage.
	     *
	     *        
	     *
	     * @param pixels    the source two-dimensional array of image data
	     *
	     * @return  a BufferedImage representing the source image.
	     *
	     * @throws  IllegalArgumentException if the source image is
	     *          ill-defined.
	     */

	    public static BufferedImage pixelsToImage(int[][] pixels) throws IllegalArgumentException {

	        if (pixels == null) {
	            throw new IllegalArgumentException();
	        }
	        
	        int width = pixels[0].length;
	        int height = pixels.length;
	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        for (int row = 0; row < height; row++) {
		        for (int col = 0; col< width; col++) {
		        	image.setRGB(col, row, (pixels[row][col] == 1) ? 0xffffffff : 0);
		      }
	        }
	        return image;
	    }
	 
	    public static void main(String[] args) throws IOException {
	    	
	    	ImageMatrix im = new ImageMatrix(new File("NEWOUTPUT/out81.jpg"));
	    	 
	    	BufferedImage originalImage = pixelsToImage(im.getPixels());
	          File imageFile = new File("ObjectDetected/1demo.jpg");
				ImageIO.write(originalImage, "jpg", imageFile);
	    	
	    	
	    }

	

}
