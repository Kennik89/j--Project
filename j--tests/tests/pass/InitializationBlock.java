package pass;

public class InitializationBlock {
	
    public static int xz;
    public static int yz;
    
    static {
        xz = 3;
    }
    
    public static int zz = 5;

    static {
        yz = 4;
    }
    
    public int x;
    public int y;
    
    {
        x = -1;
    }
    
    public int z = 2;
    
    {
        y = 1;
    }

    public InitializationBlock() {
        super();
    }
    
    public InitializationBlock(int a) {
    }

}
