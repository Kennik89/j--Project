package junit;

import junit.framework.TestCase;
import pass.InclusiveOr;

public class InclusiveOrTest extends TestCase {
	private InclusiveOr ior;
	
	protected void setUp() throws Exception {
		super.setUp();
		ior = new InclusiveOr();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDivide() {
		this.assertEquals(ior.ior(0, 42), 42);
		this.assertEquals(ior.ior(42, 11), 43);
		this.assertEquals(ior.ior(127, 3), 127);
	}

}
