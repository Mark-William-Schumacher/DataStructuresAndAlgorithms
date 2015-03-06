import java.util.Arrays;
import java.util.HashMap;
/*
 * This module addresses the the smallest number of coins problem , how would you make the smallest amount of change 
 * for an amount.
 * So make change for a 100 cents given that we have 1, 5 , 10, 25 cent coins 
 * The novel way of addressing this problem is to take away the largest coin until we can not and go from there 
 * This is a greedy algorythm that does not actually find the correct solution for certain amounts with specific coin denominations
 * EXAMPLE make change for 150 cents with { 1,15,25,30,40}
 * Using Greedy we get 40,40,40,1,1,1,1,1,1,1,1,1,1}
 * Actual Solution {40,40,30,30}
 * We can use dynamic programming to find the minimal solutions for K from 1-n . 
 * To find K we simply minus K by all of the coin denominations and choose the solution with the fewest coins
 * EXAMPLE Looking for 51 we have precomputed from 1- 50 (solutions exist at this point)
 * using denominations of {1,5,10,25} we retrieve the solutions for 51-1, 51-5, 51-10, 51-25 and return the smallest one appended with the denomination
 * now 51 is set and we can continue moving towards our goal of "n"
 * So 52 =   smallestOf( 52-1 ,  52- 5 ,  52 - 10 , 52-25)
 * 53 = 
 * 54 = ......
 * By caching the results , every result >0 will be in our hashtable
 */



public class DynamicProgrammingSmallestChange {
	public static final int[] coinDenominations = {1,  15,   20,   23,  25,   35,   40}; 
	public static final HashMap<Integer,Integer> denominationToIndex = createDenominationToIndex();
	
	
	public static void main(String args[]){
		findSmallestAmountOfCoins(20); // NO dynammic programming, large values = heap overflow due to recursion stack.         
		findSmallestDynamic(140);
	}
	
	/*
	 * We use dynamic programming to find a solution for all the values up to and including n .  That way every subsequent call can be 
	 * created from a previously computed solution, without the need to exhaustively check all cases. 
	 */
	public static void findSmallestDynamic(int num){
		HashMap<Integer, Integer[]> hashMap = new HashMap<>(num); // stores the smallest solution found for each number
		for (int i=1; i <= num ; i++){
			Integer[] coinsSofar = new Integer[coinDenominations.length];
			Arrays.fill(coinsSofar, 0);
			dynamicSolutionRec(i, coinsSofar, hashMap);
		}
		Integer[] dynamicSolution = hashMap.get(num);
		printDenominations(dynamicSolution);
	}
	
	
	/*
	 * The main worker function of our dynamicSolution, if we build our hashmap from from 1 up to n , we wont recurse into this function more then once.
	 * The reason for this is because if we have found all solutions < n when we look for a solution at n - denomination it will already be there and the
	 * solution will return after a single recursive call.
	 * The initial n will look return K solutions where K is the number of coin denominations. We only replace the local solution if the returned solution
	 * has a lower length then the current local solution.
	 */
	public static Integer[] dynamicSolutionRec(int num, Integer[] coinsSoFar, HashMap<Integer, Integer[]> hm){
		if (num == 0){
			return coinsSoFar;
		}
		if (hm.containsKey(num)){
			return Arrays.copyOf(hm.get(num),coinDenominations.length); // Dynamic Programming short circuits the recursion
		}		
		Integer[] localSolution =  null;
		for (int denomination: coinDenominations){
			Integer[] temp = Arrays.copyOf(coinsSoFar, coinDenominations.length);
			if (num - denomination >= 0){
				temp = dynamicSolutionRec(num-denomination, temp, hm);
				temp[denominationToIndex.get(denomination)]++;
				localSolution = (localSolution==null || sumOfArray(temp) < sumOfArray(localSolution))? temp : localSolution;
			}
		}
		hm.put(num, localSolution);
		return localSolution;
	}
	
	/*
	 * Sum of an array of Integer[] aka how many coins are in this solution
	 */
	public static int sumOfArray (Integer[] coins){
		int sum = 0;
		for (Integer i: coins){
			sum+= i;
		}
		return sum;
	}
	
	
	/*
	 * Helper Function that takes an Integer[] and prints all the coin denominations 
	 */
	public static void printDenominations(Integer[] coinsUsed){
		System.out.println("COINS USED FOR SOLUTION");
		for (int i = 0; i< coinDenominations.length; ++i){
			System.out.print(coinDenominations[i]+": "+ coinsUsed[i]);
			if (i< coinDenominations.length-1){
				System.out.print(",   ");
			}
		}
	}
	
	
	/*
	 * Function creates the hash map that is used for Denominations -> Index in array , reduces the
	 * big O complexity of a few of our functions
	 */
	private static HashMap<Integer, Integer> createDenominationToIndex() {
		HashMap<Integer,Integer> denominationToIndex = new HashMap<>(coinDenominations.length);
		for (int i = 0; i < coinDenominations.length; ++i){
			denominationToIndex.put(coinDenominations[i], i);
		}
		return denominationToIndex;
	}
	
	
	
	
	
	
	/*
	 * This will function creates a solutions list (which is used to store all solutions in our recursive function)
	 * and a coinsSofar list (originally empty) is copied k times at each new recursive call where k is our number
	 * of coin denominations.
	 * Once we have all our solutions we check to see which has the smallest length, this is our minimal solution
	 * we print the solution in the for [a1,a2,a3....]   EXAMPLE [1,0,3,2] is a solution for 81 cents 1 penny, 0 nick
	 * les 3 dimes, 2 quarters
	 */
	public static void findSmallestAmountOfCoins(int num){
		Integer[] solution =   new Integer[coinDenominations.length];// Will hold all the possible solutions
		Integer[] coinsSoFar = new Integer[coinDenominations.length]; 
		Arrays.fill(coinsSoFar, 0); // Sets our array to all 0s
		Arrays.fill(solution, 0);
		smallestAmountRec( num , coinsSoFar, solution); // Worker function will check all possible combinations
		if (sumOfArray(solution) == 0){
			System.out.println("NO SOLUTIONS FOUND FOR: "+num);
		}
		printDenominations(solution);
	}
	/*
	 * This is our main recursive worker function checks all possible combinations
	 * coins so far is copied and sent down the recursion stack , and solutions is just used as a pointer to the list of 
	 * all possible solutions
	 */
	public static void smallestAmountRec(int num , Integer[] coinsSoFar, Integer[] solution){
		if (num == 0){
			if (sumOfArray(solution)==0){ 								// No solution yet so first solution is the best
				copyIntoSolution(solution,coinsSoFar); 			// Java BS cant just reassign solution = coinsSoFar because its pass by value 
			}else{ 																				// Current solution exists, we only overwrite if the number of coins is less
				Integer[] temp = (sumOfArray(coinsSoFar) < sumOfArray(solution))? coinsSoFar: solution;
				copyIntoSolution(solution,temp);
			}
			return;
		}
		for (int denomination: coinDenominations){
			if (num-denomination >= 0){
				Integer[] temp = Arrays.copyOf(coinsSoFar, coinsSoFar.length);
				temp[denominationToIndex.get(denomination)]++;
				smallestAmountRec(num-denomination, temp, solution);
			}
		}
	}
	// Can't copy into the reference so we need to set each value in the array
	private static void copyIntoSolution(Integer[] a , Integer[] b){
		for (int i = 0 ; i < a.length ; ++i){
			a[i]=b[i];
		}
	}
	
}
