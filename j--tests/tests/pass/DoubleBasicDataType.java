//use in  package pass.step2
package pass;

import java.lang.System;

public class DoubleBasicDataType {
	
	public double methodReturn() {
		reutrn 6.0;
	}
	
	public double assign(double x) {
		double val = x;
		return val;
	}
	
	public double plusAssign(double start,double assign) {
		double x = start;
		x+=assign;
		return x;
	}
	
	public double minusAssign(double start, double assign) {
		double x = start;
		x -= assign;
		return x;
	}
	
	public double multiplyAssign(double start, double assign) {
		double x = start;
		x *= assign;
		return x;
	}
	
	public double divideAssign(double start, double assign) {
		double x = start;
		x/=assign;
		return x;
	}
	
	public double remainderAssign(double start, double assign) {
		double x = start;
		x %= assign;
		return x;
	}
	
	public double addition(double x, double y) {return x + y;}
	
	public double subtract(double x, double y) {return x-y;}
	
	public double multiply(double x, double y) {return x * y;}

	public double divide(double x, double y) { return x/y;}
	
	public double remainder(double mod, double value) {	return mod % value;	}

	public static void main(String[] args) {
		
		double a = 1.5;
		double b=5.0;
		System.out.println(a+b);

	}

}
