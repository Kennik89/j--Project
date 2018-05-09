// Copyright 2011 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.ATHROW;

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
<<<<<<< HEAD
     * Analyze throw statement by checking that the expr is of type Throwable
     * 
=======
>>>>>>> AddDouble
     * @param context
     *                context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JStatement analyze(Context context) {

        expr = (JExpression) expr.analyze(context);
        Type type = expr.type();
        if ((type != null) && (type.classRep() != null)) {
            if (!Type.THROWABLE.isJavaAssignableFrom(type)) {
                JAST.compilationUnit.reportSemanticError(line(),
                        "Throw type %s is not a subtype of %s", type, Type.THROWABLE);
            }
        }

        return this;
    }

    /**
     * @param output
     *                the code emitter (basically an abstraction
     *                for producing the .class file).
     */

    public void codegen(CLEmitter output) {

        expr.codegen(output);
        output.addNoArgInstruction(ATHROW);

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
