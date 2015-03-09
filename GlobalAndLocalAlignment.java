import java.util.Arrays;


public class GlobalAndLocalAlignment {
	
	static int [][] tester = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
	
	public static void main(String args[]){
		String w = "GCATGCU";
		String v = "GATTACA";
		int[][] table = initializeTableforGlobal(w, v);
		dynamicFillGlobal(table,w,v);
		PrintTable(table,w,v);
		System.out.println();
		FindAlignment(table,w,v);
		
	  v = "ACACACTA";
		w = "AGCACACA";
		int[][] table2 = initializeTableforLocal(v, w);
		dynamicFillLocal(table2,v,w);
		PrintTable(table2,v,w);
	}
	
	public static String FindAlignment(int[][] table, String s1, String s2){
		int maxRowIndex = 0;
		for(int i=0; i<table.length; i++){
			if (table[i][table[0].length-1]>= table[maxRowIndex][table[0].length-1])
				maxRowIndex=i;
		}
		String sAcross= "";
		String sDown= s2.substring(maxRowIndex);
		for (int i = maxRowIndex; i< s2.length(); i++){
			sAcross= "-".concat(sAcross);
		}
		
		int colCounter=table[0].length-1;
		int rowCounter=maxRowIndex;
		while (colCounter>0 && rowCounter>0){
			int up = table[rowCounter-1][colCounter];
			int left = table[rowCounter][colCounter-1];
			int diagonal = table[rowCounter-1][colCounter-1];
			int cur = table[rowCounter][colCounter];
			if (cur == up-1){
				sDown = String.valueOf(s2.charAt(rowCounter-1)).concat(sDown);
				sAcross = "-".concat(sAcross);
				rowCounter--;
			}else if(cur == left-1){
				sAcross = String.valueOf(s1.charAt(colCounter-1)).concat(sAcross);
				sDown = "-".concat(sDown);
				colCounter--;
			}
			else{
				sAcross = String.valueOf(s1.charAt(colCounter-1)).concat(sAcross);
				sDown = String.valueOf(s2.charAt(rowCounter-1)).concat(sDown);
				colCounter--;
				rowCounter--;
			}
		}
		
		if (colCounter==0 && rowCounter>0){
			String preFix= s2.substring(0, rowCounter-1);
			sDown=sDown.concat(preFix);
			for (int i = 0; i< preFix.length(); i++){
				sAcross= "-".concat(sAcross);
			}
		}
		if (rowCounter==0 && colCounter>0){
			String preFix= s1.substring(0, colCounter-1);
			sAcross=sAcross.concat(preFix);
			for (int i = 0; i< preFix.length(); i++){
				sDown= "-".concat(sDown);
			}
		}
			
		System.out.println(sAcross);
		System.out.println(sDown);
		return (sDown.concat("\n").concat(sAcross));
		
	}
	
	
	public static int[][] initializeTableforLocal(String s, String s2){
		int[][]table = new int[s.length()+1][s2.length()+1];
		return table;		
	}
	
	
	public static int[][] dynamicFillLocal(int[][] table,String s1, String s2){
		for (int i= 1; i < table.length; i++){
			for (int j = 1 ; j < table[0].length; j++){
				int left = table[i-1][j]-1;
				int up = table[i][j-1]-1;
				int match = (s1.charAt(j-1) == s2.charAt(i-1))?2:-1;
				int diagonal =  table[i-1][j-1] + match;
				table[i][j]=Math.max(diagonal, Math.max(left, Math.max(up,0)));
			}
		}
		return table;
	}
	
	
	
	public static int[][] initializeTableforGlobal(String s, String s2){
		int[][] table = new int[s.length()+1][];
		
		// All zeros
		for (int i= 0 ; i < table.length; i++){
			int[] row = new int [s.length()+1];
			Arrays.fill(row, 0);
			table[i]=row;
		}
		// Fill with initial top row values 
		for (int i = 0 ; i< table[0].length; i++){
			table[0][i]=-i;
		}
		for (int i = 0 ; i< table.length; i++){
			table[i][0]=-i;
		}	
		return table;
	}
	public static int[][] dynamicFillGlobal(int[][] table,String s, String s2){
		for (int i = 1 ; i< table.length; i++){
			for (int j = 1; j< table[i].length; j++){
				int left = table[i-1][j]-1;
				int up = table[i][j-1]-1;
				int match = (s.charAt(j-1) == s2.charAt(i-1))?1:-1;
				int diagonal =  table[i-1][j-1] + match;
				table[i][j]=Math.max(diagonal, Math.max(left, up));
			}
		}
		return table;
	}
	
	
	public static void PrintTable(int[][] table,String s, String s2){
		s= "--".concat(s);
		s2= "-".concat(s2);
		for (int i=0; i<s.length();i++){
			System.out.printf("%5c", s.charAt(i));
		}
		System.out.println();
		
		
		for (int i= 0 ; i< table.length ; i++){
			System.out.printf("%5c", s2.charAt(i));
			for (int j= 0 ; j < table[i].length; j++){
				System.out.printf("%5d", table[i][j]);
			}
			System.out.println();
		}
		
	}
	
}
