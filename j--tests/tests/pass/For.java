package pass;

import java.lang.System;

public class For {

    public static int testfor() {
    	int counter = 0;
    	int x = 0;
    	
        for(x=0; x<11; ++x){
        	counter += 1;
       }
        return counter;
    }
	
	public static void main(String[] args) {
		int counter = testfor();
		System.out.println(counter);
	}

}
