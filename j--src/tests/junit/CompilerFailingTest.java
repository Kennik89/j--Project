// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package junit;

import java.io.File;
import junit.framework.TestCase;
import jminusminus.Main;

/**
 * JUnit test case for running the j-- compiler on the j-- test programs under
 * files/fail folder.
 */

public class CompilerFailingTest extends TestCase {

    /**
     * Construct a CompilerFailingTest object.
     */

    public CompilerFailingTest() {
        super("JUnit test case for the j-- compiler");
    }

    /**
     * Run the j-- compiler against each fail-test file under the folder
     * specified by FAIL_TESTS_DIR property in the build.xml file. FRONT_END
     * property determines the frontend (handwritten or JavaCC) to use.
     */

    public void testFail() {
        File failTestsDir = new File(System.getProperty("FAIL_TESTS_DIR"));
        File genClassDir = new File(System.getProperty("GEN_CLASS_DIR"));
        File[] files = failTestsDir.listFiles();
        boolean errorHasOccurred = true;
        for (int i = 0; files != null && i < files.length; i++) {
            if (files[i].toString().endsWith(".java")) {
                String[] args = null;
                System.out.printf("Running j-- (with "
                        + "handwritten frontend) on %s ...\n\n", files[i]
                        .toString());
                args = new String[] { "-d", genClassDir.getAbsolutePath(),
                        files[i].toString() };
                Main.main(args);
                System.out.printf("\n\n");

                // true only if all tests fail
                errorHasOccurred &= Main.errorHasOccurred();
            }
        }

        // We want all tests to fail
        assertTrue(errorHasOccurred);
    }

    /**
     * Entry point.
     * 
     * @param args
     *            command-line arguments.
     */

    public static void main(String[] args) {
        junit.textui.TestRunner.run(CompilerFailingTest.class);
    }

}
