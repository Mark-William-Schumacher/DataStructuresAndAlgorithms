import java.util.Arrays;


public class MaximumSubArray {
	// Here we want to find the maximum sum in a sliding range
	// of an array . We can compute this in O(n)
	//Our subIndex is where our trailing sum is counted from
	// if trailing Sum goes negative we can start a new subIndex
	// SEE Page 125 of Elements of Programming Interviews
	
	public static int[] findMaximumSubArray(int A[]){
		int maxSum = 0,trailingSum = 0,subIndex = 0;
		int[] range= new int[] {0,0};
		for (int i = 0; i < A.length; i++){
			trailingSum += A[i];
			if (trailingSum <= 0){ // Our index is very -
				trailingSum = 0;
				subIndex = i+1;
			}
			if (trailingSum > maxSum){
				range= new int[] {subIndex,i};
				maxSum=trailingSum;
			}
		}
		return range;
	}
	
	public static void main (String args[]){
		int[] A= new int[] {10,20,-30,70,-100,3000,-2500,8000,-8400,8401};
		int[] range = findMaximumSubArray(A);
	  System.out.println(Arrays.toString(range));
	}
	
	
}
