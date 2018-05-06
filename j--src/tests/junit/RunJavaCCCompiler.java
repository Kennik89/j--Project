package junit;

import java.io.File;
import java.io.IOException;

import jminusminus.JavaCCMain;

public class RunJavaCCCompiler {

	public static void main(String[] args) throws IOException {
		File output = new File(args[0]).getAbsoluteFile();
		File dst = new File(output, "gen");
		dst.mkdir();

		File workspace = output.getParentFile();
		File project = new File(workspace, "j--tests");
		File tests = new File(project, "tests");

		compile(dst, tests);
	}
	
	private static void compile(File dst, File src) {
		if (src.isDirectory()) {
			for (File file : src.listFiles()) {
//				if (!file.getName().contains("step")) {
					compile(dst, file);
//				}
			}
		}
		else if (src.isFile() && src.getName().endsWith(".java")) {
			System.out.printf("Running j-- (with javacc frontend) on %s ...\n\n", src.toString());
			JavaCCMain.main(new String[] {"-d", dst.getAbsolutePath(), src.getAbsolutePath()});
            System.out.printf("\n\n");
		}
	}

}
