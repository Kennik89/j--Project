// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

/**
 * The AST node for a catch clause declaration. All analysis and code
 * generation is done in a parent AST.
 */

class JCatchClause extends JAST {

    /** Catch clause formal parameter. */
    private JFormalParameter param;

    /** Catch clause block. */
    private JBlock block;

    /**
     * Construct an AST node for a catch clause declaration given its line
     * number, formal parameter declaration, and block.
     * 
     * @param line
     *            line in which the catch clause occurs in the source file.
     * @param param
     *            formal parameter.
     * @param block
     *            block.
     */

    public JCatchClause(int line, JFormalParameter param, JBlock block) {
        super(line);
        this.param = param;
        this.block = block;
    }

    /**
     * Return the formal parameter.
     * 
     * @return the formal parameter.
     */

    public JFormalParameter param() {
        return param;
    }

    /**
     * Return the block.
     * 
     * @return the block.
     */

    public JBlock block() {
        return block;
    }

    /**
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JAST analyze(Context context) {
        // TODO
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
        p.printf("<JCatchClause line=\"%d\">\n", line());
        p.indentRight();
        param.writeToStdOut(p);
        block.writeToStdOut(p);
        p.indentLeft();
        p.printf("</JCatchClause>\n");
    }

}
