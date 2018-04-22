package pass.step2;

import java.lang.System;

public class PrefixAndPostfixOperators {	
	public static int PostfixInc(int x, int y) {
		x = y++;
		return x;
	}
	
	public static int PostfixDec(int x, int y) {
		x = y--;
		return x;
	}
	
	public static int PrefixInc(int x, int y) {
		x = ++y;
		return x;
	}
	
	public static int PrefixDec(int x, int y) {
		x = --y;
		return x;
	}
	
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        // TODO: add logic for print inc/dec postfix/prefix

        int preinc = (x = ++y);
        int predec = (x = --y);
        int postinc = (x = y++);
        int postdec = (x = y--);

        System.out.println(preinc);
        System.out.println(predec);
        System.out.println(postinc);
        System.out.println(postdec);

    }
	
}
