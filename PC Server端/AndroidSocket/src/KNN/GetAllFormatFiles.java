package KNN;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GetAllFormatFiles {
	public static void getAllFormatFiles(String path, ArrayList<File> files) {

		File file = new File(path);

		File[] fileArray = file.listFiles();
		for (File f : fileArray) {
			files.add(f);
		}
	}
	public static ArrayList<Integer> ImagetoVector(File f){
		ArrayList<Integer> vect = new ArrayList<Integer>();
		String line = "";
		try {
			BufferedReader bf= new BufferedReader(new FileReader(f));
			line = bf.readLine();
			while(line != null){
				char[] chars = line.toCharArray();
				for(char c : chars){
					if(c == '1'){
						vect.add(1);
					}
					if(c == '0'){
						vect.add(0);
					}
				}
				line = bf.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vect;
	}
}
