import java.util.ArrayList;
import java.util.LinkedList;


public class MyLinkedList {
	
	public static class Node{
		public Node next;
		public int data;
		public Node(Node n, int d){
			this.next=n;
			this.data=d;
		}
		public String toString(){
			Node ptr= this;
			StringBuilder sb = new StringBuilder();
			while (ptr!=null){
				sb.append("[ ");
				sb.append(ptr.data);
				sb.append(" ] ->");
				ptr= ptr.next;
			}
			return sb.toString();
		}
		public int compareTo(Node other){
			return Integer.valueOf(this.data).compareTo(other.data);
		}
	}
	
	
	public static Node arrayToLinkedList(int[] array){
		Node root = new Node(null,array[0]);
		Node pntr1=root;
		for (int i=1; i < array.length; ++i){
			pntr1.next=new Node(null,array[i]);
			pntr1=pntr1.next;
		}
		return root;
	}
	
	
	public static Node LLmerge(Node r1 ,Node r2){
		Node rootPtr = ((r1.compareTo(r2)>=0)?r2:r1);
		Node cursorA = ((r1.compareTo(r2)>=0)?r1:r1.next);
		Node cursorB = ((r1.compareTo(r2)>=0)?r2.next:r2);
		Node cur = rootPtr;
		while (cursorA!=null &&cursorB!=null){
			if (cursorA.compareTo(cursorB)>=0){
				cur.next=cursorB;
				cursorB=cursorB.next;
			}else{
				cur.next=cursorA;
				cursorA=cursorA.next;
			}
			cur=cur.next;
		}
		// adds the remaining list to the tail
		if (cursorA!=null){
			cur.next=cursorA;
		}else{
			cur.next=cursorB;
		}
		return rootPtr;
	}
	
	
	public static Node reverse(Node root){
		Node prev= null; 
		Node cur= root;
		while (cur!= null){
			Node tmp = cur.next;
			cur.next=prev;
			prev=cur;
			cur = tmp;
		}
		return prev;
	}
	
	public static Node reverseSubList(Node root, int lb, int ub){
		// Be careful we want to build a reverse function here
		// before we just start trying to make an all in one function 
		Node cur = root; 
		int i = 0;
		Node subList = null, leftPointer =null, rightPointer=null, prev= null;
		while (cur != null){
			if (i == lb){
				leftPointer = prev;
				subList = cur;
			}
			if (i == ub){
				rightPointer = cur.next;
				cur.next=null;
				System.out.println(subList);
				Node rv=reverse(subList);
				System.out.println(rv);
				System.out.println("RP"+rightPointer);
				subList.next=rightPointer;
				if (leftPointer != null){
					leftPointer.next=rv;
				}else{
					root = rv;
				}
				break;
			}
			prev = cur;
			cur= cur.next;
			i++;
		}
		return root;
	}
	
	public static boolean isOverlapping(Node r1, Node r2){
		// Q6: This will test is any portion of the list is overlapping IE share same suffix
		 
		
		
		
		
		return true;
	}
	
	
	
	public static void main(String args[]){
		int[] a= {2,3,7,9,10,33};
		int[] b= {1,2,4,7,9};
		int[] c= {100};
		Node root=arrayToLinkedList(a);
		Node root2=arrayToLinkedList(b);
		Node root3=arrayToLinkedList(c);
//		System.out.println(root.toString());
//		System.out.println(root2.toString());
//		Node newRoot=LLmerge(root,root2);
//		System.out.println(newRoot.toString());	
//		newRoot=reverse(newRoot);
//		System.out.println(newRoot.toString());	
//		newRoot=reverseSubList(newRoot, 0, 7);
//		System.out.println(newRoot.toString());	
//		
		a=new int[]{1,2,3,4,5,6,7};
		b=new int[]{0,69};
		c=new int[]{99,98};
		root=arrayToLinkedList(a);
		root2=arrayToLinkedList(b);
		root3=arrayToLinkedList(c);
		root2.next.next=root;
		root3.next.next=root;
		System.out.println(root3.toString());	
		System.out.println(root2.toString());	
		LinkedList al = new LinkedList<String>();
		// root3 and root2 share the same suffix , but a different prefix
		// We now need to test for this
		
		
		
	}
	
	
	
	
	
	
}
