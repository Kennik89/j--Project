package fail;

import java.lang.Exception;

public class ExceptionThrow {
	
    public int exceptions4(int arg) throws Exception {
        //throw new MyException();
        throw new String();
        throw new Exception();
        return arg;
    }

}
