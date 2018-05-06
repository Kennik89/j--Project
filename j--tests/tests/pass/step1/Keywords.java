//package pass.step1;
//
//import java.lang.String;
//
////Keywords:
//// +abstract +boolean +break +byte +case +catch +char +class
//// +continue +default +do +double +else +extends +final +new
//// +finally +int +float +interface +for +long +native +short
//// +if +implements +package +import +private +instanceof +protected
//// +public +return +static +strictfp +super +switch -synchronized
//// +this +throw +throws +transient +try +void +volatile +while
//// ----const ----enum ----assert ----goto
//
//public abstract strictfp class Keywords extends Object implements Contract {
//	
//	public final static String t = "Hello World";
//	
//	public boolean n;
//	public byte b;
//	public char c;
//	public short s;
//	public int i;
//	public long l;
//	public float f;
//	public double d;
//	
//	public static volatile String vola;
//	private static transient String tran;
//	
//	Keywords() throws IllegalArgumentException {
//		super();
//
//		try {
//			if (t instanceof String) {
//				this.n = true;
//			}
//			else if (t == null) {
//				this.n = false;
//			}
//		}
//		catch (Exception e) {
//			throw new IllegalArgumentException(e);
//		}
//		finally {
//			for (i = 0; i != t.length(); i++) {
//				c = t.charAt(i);
//			}
//		}
//		
//	}
//
//	protected synchronized boolean method1(boolean arg1) {
//		do {
//			if (arg1) {
//				continue;
//			}
//		}
//		while (n);
//
//		return true;
//	}
//	
//	private byte method2(int arg2) {
//		switch (arg2) {
//			case 42:
//				break;
//			default:
//				break;
//		}
//		return (byte)0;
//	}
//	
//	native void method3();
//
//}
//
//interface Contract {
//	
//}
