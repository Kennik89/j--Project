package pass.step2;

public class AssignmentOperators {
	
	public static int assignments(int x, int p, int s, int m, int d, int r) {
		int y = x;
		y += p;
		y -= s;
		y *= m;
		y /= d;
		y %= r;
		return y;
	}

}
