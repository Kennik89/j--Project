package junit;

import junit.framework.Test;
import junit.framework.TestSuite;

public class CompilerOutputTest {

	public static Test suite() {
		TestSuite suite = new TestSuite(CompilerOutputTest.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(AndTest.class);
		suite.addTestSuite(ClassesTest.class);
		suite.addTestSuite(DivisionTest.class);
		suite.addTestSuite(ExceptionHandlingTest.class);
		suite.addTestSuite(ExclusiveOrTest.class);
		suite.addTestSuite(FactorialTest.class);
		suite.addTestSuite(GCDTest.class);
		suite.addTestSuite(HelloWorldTest.class);
		suite.addTestSuite(InclusiveOrTest.class);
        suite.addTestSuite(InitializationBlockTest.class);
		suite.addTestSuite(LeftShiftTest.class);
		suite.addTestSuite(LogicalRightShiftTest.class);
		suite.addTestSuite(MultiplyTest.class);
		suite.addTestSuite(NotTest.class);
		suite.addTestSuite(RemainderTest.class);
		suite.addTestSuite(RightShiftTest.class);
		suite.addTestSuite(SeriesTest.class);
		suite.addTestSuite(SubtractTest.class);
		suite.addTestSuite(UnaryPlusTest.class);
		//$JUnit-END$
		return suite;
	}

}
