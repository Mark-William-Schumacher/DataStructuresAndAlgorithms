import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
 *  Reversal Distance Problem
 *  
 *  Given 2 permuations, find a shortest genes of reversals that transforms one premuation to another.
 *  Input: Permuation A and B
 *  Output permuation to another.
 *  gtcaacagggt    INTO gtacggaacgt
 *  
 *  
 *  Computing the reversal distance is an optimization problem. 
 *  Suppose we have an optimization problem with input instance PIE. We can express the optimal value
 *  as OPT(A). We can denote the approximate solution that our algorithm callit algo A , produces an instance
 *  PIE as A(PIE) . If our optimization problem is to minimize then we say that our algorithm has approximation ration C, if for all instances PIE of size n 
 *  A (PIE) 
 *  -------   <= C
 *  OPT(PIE)
 *  
 *  Technique is to obtain a lower bound for the quantity OPT(PIE)  for our first greedy algorithm all we can say is that :
 *  A(PIE)
 *  ------      <=  (n-1)/2     E   O(n)  
 *  OPT(PIE)   
 *  
 */
public class ReversalDistanceProblem {
	
	public static void main (String args[]){
		// This is an example where our greedy Algorythm does okay.
		char [] A = "gtcaacagggt".toCharArray();
		char [] B = "gtacggaacgt".toCharArray();
		String printOut =greedyFlipping(A,B);
		System.out.println(printOut);
		
		// This is an example where our greedy Algorythm fails hard
		A = "912345678".toCharArray();
		B = "123456789".toCharArray();
		printOut = greedyFlipping(A,B);
		System.out.println(printOut);
		
		A = "3152749608".toCharArray();
		B = "1234567890".toCharArray();
		FlipObject min = findMinimumAmountOfFlips(A,B);
		min.Printout(B);
		
		A = "712543698".toCharArray();
		B = "123456789".toCharArray();
		min = findMinimumAmountOfFlips(A,B);
		min.Printout(B);
	}
	
	public static class FlipObject{
		String flips="";
		String current="";
		public FlipObject(String flips, String current){
			this.flips=flips;
			this.current=current;
		}
	public void Printout(char[] A){
		String[] s=flips.split(",");
		System.out.println("Total Number of Flips: "+String.valueOf(s.length-1));
		System.out.print("Original:  "+ String.valueOf(A)+"\n");
		for (int i=0 ; i< s.length-1; i++){
			String[] tmp=s[i].split("-");
			reverse(A, Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
			System.out.print("Flip: "+s[i]+", "+ String.valueOf(A)+ "\n");
		}
		System.out.print("\n\n");
			}
		
	}
	
	public static FlipObject findMinimumAmountOfFlips(char[] A, char[] B){
		int numberOfFlips=0;
		Queue<FlipObject> q = new LinkedList<FlipObject>();
		HashSet<String> visited = new HashSet<String>();
		q.add(new FlipObject("",String.valueOf(A)));
		while (!q.isEmpty()){
			FlipObject cur = q.remove();
			char[] X= cur.current.toCharArray();
			int outOfPos = outOfPositionChars(X,B);
			if (outOfPos==0){
				return cur;
			}
			// Adding this little statement made it actually run.
			if (visited.contains(cur.current) || (A.length-numberOfFlips)<outOfPos){
				continue;
			}
			
			
			for (int i = 0; i<A.length; i++){
				for (int j = i+1 ; j<A.length; j++){
					visited.add(cur.current);
					char[] tmp = cur.current.toCharArray();
					reverse(tmp, i, j);
					String newFlips = cur.flips+String.valueOf(i)+"-"+String.valueOf(j)+",";
					FlipObject x = new FlipObject(newFlips,String.valueOf(tmp));
					q.add(x);
				}
			}
			numberOfFlips++;
		}
		return null;
	}
	
	public static int outOfPositionChars(char[] A, char[] B){
		int sum=0;
		for (int i=0 ; i < A.length; i++){
			if (A[i]!=B[i]) sum++;
		}
		return sum;
	}
	
	
	public static String greedyFlipping(char[] a, char[] b){
		ArrayList<Integer[]> flipSequence = new ArrayList<>();
		String printOut= ("Original:  "+String.valueOf(a)+"\n");
		for (int i = 0; i < b.length; i++){
			if (a[i] != b[i]){
				int nextIndex=findNextIndex(b[i],a,i);
				reverse(a, i, nextIndex);
				printOut=printOut.concat("Flip: " + String.valueOf(i)+"-"+String.valueOf(nextIndex) +", "+String.valueOf(a)+"\n");
				flipSequence.add( new Integer[] {i, nextIndex } );
			}
		}
		return printOut;
	}
	public static int findNextIndex(char toFind, char[] C, int lb){
		for (int i = lb ; i<C.length ; i++){
			if (C[i]== toFind){
				return i;
			}
		}
		return -1;
	}
	
	public static void reverse (char[] a, int lb , int ub){
		if (lb>=ub){
			return;
		}
		char tmp = a[lb];
		a[lb] = a[ub];
		a[ub]=tmp;
		reverse(a,lb+1,ub-1);
	}
	

	
}
