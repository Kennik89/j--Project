package pass;

import java.io.IOException;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.ArithmeticException;
import java.lang.NullPointerException;
import java.lang.RuntimeException;

public class ExceptionHandling {
	
    public int method1(Integer a, Integer b) throws IOException {
        int c = 0;

        try {
            c = a.intValue() / b.intValue();
        }
        catch (ArithmeticException e) {
            c = 100;
        }
        catch (NullPointerException e) {
            c = 200;
        }
        finally {
            c += 1000;
        }
        return c;
    }

    public int method2(Integer a, Integer b) throws IOException {
        int c = 0;

        try {
            c = a.intValue() / b.intValue();
        }
        catch (ArithmeticException e) {
            c = 100;
        }
        catch (NullPointerException e) {
            c = 200;
        }
        return c;
    }

    public int method3(Integer a, Integer b) throws IOException {
        int c = 0;

        try {
            c = a.intValue() / b.intValue();
        }
        catch (ArithmeticException e) {
            c = 100;
        }
        catch (NullPointerException e) {
            c = 200;
        }
        finally {
            c += 1000;
            c = "".codePointAt(c);
        }
        return c;
    }

    public int method4(Integer a, Integer b) throws IOException {
        int c = 0;

        try {
            c = a.intValue() / b.intValue();
            c = "".codePointAt(c);
        }
        catch (ArithmeticException e) {
            c = 100;
            c = "".codePointAt(c);
        }
        catch (NullPointerException e) {
            c = 200;
            c = "".codePointAt(c);
        }
        return c;
    }

    public int exceptions(int arg) throws Exception {
		try {
			while(arg <= 0) {
				arg = arg + 1;
			}
		}
		catch (RuntimeException e) {
			throw new Exception();
		}
		finally {
			if (arg <= 2) {
				throw new Exception();
			}
		}
		if (arg <= 2) {
			throw new Exception();
		}
		return arg;
	}

}
