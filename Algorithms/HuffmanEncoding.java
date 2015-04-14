import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class HuffmanEncoding {
	/*
	 * The idea behind Huffman encoding is that we are going to take the characters
	 * that are said most frequently , sort them , and build a binary tree joining
	 * the least frequently said nodes first. Each time a node is combined that node
	 * is placed back in the running for the next node to be picked. 
	 */
  private static final double[] ENGLISH_FREQ = {8.167, 1.492, 2.782, 4.253,
    12.702, 2.228, 2.015, 6.094, 6.966, 0.153, 0.772, 4.025, 2.406, 6.749,
    7.507, 1.929, 0.095, 5.987, 6.327, 9.056, 2.758, 0.978, 2.360, 0.150,
    1.974, 0.074};
  private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	public static class TreeNode implements Comparable<TreeNode>{
		public TreeNode left,right;
		public Character c;
		public double freq;
		public boolean isLeaf;
		
		public TreeNode(TreeNode left,TreeNode right, Character c, double freq){
			this.left=left;
			this.right=right;
			this.c=c;
			this.freq=freq;
		}
		
		public int compareTo(TreeNode o){
			// If we compare the other to this is essentially switches the lower
			// double as the "higher value"
			return Double.valueOf(this.freq).compareTo(o.freq);
		}
	}
	
	public static class CodeWord {
		public Character c;
		public String s;
		public CodeWord(Character c, String s){
			this.c = c;
			this.s = s;
		}
		
		@Override
		public String toString(){
			return "[" + c + ", " + s + "]";
		}
	}
	
	
	public static List<CodeWord> createHuffmanEncoding(){
		PriorityQueue<TreeNode> minHeap = new PriorityQueue<TreeNode>(); 
		for (int i = 0; i < ENGLISH_FREQ.length; i++){
			TreeNode node= new TreeNode(null, null, ALPHABET[i], ENGLISH_FREQ[i]);
			minHeap.add(node);
		}
		while (minHeap.size()>=2){
			TreeNode node = minHeap.remove();
			TreeNode node2 = minHeap.remove();
			TreeNode combinedNode = new TreeNode(node, node2, null, node.freq +node2.freq);
			minHeap.add(combinedNode);
		}
		return toBitString(minHeap.remove());
	}
	
	public static List<CodeWord> toBitString(TreeNode fullTree){
		List<CodeWord> allBitStrings= new ArrayList<CodeWord>();
		return toBitStringRec(allBitStrings, fullTree, "");
	}
	
	public static List<CodeWord> toBitStringRec(List<CodeWord> allBitStrings,
			TreeNode node, String s){
		if (node==null){
			return null; // All trees should have recursed by now
		}
		if (node.c!= null){
			allBitStrings.add(new CodeWord(node.c, s));
			return null;
		}
		toBitStringRec(allBitStrings, node.left, s.concat("0"));
		toBitStringRec(allBitStrings, node.right, s.concat("1"));
		return allBitStrings;
	}
	
	public static void main (String args[]){
		List<CodeWord> lc=createHuffmanEncoding();
		for (CodeWord cw: lc){
			System.out.println(cw);
		}
	}	
}

