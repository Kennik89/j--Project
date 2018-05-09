package junit;

import junit.framework.TestCase;
import pass.CondtionalExpression;

public class CondtionalTest extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testReturnCondtionTrue() {
		int expected = 7;
		
		int actual = new CondtionalExpression().condtional(true, expected, 199);
		assertEquals(expected, actual);
	}
	
	public void testReturnCondtionFalse() {
		int expected = 7;
		
		int actual = new CondtionalExpression().condtional(false, 199, expected);
		assertEquals(expected, actual);
	}
	
	
}
