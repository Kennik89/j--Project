package fail;

import java.lang.Exception;

public class ExceptionCatch {
	
    public int exceptions4(int arg) throws Exception {
        try {
        }
        catch (MyException e) {
        }
        catch (String e) {
        }
        catch (Exception e) {
        }
        return arg;
    }

    public int exceptions5(int arg) throws Exception {
        try {
        }
        return arg;
    }

}
