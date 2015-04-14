import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class ComputeParity {
	
	/* This is absolutely the cleanest way of creating a static lookup Table 
	 * much cleaner and nicer than anything I've seen using inner classes! */
	public static final Character[] intToString = new 
			Character[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	public static final Map<Character, Integer> lookupMap = createLookup();
	private static Map<Character, Integer> createLookup (){
    Map<Character, Integer> result = new HashMap<Character, Integer>();
    result.put('0', 0);
    result.put('1', 1);
    result.put('2', 2);
    result.put('3', 3);
    result.put('4', 4);
    result.put('5', 5);
    result.put('6', 6);
    result.put('7', 7);
    result.put('8', 8);
    result.put('9', 9);
    result.put('A', 10);
    result.put('B', 11);
    result.put('C', 12);
    result.put('D', 13);
    result.put('E', 14);
    result.put('F', 15);
		return result;  // Or return Collections.unmodifiableMap(result);
	}
	
	public static int parity(long ourNum){
		// Computes the parity of a binary number
		// The parity of digit with an odd number of bits ON is 1
		// The parity of a digit with an even number of bits on is 0
		return (Long.toBinaryString(ourNum).replace("0","").length()&1);
	}
	
	
  public static int parity1(long x) {
  	int result=0;
  	while (x != 0){
  		result ^= (x & 1);
  		x >>>= 1 ;
  	}
  	return result;
  }
	
  
  public static int sign(int i) {
    if (i == 0) return 0;
    if (i >> 31 != 0) return -1;
    return +1;
  }
  
  
	public static int multiplyWithBits(int x , int mult){
	// A function which multiplys only using bit shifts and addition
	// Note we could use bit addition if the Question asks for no operators 
		int bitIndex=0;
		int result=0;
		while (mult!=0){
			if ((1 & mult) == 1){
				result += x<<bitIndex; 
			}
			bitIndex+=1;
			mult>>>=1;
		}
		return result;	
	}
	
	
	public static int addOnlyUsingBits(int x, int y){
		// function that only uses bitwise to add numbers.
		int carry= (x&y)<<1;
		int result= x^y;
		while (carry!=0){
			int carryT = (carry & result)<< 1;
			result = carry ^ result;
			carry = carryT;
		}
		return result;
	}
	
	
	public static int mostSignificantBit(int x){
		// This function will return the most Significant bit (1) at the leftmost index
		int msbIndex=-1;
		while (x!=0){
			msbIndex+=1;
			x>>=1;
		}
		return msbIndex;	
	}
	
	
	public static long reverseBit(long x){
		// This function will return the reverse of a binary bit  EX 10010 -> 01001
		// Faster implementation with an array based lookup table 
		for (int i=63,j=0; j<=31; i--, j++){
				x=swapBits(x,i,j);
			}
		return x;
	}
	
	
	public static long swapBits(long x,int index1, int index2){ 
		// Swaps the indices of two bits takes advantage of a clever trick 
		// that notes we don't have to swap the indices unless they are different
		// and at that point , we only actually need to TOGGLE(common bitwise function)
		long bitAt1=((x >> index1) & 1L); // get bit at each index
		long bitAt2=((x >> index2) & 1L); // get bit at each index
		if (bitAt1 != bitAt2){
			x^= (1L<<index1); // toggle the bits at each index
			x^= (1L<<index2); // toggle the bits at each index
		}
		return x;
	}
	
	
  public static void compare(long number){
  	assert (parity1(number)==parity(number));
  }
  
  
  public static int powerOfWithBits(int num, int exponent){
  	// The immediate answer to solve this question would be doing num*num*num.. exponent 
  	// amount of times . However, there is a more clever answer using bitshifting.
  	// That can take O(N) time. 
  	int total=1;
  	while (exponent>0){
  		if ((exponent & 1) == 1){
  			total*= num;
  		}
  		num*=num;
  		exponent>>=1;
  	}
  	return total;
  }
  
  
  public static double powerOfWithBitsNegative(int num, int exponent){
  	// This includes the negative cases too , which require doubles 
  	double total=1;
  	double base = num;
  	if (exponent<0){
  		exponent= -exponent;
  		base = 1.0/num;
  	}
  	while (exponent!=0){
  		if ((exponent & 1) == 1){
  			total*= base;
  		}
  		base*=base;
  		exponent>>=1;
  	}
  	return total;
  }
  
  
  public static String convertToBase(String input, int base1, int baseC){
  	// Converts the input string which is in base1 ,to the desired baseC output
  	// Base C must be <=16 
  	// Returns a String  Example "255" , 10 , 16 -> "FF"
  	// We can also readInput and then just do a 
  	// remainder = num MOD Base    (CONVERT (remainder) append to front of string )
  	// num /= Base                 
  	// This may be easier but MOD may be very costly. 
  	
  	int numberRep = readInput(input, base1);
  	int count= 1; 
  	while (Math.pow(baseC,count+1) <= numberRep){
  		count++;
  	}
  	String representation= "";
  	while (count>=0){
  		int currentDigit = 0;
  		while ((currentDigit+1)*Math.pow(baseC, count) <= numberRep){
  			currentDigit++;
  		}
  		numberRep -= currentDigit*Math.pow(baseC, count);
  		representation += (ComputeParity.intToString[currentDigit]);
  		count --;
  	}
  	return representation;
  }
  
  
  public static int readInput(String input, int base1){
  	// This function returns an value of the string input given its BASE
  	// Example "FF" , 16  -> 255 
  	// Example "102", 10 -> 102 
  	int result = 0;
  	int multiplier = 1; 
  	for (int i=input.length()-1; i>=0 ; i--){
  		result += multiplier * ComputeParity.lookupMap.get(input.charAt(i));
  		multiplier*=base1;
  	}
  	return result;
  }
  
  public static int rand7(){
  	// This will be our pretend function that can only give us a random number
  	// from 1-7 INCLUSIVE . We must then convert this into a function that creates
  	// a random distribution of numbers between a given series of numbers
  	Random r = new Random();
  	// upper - lower + 1 gives us Lower - Upper Inclusive.
  	return 1+r.nextInt(7 - 1 +1);
  }
  public static void rand7Test(){
  	// Test for Rand7 
  	int[] j = new int[10];
		for (int i=0; i<1000000; i++){
			int t=anyRand(0,8);
			j[t]++;
		}
		System.out.println(Arrays.toString(j));	
  }
  
  public static int anyRand(int lb, int ub){
  	// Creating a uniform range of numbers given a rand7() function , first create 
  	// a random bit and then we can easily create any number we wish
  	int result = lb -1;
  	while (result < lb || result > ub){
  		result=0;
  		for (int i=0; ub>=Math.pow(2, i); i++){
  			result |= randBit()<<i;
  		}
  	}
  	return result;
  }
  
  public static int randBit(){
  	// 0001 , 0010 , 0011, 0100 , 0101, 0110 , 0111 = 1-7 , We only include 6 of them to 
  	// get an even distribution of 1's vs 0's at the ending bit
  	int number= rand7();
  	while (number == 7){
  		number = rand7();
  	}
  	return number&1;
  }
  
  
  
	public static void main(String args[]){
		for (int i=-100 ; i < 5000 ; i++){
			compare(i);
		}
		
		long x=0x329F29;
//		rand7Test();
		System.out.println(x);
		System.out.println(Long.toBinaryString(x));
		System.out.println(powerOfWithBitsNegative(2,-1));
		
		
		
	}
}
