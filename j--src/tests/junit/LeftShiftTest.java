package junit;

import junit.framework.TestCase;
import pass.LeftShift;

public class LeftShiftTest extends TestCase {
	private LeftShift asl;
	
	protected void setUp() throws Exception {
		super.setUp();
		asl = new LeftShift();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDivide() {
		this.assertEquals(asl.asl(0, 11), 0);
		this.assertEquals(asl.asl(-1, 1), -2);
		this.assertEquals(asl.asl(0xFFFFF000, 8), 0xFFF00000);
	}

}
