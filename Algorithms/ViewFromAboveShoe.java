import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class ViewFromAboveShoe {
	
	public static class LineSegment implements Comparable<LineSegment>{
		int left,right,height,colour;
		
		public LineSegment(int l, int r, int c, int h){
			this.left=l;
			this.right=r;
			this.height=h;  // Can be a unique identifier for a given range NO intersections
			this.colour=c;
		}
		
		@Override
		public String toString(){
			return ("["+this.left+","+this.right+"],"
					+ " colour = "+ this.colour+", height = "+this.height);
		}

		@Override
		public int compareTo(LineSegment other) {
			return Integer.valueOf(this.height).compareTo(other.height);
		}
	}
	
	
	public static class EndPoint implements Comparable<EndPoint>{
		public boolean isLeft;
		public LineSegment l;
		
		public EndPoint(LineSegment line, boolean b){
			this.isLeft=b;
			this.l=line;
		}
		
		public int val(){
			if (isLeft){
				return l.left;
			}
			return l.right;
		}
		
		@Override
		public int compareTo (EndPoint other){
			return (Integer.valueOf(this.val()).compareTo(other.val()));
		}
	}
	
	// Done more simply
	public static List<Integer> viewFromAbove(List<LineSegment> lineSegs){
		ArrayList<EndPoint> e = new ArrayList<EndPoint>();
		for(LineSegment l : lineSegs){
			e.add(new EndPoint(l,true));
			e.add(new EndPoint(l,false));
		}
		Collections.sort(e);
		
		
		TreeSet<LineSegment> currentLines = new TreeSet<LineSegment>();
		EndPoint previous = (e.get(0));
		List<Integer> colorView= new ArrayList<Integer>();
		currentLines.add(previous.l); // LineSeg and index
		
		for (int i=1; i < e.size(); i++){
			EndPoint current = e.get(i);
			if (current.val() > previous.val()){// Add a color for last index
				int colourToAdd= !currentLines.isEmpty()? currentLines.last().colour: 0;
				colorView.add(colourToAdd);
			}
			if (current.isLeft){
				currentLines.add(current.l);
			}else{
				currentLines.remove(current.l);
			}	
			previous= current;
		}
		return colorView;
	}
		
	  public static void main(String[] args) {
		    List<LineSegment> A = new ArrayList<>();
		    A.add(new LineSegment(0, 4, 0, 0));
		    A.add(new LineSegment(1, 3, 1, 2));
		    A.add(new LineSegment(2, 7, 99, 1));
		    A.add(new LineSegment(2, 5, 3, 3));
		    A.add(new LineSegment(5, 7, 3, 0));
		    A.add(new LineSegment(6, 10, 0, 2));
		    A.add(new LineSegment(8, 9, 0, 1));
		    A.add(new LineSegment(9, 18, 4, 0));
		    A.add(new LineSegment(11, 13, 3, 2));
		    A.add(new LineSegment(12, 15, 4, 1));
		    A.add(new LineSegment(14, 15, 2, 2));
		    A.add(new LineSegment(16, 17, 3, 2));
		    for (LineSegment s : A) {
		      System.out.println("line segment, left = " + s.left + ", right = "
		          + s.right + ", color = " + s.colour + ", height = " + s.height);
		    }
		    List<Integer> a=viewFromAbove(A);
		    System.out.println(a);
		}		
		 
}