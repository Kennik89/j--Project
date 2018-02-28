package junit;

import junit.framework.TestCase;
import pass.RightShift;

public class RightShiftTest extends TestCase {
	private RightShift asr;
	
	protected void setUp() throws Exception {
		super.setUp();
		asr = new RightShift();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDivide() {
		this.assertEquals(asr.asr(0, 11), 0);
		this.assertEquals(asr.asr(-1, 1), -1);
		this.assertEquals(asr.asr(0xFFFFF000, 8), 0xFFFFFFF0);
	}

}
