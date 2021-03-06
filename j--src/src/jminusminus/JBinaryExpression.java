// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a binary expression. A binary expression has an operator and
 * two operands: a lhs and a rhs.
 */

abstract class JBinaryExpression extends JExpression {

	/** The binary operator. */
	protected String operator;

	/** The lhs operand. */
	protected JExpression lhs;

	/** The rhs operand. */
	protected JExpression rhs;

	/**
	 * Construct an AST node for a binary expression given its line number, the
	 * binary operator, and lhs and rhs operands.
	 * 
	 * @param line
	 *            line in which the binary expression occurs in the source file.
	 * @param operator
	 *            the binary operator.
	 * @param lhs
	 *            the lhs operand.
	 * @param rhs
	 *            the rhs operand.
	 */

	protected JBinaryExpression(int line, String operator, JExpression lhs,
			JExpression rhs) {
		super(line);
		this.operator = operator;
		this.lhs = lhs;
		this.rhs = rhs;
	}

	/**
	 * @inheritDoc
	 */

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JBinaryExpression line=\"%d\" type=\"%s\" "
				+ "operator=\"%s\">\n", line(), ((type == null) ? "" : type
						.toString()), Util.escapeSpecialXMLChars(operator));
		p.indentRight();
		p.printf("<Lhs>\n");
		p.indentRight();
		lhs.writeToStdOut(p);
		p.indentLeft();
		p.printf("</Lhs>\n");
		p.printf("<Rhs>\n");
		p.indentRight();
		rhs.writeToStdOut(p);
		p.indentLeft();
		p.printf("</Rhs>\n");
		p.indentLeft();
		p.printf("</JBinaryExpression>\n");
	}

	//Binary expression helper functions
	protected void binaryCodegen(CLEmitter output,int action) {
		lhs.codegen(output);
		rhs.codegen(output);
		output.addNoArgInstruction(action);        
	}
	protected boolean lhsAndRhsIs(Type type) {
		return lhs.type()== type && rhs.type()== type;
	}
	
	protected void BinaryExpressionError() {
		type = Type.ANY;
		JAST.compilationUnit.reportSemanticError(line(),
				"Invalid operand types for "+operator);
	}

}

/**
 * The AST node for a plus (+) expression. In j--, as in Java, + is overloaded
 * to denote addition for numbers and concatenation for Strings.
 */

class JPlusOp extends JBinaryExpression {

	/**
	 * Construct an AST node for an addition expression given its line number,
	 * and the lhs and rhs operands.
	 * 
	 * @param line
	 *            line in which the addition expression occurs in the source
	 *            file.
	 * @param lhs
	 *            the lhs operand.
	 * @param rhs
	 *            the rhs operand.
	 */

	public JPlusOp(int line, JExpression lhs, JExpression rhs) {
		super(line, "+", lhs, rhs);
	}

	/**
	 * Analysis involves first analyzing the operands. If this is a string
	 * concatenation, we rewrite the subtree to make that explicit (and analyze
	 * that). Otherwise we check the types of the addition operands and compute
	 * the result type.
	 * 
	 * @param context
	 *            context in which names are resolved.
	 * @return the analyzed (and possibly rewritten) AST subtree.
	 */

	public JExpression analyze(Context context) {
		lhs = (JExpression) lhs.analyze(context);
		rhs = (JExpression) rhs.analyze(context);



		if(lhs.type() == Type.STRING || rhs.type() == Type.STRING)
			return (new JStringConcatenationOp(line, lhs, rhs)).analyze(context);	
		
		else if (lhsAndRhsIs(Type.INT)) 	type = Type.INT;		
		else if (lhsAndRhsIs(Type.DOUBLE)) 	type = Type.DOUBLE; 		
		else 	BinaryExpressionError();
		return this;
	}

	/**
	 * Any string concatenation has been rewritten as a JStringConcatenationOp
	 * (in analyze()), so code generation here involves simply generating code
	 * for loading the operands onto the stack and then generating the
	 * appropriate add instruction.
	 * 
	 * @param output
	 *            the code emitter (basically an abstraction for producing the
	 *            .class file).
	 */

	public void codegen(CLEmitter output) {

		if( type == Type.INT) super.binaryCodegen(output, IADD);
		else if ( type == Type.DOUBLE) super.binaryCodegen(output, DADD);
	}

}

/**
 * The AST node for a subtraction (-) expression.
 */

class JSubtractOp extends JBinaryExpression {

	/**
	 * Construct an AST node for a subtraction expression given its line number,
	 * and lhs and rhs operands.
	 * 
	 * @param line
	 *            line in which the subtraction expression occurs in the source
	 *            file.
	 * @param lhs
	 *            the lhs operand.
	 * @param rhs
	 *            the rhs operand.
	 */

	public JSubtractOp(int line, JExpression lhs, JExpression rhs) {
		super(line, "-", lhs, rhs);
	}

	/**
	 * Analyzing the - operation involves analyzing its operands, checking
	 * types, and determining the result type.
	 * 
	 * @param context
	 *            context in which names are resolved.
	 * @return the analyzed (and possibly rewritten) AST subtree.
	 */

	public JExpression analyze(Context context) {
		lhs = (JExpression) lhs.analyze(context);
		rhs = (JExpression) rhs.analyze(context);
		if		(lhsAndRhsIs(Type.INT)) 	type = Type.INT;
		else if	(lhsAndRhsIs(Type.DOUBLE)) 	type = Type.DOUBLE;
		else 	BinaryExpressionError();
		return this;
	}

	/**
	 * Generating code for the - operation involves generating code for the two
	 * operands, and then the subtraction instruction.
	 * 
	 * @param output
	 *            the code emitter (basically an abstraction for producing the
	 *            .class file).
	 */

	public void codegen(CLEmitter output) {
		if		(type == Type.INT) binaryCodegen(output, ISUB);
		else if (type == Type.DOUBLE) binaryCodegen(output, DSUB);
	}

}

/**
 * The AST node for a multiplication (*) expression.
 */

class JMultiplyOp extends JBinaryExpression {

	/**
	 * Construct an AST for a multiplication expression given its line number,
	 * and the lhs and rhs operands.
	 * 
	 * @param line
	 *            line in which the multiplication expression occurs in the
	 *            source file.
	 * @param lhs
	 *            the lhs operand.
	 * @param rhs
	 *            the rhs operand.
	 */

	public JMultiplyOp(int line, JExpression lhs, JExpression rhs) {
		super(line, "*", lhs, rhs);
	}

	/**
	 * Analyzing the * operation involves analyzing its operands, checking
	 * types, and determining the result type.
	 * 
	 * @param context
	 *            context in which names are resolved.
	 * @return the analyzed (and possibly rewritten) AST subtree.
	 */

	public JExpression analyze(Context context) {
		lhs = (JExpression) lhs.analyze(context);
		rhs = (JExpression) rhs.analyze(context);
		
		if		(lhsAndRhsIs(Type.INT)) 	type = Type.INT;
		else if (lhsAndRhsIs(Type.DOUBLE)) 	type = Type.DOUBLE;
		else	BinaryExpressionError();
		return this;
	}

	/**
	 * Generating code for the * operation involves generating code for the two
	 * operands, and then the multiplication instruction.
	 * 
	 * @param output
	 *            the code emitter (basically an abstraction for producing the
	 *            .class file).
	 */

	public void codegen(CLEmitter output) {
		if 		(type == Type.INT) binaryCodegen(output, IMUL);
		else if (type == Type.DOUBLE) binaryCodegen(output, DMUL);
	}

}

/**
 * The AST node for a division (/) expression.
 */

class JDivideOp extends JBinaryExpression {

	/**
	 * Construct an AST for a division expression given its line number,
	 * and the lhs and rhs operands.
	 * 
	 * @param line
	 *            line in which the division expression occurs in the
	 *            source file.
	 * @param lhs
	 *            the lhs operand.
	 * @param rhs
	 *            the rhs operand.
	 */

	public JDivideOp(int line, JExpression lhs, JExpression rhs) {
		super(line, "/", lhs, rhs);
	}

	/**
	 * Analyzing the / operation involves analyzing its operands, checking
	 * types, and determining the result type.
	 * 
	 * @param context
	 *            context in which names are resolved.
	 * @return the analyzed (and possibly rewritten) AST subtree.
	 */

	public JExpression analyze(Context context) {
		lhs = (JExpression) lhs.analyze(context);
		rhs = (JExpression) rhs.analyze(context);
		
		if		(lhsAndRhsIs(Type.INT))		type = Type.INT;
		else if (lhsAndRhsIs(Type.DOUBLE))	type = Type.DOUBLE;
		else	BinaryExpressionError();
		return this;
	}

	/**
	 * Generating code for the / operation involves generating code for the two
	 * operands, and then the division instruction.
	 * 
	 * @param output
	 *            the code emitter (basically an abstraction for producing the
	 *            .class file).
	 */

	public void codegen(CLEmitter output) {
		lhs.codegen(output);
		rhs.codegen(output);
		if		(type == Type.INT)		output.addNoArgInstruction(IDIV);
		else if (type == Type.DOUBLE)	output.addNoArgInstruction(DDIV);

	}

}

/**
 * The AST node for a remainder (%) expression.
 */

class JRemainderOp extends JBinaryExpression {

	/**
	 * Construct an AST for a remainder expression given its line number,
	 * and the lhs and rhs operands.
	 * 
	 * @param line
	 *            line in which the remainder expression occurs in the
	 *            source file.
	 * @param lhs
	 *            the lhs operand.
	 * @param rhs
	 *            the rhs operand.
	 */

	public JRemainderOp(int line, JExpression lhs, JExpression rhs) {
		super(line, "%", lhs, rhs);
	}

	/**
	 * Analyzing the % operation involves analyzing its operands, checking
	 * types, and determining the result type.
	 * 
	 * @param context
	 *            context in which names are resolved.
	 * @return the analyzed (and possibly rewritten) AST subtree.
	 */

	public JExpression analyze(Context context) {
		lhs = (JExpression) lhs.analyze(context);
		rhs = (JExpression) rhs.analyze(context);
		
		if		(lhsAndRhsIs(Type.INT))		type = Type.INT;
		else if (lhsAndRhsIs(Type.DOUBLE))	type = Type.DOUBLE;
		else	BinaryExpressionError();
		return this;
	}

	/**
	 * Generating code for the % operation involves generating code for the two
	 * operands, and then the remainder instruction.
	 * 
	 * @param output
	 *            the code emitter (basically an abstraction for producing the
	 *            .class file).
	 */

	public void codegen(CLEmitter output) {
		if 		(type == Type.INT)	binaryCodegen(output, IREM);	
		else if (type == Type.DOUBLE)	binaryCodegen(output, DREM);
	}

}

/**
 * The AST node for a ASL (<<) expression.
 */

class JASLOp extends JBinaryExpression {

	/**
	 * Construct an AST for a ASL expression given its line number,
	 * and the lhs and rhs operands.
	 * 
	 * @param line
	 *            line in which the ASL expression occurs in the
	 *            source file.
	 * @param lhs
	 *            the lhs operand.
	 * @param rhs
	 *            the rhs operand.
	 */

	public JASLOp(int line, JExpression lhs, JExpression rhs) {
		super(line, "<<", lhs, rhs);
	}

	/**
	 * Analyzing the << operation involves analyzing its operands, checking
	 * types, and determining the result type.
	 * 
	 * @param context
	 *            context in which names are resolved.
	 * @return the analyzed (and possibly rewritten) AST subtree.
	 */

	public JExpression analyze(Context context) {
		lhs = (JExpression) lhs.analyze(context);
		rhs = (JExpression) rhs.analyze(context);
		
		if 		(lhsAndRhsIs(Type.INT)) type = Type.INT;
		else 	BinaryExpressionError();
		return this;
	}

	/**
	 * Generating code for the << operation involves generating code for the two
	 * operands, and then the shl instruction.
	 * 
	 * @param output
	 *            the code emitter (basically an abstraction for producing the
	 *            .class file).
	 */

	public void codegen(CLEmitter output) {
		binaryCodegen(output, ISHL);
	}

}

/**
 * The AST node for a ASR (>>) expression.
 */

class JASROp extends JBinaryExpression {

	/**
	 * Construct an AST for a ASR expression given its line number,
	 * and the lhs and rhs operands.
	 * 
	 * @param line
	 *            line in which the ASR expression occurs in the
	 *            source file.
	 * @param lhs
	 *            the lhs operand.
	 * @param rhs
	 *            the rhs operand.
	 */

	public JASROp(int line, JExpression lhs, JExpression rhs) {
		super(line, ">>", lhs, rhs);
	}

	/**
	 * Analyzing the >> operation involves analyzing its operands, checking
	 * types, and determining the result type.
	 * 
	 * @param context
	 *            context in which names are resolved.
	 * @return the analyzed (and possibly rewritten) AST subtree.
	 */

	public JExpression analyze(Context context) {
		lhs = (JExpression) lhs.analyze(context);
		rhs = (JExpression) rhs.analyze(context);
		if 		(lhsAndRhsIs(Type.INT)) type = Type.INT;
		else	BinaryExpressionError();
		return this;
	}

	/**
	 * Generating code for the >> operation involves generating code for the two
	 * operands, and then the shr instruction.
	 * 
	 * @param output
	 *            the code emitter (basically an abstraction for producing the
	 *            .class file).
	 */

	public void codegen(CLEmitter output) {
		binaryCodegen(output, ISHR);
	}

}

/**
 * The AST node for a LSR (>>>) expression.
 */

class JLSROp extends JBinaryExpression {

	/**
	 * Construct an AST for a LSR expression given its line number,
	 * and the lhs and rhs operands.
	 * 
	 * @param line
	 *            line in which the LSR expression occurs in the
	 *            source file.
	 * @param lhs
	 *            the lhs operand.
	 * @param rhs
	 *            the rhs operand.
	 */

	public JLSROp(int line, JExpression lhs, JExpression rhs) {
		super(line, ">>>", lhs, rhs);
	}

	/**
	 * Analyzing the >>> operation involves analyzing its operands, checking
	 * types, and determining the result type.
	 * 
	 * @param context
	 *            context in which names are resolved.
	 * @return the analyzed (and possibly rewritten) AST subtree.
	 */

	public JExpression analyze(Context context) {
		lhs = (JExpression) lhs.analyze(context);
		rhs = (JExpression) rhs.analyze(context);
		
		if 		(lhsAndRhsIs(Type.INT)) type = Type.INT;
		else 	BinaryExpressionError();
		return this;
	}

	/**
	 * Generating code for the >>> operation involves generating code for the two
	 * operands, and then the ushr instruction.
	 * 
	 * @param output
	 *            the code emitter (basically an abstraction for producing the
	 *            .class file).
	 */

	public void codegen(CLEmitter output) {
		binaryCodegen(output, IUSHR);
	}

}

/**
 * The AST node for a bior (|) expression.
 */

class JBIOROp extends JBinaryExpression {

	/**
	 * Construct an AST for a bior expression given its line number,
	 * and the lhs and rhs operands.
	 * 
	 * @param line
	 *            line in which the bior expression occurs in the
	 *            source file.
	 * @param lhs
	 *            the lhs operand.
	 * @param rhs
	 *            the rhs operand.
	 */

	public JBIOROp(int line, JExpression lhs, JExpression rhs) {
		super(line, "|", lhs, rhs);
	}

	/**
	 * Analyzing the | operation involves analyzing its operands, checking
	 * types, and determining the result type.
	 * 
	 * @param context
	 *            context in which names are resolved.
	 * @return the analyzed (and possibly rewritten) AST subtree.
	 */

	public JExpression analyze(Context context) {
		lhs = (JExpression) lhs.analyze(context);
		rhs = (JExpression) rhs.analyze(context);
		if		(lhsAndRhsIs(Type.INT)) type = Type.INT;
		else 	BinaryExpressionError();
		return this;
	}

	/**
	 * Generating code for the | operation involves generating code for the two
	 * operands, and then the ior instruction.
	 * 
	 * @param output
	 *            the code emitter (basically an abstraction for producing the
	 *            .class file).
	 */

	public void codegen(CLEmitter output) {
		binaryCodegen(output, IOR);
	}

}

/**
 * The AST node for a bxor (^) expression.
 */

class JBXOROp extends JBinaryExpression {

	/**
	 * Construct an AST for a bxor expression given its line number,
	 * and the lhs and rhs operands.
	 * 
	 * @param line
	 *            line in which the bxor expression occurs in the
	 *            source file.
	 * @param lhs
	 *            the lhs operand.
	 * @param rhs
	 *            the rhs operand.
	 */

	public JBXOROp(int line, JExpression lhs, JExpression rhs) {
		super(line, "^", lhs, rhs);
	}

	/**
	 * Analyzing the ^ operation involves analyzing its operands, checking
	 * types, and determining the result type.
	 * 
	 * @param context
	 *            context in which names are resolved.
	 * @return the analyzed (and possibly rewritten) AST subtree.
	 */

	public JExpression analyze(Context context) {
		lhs = (JExpression) lhs.analyze(context);
		rhs = (JExpression) rhs.analyze(context);
		if		(lhsAndRhsIs(Type.INT)) type = Type.INT;
		else 	BinaryExpressionError();
		return this;
	}

	/**
	 * Generating code for the ^ operation involves generating code for the two
	 * operands, and then the ixor instruction.
	 * 
	 * @param output
	 *            the code emitter (basically an abstraction for producing the
	 *            .class file).
	 */

	public void codegen(CLEmitter output) {
		binaryCodegen(output, IXOR);
	}

}

/**
 * The AST node for a band (&) expression.
 */

class JBANDOp extends JBinaryExpression {

	/**
	 * Construct an AST for a band expression given its line number,
	 * and the lhs and rhs operands.
	 * 
	 * @param line
	 *            line in which the band expression occurs in the
	 *            source file.
	 * @param lhs
	 *            the lhs operand.
	 * @param rhs
	 *            the rhs operand.
	 */

	public JBANDOp(int line, JExpression lhs, JExpression rhs) {
		super(line, "&", lhs, rhs);
	}

	/**
	 * Analyzing the & operation involves analyzing its operands, checking
	 * types, and determining the result type.
	 * 
	 * @param context
	 *            context in which names are resolved.
	 * @return the analyzed (and possibly rewritten) AST subtree.
	 */

	public JExpression analyze(Context context) {
		lhs = (JExpression) lhs.analyze(context);
		rhs = (JExpression) rhs.analyze(context);
		if		(lhsAndRhsIs(Type.INT)) type = Type.INT;
		else	BinaryExpressionError();
		return this;
	}

	/**
	 * Generating code for the & operation involves generating code for the two
	 * operands, and then the iand instruction.
	 * 
	 * @param output
	 *            the code emitter (basically an abstraction for producing the
	 *            .class file).
	 */

	public void codegen(CLEmitter output) {
		binaryCodegen(output, IAND);
	}

}
