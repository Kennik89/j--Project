package pass;

import java.lang.System;
import java.lang.Integer;

public class PrefixAndPostfixOperators {
	
	
	public static int[] PostfixInc (int x, int y) {
		int[] XandY = new int [2];
		x = y++;
		XandY[0] = x;
		XandY[1] = y;
		return XandY;
	}
	
	public static int[] PostfixDec(int x, int y) {
		int[] XandY = new int [2];
		x = y--;
		XandY[0] = x;
		XandY[1] = y;
		return XandY;
	}
	
	public static int[] PrefixInc(int x, int y) {
		int[] XandY = new int [2];
		x = ++y;
		XandY[0] = x;
		XandY[1] = y;
		return XandY;
	}
	
	public static int[] PrefixDec(int x, int y) {
		int[] XandY = new int [2];
		x = --y;
		XandY[0] = x;
		XandY[1] = y;
		return XandY;
	}
	
    public static void main(String[] args) {
    	int x = 5;
    	int y = 2;
    	
    	PrefixAndPostfixOperators pp = new PrefixAndPostfixOperators();
        // TODO: add logic for print inc/dec postfix/prefix
        int[] predec = pp.PrefixDec(x, y);
        int[] preinc = pp.PrefixInc(x, y);
        int[] postdec = pp.PostfixDec(x, y);
        int[] postinc = pp.PostfixInc(x, y);

        System.out.println("Initial x : " + x);
        System.out.println("Initial y : " + y);
        System.out.println("postinc x : " + postinc[0] + " - postinc y : " + postinc[1]);
        System.out.println("postdec x : " + postdec[0] + " - postdec y : " + postdec[1]);
        System.out.println("preinc x : " + preinc[0] + " - preinc y : " + preinc[1]);
        System.out.println("predec x : " + predec[0] + " - predec y : " + predec[1]);

    }
	
}
