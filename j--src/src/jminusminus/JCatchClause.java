// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

<<<<<<< HEAD
import static jminusminus.CLConstants.ASTORE;

=======
>>>>>>> AddDouble
/**
 * The AST node for a catch clause declaration. All analysis and code
 * generation is done in a parent AST.
 */

class JCatchClause extends JAST {

    /** Catch clause formal parameter. */
    private JFormalParameter param;

<<<<<<< HEAD
    /** Catch clause exception type. */
    private Type type;

=======
>>>>>>> AddDouble
    /** Catch clause block. */
    private JBlock block;

    /**
<<<<<<< HEAD
     * The new context and vardefn (built in analyze()) for the formal parameter.
     */
    private LocalContext context;
    private LocalVariableDefn vardefn;

    /**
=======
>>>>>>> AddDouble
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
<<<<<<< HEAD
     * Return the type.
     * 
     * @return the type.
     */

    public Type type() {
        return type;
    }

    /**
=======
>>>>>>> AddDouble
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
<<<<<<< HEAD
        type = param.type().resolve(context);
        if ((type != null) && (type.classRep() != null)) {
            if (!Type.THROWABLE.isJavaAssignableFrom(type)) {
                JAST.compilationUnit.reportSemanticError(line(),
                        "Catch type %s is not a subtype of %s", type, Type.THROWABLE);
            }
        }

        this.context = new LocalContext(context);
        this.vardefn = new LocalVariableDefn(type, this.context.nextOffset());
        this.vardefn.initialize();
        this.context.addEntry(param.line(), param.name(), this.vardefn);

        block = block.analyze(this.context);
=======
        // TODO
>>>>>>> AddDouble
        return this;
    }

    /**
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
<<<<<<< HEAD
        output.addOneArgInstruction(ASTORE, this.vardefn.offset());
        block.codegen(output);
=======
        // TODO
>>>>>>> AddDouble
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
