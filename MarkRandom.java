import java.util.Random;


public class MarkRandom{
	public static Integer[] randomList(int size, int lowerRange, int upperRange){
		Integer[] arrayRand=new Integer[size];
		for	(int j=0; j<size; j++){
			int i =randInt(lowerRange, upperRange);
			arrayRand[j]=i;
		}
		return arrayRand;
	}
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
}
