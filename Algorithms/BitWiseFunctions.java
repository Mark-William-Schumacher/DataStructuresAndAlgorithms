import java.util.Arrays;
import java.util.HashMap;


public class BitWiseFunctions {
	public static int[] bitCache = createBitCache();
	
	public BitWiseFunctions(){
		int randInt=MarkRandom.randInt(0, Integer.MAX_VALUE-1);
		String binString= String.format("%32s", 
				Integer.toBinaryString(randInt)).replace(" ", "0");
		System.out.println("\n BitWise Functions \n"+ binString);
		System.out.println("Actual Decimal Value: "+randInt+
				"\nNumber of Ones: "+ computeAllOnes(randInt) +
					"\nUsing Cache: "+computeOnesBitWise(randInt));
	}
	
	/*
	 * Computes all the ones inside of a bitstring
	 * Easiest is with .toBinaryString and Replace. O(n)
	 * Harder is with bitWise functions 
	 * We Can optimize this by using an array P where P[i] 
	 * is the number of 1's for that string. We can reduce the 
	 * size of P by making a 32 bit int a sum of 4 different
	 * 8 bit masks and store that in memory.
	 */
	public static int computeAllOnes(int bit){
		int numberOf1s = Integer.toBinaryString(bit)
								.replaceAll("0", "").length();
		return numberOf1s; 
	}	
	
	
	public static int computeOnesBitWise(int bit){
		// With a cache to save time on 32 bit ints array of 255 numbers
		int bitMask=(int)Math.pow(2, 8)-1;
		int sum = 0;
		for (int i=0; i<4;i++){
			int newbit= bit >> 8*i & bitMask ; // bit shift , then AND
			sum += bitCache[newbit];
		}
		return sum;	
	}
	
	
	private static int[] createBitCache(){
		int[] bitCache = new int[(int)Math.pow(2, 8)];
		System.out.println(bitCache.length);
		for (int i = 0; i<bitCache.length; i++){
			bitCache[i] = Integer.toBinaryString(i).replaceAll
											("0", "").length();
		}
		return bitCache;
	}
}
