package fail;

import java.lang.Exception;

public class ExceptionThrows {
	
	public int exceptions1(int arg) throws MyException {
		return arg;
	}

    public int exceptions2(int arg) throws String {
        return arg;
    }

    public int exceptions3(int arg) throws Exception {
        return arg;
    }
    
}
