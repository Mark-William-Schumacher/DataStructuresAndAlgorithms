import java.util.Arrays;


public class Sorting {
	
	public static void run(){
		// Test Quick sort
		int[] a={1,2,45,7,20,99,123,99,10,9,8,7};
		int[] b={};
		int[] c={1};
		int[] d={99,1};
		System.out.println("Before: "+Arrays.toString(a));
		quickSort(a);
		System.out.println("After: "+ Arrays.toString(a));
		
		System.out.println("Before: "+Arrays.toString(b));
		quickSort(b);
		System.out.println("After: "+ Arrays.toString(b));
		
		System.out.println("Before: "+Arrays.toString(c));
		quickSort(c);
		System.out.println("After: "+ Arrays.toString(c));
		
		System.out.println("Before: "+Arrays.toString(d));
		quickSort(d);
		System.out.println("After: "+ Arrays.toString(d));
		
		// Test Merge Sort
		int[] e={1,2,45,7,20,99,123,99,10,9,8,7};
		int[] f={};
		int[] g={1};
		int[] h={99,1};
		Object x;
		
		System.out.println("Before: "+Arrays.toString(e));
		mergeSort(e);
		System.out.println("After: "+ Arrays.toString(e));
		
		System.out.println("Before: "+Arrays.toString(f));
		mergeSort(f);
		System.out.println("After: "+ Arrays.toString(f));
		
		System.out.println("Before: "+Arrays.toString(g));
		mergeSort(g);
		System.out.println("After: "+ Arrays.toString(g));
		
		System.out.println("Before: "+Arrays.toString(h));
		mergeSort(h);
		System.out.println("After: "+ Arrays.toString(h));
	}
	public void testQuickSort(){
	}

	public static int[] quickSort(int[] inputArray){
		// This will input 
		inPlaceSort(0,inputArray.length-1,inputArray);
		return inputArray;
	}
	private static void inPlaceSort(int lowerBound, int upperBound, int[] inputArray){
		if (lowerBound>=upperBound) return;
		int pivotIndex=choosePivot(inputArray,lowerBound, upperBound);
		int leftIndex=lowerBound;
		while (leftIndex!=pivotIndex){
			if (inputArray[leftIndex]<=inputArray[pivotIndex])
				leftIndex++;
			else{
				swap(leftIndex, pivotIndex-1,inputArray);
				swap(pivotIndex-1,pivotIndex,inputArray);
				pivotIndex--;
			}
		}
		inPlaceSort(lowerBound,pivotIndex-1,inputArray);
		inPlaceSort(pivotIndex+1,upperBound,inputArray);
	}
	private static int choosePivot(int[] inputArray, int lb, int ub){
		// We need to put our pivot at the end of the Array 
		int sizeOfArray= ub-lb;
		int pivotIndex= lb+(int)Math.floor(sizeOfArray/2);
		swap(pivotIndex,ub,inputArray);
		return ub; // Spot of our pivot
	}
	private static void swap(int i1, int i2, int[] inputArray){
		int temp=inputArray[i1];
		inputArray[i1]=inputArray[i2];
		inputArray[i2]=temp;
	}
	public static void mergeSort(int[] array){
		int[] tempArray=new int[array.length];
		mergeSort(0,array.length-1,array,tempArray);
	}
	
	private static void mergeSort(int lb, int ub, int[] array, int[] tempArray){
		if (lb>=ub)
			return;
		// Divide
		int middle= lb + ((ub-lb)/2);
		mergeSort(lb,middle,array,tempArray);
		mergeSort(middle+1,ub,array,tempArray);
		
		// Conquer
		merge(lb, middle+1, ub, array, tempArray);
	}
	
	private static void merge(int lb, int middle, int ub, int[] array, int[] tempArray){
		for (int i=lb; i<=ub; i++){
			tempArray[i]=array[i];
		}
		
		int cursorA=lb, cursorB=middle;
		while (cursorA<middle){
			if (cursorB>ub || tempArray[cursorA]<tempArray[cursorB]){
				array[lb]=tempArray[cursorA];
				cursorA++;
			}
			else{
				array[lb]=tempArray[cursorB];
				cursorB++;
			}
			lb++;
		}
	}
}
