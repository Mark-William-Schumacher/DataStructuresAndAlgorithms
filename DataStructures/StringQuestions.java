import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StringQuestions {
	
	public static final char[][] lookup = createCharArray();
	private static char[][] createCharArray(){
		char [][] lookup= { // This is for the compute all mnemonics question
				{' '},
				{' '},
				{'a','b','c'},
				{'d','e','f'},
				{'g','h','i'},
				{'j','k','l'},
				{'m','n','o'},
				{'p','q','r','s'},
				{'t','u','v'},
				{'w','x','y','z'}
		};
		return lookup;
	}
	
	public static List<String> computeAllMneumonics(int phoneNum){
		// This function creates all the mneumonics of a phone number 
		// Don't send too many digits in or memory overload 
		// Also O(N**4) time complexity - cant be reduced
		List<String> allCombs = new ArrayList <String>();
		StringBuilder sb = new StringBuilder();
		mneumonicRecursive(phoneNum, allCombs, sb);
		for (String s: allCombs){
			System.out.println(s);
		}
		System.out.println(allCombs.size());
		return allCombs;
	}
	
	private static void mneumonicRecursive(int phoneNum, List<String> allCombs, StringBuilder sb){
		if ( phoneNum == 0 ){
			allCombs.add(sb.toString());
			return ;
		}
		int cur= phoneNum % 10 ; 
		phoneNum /= 10;
		for (char c : StringQuestions.lookup[cur]){
			StringBuilder nsb = new StringBuilder(sb.toString());
			mneumonicRecursive(phoneNum, allCombs, nsb.append(c));
		}
	}
	
	public static List<String> mneumonicsIter(String phoneNum){
		// Same function , but dont iteratively no Recursion 
		// using string , because int is limiting
		StringBuilder sb = new StringBuilder("");
		List<StringBuilder> curCombs = new ArrayList <StringBuilder>();
		curCombs.add(sb);
		for (int i=0 ; i<phoneNum.length(); i++ ){
			List<StringBuilder> updatedComps= new ArrayList<StringBuilder>();
			for (StringBuilder s : curCombs){
				for (char c: StringQuestions.lookup[(phoneNum.charAt(i))-'0']){
					StringBuilder newBuilder= new StringBuilder(s);
					newBuilder.append(c);
					updatedComps.add(newBuilder);
				}
			}
			curCombs = updatedComps;
		}
		List<String> los = new ArrayList<String>();
		for (StringBuilder s: curCombs){
			los.add(s.toString());
		}
		System.out.println(los.size());
		return new ArrayList<String>();
		
	}
	
	
	public static String randIntString(int len) {
		// This function creates a random String that contains only integer values
		// "-2190" or "2323" to the desired length , nothing besides numbers, 
		// however we may extend this to include ascii values that arn't 0 - 9 later.
    Random r = new Random();
    StringBuilder ret = new StringBuilder();
    if (len == 0) {
      return "0";
    }
    if (r.nextBoolean()) {
      ret.append('-');
    }
    ret.append((char) ('1' + r.nextInt(9)));
    while (--len != 0) {
      ret.append((char) ('0' + r.nextInt(10)));
    }
    return ret.toString();
  }
	
	
	public static void testingIntStringConversion(String[] args){
		// This function tests our build stringToInt and intToString functions 
    Random r = new Random();
    if (args.length == 1) {
      try {
        System.out.println(stringToInt(args[0]));
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    } else {
      for (int times = 0; times < 10000; ++times) {
        int x = r.nextInt();
        String str = intToString(x);
        System.out.println(x + " " + str);
        assert (x == Integer.parseInt(str));
        str = randIntString(r.nextInt(10));
        x = stringToInt(str);
        System.out.println(str + " " + x);
        assert (x == Integer.parseInt(str));
      }
    }
  }
	
	public static int stringToInt(String strInt){
		boolean isNegative = strInt.charAt(0) == '-';
		int total = 0;
		for (int i = strInt.charAt(0)=='-' ? 1 : 0; i <strInt.length(); i++){
		  int digit = strInt.charAt(i) - '0';
		  if (digit <= 9 && digit >= 0 ){ // Validity check
		  	total = total*10 + digit;
		  }
		}
		if (isNegative)
			total = -total;
		return total;
	}
	
	public static String intToString(int num){
		boolean isNegative = (num<0);
		StringBuilder sb = new StringBuilder();
		while (num != 0){
			char c = (char) ('0' +Math.abs(num % 10));
			sb.append(c);
			num/=10;
		}
		if (isNegative){
			sb.append('-');
		}
		return sb.reverse().toString();
	}
	
	public static String replaceAndRemove(String s ){
		// This problem is super easy with a string builder object. 
		// Our problem is remove all 'b's and replace 'a' with "dd"
		StringBuilder sb = new StringBuilder();
		for (int i= 0 ; i < s.length(); i++){
			char c = s.charAt(i);
			if (c!='b'){
				if (c=='a'){
					sb.append("dd");
				}else{
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}
	public static String replaceAndRemoveNoSB(String s){
		// This time were not aloud to use string builder 
		// Our problem is remove all 'b's and replace 'a' with "dd"
		// First we need to create the length of the string by scanning ahead
		// of time . 
		int afterLen = 0;
		for (int i=0 ; i < s.length() ; i++){
			if (s.charAt(i)=='a'){
				afterLen +=1;
			}
			else if(s.charAt(i)=='b'){
				afterLen -=1;
			}
			afterLen +=1;
		}
		char[] transformedString = new char[afterLen];
		int cursorT=0;
		for (int i=0 ; i < s.length() ; i++){
			char cur = s.charAt(i);
			if (cur == 'a'){
				transformedString[cursorT++] = 'd';
				transformedString[cursorT++] = 'd';
			}
			else if(cur != 'b'){
				transformedString[cursorT++] = cur;
			}
		}
		return String.valueOf(transformedString);
	}
	
	public static boolean isPalindrome(String s){
		// This function tests whether a word is a palidrome
		// the key idea to remember here is that we don't care about white space
		// only alphanumeric letters.
		String b = onlyNumAndChar(s);
		int rightPtr= b.length()-1;
		int leftPtr= 0;
		while (rightPtr > leftPtr){
			if (b.charAt(leftPtr)!=b.charAt(rightPtr)){
				return false;
			}
			rightPtr--;
			leftPtr++;
		}
		return true;
	}
	public static String onlyNumAndChar(String s){
		// Removing the whitespace and punctuation from a string. 
		StringBuilder sb = new StringBuilder(); 
		for (int i=0 ; i < s.length(); ++i){
			char c = Character.toLowerCase(s.charAt(i));
			if ( ('9'>=c && c>='0')  || (c>='a' && c<='z') ){
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	
	public static void main (String args[]){
		computeAllMneumonics(9542);
		mneumonicsIter("9542");
		
		
	}
	
	

}
