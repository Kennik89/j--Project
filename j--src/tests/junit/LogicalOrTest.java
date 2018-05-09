package junit;

import junit.framework.TestCase;
import pass.

public class LogicalOrTest extends TestCase {
	private ExclusiveOr xor;
	
	protected void setUp() throws Exception {
		super.setUp();
		xor = new ExclusiveOr();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDivide() {
		this.assertEquals(xor.xor(0, 42), 42);
		this.assertEquals(xor.xor(42, 11), 33);
		this.assertEquals(xor.xor(127, 3), 124);
	}

}
