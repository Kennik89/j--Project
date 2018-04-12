// Copyright 2011 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

/**
 * The AST node for a throw-statement.
 */

class JThrowStatement
    extends JStatement {

    /** The thrown expression. */
    private JExpression expr;

    /**
     * Construct an AST node for a throw-statement given its
     * line number, and the expression that is thrown.
     * 
     * @param line
     *                line in which the throw-statement appears
     *                in the source file.
     * @param expr
     *                the thrown expression.
     */

    public JThrowStatement(int line, JExpression expr) {
        super(line);
        this.expr = expr;
    }

    /**
     * @param context
     *                context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JStatement analyze(Context context) {
        // TODO
        return this;
    }

    /**
     * @param output
     *                the code emitter (basically an abstraction
     *                for producing the .class file).
     */

    public void codegen(CLEmitter output) {
        // TODO
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JThrowStatement line=\"%d\">\n", line());
        p.indentRight();
        expr.writeToStdOut(p);
        p.indentLeft();
        p.printf("</JThrowStatement>\n");
    }
}
