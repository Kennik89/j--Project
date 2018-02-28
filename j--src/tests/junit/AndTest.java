package junit;

import junit.framework.TestCase;
import pass.And;

public class AndTest extends TestCase {
	private And and;
	
	protected void setUp() throws Exception {
		super.setUp();
		and = new And();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDivide() {
		this.assertEquals(and.and(0, 42), 0);
		this.assertEquals(and.and(42, 11), 10);
		this.assertEquals(and.and(127, 3), 3);
	}

}
