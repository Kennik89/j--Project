package junit;
import junit.framework.TestCase;
import pass.PrefixAndPostfixOperators;

public class PostfixAndPrefixTest extends TestCase {
	private PrefixAndPostfixOperators prepostfix;
	private int x = 5;
	private int y = 2;
	
	protected void setUp() throws Exception{	
		super.setUp();
		prepostfix = new PrefixAndPostfixOperators();
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
	}
	
	public void testPostFix() {
		int[] XandY_postfixinc = prepostfix.PostfixInc(x, y);
		int[] XandY_postfixdec = prepostfix.PostfixDec(x, y);
		System.out.println(x + " " + y);
		assertEquals(XandY_postfixinc[0], 5);
		assertEquals(XandY_postfixinc[1], 6);
		assertEquals(XandY_postfixdec[1], 5);
		assertEquals(XandY_postfixdec[1], 4);
		
	}
	
	public void testPreFix() {
		int[] XandY_prefixinc = prepostfix.PrefixInc(x, y);
		int[] XandY_prefixdec = prepostfix.PrefixDec(x, y);
		
		assertEquals(XandY_prefixinc[0], 6);
		assertEquals(XandY_prefixinc[1], 6);
		assertEquals(XandY_prefixdec[1], 4);
		assertEquals(XandY_prefixdec[1], 4);

	}
	
}
