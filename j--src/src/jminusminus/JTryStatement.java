// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.ALOAD;
import static jminusminus.CLConstants.ASTORE;
import static jminusminus.CLConstants.ATHROW;

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
     * The new context and offset (built in analyze()) for the throw on part.
     */
    private LocalContext context;
    private int nextOffset;

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

        tryBlock = tryBlock.analyze(context);
        boolean hasCatch = false;
        if (catchClauses != null) {
            for (JCatchClause catchClause : catchClauses) {
                catchClause.analyze(context);
                hasCatch = true;
            }
        }
        if (finallyBlock != null) {
            finallyBlock = finallyBlock.analyze(context);
        }
        else if (!hasCatch) {
            JAST.compilationUnit.reportSemanticError(line(),
                    "Try statement with no finally statement must have at least one catch clause");
        }
        this.context = new LocalContext(context);
        this.nextOffset = this.context.nextOffset();

        return this;
    }

    /**
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {

        String tryBegin = "tryBegin_" + line();
        String tryEnd = "tryEnd_" + line();
        String finallyBegin = "finallyBegin_" + line();
        String finallyEnd = "finallyEnd_" + line();
        output.addLabel(tryBegin);
        tryBlock.codegen(output);
        output.addLabel(tryEnd);
        if (finallyBlock != null) {
            finallyBlock.codegen(output);
        }
        output.addBranchInstruction(CLConstants.GOTO, finallyEnd);
        
        if (catchClauses != null) {
            int index = 1;
            for (JCatchClause catchClause : catchClauses) {
                String catchBegin = "catchBegin_" + line() + "_" + catchClause.param().type().simpleName() + "_" + index;
                String catchEnd = "catchEnd_" + line() + "_" + catchClause.param().type().simpleName() + "_" + index;
                index++;
                output.addLabel(catchBegin);
                catchClause.codegen(output);
                output.addLabel(catchEnd);
                if (finallyBlock != null) {
                    finallyBlock.codegen(output);
                }
                output.addBranchInstruction(CLConstants.GOTO, finallyEnd);
                output.addExceptionHandler(tryBegin, tryEnd, catchBegin, catchClause.type().jvmName());
                output.addExceptionHandler(catchBegin, catchEnd, finallyBegin, null);
            }
        }

        output.addLabel(finallyBegin);
        output.addOneArgInstruction(ASTORE, this.nextOffset);
        if (finallyBlock != null) {
            finallyBlock.codegen(output);
        }
        output.addOneArgInstruction(ALOAD, this.nextOffset);
        output.addNoArgInstruction(ATHROW);
        output.addExceptionHandler(tryBegin, tryEnd, finallyBegin, null);
        output.addLabel(finallyEnd);

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
