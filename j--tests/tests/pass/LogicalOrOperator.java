package pass;

import java.lang.Boolean;
import java.lang.System;

public class LogicalOrOperator {

	public boolean lor(boolean x, boolean y) {
			return x || y;
		}

		public static void main(String[] args) {
			boolean a = Boolean.parseBoolean(args[0]);
	        boolean b = Boolean.parseBoolean(args[1]);
	        System.out.println(a || b);
	    }
}
