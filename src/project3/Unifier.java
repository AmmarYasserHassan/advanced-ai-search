package project3;

import java.util.ArrayList;

public class Unifier {
	
	public static void main(String [] args){
//		String test = "p(X,Y)";
//		System.out.println(isConstant(test));
//		System.out.println(isVariable(test));
//		System.out.println(isPredicate(test));
		
		ArrayList<String> vars = new ArrayList<String>();
		
		vars.add("X");
		vars.add("Y");
		
		Literal l = new Literal("g",vars);
		
//		ArrayList<String> vars2 = new ArrayList<String>();
//		vars2.add("a");
//		vars2.add("b");
//		vars2.add("c");
//		Literal l2 = new Literal("f",vars2);
		Literal l2 = new Literal("Z",true,false,false);
		
		ArrayList<String> vars3 = new ArrayList<String>();
		vars3.add("a");
		vars3.add("a");
		vars3.add("Z");
		Literal l3 = new Literal("f",vars3);
//		Literal l3 = new Literal("X",true,false,false);
		
		ArrayList<String> vars4 = new ArrayList<String>();
		vars4.add(l.toString());
		vars4.add(l2.toString());
		Literal l4 = new Literal("p",vars4);
		

		System.out.println(l2.toString());
		System.out.println(l3.toString());
		System.out.println(unify(l2,l3));
		
	}
	
	
	public static boolean unifyPredicates(String e1, String e2)
	{
		if(isConstant(e1) && isConstant(e2))
			return e1.equals(e2);
		
		if(isConstant(e1) && isVariable(e2))
			return true;
		
		if(isVariable(e1) && isConstant(e2))
			return true;
		
		if(isVariable(e1) && isVariable(e2))
			return true;
		
		// occurence check
		if(isPredicate(e1) && isVariable(e2))
			return true;
		
		// occurence check
		if(isVariable(e1) && isPredicate(e2.toString()))
			return true;
		
		// occurence check
		if(isPredicate(e1) && isPredicate(e2))
					return true;
		
		return false;
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
			if(!e1.arity.contains(e2.name))
			return true;
		}

		if(isPredicate(e2.toString()) && isVariable(e1.toString()))
		{   
			if(!e2.arity.contains(e1.name))
			return true;
		}
		
//		// occurence check
//		if(isPredicate(e1.toString()) && isPredicate(e2.toString()))
//		{
//			if(e1.name.equals(e2.name))
//			{
//				if(e1.arity.size() == e2.arity.size())
//				{
//					for(int i = 0; i<e1.arity.size();i++)
//					{
//						if(!unifyPredicates(e1.arity.get(i), e2.arity.get(i)))
//							return false;
//					}
//					return true;
//				}
//			}
//		}
		
		return false;
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
}
