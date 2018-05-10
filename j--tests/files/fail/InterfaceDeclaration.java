package fail;

import java.lang.Object;

public abstract interface InterfaceDeclaration extends Object{ // Wrong: Interface already is an abstract file + cannot extends a class

	public interfaceDeclaration()	{ // Wrong: Interface cannot contains a constructor
		this;
	}
	
	void foo(int x)	{	//Wrong: Interface only has the abstract methods
		String line = "hello";
	}
	
	int bar(int x)	{	//Wrong: Interface only has the abstract methods
		return x;
	}
	
	String s = new String();	// Interface cannot instantiate new variable
	
}
