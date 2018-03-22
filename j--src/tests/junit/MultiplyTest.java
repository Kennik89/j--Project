package junit;

import junit.framework.TestCase;
import pass.MultiplyAssign;

public class MultiplyTest extends TestCase {
	private MultiplyAssign multiplyAssign;
	
	protected void setUp() throws Exception {
		super.setUp();
		multiplyAssign = new MultiplyAssign();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testMultiply() {
		this.assertEquals(multiplyAssign.multiply(0, 42), 0);
		this.assertEquals(multiplyAssign.multiply(42, 1), 42);
		this.assertEquals(multiplyAssign.multiply(127, 3), 381);
	}

}
