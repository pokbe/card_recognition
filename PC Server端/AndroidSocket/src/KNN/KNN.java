package KNN;

import java.util.ArrayList;

public class KNN {

	public static int classify(ArrayList<Integer> inX, ArrayList<ArrayList<Integer>> dataSet, ArrayList<Integer> labels, int k)
	{
		int dataSetSize = dataSet.size();
		int labelsum = 0;
		ArrayList<Integer> distances = new ArrayList<Integer>();
		for (int i = 0; i < dataSetSize; i++){
			int sum = 0;
			for (int j = 0; j < inX.size(); j++){
				int tmp = inX.get(j) - dataSet.get(i).get(j);
				tmp *= tmp;
				sum += tmp;
			}
			sum=(int)Math.sqrt((double)sum);
			distances.add(sum);
		}
		ArrayList<Integer> sortedDistIndix;
		sortedDistIndix = insertSort(distances);
		for (int i = 0; i < k; i++){
			labelsum += labels.get(sortedDistIndix.get(i));
		}
		return (int) (labelsum / k + 0.5);
	}
	
	
	static ArrayList<Integer> insertSort(ArrayList<Integer> nums)
	{
		ArrayList<Integer> sortedIndx = new ArrayList<Integer>() ;
		for (int i = 0; i<nums.size(); i++){
			sortedIndx.add(i);
		}
		for (int j = 1; j < nums.size(); j++){
			int key = nums.get(j);
			int indx = sortedIndx.get(j);
			int i = j - 1;
			while (i>=0&&nums.get(i)>key)
			{
				nums.set((i+1), nums.get(i));
				sortedIndx.set((i+1), sortedIndx.get(i));
				i--;
			}
			nums.set((i + 1) , key);
			sortedIndx.set((i + 1),indx);
		}
		return sortedIndx;
	}
	
	
}
