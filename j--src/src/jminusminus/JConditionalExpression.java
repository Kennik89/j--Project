package jminusminus;

import static jminusminus.CLConstants.GOTO;

public class JConditionalExpression extends JExpression {
	private JExpression thenPart;
	private JExpression elsePart;
	private JExpression cond;
	public JConditionalExpression(int line, JExpression cond, JExpression lhs, JExpression rhs) {
		super(line);
		this.thenPart = lhs;
		this.elsePart = rhs;
		this.cond = cond;
		// TODO Auto-generated constructor stub
	}

	@Override
	public JExpression analyze(Context context) {
		cond  = cond.analyze(context);

		cond.type().mustMatchExpected(line(), Type.BOOLEAN);

		thenPart =thenPart.analyze(context);
		elsePart = elsePart.analyze(context);
		type = thenPart.type();
		
		if(thenPart.type() != elsePart.type()) {
			JAST.compilationUnit.reportSemanticError(line, "conditional selection statements are nof same type:");
			type = Type.ANY;
		}
		
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see jminusminus.JAST#codegen(jminusminus.CLEmitter)
	 * Unlike an if with an optional else, A conditional expression
	 * all ways have an else option
	 */
	@Override
	public void codegen(CLEmitter output) {
		String elseLabel = output.createLabel();
		String endLabel = output.createLabel();
		cond.codegen(output, elseLabel, false);
		thenPart.codegen(output);
		output.addBranchInstruction(GOTO, endLabel);		
		output.addLabel(elseLabel);
		elsePart.codegen(output);
		output.addLabel(endLabel);

		// TODO Auto-generated method stub

	}

	@Override
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JConditionalExpression line=\"%d\">\n", line());
		p.indentRight();
		p.printf("<TestExpression>\n");
		p.indentRight();
		cond.writeToStdOut(p);
		p.indentLeft();
		p.printf("</TestExpression>\n");
		p.printf("<ThenClause>\n");
		p.indentRight();
		thenPart.writeToStdOut(p);
		p.indentLeft();
		p.printf("</ThenClause>\n");
		p.printf("<ElseClause>\n");
		p.indentRight();
		elsePart.writeToStdOut(p);
		p.indentLeft();
		p.printf("</ElseClause>\n");
		p.indentLeft();
		p.printf("</JConditionalExpression>\n");
		// TODO Auto-generated method stub

	}

}
