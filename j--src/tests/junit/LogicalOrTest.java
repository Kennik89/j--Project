package junit;

import junit.framework.TestCase;
import pass.LogicalOrOperator;

public class LogicalOrTest extends TestCase {
	private LogicalOrOperator lor;
	
	protected void setUp() throws Exception {
		super.setUp();
		lor = new LogicalOrOperator();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testLogicalOr() {
		this.assertEquals(lor.lor(true, true), true);
		this.assertEquals(lor.lor(true, false), true);
		this.assertEquals(lor.lor(false, true), true);
		this.assertEquals(lor.lor(false, false), false);
		
		this.assertEquals(true || shortCiruitCheck(false), true);
	}
	
	private boolean shortCiruitCheck(boolean b)	{
		this.assertFalse("rhs-condition should not be evaluated when lhs is true", true);
		return b;
	}
}
