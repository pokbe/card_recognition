package convert; 
import java.awt.image.*; 
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;

import javax.imageio.ImageIO;


public class convert{
	public void conimg() throws IOException{
		File file = new File("graph");
		String[] list = file.list();
		for(int k =0;k<list.length;k++) {
			File files = new File("graph"+"/"+list[k]);
			File o = new File("test"+"/"+list[k]+".txt");
			BufferedImage bi=ImageIO.read(files);
			int h=bi.getHeight();
			int w=bi.getWidth();
	        int[][] gray=new int[h][w];  
	        for (int x = 0; x < h; x++) {  
	            for (int y = 0; y < w; y++) {  
	                gray[x][y]=getGray(bi.getRGB(x, y));  
	            }  
	        }
	    	FileWriter out = new FileWriter(o);  
	    	  for(int i=0;i<h;i++){
	    		   for(int j=0;j<w;j++){
	    			if( gray[j][i]>180){  //ãÐÖµ
	    		    out.write('1');
	    			}
	    		    else{
	    		    	out.write('0');
	    		    }
	    		    
	    			
	    		   }
	    		   out.write("\r\n");
	    		  }
	    		  out.close();
		}

	}
        public static int getGray(int rgb){  
            String str=Integer.toHexString(rgb);  
            int r=Integer.parseInt(str.substring(2,4),16);  
            int g=Integer.parseInt(str.substring(4,6),16);  
            int b=Integer.parseInt(str.substring(6,8),16);  
            Color c=new Color(rgb);  
            r=c.getRed();  
            g=c.getGreen();  
            b=c.getBlue();  
            int top=(r+g+b)/3;  
            return (int)(top);  
        }
        public static void main(String[] args) throws IOException {     	
        	convert demo = new convert();  
            demo.conimg();
            System.out.println("successful!");
            } 
		
    
}