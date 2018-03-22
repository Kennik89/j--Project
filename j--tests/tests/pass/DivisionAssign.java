package pass;

import java.lang.Integer;
import java.lang.System;

public class DivisionAssign {
	public int divide(int x, int y) {
		int z = x;
		z /= y;
		return z;
	}

    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
		int c = a;
		c /= b;
        System.out.println(c);
    }
}
