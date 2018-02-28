package junit;

import junit.framework.TestCase;
import pass.UnaryPlus;

public class UnaryPlusTest extends TestCase {
	private UnaryPlus positive;
	
	protected void setUp() throws Exception {
		super.setUp();
		positive = new UnaryPlus();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDivide() {
		this.assertEquals(positive.plus(0), 0);
		this.assertEquals(positive.plus(42), 42);
		this.assertEquals(positive.plus(-1), -1);
		this.assertEquals(positive.plus(0xFFFFF000), 0xFFFFF000);
	}

}
