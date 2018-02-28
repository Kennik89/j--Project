package junit;

import junit.framework.TestCase;
import pass.LogicalRightShift;

public class LogicalRightShiftTest extends TestCase {
	private LogicalRightShift lsr;
	
	protected void setUp() throws Exception {
		super.setUp();
		lsr = new LogicalRightShift();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDivide() {
		this.assertEquals(lsr.lsr(0, 11), 0);
		this.assertEquals(lsr.lsr(-1, 1), 0x7FFFFFFF);
		this.assertEquals(lsr.lsr(0xFFFFF000, 8), 0x00FFFFF0);
	}

}
