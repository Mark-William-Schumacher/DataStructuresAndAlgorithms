import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;


public class BinTrees {
	public static class BTnode {
		BTnode right;
		BTnode left;
		BTnode parent;
		int height;
		int key;
		String value;
		public BTnode (int t, String v){
			this.key=t;
			this.value=v;
		}
		public boolean equals(BTnode other){
			return ((other==null)? false: (this.key==other.key));
		}
		
		public int getHeight(){
			// Height is measure from 0 starting at root
			return Math.max(getHeightRec(left), getHeightRec(right));
		}
		
		private int getHeightRec(BTnode b){
			if (b==null){
				return 0;
			}
			return Math.max(getHeightRec(b.left), getHeightRec(b.right))+1;
		}
	public String PrintTreeKeys(){
		return PrintTree(false);
	}
	public String PrintTreeValues(){
		return PrintTree(true);
	}
	private String PrintTree(boolean printValues){
		/* Prints a BINARY Tree in console output
		 */
		int height = getHeight();
		char[][] emptyStringTree = createEmptyTree(height);
		for (char[] x : emptyStringTree){
			System.out.println(String.valueOf(x).concat("\n"));
		}
		fillTree(emptyStringTree,this, 0,printValues);
		String cur= "";
		for (char[] x : emptyStringTree){
			cur = cur.concat(String.valueOf(x).concat("\n"));
		}
		return cur;
	}
	private void fillTree(char[][] btree, BTnode current, int depth, boolean printValues){
		if (current == null){
			DestroySubTree(btree,depth);
			return;
		}
		String printData =null;
		if (printValues){
			printData = current.value;
		}else{
			printData = Integer.toString(current.key);
		}
		while(printData.length()<4){
			printData=" ".concat(printData);
		}
		if (printData.length()>4){
			printData=printData.substring(0, 4);
		}
		int indexOfNode= String.valueOf(btree[depth]).indexOf("XXXX");
		btree[depth]=String.valueOf(btree[depth]).replaceFirst("XXXX", printData).toCharArray();
		if (current.left!= null){
			int i= indexOfNode-1;
			int lengthOfArm=(int)Math.pow(2,(btree.length-(depth))) -2;
			while (lengthOfArm>0){
				btree[depth][i]= '-';
				lengthOfArm--;
				i--;
			}
			btree[depth][i]= '|';
		}
		if (current.right!= null){
			int i= indexOfNode+4;
			int lengthOfArm=(int)Math.pow(2,(btree.length-(depth))) -2 + i;
			while (i < lengthOfArm){
				btree[depth][i]= '-';
				i++;
			}
			btree[depth][i]= '|';
		}
		fillTree(btree, current.left, depth+1, printValues);
		fillTree(btree, current.right, depth+1, printValues);
	}
	private void DestroySubTree(char[][] btree, int depth){
		int sum=1;
		for (int i = depth; i<= btree.length-1 ; i++){ //btree length is height of tree
			for (int j =sum ; j>0; j--){
				btree[i]=String.valueOf(btree[i]).replaceFirst("XXXX", "    ").toCharArray();
			}
			sum*=2;
		}
	}
	private char[][] createEmptyTree(int height){
		char[][] btree = new char[height+1][];
		int numOfLeaves= (int) Math.pow(2, height);
		String prevSpace="";
		String currentSpace="    ";
		for(int j=height; j>=0; j--){
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<numOfLeaves; i++){
				if (i==0){
					sb.append(prevSpace);
				} else{
					sb.append(currentSpace);
				}
				sb.append("XXXX");
				}
			sb.append(prevSpace);
			btree[j]=sb.toString().toCharArray();
			numOfLeaves/=2;
			prevSpace = currentSpace;
			currentSpace= currentSpace.concat(currentSpace).concat("    ");
			}
		return btree;
		}
	}// End of BTnode
	
	
	public static void assignParentPointers(BTnode cur, BTnode parent){
		// Basic in-order traversal with recursion 
		if (cur == null) return;
		cur.parent=parent;
		assignParentPointers(cur.left, cur);
		System.out.print(cur.value+" ");
		assignParentPointers(cur.right, cur);
	}
	
	
	public static void inOrder(BTnode root){
		if (root == null) return;
		preOrder(root.left);
		System.out.print(root.value+ " ");
		preOrder(root.right);
	}
	
	public static void preOrder(BTnode root){
		if (root == null) return;
		System.out.print(root.value+ " ");
		preOrder(root.left);
		preOrder(root.right);
	}
  
	public static void inOrderWStks(BTnode root){
		/* This is an inOrder Traversal using stacks instead of the standard recursion based traversal */
		Stack<BTnode> stk = new Stack<BTnode>();
		BTnode cur=root;
		while (!stk.isEmpty() || cur!= null){
			if (cur== null){
				BTnode tmp=stk.pop();
				System.out.print(tmp.value+" "); // Here is where we play with the node
				cur=tmp.right;
			}else{
				stk.push(cur);
				cur=cur.left;
			}
		}
	}
  
	
	public static void depthFirstIter(BTnode root){
		Queue<BTnode> q = new LinkedList<BTnode>();
		q.add(root);
		while (!q.isEmpty()){
			BTnode cur = q.remove();
			System.out.print(cur.value+" ");
			if (cur.left!=null) q.add(cur.left);
			if (cur.right!=null) q.add(cur.right);
		}
	}
	
	
	public static void depthFirstRec(BTnode root){
		//  No such thing!
	}
  
	
	public static void setHeights(BTnode root, int rootHeight){
		// Set the field height of the tree  = we can make this run with 
		// O(1) mem if parent Pointers
			
	}

	
	public static class Tuple{
		int h;  boolean isBal;
		public Tuple(int h, boolean b){
			this.h=h;
			this.isBal=b;}
	}
	
	public static boolean isBalanced(BTnode root){
		return heightRec(root).isBal;
	}
	
	public static Tuple heightRec(BTnode root){
		if (root == null){
			return new Tuple(0, true);
		}
		Tuple t = heightRec(root.left);
		Tuple t2 = heightRec(root.right);
		int h = Math.max(heightRec(root.left).h, heightRec(root.right).h)+1;
		boolean b= Math.abs(t.h -t2.h)<=1 && t.isBal && t2.isBal;
		return new Tuple(h,b);
	}
	
	
	// Question FIND K unbalanced NODES
	public static class TupleK{
		boolean found;
		int height;
		String data;
		public TupleK(boolean b, int h, String data){
		 this.found=b;
		 this.height=h;
		 this.data = data;
		}
	}
	public static String findKunbalancedNodes(BTnode root, int k){
		return KUnbalRec(root, k).data;
	}
	static TupleK KUnbalRec(BTnode root, int k){
		TupleK ret = new TupleK(false, 0, null);
		if (root == null) return ret;
		TupleK l=KUnbalRec(root.left, k);
		if (l.found){
			return l;
		}
		TupleK r=KUnbalRec(root.right, k);
		if (r.found){
			return r;
		}
		if (Math.abs(l.height - r.height) == k){
			return new TupleK(true, k, root.value);
		}
		return new TupleK(false, Math.max(l.height, r.height)+1, null);
	}
	// End of question
	
	
	// Question test to see if symmetric
	public static boolean isSymmetric(BTnode root){
		return isSymmetricRec(root.right, root.left);
	}
	public static boolean isSymmetricRec(BTnode ptrL, BTnode ptrR){
		if (ptrL == null || ptrR == null){
			return (ptrL == null && ptrR == null);
		}
		return (ptrL.equals(ptrR) 
				&& isSymmetricRec(ptrL.left, ptrR.right) 
				&& isSymmetricRec(ptrL.right, ptrR.left));
	}//End of question
	
	
	// Start LCA  This way will return the wrong value
	// We only want to return if they share, NOT direct relative 
	// or 
	public static String LCA(BTnode root, String key1, String key2){
		int i1 = search(root, key1, 1);
		int i2 = search(root, key2, 1);
		while (i1 != i2){
			if (i1>i2){
				i1/=2; 
			} else {
				i2/=2;
			}
		}
		return dataAt(root,i1);
	}
	public static int search(BTnode ptr, String key, int index){
		if (ptr == null) return -1;
		if (ptr.value.equals(key)){
			return index;
		}
		int x=search(ptr.left, key, index*2);
		if (x!=-1) return x;
		int y=search(ptr.right, key, index*2+1);
		if (y!=-1) return y;
		return -1;
	}
	public static String dataAt(BTnode root, int i){
		BTnode ptr = root;
		Stack<Boolean> path = new Stack<Boolean>();
		while (i!=1){
			path.add((i&1)!=1);
			i/=2;
		}
		while ((ptr != null) && !path.empty()){
			ptr = (path.pop())?ptr.left:ptr.right;
		}
		return (ptr==null)?"":ptr.value;
	}
	// END LCA
	

	// LCA actual , pg 87 of EOPI  common ancestor J, I = A because  
	// even though J is I's son, There least common ANCESTOR = I's father
	// so the root.
	// Knowing this , our recusion should return a tuple 
	//  Containing boolean = foundKey1, foundKey2, BTnode
	// if foundKey1 and 2 then set the BTnode if it has not been set yet 
	// to the current node. 
	public String leastCommonAncestor(BTnode b, String key1, String key2){
		return "";
	}
	
	
	public static class TupleX{
		boolean found;
		String path;
		public TupleX(String s, boolean b){
			this.found = b;
			this.path=s;
		}
	}
	public static String findARootToLeafSum(BTnode root,int sum ){
		// We return the leaf node that is equal to that sum
		// Assuming we have parent Pointers, if not create a hash
		// table for them...
		return rootToLeafRec(root, sum, "").path;
	}
	public static TupleX rootToLeafRec(BTnode root, int sum, String path){
		if (root==null || sum <0){
			return new TupleX ("",false);
		}
		sum-= root.key;
		path = path.concat(root.value);
		if (sum == 0 && root.right==null && root.left == null){
			return new TupleX (path,true);
		}
		
		
		TupleX l = rootToLeafRec (root.left, sum , path);
		if (l.found) return l;
		TupleX r = rootToLeafRec (root.right, sum , path);
		if (r.found) return r;
		
		return new TupleX ("",false);
	}
	
	
	public static String findSuccessor(BTnode node){
		// This node must have parent pointers! or else its not possible. 
		// If we are given a root we can compute it 
		// HashMap<BTnode, BTnode> parentPointers= computeParentPointers(root node)
		if (node.right != null){
			node = node.right;
			while (node.left != null){
				node= node.left;
			}
			return node.value;
		}
		else if(node.parent !=null && node.parent.left == node){
			node = node.parent;
			return node.value;
		}
	
		return "NONE";
	}
	
	public static BTnode buildWithPreOrder(char[] inOrder, char[] preOrder){
		Stack<Character> stk = new Stack<Character>();
		for (int i= preOrder.length-1 ; i >= 0 ; i--){
			stk.push(preOrder[i]);
		}
		HashMap<Character,Integer> inOrderMap = new HashMap<Character,Integer>();
		for (int i=0; i<inOrder.length; ++i){
			inOrderMap.put(inOrder[i],i);
		}
		return buildRec(0,stk.size()-1, inOrderMap,stk);
	}
	public static BTnode buildRec(int lb, int ub, HashMap<Character,Integer> inOrder, Stack<Character> stk){
		if (lb>ub || stk.isEmpty()){
			return null;
		}
		char cur = stk.pop();
		BTnode root = new BTnode(0, String.valueOf(cur));
		if (lb==ub){
			return root;
		}
		root.left = buildRec(lb,inOrder.get(cur)-1,inOrder,stk);
		root.right = buildRec(inOrder.get(cur)+1,ub,inOrder,stk);
		return root;
	}
	
	
	
	public static void main(String args[]){
		
		BTnode root= new BTnode(314,"  A ");
		root.left= new BTnode(6,"  B ");
		root.left.left= new BTnode(271,"  C ");
		root.left.left.left= new BTnode(28,"  D ");
		root.left.left.right= new BTnode(0,"  E ");
		root.left.right= new BTnode(561,"  F ");
		root.left.right.right= new BTnode(3,"  G ");
		root.left.right.right.left= new BTnode(17,"  H ");
		root.right= new BTnode(6,"  I ");
		root.right.right= new BTnode(271,"  O ");
		root.right.right.right= new BTnode(28,"  P ");
		root.right.left= new BTnode(2,"  J ");
		root.right.left.right= new BTnode(1,"  K ");
		root.right.left.right.left= new BTnode(401,"  L ");
		root.right.left.right.left.right= new BTnode(641,"  M ");
		
		BTnode root2= new BTnode(30,"  A ");
		root2.left=new BTnode(30,"  B ");
		root2.left.right=new BTnode(30,"  H ");
		root2.right=new BTnode(30,"  K ");
		root2.right.right=new BTnode(30,"  O ");
		root2.right.left=new BTnode(30,"  L ");
		root2.right.left.right=new BTnode(30,"  N ");
		
		//  Symetric BST
		BTnode root3= new BTnode(100,"A");
		root3.left=new BTnode(30,"B");
		root3.right=new BTnode(30,"C");
		root3.left.left=new BTnode(70,"D");
		root3.right.right=new BTnode(70,"E");
		root3.right.left=new BTnode(100,"F");
		root3.left.right=new BTnode(100,"H");
		
//		assignParentPointers(root,null);
//		System.out.println();
//		System.out.println();
//		depthFirstIter(root);
//		System.out.println(isBalanced(root));
//		System.out.println(isBalanced(root2));
//		String n = findKunbalancedNodes(root,2);
//		if (n!=null){
//			System.out.println(findKunbalancedNodes(root,3));	
//		}
		
//		System.out.println (isSymmetric(root3));
//		System.out.println(search(root, "C",1));
//		System.out.println(dataAt(root, 4));
//		System.out.println(LCA(root, "P", "L"));
//		System.out.println(findARootToLeafSum(root,(314+6+561+3+12)));
//		System.out.println();
//		System.out.println(findSuccessor(root));
		inOrderWStks(root);
		System.out.println();
		preOrder(root);
		char[] preOrder= "A B C D E F G H I J K L M O P".replace(" ", "").toCharArray();
		char[] inOrder= "D C E B F H G A J L M K I O P".replace(" ","").toCharArray();
		BTnode rootX = buildWithPreOrder(inOrder,preOrder);
		System.out.println();
		inOrderWStks(rootX);
		System.out.println();
		preOrder(rootX);
		
		HashMap<String, Integer> x = new HashMap<>();
		ArrayList<String> al = new ArrayList<>();
		
		BitSet b = new BitSet(100);
		b.set(99);
		System.out.println(b.toString());
		HashMap<String, Integer> a = new HashMap<>();
		
		System.out.println("HELLLO".indexOf("ELL"));
		
		System.out.println(root.PrintTree(true));
		System.out.println(root.PrintTree(false));
		inOrderWStks(root2);
		root2=null;
	}
	
	
	
	
	
	
	
	
}
