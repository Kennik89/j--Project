package jminusminus;

import static jminusminus.CLConstants.GOTO;

import junit.framework.Assert;

public class JForLoop extends JStatement {
	
    //private JStatementExpression for_decl;
	private JVariableDeclarator for_decl;
    private JExpression condition;
    private JStatementExpression incrementer;
    private JStatement body;
    
    
	public JForLoop(int line, JVariableDeclarator for_decl, JExpression condition, JStatementExpression incrementer, JStatement body) {
		super(line);
		//JStatement body)
		this.for_decl = for_decl;
		this.condition = condition;
		this.incrementer = incrementer;
		this.body = body;

	}

	/**
     * Analyzing the for loop means analyzing its components and checking
     * that the test is boolean.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JForLoop analyze(Context context) {
    	
    	for_decl = (JVariableDeclarator) for_decl.analyze(context);
    	if(!(for_decl.type() == Type.INT))
    		error_printer("For_Decl: Expected to find INT but did not");
      	
    	condition = condition.analyze(context);
    	if(!(condition.type() == Type.BOOLEAN))
    		error_printer("For_Condition: Expected to find Boolean but did not");

    	incrementer = (JStatementExpression) incrementer.analyze(context);

    	body = (JStatement) body.analyze(context);
    	
    	return this;
    }

    /**
     * Code generation for an if-statement. We generate code to branch over the
     * consequent if !test; the consequent is followed by an unconditonal branch
     * over (any) alternate.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
    	
        String condition_Label = output.createLabel();
        String endLabel = output.createLabel();

        output.addLabel(condition_Label);
        condition.codegen(output, endLabel, false);
        
        if(condition != null) {
            body.codegen(output);
            //incrementer.codegen(output);
            output.addBranchInstruction(GOTO, condition_Label);
        } else {
            output.addBranchInstruction(GOTO, endLabel);
        }
        output.addLabel(endLabel);    	
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForLoopStatement line=\"%d\">\n", line());
        p.indentRight();
        
        p.printf("<ForDeclaration>\n");
        p.indentRight();
        for_decl.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ForDeclaration>\n");

        p.printf("</Condition>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Condition>\n");
        
        p.printf("</Incrementer>\n");
        p.indentRight();
        incrementer.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Incrementer>\n");
        
        p.printf("</Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        
        p.indentLeft();
        p.printf("</JForLoopStatement>\n");
    }
    
    private void error_printer(String message) {
    	JAST.compilationUnit.reportSemanticError(line(), message);
    }
    
}
