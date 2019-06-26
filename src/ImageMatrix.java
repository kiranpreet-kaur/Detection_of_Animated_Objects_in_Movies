import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;



public class ImageMatrix 
{
	private int[][] myPixels;

	/**
     * Constructor takes in the file that contains the image
     * and converts it to a BufferedImage. This image is stored
     * in an instance variable in the form of an integer array.
     *
     *       
     * 
     * @param file, file that contains the image
     */
	
	public ImageMatrix(File file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (Exception e) {
            System.out.println("Incorrect File ");
            return;
        }
        //if (img.getType() == BufferedImage.TYPE_INT_RGB) {
            myPixels = imageToPixels(img);
        /*} else {
            BufferedImage tmpImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            tmpImage.createGraphics().drawImage(img, 0, 0, null);
            myPixels = imageToPixels(tmpImage);
        } */       
    }
    
	
	 public int[][] getPixels() {
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
	     * This returns a true bitmap where each element in the grid is either a 0 or a
	     * 1. A 1 means the pixel is white and a 0 means the pixel is black.
	     * 
	     * If the incoming image doesn't have any pixels in it then this method returns
	     * null;
	     * 
	     * @param image
	     * @return
	     */
	 
	 
	    public static int[][] imageToPixels(BufferedImage image) 
	    {
	    	if (image == null || image.getWidth() == 0 || image.getHeight() == 0)
				return null;

			// This returns bytes of data starting from the top left of the bitmap
			// image and goes down.
			// Top to bottom. Left to right.
			final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

			
			
			final int width = image.getWidth();
			final int height = image.getHeight();

			int[][] result = new int[height][width];
			for (int y = 0; y < height; y++)
			{
//				image.getRGB(0, y, width, 1, result[y]);
				for (int x = 0; x < width; x++) {
					result[y][x] = ((image.getRGB(x, y) & 0xff) > 0x1f) ? 1 : 0; 
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
	     * @return  a BufferedImage representing the source image in matrix.
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
	    	
	    	ImageMatrix im = new ImageMatrix(new File("NEWINPUT/test18.jpg"));
	    	 
	    	BufferedImage originalImage = pixelsToImage(im.getPixels());
	          File imageFile = new File("NEWOUTPUT/19new.jpg");
				ImageIO.write(originalImage, "jpg", imageFile);
	    	
	    	
	    }

}