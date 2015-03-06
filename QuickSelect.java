import java.util.Arrays;
import java.util.Random;

public class QuickSelect {
  // divide and conquer
	public static int findKthLargest(int[] A, int k){
		return recursiveKthLargest(A, A.length-1-k, 0, A.length-1);
	}
	
	// if the element 
  public static int findKthLargest(int[] A, int k, int lower, int upper){
  	int currentIndex= partition(A, lower, upper);
  	while (currentIndex != k){
  		if (k > currentIndex){
  			lower= currentIndex+1;
  			currentIndex= partition(A, lower,upper);
  		}
  		else{
  			upper= currentIndex-1;
  			currentIndex= partition(A,lower, upper);
  		}
  	}
  	return currentIndex;
  }
  public static void swap(int[] A , int i1, int i2){
  	int tmp= A[i1];
  	A[i1]=A[i2];
  	A[i2]=tmp;
  }
  
  public static int partition(int[] A, int lower,int upper){
  	if (lower==upper){
  		return lower;
  	}
  	Random r = new Random();
  	System.out.println(lower);
  	System.out.println(upper);
  	int pivotIndex = r.nextInt(upper-lower) + lower;
  	swap(A,pivotIndex, upper);
  	pivotIndex=upper;
  	while (lower < pivotIndex){
  		if (A[lower] > A[pivotIndex]){
  			swap(A, pivotIndex , lower);
  			swap(A, pivotIndex-1, lower);
  			pivotIndex--;
  		}
  		else{
  			lower++;
  		}
  	}
  	return pivotIndex;
  }
  
  public static int recursiveKthLargest(int[] A,int k
  																					,int lower, int upper){
  	if (lower==upper){
  		return lower;
  	}
  	int knownIndex= partition(A, lower, upper);
  	if (knownIndex == k){
  		return k;
  	}
  	if (k > knownIndex){
  		return recursiveKthLargest(A,k, knownIndex+1, upper);
  	}else{
  		return recursiveKthLargest(A, k, lower, knownIndex-1);
  	}
  }
  
  
  

  public static void main(String args[]){
  	int[] A = new int[] {1,2,3,4,5,6,7,8,9,10}; // 5
  	int medA = 0;
  	medA=findKthLargest(A,9);
  	System.out.println("A: "+A[medA]);
  	int[] B = new int[] {2,58,51,77,42,87,20,90,1};
  	int medB = 0;
  	medB=findKthLargest(B,A.length/2);
    System.out.println("B: "+A[medB]);
  }
}

