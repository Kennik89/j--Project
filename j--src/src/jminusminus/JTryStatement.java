// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;

/**
 * The AST node for a try-statement.
 */

class JTryStatement extends JStatement {

    /** Try block. */
    private JBlock tryBlock;

    /** The catch clauses. */
    protected ArrayList<JCatchClause> catchClauses;

    /** Finally block. */
    private JBlock finallyBlock;

    /**
     * Construct an AST node for a try-statement given its line number,
     * the try block, the catch clauses, and the finally block.
     * 
     * @param line
     *            line in which the try-statement occurs in the source file.
     * @param tryBlock
     *            try block.
     * @param catchClauses
     *            catch clauses.
     * @param finallyBlock
     *            finally block.
     */

    public JTryStatement(int line, JBlock tryBlock,
    			ArrayList<JCatchClause> catchClauses, JBlock finallyBlock) {
        super(line);
        this.tryBlock = tryBlock;
        this.catchClauses = catchClauses;
        this.finallyBlock = finallyBlock;
    }

    /**
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JStatement analyze(Context context) {
		// TODO
//        condition = (JExpression) condition.analyze(context);
//        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
//        thenPart = (JStatement) thenPart.analyze(context);
//        if (elsePart != null) {
//            elsePart = (JStatement) elsePart.analyze(context);
//        }
        return this;
    }

    /**
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
    		// TODO
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JTryStatement line=\"%d\">\n", line());
        p.indentRight();
        if (tryBlock != null) {
	        p.printf("<TryBlock>\n");
	        p.indentRight();
	        tryBlock.writeToStdOut(p);
	        p.indentLeft();
	        p.printf("</TryBlock>\n");
        }
        for (JCatchClause catchClause : catchClauses) {
        		catchClause.writeToStdOut(p);
        }
        if (finallyBlock != null) {
	        p.printf("<FinallyBlock>\n");
	        p.indentRight();
            finallyBlock.writeToStdOut(p);
	        p.indentLeft();
	        p.printf("</FinallyBlock>\n");
        }
        p.indentLeft();
        p.printf("</JTryStatement>\n");
    }

}
