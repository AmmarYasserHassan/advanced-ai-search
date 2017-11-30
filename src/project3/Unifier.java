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
		
		ArrayList<Literal> vars5 = new ArrayList<Literal>();
		vars5.add(consA);
		Literal f1 = new Literal("f1",vars5);
		
		ArrayList<Literal> vars6 = new ArrayList<Literal>();
		vars6.add(consA);
		vars6.add(consB);
		Literal f2 = new Literal("f1",vars6);
		
		ArrayList<Literal> vars3 = new ArrayList<Literal>();
		vars3.add(l2);
		Literal l3 = new Literal("h",vars3);
		
		ArrayList<Literal> vars7 = new ArrayList<Literal>();
		vars7.add(varZ);
		vars7.add(varY);
		Literal h_x = new Literal("p",vars7);
		
		ArrayList<Literal> vars4 = new ArrayList<Literal>();
		vars4.add(l);
		vars4.add(l3);
		Literal l4 = new Literal("p",vars4);
		
//		System.out.println(l);
//		System.out.println(l2);
//		System.out.println(l3);
//		System.out.println(l4);
		
		
		ArrayList<String> subs = new ArrayList<String>();
		System.out.println(h_x);
		System.out.println(l4);
		System.out.println(unify(l4,h_x,subs));
		getSigma(subs);
		System.out.println(subs);
		

		
	}
	
	public static void getSigma(ArrayList<String> subs)
	{
		ArrayList<String> varsWithSubs = new ArrayList<String>();
		
		for(String str : subs)
		{   
			String var = str.split("/")[1];
			if(!varsWithSubs.contains(var))
			varsWithSubs.add(var);
		}
		
		
		//TODO if Consistent
		for(String var: varsWithSubs)
		{
			propagate(subs, var,getSubstitution(subs, var) );
		}
		
		
	}
	
	public static void propagate(ArrayList<String> subs,String var,String sub)
	{
		for(int i=0; i<subs.size();i++)
		{  
		 if(subs.get(i).split("/")[0].contains(var))
		 {
		 String str = subs.get(i).replace(var,sub);
		 subs.remove(i);
		 subs.add(i,str);
		 }			
		}
	}
	
	
	public static boolean unify(Literal e1, Literal e2,ArrayList<String> subs)
	{
		if(isConstant(e1.toString()) && isConstant(e2.toString()))
			return e1.toString().equals(e2.toString());
		
		if(isConstant(e1.toString()) && isVariable(e2.toString()))
		{
			subs.add(e1.toString() +"/" + e2.toString());
			return true;
		}
		
		if(isVariable(e1.toString()) && isConstant(e2.toString()))
		{
			subs.add(e2.toString() +"/" + e1.toString());

			return true;
		}
		
		if(isVariable(e1.toString()) && isVariable(e2.toString()))
		{
			subs.add(e1.toString() +"/" + e2.toString());

			return true;
		}
		
		
		if(isPredicate(e1.toString()) && isVariable(e2.toString()))
		{    
			if(!e1.occurs(e2.name))
			{ 
				subs.add(e1.toString() +"/" + e2.toString());
				return true;
			}
			
		}

		if(isPredicate(e2.toString()) && isVariable(e1.toString()))
		{  
			if(!e2.occurs(e1.name))
			{
				subs.add(e2.toString() +"/" + e1.toString());
				return true;
			}
		
		}
		
		if(isPredicate(e1.toString()) && isPredicate(e2.toString()))
		{
			if(e1.name.equals(e2.name))
			{
				if(e1.arity.size() == e2.arity.size())
				{
					for(int i = 0; i<e1.arity.size();i++)
					{
						if(!unify(e1.arity.get(i),e2.arity.get(i),subs))
							return false;
					}
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	public static String getSubstitution(ArrayList<String> subs, String var)
	{
		for(String sub: subs)
		{
			String[] arr = sub.split("/");
			String str = arr[1];
			if(str.equals(var))
				return arr[0];
		}
		
		return "_";
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
