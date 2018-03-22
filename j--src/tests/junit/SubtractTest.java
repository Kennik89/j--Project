package junit;

import junit.framework.TestCase;
import pass.SubtractAssign;

public class SubtractTest extends TestCase {
	private SubtractAssign subtractAssign;
	
	protected void setUp() throws Exception {
		super.setUp();
		subtractAssign = new SubtractAssign();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSubtract() {
		this.assertEquals(subtractAssign.subtract(0, 42), -42);
		this.assertEquals(subtractAssign.subtract(42, 1), 41);
		this.assertEquals(subtractAssign.subtract(127, 3), 124);
	}

}
