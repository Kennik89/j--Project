/*package pass.step2;

public class ExceptionHandling {
	
	public static int exceptions(int arg) throws Exception {
		try {
			while(arg <= 0) {
				arg = arg + 1;
			}
		}
		catch (RuntimeException e) {
			throw new Exception(e);
		}
		finally {
			if (arg <= 2) {
				throw new Exception();
			}
		}
		return arg;
	}

}
*/