package junit;

import junit.framework.TestCase;
import pass.DoubleBasicDataType;
public class DoubleTypeTest extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	// methods
	public void testMethodReturn() {
		DoubleBasicDataType d = new DoubleBasicDataType();
		try {
			// return value is all ways 0.0
			assertEquals(0.0,d.methodReturn());
		}catch(Exception e) {
			super.fail("method double return type not accepted");
		}
	}
	public void testAcceptDoubleParams() {
		DoubleBasicDataType d = new DoubleBasicDataType();
		try {
			assertEquals(true,d.acceptMethodParams(4.33, 3.111));
		}catch(Exception e) {
			super.fail("two method params not accepted");
		}
	}
	
//	public void testCorrectParamValues() {
//		DoubleBasicDataType d = new DoubleBasicDataType();
//		try {
//			double expected1 = 783.8,expected2 = 3.232;
//			
//			double actual = d.correctMethodsParamValue(expected1);
//			
//			assertEquals(expected1, actual);
//			
//			double[] actuals = d.correctMethodsParamsValues(expected1, expected2);
//			
//			assertEquals(expected1, actuals[0]);
//			assertEquals(expected2, actuals[1]);
//		}catch(Exception e) {
//			super.fail("two method params not accepted");
//		}
//	}
	
	// assignments ######################################
	public void testAssignment() {
		DoubleBasicDataType d = new DoubleBasicDataType();
		double expected = 5.7;
		double  actual= d.assign(5.7);
		this.assertEquals(expected, actual);		
	}
	
	public void testPlusAssign() {
		DoubleBasicDataType d= new DoubleBasicDataType();
		double lhs = 3.6;
		double rhs = 6.33;
		double expected = lhs + rhs;
		double actual =d.plusAssign(lhs, rhs);
		
		this.assertEquals(expected, actual);
	}
	
	public void testMinusAssign() {
		DoubleBasicDataType d= new DoubleBasicDataType();
		double lhs = 15.11;
		double rhs = 3.009;
		double expected = lhs - rhs;
		double actual = d.minusAssign(lhs, rhs);
		assertEquals(expected, actual);
	}
	
	public void testMultplyAssign() {
		DoubleBasicDataType d= new DoubleBasicDataType();
		double lhs = 3.6;
		double rhs = 6.33;
		double expected = lhs * rhs;
		double actual = d.multiplyAssign(lhs, rhs);
		assertEquals(expected, actual);
	}
	
	public void testDivisionAssign() {
		DoubleBasicDataType d= new DoubleBasicDataType();
		double lhs = 3.6;
		double rhs = 6.33;
		double expected = lhs / rhs;
		double actual = d.divideAssign(lhs, rhs);
		assertEquals(expected, actual);
	}
	
	public void testRemainderAssign() {
		DoubleBasicDataType d= new DoubleBasicDataType();
		double lhs = 3.6;
		double rhs = 6.33;
		double expected = lhs % rhs;
		double actual = d.remainderAssign(lhs, rhs);
		assertEquals(expected, actual);
	}
	
	
	// simple math
	public void testPlus() {
		DoubleBasicDataType d= new DoubleBasicDataType();
		double lhs = 3.6;
		double rhs = 6.33;
		double expected = lhs + rhs;
		double actual =d.addition(lhs, rhs);
		
		assertEquals(expected, actual);
	}
	
	public void testMinus() {
		DoubleBasicDataType d= new DoubleBasicDataType();
		double lhs = 15.11;
		double rhs = 3.009;
		double expected = lhs - rhs;
		double actual = d.subtract(lhs, rhs);
		assertEquals(expected, actual);
	}
	
	public void testMultply() {
		DoubleBasicDataType d= new DoubleBasicDataType();
		double lhs = 3.6;
		double rhs = 6.33;
		double expected = lhs * rhs;
		double actual = d.multiply(lhs, rhs);
		assertEquals(expected, actual);
	}
	
	public void testDivision() {
		DoubleBasicDataType d= new DoubleBasicDataType();
		double lhs = 3.6;
		double rhs = 6.33;
		double expected = lhs / rhs;
		double actual = d.divide(lhs, rhs);
		assertEquals(expected, actual);
	}
	
	public void testRemainder() {
		DoubleBasicDataType d= new DoubleBasicDataType();
		double lhs = 3.6;
		double rhs = 6.33;
		double expected = lhs % rhs;
		double actual = d.remainder(lhs, rhs);
		assertEquals(expected, actual);
	}
	
}
