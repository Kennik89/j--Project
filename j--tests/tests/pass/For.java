package pass;

public class For {

    public static int testfor(int y) {
    	int counter = y;
    	
        for(int x=1; y<11; y++){
        	counter++;
       }
        return counter;
    }
	
	public static void main(String[] args) {
		int y = 1;
		int counter = testfor(1); //Interates 10 times
		

	}

}
