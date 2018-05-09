// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package junit;

import junit.framework.TestCase;
import pass.InitializationBlock;

public class InitializationBlockTest extends TestCase {
    private InitializationBlock ib;
    
    protected void setUp() throws Exception {
        super.setUp();
        ib = new InitializationBlock();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testInstanceInit() {
        this.assertEquals(3, InitializationBlock.xz);
        this.assertEquals(4, InitializationBlock.yz);
        this.assertEquals(5, InitializationBlock.zz);
        this.assertEquals(-1, ib.x);
        this.assertEquals(1, ib.y);
        this.assertEquals(2, ib.z);
    }

}
