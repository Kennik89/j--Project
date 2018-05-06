package pass;

public class CondtionalExpression {

	
	public int condtional(boolean cond,int thenpart, int elsepart) {
		return cond ? thenpart : elsepart;
	}
	
	public int nestedConditional() {
		return false ? 2 : true ? 4 : 7;
	}
	
	public static void main(String[] args ) {
		int i = new CondtionalExpression().condtional(true,2,3);
	}
}
