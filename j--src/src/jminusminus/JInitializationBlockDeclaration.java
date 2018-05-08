// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;

/**
 * The AST node for a initialization block declaration.
 */

class JInitializationBlockDeclaration extends JAST implements JMember {

    /** Initialization block modifiers. */
    protected ArrayList<String> mods;

    /** InitializationBlock body. */
    protected JBlock body;

    /** Built in analyze(). */
    protected MethodContext context;

    /** Computed by preAnalyze(). */
    protected String descriptor;

    /** Is initialization block static. */
    protected boolean isStatic;

    /**
     * Construct an AST node for a initialization block declaration given the
     * line number and the initialization block body.
     * 
     * @param line
     *                line in which the initialization block declaration occurs
     *                in the source file.
     * @param mods
     *                modifiers.
     * @param body
     *                initialization block body.
     */

    public JInitializationBlockDeclaration(int line, ArrayList<String> mods, JBlock body)
    {
        super(line);
        this.mods = mods;
        this.body = body;
        this.isStatic = mods.contains("static");
    }

    /**
     * Return the list of modifiers.
     * 
     * @return list of modifiers.
     */

    public ArrayList<String> mods() {
        return mods;
    }

    /**
     * Declare this initialization block in the parent (class) context.
     * 
     * @param context
     *                the parent (class) context.
     * @param partial
     *                the code emitter (basically an abstraction
     *                for producing the partial class).
     */

    public void preAnalyze(Context context, CLEmitter partial) {
    }

    /**
     * Analysis for a initialization block declaration involves (1) creating a
     * new initialization block context (that records the return type; this is
     * used in the analysis of the initialization block body), (2) bumping up
     * the offset (for instance initialization blocks), (3) declaring the
     * formal parameters in the initialization block context, and (4) analyzing
     * the initialization block's body.
     * 
     * @param context
     *                context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JAST analyze(Context context) {
        MethodContext methodContext = 
        new MethodContext(context, isStatic, Type.VOID);
        this.context = methodContext;

        if (!isStatic) {
            // Offset 0 is used to address "this".
            this.context.nextOffset();
        }

        if (body != null) {
            body = body.analyze(this.context);
        }
        return this;
    }

    /**
     * Generate code for any initialization block.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegenInitializations(CLEmitter output) {
        if (body != null) {
            body.codegen(output);
        }
    }

    /**
     * Generate code for the initialization block declaration.
     * 
     * @param output
     *                the code emitter (basically an abstraction
     *                for producing the .class file).
     */

    public void codegen(CLEmitter output) {
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInitializationBlockDeclaration line=\"%d\">\n", line());
        p.indentRight();
        if (context != null) {
            context.writeToStdOut(p);
        }
        if (mods != null) {
            p.println("<Modifiers>");
            p.indentRight();
            for (String mod : mods) {
                p.printf("<Modifier name=\"%s\"/>\n", mod);
            }
            p.indentLeft();
            p.println("</Modifiers>");
        }
        if (body != null) {
            p.println("<Body>");
            p.indentRight();
            body.writeToStdOut(p);
            p.indentLeft();
            p.println("</Body>");
        }
        p.indentLeft();
        p.println("</JInitializationBlockDeclaration>");
    }

}
