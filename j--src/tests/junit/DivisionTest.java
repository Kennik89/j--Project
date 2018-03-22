package junit;

import junit.framework.TestCase;
import pass.Division;
import pass.DivisionAssign;

public class DivisionTest extends TestCase {
	private Division division;
	private DivisionAssign divisionAssign;
	
	protected void setUp() throws Exception {
		super.setUp();
		division = new Division();
		divisionAssign = new DivisionAssign();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDivide() {
		this.assertEquals(division.divide(0, 42), 0);
		this.assertEquals(division.divide(42, 1), 42);
		this.assertEquals(division.divide(127, 3), 42);
		this.assertEquals(divisionAssign.divide(0, 42), 0);
		this.assertEquals(divisionAssign.divide(42, 1), 42);
		this.assertEquals(divisionAssign.divide(127, 3), 42);
	}

}
