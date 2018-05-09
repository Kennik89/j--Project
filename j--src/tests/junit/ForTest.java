package junit;

import junit.framework.TestCase;
import pass.For;

public class ForTest extends TestCase {
	private For fortest;
	
	protected void setUp() throws Exception{	
		super.setUp();
		fortest = new For();
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
	}
	
	public void testFor() {
		int test = fortest.testfor();
		assertEquals(test, 11);
		
	}
	
}
