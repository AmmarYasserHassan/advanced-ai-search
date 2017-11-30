package project3;

import java.util.ArrayList;

public class Unifier {
	
	public static void main(String [] args){
		
		Literal varX = new Literal("X",false,true,false);
		Literal varY = new Literal("Y",false,true,false);
		Literal varZ = new Literal("Z",false,true,false);
		
		Literal consA = new Literal("a",true,false,false);
		Literal consB = new Literal("b",true,false,false);
		Literal consC = new Literal("c",true,false,false);
		
		ArrayList<Literal> vars = new ArrayList<Literal>();
		vars.add(varX);
		vars.add(varY);
		
		Literal l = new Literal("g",vars);
		
		ArrayList<Literal> vars2 = new ArrayList<Literal>();
		vars2.add(consA);
		Literal l2 = new Literal("f",vars2);
		
		ArrayList<Literal> vars3 = new ArrayList<Literal>();
		vars3.add(l2);
		Literal l3 = new Literal("h",vars3);
		
		ArrayList<Literal> vars4 = new ArrayList<Literal>();
		vars4.add(l);
		vars4.add(l2);
		Literal l4 = new Literal("p",vars4);
		
//		System.out.println(l);
//		System.out.println(l2);
//		System.out.println(l3);
//		System.out.println(l4);
		
		System.out.println(unify(varX,l));
	}
	
	
	public static boolean unify(Literal e1, Literal e2)
	{
		if(isConstant(e1.toString()) && isConstant(e2.toString()))
			return e1.toString().equals(e2.toString());
		
		if(isConstant(e1.toString()) && isVariable(e2.toString()))
			return true;
		
		if(isVariable(e1.toString()) && isConstant(e2.toString()))
			return true;
		
		if(isVariable(e1.toString()) && isVariable(e2.toString()))
			return true;
		
		
		if(isPredicate(e1.toString()) && isVariable(e2.toString()))
		{    
			if(!e1.occurs(e2.name))
			return true;
		}

		if(isPredicate(e2.toString()) && isVariable(e1.toString()))
		{  
			if(!e2.occurs(e1.name))
			return true;
		}
		
		if(isPredicate(e1.toString()) && isPredicate(e2.toString()))
		{
			if(e1.name.equals(e2.name))
			{
				if(e1.arity.size() == e2.arity.size())
				{
					for(int i = 0; i<e1.arity.size();i++)
					{
						if(!unify(e1.arity.get(i), e2.arity.get(i)))
							return false;
					}
					return true;
				}
			}
		}
		
		return false;
	}

	public static String getNameOfPredicate(String folTerm)
	{
		String name = "";
		
		for (int i = 0; i < folTerm.length(); i++) {
			if(folTerm.charAt(i)=='(')
				break;
			name+=folTerm.charAt(i);
		}
		return name;
	}
	
	public static boolean isConstant(String statement)
	{
		if(!statement.contains("("))
			if(Character.isLowerCase(statement.charAt(0)))
				return true;
		return false;
	}
	
	public static boolean isVariable(String statement)
	{
		if(!statement.contains("("))
			if(!Character.isLowerCase(statement.charAt(0)))
				return true;
		return false;
	}
	
	public static boolean isPredicate(String statement)
	{
		if(statement.contains("("))
			return true;
		return false;
	}
	
	public static void getNestedPredicate(String s)
	{
		System.out.println(s.lastIndexOf("("));
		System.out.println(s.indexOf(")"));
	}
}
