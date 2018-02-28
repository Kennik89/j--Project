package junit;

import junit.framework.TestCase;
import pass.Not;

public class NotTest extends TestCase {
	private Not not;
	
	protected void setUp() throws Exception {
		super.setUp();
		not = new Not();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDivide() {
		this.assertEquals(not.not(0), 0xFFFFFFFF);
		this.assertEquals(not.not(0xFFFFF000), 0x00000FFF);
	}

}
