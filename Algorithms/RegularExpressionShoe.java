
public class RegularExpressionShoe {
	// Without the use of a subString , Which in JAVA 7 is a linear operation! so this will save us alot of time . 
	// 
	
	public static boolean isMatch (String regex, String normal){
		if (regex.charAt(0)==('^')){ // We need a match on the prefex regex
			return isMatchRec(regex, 1, normal, 0);
		}
		for (int i=0; i <= normal.length(); i++){
			if (isMatchRec(regex, 0, normal, i)){
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean isMatchRec(String regex, int rIndex, String normal, int nIndex){
		// CASE end of regex
		if (regex.length() - rIndex <= 0){
			return true; // found the end of regex 
		}
		
		// CASE last regex is $
		if (regex.length() - rIndex >= 2 && regex.charAt(rIndex+1)=='$'){
			return ((normal.length()-nIndex==1) &&
					(regex.charAt(rIndex)=='.' || regex.charAt(rIndex) == normal.charAt(nIndex)));
		}
		
		// CASE regex has *
		if (regex.length() - rIndex >= 2 && (regex.charAt(rIndex+1)== '*')){ // We can have 0 to infinate regex charAt(0)
			char zeroOrMany = regex.charAt(rIndex);  // call is match at all new points
			for (int i = nIndex; i < normal.length(); i++){
					if (zeroOrMany == '.' || zeroOrMany == normal.charAt(i)){
						if (isMatchRec(regex, rIndex+2, normal, i+1)){
							return true;
						}
					}
					else{
						break; // IMPORTANT, because if we stop getting matches we don't progress through the string anymore
					}
			}
			return isMatchRec(regex, rIndex+2, normal , nIndex); // Case where there are 0 entries
		}
		
		// CASE single to single match   STRING cannot be empty
		if (normal.length()-nIndex>0 && (regex.charAt(rIndex) == normal.charAt(nIndex) || regex.charAt(rIndex)=='.'))
			return isMatchRec(regex,rIndex+1,normal,nIndex+1);
		
		// CASE Not a match
		return false;
	}
	

  public static void main(String[] args) {
    System.out.println (isMatch(".", "a"));
    System.out.println (isMatch("a", "a"));
    System.out.println (!isMatch("a", "b"));
    System.out.println (isMatch("a.9", "aW9"));
    System.out.println (!isMatch("a.9", "aW19"));
    System.out.println (isMatch("^a.9", "aW9"));
    System.out.println (!isMatch("^a.9", "baW19"));
    System.out.println (isMatch(".*", "a"));
    System.out.println (isMatch(".*", ""));
    System.out.println (isMatch("c*", "c"));
    System.out.println (!isMatch("aa*", "c"));
    System.out.println (isMatch("ca*", "c"));
    System.out.println (isMatch(".*", "asdsdsa"));
    System.out.println (isMatch("9$", "xxxxW19"));

    System.out.println (isMatch(".*a", "ba"));

    System.out.println (isMatch(".*a", "ba"));
    System.out.println (isMatch("^a.*9$", "axxxxW19"));

    System.out.println (isMatch("^a.*W19$", "axxxxW19"));
    System.out.println (isMatch(".*a.*W19", "axxxxW19123"));
    System.out.println (!isMatch(".*b.*W19", "axxxxW19123"));
    System.out.println (isMatch("n.*", "n"));
    System.out.println (isMatch("a*n.*", "an"));
    System.out.println (isMatch("a*n.*", "ana"));
    System.out.println (isMatch("a*n.*W19", "anaxxxxW19123"));
    System.out.println (isMatch(".*a*n.*W19", "asdaaadnanaxxxxW19123"));
    System.out.println (isMatch(".*.*.a*n.*W19", "asdaaadnanaxxxxW19123"));
    System.out.println (isMatch("^Hy$", "Happy"));
  }
	
}
