import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

// Stack And Queue Questions 
public class MyStack {
	public static class Pair<T extends Comparable<T>> {
		T data;
		T max;
		public Pair(T d, T max){
			this.data = d;
			this.max = max;
		}
	}
	
	public static class StackWMax<T extends Comparable<T>>{
		public LinkedList<Pair<T>> myStack = new LinkedList<Pair<T>>();
		
		
		public StackWMax ( T[] arrayOfTs ){
			for (T item : arrayOfTs){
				push(item);
			}
		}
		
		
		public void push (T item){
			T newMax = (myStack.isEmpty())? item
					: (item.compareTo(myStack.peek().max)>=0)? item
							: myStack.peek().max;
			// Showing off.			
			Pair<T> newPair = new Pair<T>(item, newMax);
			myStack.push(newPair);
		}
		
		
		public T getMax(){
			return myStack.peek().max;
		}
		
		
		public T pop(){
			return myStack.pop().data;
		}	
		
		
		public String toString(){
			StringBuilder sb = new StringBuilder();
			for (Pair<T> item : myStack){
				sb.append(" [");
				sb.append("Item: "+item.data+" MaxVal: "+item.max);
				sb.append(" ]");
			}
			return sb.toString();
		}
		
	}// End of StackWMax Class
	
	
	
	public static int rpnExpressions (String rpnExp){
		// The idea of an RPN expression is that it is evaluated once it hits
		// an operator  ".+-/" so any number previously will be evaluated
		//  assert(42 == rpnExpressions("1,2,3,4,5,+,*,+,+,3,4,*,+");
		//  assert(15 == rpnExpressions("1,2,+,3,4,*,+"));
		String[] rpnArray = rpnExp.split(",");
		LinkedList<Integer> partialResults= new LinkedList<Integer>();
		for (String cur : rpnArray){
			if (cur.length()<1) continue;
			if ("+-/*".contains(cur) && cur.length()==1){
				if(partialResults.size()<2) 
					throw new IllegalArgumentException("Malformed RPN at :" + cur);
				int y = (int)partialResults.pop();
				int x = (int)partialResults.pop();
				partialResults.push((Integer)evaluate(x,y,cur.charAt(0)));
			}else{
				partialResults.push(Integer.parseInt(cur));
			}
		}
		return (int) partialResults.pop();
	}
	public static int evaluate(int x, int y, char oper){
		if (oper=='*'){
			return x*y;
		}else if (oper=='+'){
			return x+y;	
		}else if (oper=='-'){
			return x-y;	
		}else if (oper=='/'){
			return x/y;	
		}else{
			return 0;
		}
	}
	
	
	public static class myQueue{
		int[] queue ;
		int size, ptrQ, ptrNxt,numEles;
		public myQueue (){
			this(10);
		}
		public myQueue(int size){
			this.size=size;
			this.queue= new int[10];
			this.ptrQ=0;
			this.ptrNxt=0;
			this.numEles=0;
		}
		public void enqueue(int x){
			queue[ptrNxt++]=x;
			if (++numEles >= size){
				resize();
			}
			ptrNxt = (ptrNxt>=size)? ptrNxt % size : ptrNxt;
		}
		private void resize() {
			int[] newQueue = new int[size*2];
			for (int i = 0 ; i < numEles; i++){
				newQueue[i] = queue [(ptrQ+i) % size];
			}
			this.size=size*2;
			ptrNxt=numEles;
			ptrQ=0;
			queue=newQueue;
    }
		
		
		
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append("Size, ptrQ, ptrNxt, numEles\n");
			sb.append(this.size+" "+this.ptrQ+" "+this.ptrNxt+" "+this.numEles);
			sb.append("\n" + Arrays.toString(queue));
			return sb.toString();
		}
	}
	
	public static void main(String args[]){
// // STACK MAX TESTING
//		Integer[] array = {4, 3, 200, 1, 90 , 100};
//		StackWMax<Integer> swm = new StackWMax<Integer>(array);
//		System.out.println(swm.getMax());
//		System.out.println(swm.toString());
//		System.out.println(swm.getMax());	
			assert (0 == rpnExpressions("2,-10,/"));
			assert (-5 == rpnExpressions("-10,2,/"));
			assert (5 == rpnExpressions("-10,-2,/"));
			assert (-5 == rpnExpressions("5,10,-"));
	    assert (6 == rpnExpressions("-10,-16,-"));
	    assert (12 == rpnExpressions("10,2,+"));
	    assert (15 == rpnExpressions("1,2,+,3,4,*,+"));
	    assert (42 == rpnExpressions("1,2,3,4,5,+,*,+,+,3,4,*,+"));
	    Stack<Integer> stk = new Stack<Integer>();
	    LinkedList<Integer> ll = new LinkedList<Integer>();
	    
	    myQueue q= new myQueue();
	    for (int item: new int[] {1,2,3,4,6,7,23,20,90,100}){
	    	q.enqueue(item);
	    }
	    System.out.println(q.toString());
	    
	    
	    
	}
	
	
	
}
