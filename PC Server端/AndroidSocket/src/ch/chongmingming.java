package ch;

import java.io.File;

public class chongmingming {
	static String trainpath = "E:\\jiqixuexi\\";
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			String filefolder=trainpath+i;
			//System.out.println(filefolder);
			File file = new File(filefolder);
			File[] fileArray = file.listFiles();
			int m=1;
			for (File f : fileArray) {
				String filename=i+"_"+m+".jpg";
				f.renameTo(new File(filename));
				m++;
			}
		}
	}
}
