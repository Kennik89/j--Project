package junit;

import java.io.IOException;

import junit.framework.TestCase;
import pass.ExceptionHandling;

public class ExceptionHandlingTest extends TestCase {
	private ExceptionHandling ex;
	
	protected void setUp() throws Exception {
		super.setUp();
		ex = new ExceptionHandling();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testExceptionHandling() throws IOException {
		try {
			ex.exceptions(2);
			this.fail();
		}
		catch (Exception e) {
		}
		try {
			ex.exceptions(3);
		}
		catch (Exception e) {
			this.fail();
		}
		
        this.assertEquals(1001, ex.method1(Integer.valueOf(1), Integer.valueOf(1)));
        this.assertEquals(1100, ex.method1(Integer.valueOf(1), Integer.valueOf(0)));
        this.assertEquals(1200, ex.method1(null, Integer.valueOf(1)));
        
        this.assertEquals(2, ex.method2(Integer.valueOf(2), Integer.valueOf(1)));
        this.assertEquals(100, ex.method2(Integer.valueOf(2), Integer.valueOf(0)));
        this.assertEquals(200, ex.method2(null, Integer.valueOf(1)));
        
        try {
            ex.method3(Integer.valueOf(1), Integer.valueOf(1));
            this.fail();
        }
        catch (StringIndexOutOfBoundsException e) {
            this.assertTrue(e.getMessage().contains("index 1001"));
        }
        try {
            ex.method3(Integer.valueOf(1), Integer.valueOf(0));
            this.fail();
        }
        catch (StringIndexOutOfBoundsException e) {
            this.assertTrue(e.getMessage().contains("index 1100"));
        }
        try {
            ex.method3(null, Integer.valueOf(1));
            this.fail();
        }
        catch (StringIndexOutOfBoundsException e) {
            this.assertTrue(e.getMessage().contains("index 1200"));
        }
        
        try {
            ex.method4(Integer.valueOf(1), Integer.valueOf(1));
            this.fail();
        }
        catch (StringIndexOutOfBoundsException e) {
            this.assertTrue(e.getMessage().contains("index 1"));
        }
        try {
            ex.method4(Integer.valueOf(1), Integer.valueOf(0));
            this.fail();
        }
        catch (StringIndexOutOfBoundsException e) {
            this.assertTrue(e.getMessage().contains("index 100"));
        }
        try {
            ex.method4(null, Integer.valueOf(1));
            this.fail();
        }
        catch (StringIndexOutOfBoundsException e) {
            this.assertTrue(e.getMessage().contains("index 200"));
        }
	}

}
