package KNN;

import java.io.File;
import java.util.ArrayList;



public class KnnTest {
	
	public KnnTest(){
		
	}

	public int useKnn(String trainpath , String testFileName){
		ArrayList<File> files = new ArrayList<File>();
		GetAllFormatFiles.getAllFormatFiles( trainpath , files);
		ArrayList<ArrayList<Integer>> traingMat = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> labels = new ArrayList<Integer>();
		for (int i = 0; i < files.size(); i++){
			String filename = files.get(i).getName();
			String chap = filename.substring(0,1);
			int label = Integer.parseInt(chap);
			labels.add(label);
			traingMat.add(GetAllFormatFiles.ImagetoVector(files.get(i)));
		}
		File testFile = new File(testFileName);
		ArrayList<Integer> inX = GetAllFormatFiles.ImagetoVector(testFile);

		int result = KNN.classify(inX, traingMat, labels,2 );
		
		return result;
	}

}
