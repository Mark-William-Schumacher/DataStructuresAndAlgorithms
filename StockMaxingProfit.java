import java.lang.reflect.Array;
import java.util.Arrays;

public class StockMaxingProfit {
	/*
	 * QUESTION: Given a stock and its opening price points at each day
	 * how can we maximize the profit made of that stock, given that we 
	 * can only buy and sell at opening price. To emulate this function
	 * we can use a random array of numbers */
	public StockMaxingProfit(){
		Integer[] randomList=MarkRandom.randomList(20,0,100);
		System.out.println(Arrays.toString(randomList));
		Integer[] returnOfN2 = N2StockPrice(randomList);
		System.out.println("\n========N^2=========");
		System.out.println("Difference: "+returnOfN2[0] + 
			"\nBuy On: "+returnOfN2[1] +"\nSell On: " + returnOfN2[2]);
		
		System.out.println("\n========Divide And Conquer=========");
		Integer[] divAndC= DivideAndConquer(randomList);
		System.out.println("Difference: "+divAndC[0] + 
				"\nBuy On: "+divAndC[1] +"\nSell On: " + divAndC[2]);
		
		System.out.println("\n========Order N=========");
		Integer[] orderN= orderNSolution(randomList);
		System.out.println("Difference: "+orderN[0] + 
				"\nBuy On: "+orderN[1] +"\nSell On: " + orderN[2]);
		
	}
	
	/* 
	 * This is the first N^2 solution to the find the maximum profits for
	 * the stock prices.
	 */
	public Integer[] N2StockPrice(Integer[] stockPrices){
		int maxDiffSeen=0,buy=0,sell=0; // days to buy and sell on
		for (int i = 0; i <= stockPrices.length-2; i++){
			for (int j = i+1; j <= stockPrices.length-1; j++){
				int currentdifference = stockPrices[j]-stockPrices[i];
				if (maxDiffSeen < currentdifference){
					buy=i;
					sell=j;
					maxDiffSeen = currentdifference;
				}
			}
		}
		return new Integer[] {maxDiffSeen,buy,sell};
	}
	
	
	/* 
	 * Divide and conquer
	 * First Function = overloaded 
	 * Second Function = Divide
	 * Third Function is merge
	 */
	public Integer[] DivideAndConquer(Integer[] stockPrices){
		return DivideAndConquer(stockPrices,0,stockPrices.length-1);
	}
	public Integer[] DivideAndConquer(Integer[] stockPrices, int low, int high){
		if (low >= high)
			return new Integer[] {0,low,high,0,0};
		int mid = (high-low)/2 + low;
		Integer[] leftList = DivideAndConquer(stockPrices,low,mid);
		Integer[] rightList = DivideAndConquer(stockPrices,mid+1,high);
		return MergeStep(leftList,rightList, stockPrices);
	}
	/*
	 * For the merge function we need to take into account the case where
	 * the best buy day is in the left list , and the best sell day is in
	 * the right, so Min of left, Max of right difference .
	 *  DIFF , LEFT LOWER , LEFT UPPER , BEST BUY , BEST SELL
	 */
	public Integer[] MergeStep(Integer[] left,
									Integer[] right, Integer[] stockPrices){
		int minLeft=left[1];
		for (int i=left[1]+1; i <= left[2]; i++){
			if (stockPrices[minLeft] > stockPrices[i]){
				minLeft=i;
			}
		}
		int maxRight=right[1];
		for (int i=right[1]+1; i <= right[2]; i++){
			if (stockPrices[maxRight] < stockPrices[i]){
				maxRight=i;
			}
		}
		int leftRightDiff = stockPrices[maxRight]-stockPrices[minLeft];
		if (leftRightDiff> Math.max(left[0], right[0])) {
			return new Integer[] {leftRightDiff,left[1],right[2],minLeft,maxRight};
			// DIFF , LEFT LOWER , LEFT UPPER , BEST BUY , BEST SELL
		}
		else if (left[0]>right[0]){
			return new Integer[] {left[0],left[1],right[2],left[3],left[4]};
		}
		else{
			return new Integer[] {right[0],left[1],right[2],right[3],right[4]};
		}
	}
	
	/*
	 * So if we look at our rand array 
	 * [6, 8, 48, 77, 25, 56, 42, 75, 36, 39,1,20]
	 * moving forward through the array we need if we keep track of smallest num
	 * any new number seen after this , will give a bigger difference
	*/
	public Integer[] orderNSolution(Integer[] stockPrices){
		int minSeen = 0;
		// Difference, Buy Day, Sell Day
		Integer[] solution = {0,0,0};
		for (int i=1; i<stockPrices.length; i++){
			if (stockPrices[i]< stockPrices[minSeen])
				minSeen=i;
			int difference = stockPrices[i]-stockPrices[minSeen];
			if (difference > solution[0])
				solution=new Integer[] {difference, minSeen, i};
		}
		return solution;
	}
	
	
}
