package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * 
 * The AST Node for a Double Literal
 * @author Jesper Rytter JEnsen
 *
 */
class JLiteralDouble extends JExpression {
private String text;
	protected JLiteralDouble(int line,String text) {
		super(line);
		this.text = text;
		// TODO Auto-generated constructor stub
	}

	@Override
	public JExpression analyze(Context context) {
		type = Type.DOUBLE;
		return this;
	}

	@Override
	public void codegen(CLEmitter output) {
		// TODO Auto-generated method stub
		double d  = Double.parseDouble(text);
		
		if		(d==0.0) 	output.addNoArgInstruction(DCONST_0);
		else if	(d==1.0)	output.addNoArgInstruction(DCONST_1);
		else {
			output.addLDCInstruction(d);	
			
		}
	}

	@Override
	public void writeToStdOut(PrettyPrinter p) {
		 p.printf("<JLiteralDouble line=\"%d\" type=\"%s\" " + "value=\"%s\"/>\n",
	                line(), ((type == null) ? "" : type.toString()), text);
		
	}

}
