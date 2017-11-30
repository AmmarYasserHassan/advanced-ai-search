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
		//vars2.add(consA);
		vars2.add(varX);
		Literal l2 = new Literal("f",vars2);
		
		ArrayList<Literal> vars5 = new ArrayList<Literal>();
		vars5.add(consA);
		Literal f1 = new Literal("f1",vars5);
		
		ArrayList<Literal> vars6 = new ArrayList<Literal>();
		vars6.add(consA);
		vars6.add(consB);
		//TODO
		vars6.add(varY);
		Literal f2 = new Literal("f1",vars6);
		
		ArrayList<Literal> vars3 = new ArrayList<Literal>();
//		vars3.add(varY);
		vars3.add(l2);
		Literal l3 = new Literal("g",vars3);
		
		ArrayList<Literal> vars7 = new ArrayList<Literal>();
		vars7.add(varZ);
		vars7.add(varY);
		vars7.add(varX);
		Literal h_x = new Literal("p",vars7);
		
		ArrayList<Literal> vars4 = new ArrayList<Literal>();
		vars4.add(l);
		vars4.add(l3);
		vars4.add(f2);
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
		
//		System.out.println(l3);
//		System.out.println(l);
//		System.out.println(unify(l3, l, subs));
//		System.out.println(subs);
//		getSigma(subs);
//		System.out.println(subs);
		

//		
	}
	
	public static boolean add(ArrayList<String> subs,String var, String sub)
	{
		String oldSub = getSubstitution(subs, var);
		
		if(!oldSub.equals("_"))
		{
		
		if(isConstant(oldSub))
		{
			if(isConstant(sub))
			{
				if(!oldSub.equals(sub))
					return false;
				else
					return true;
			}
			
			if(isPredicate(sub))
				return false;
			
			if(isVariable(sub))
			{
				System.out.println("h1");
				subs.add(sub+"/"+oldSub);
				return true;
			}
			
		}
		
		if(isPredicate(oldSub))
		{

			if(isConstant(sub))
				return false;
			
			//TODO
			if(isPredicate(sub))
			{
				System.out.println("heereeeeeeee");
				System.out.println(sub);
				System.out.println(oldSub);
			}
			
			if(isVariable(sub))
			{
				System.out.println("h2");
				subs.add(sub+"/"+oldSub);
				return true;
			}
		}
		
		if(isVariable(oldSub))
		{   
			if(!isVariable(sub))
			{
			System.out.println("h3");
			subs.remove(oldSub+"/"+var);
			subs.add(sub+"/"+var);
			subs.add(sub+"/"+oldSub);
			return true;
			}
			else
			{		
				System.out.println("h4");
				subs.add(sub+"/"+var);
				subs.add(sub+"/"+oldSub);
				return true;
			}
		}
		}
		
		System.out.println("h5");
		
		subs.add(sub+"/"+var);
		return true;
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
	
	public static boolean checkIfBindingsOfAVariableAreConsistent(ArrayList<String> subs,String var,String sub)
	{
		ArrayList<String> subsOfVar = getSubstitutions(subs,var);
		
		if(subsOfVar.size()>1)
		{
			
		}
		
		return true;
		
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
			return add(subs,e2.toString(),e1.toString());
		
		if(isVariable(e1.toString()) && isConstant(e2.toString()))
			return add(subs,e1.toString(),e2.toString());
		
		if(isVariable(e1.toString()) && isVariable(e2.toString()))
			return add(subs,e1.toString(),e2.toString());
		
		
		if(isPredicate(e1.toString()) && isVariable(e2.toString()))
		{    
			if(!e1.occurs(e2.name))
				return add(subs,e2.toString(),e1.toString());
			
		}

		if(isPredicate(e2.toString()) && isVariable(e1.toString()))
		{  
			if(!e2.occurs(e1.name))
				return add(subs,e1.toString(),e2.toString());
		
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
	
	public static ArrayList<String> getSubstitutions(ArrayList<String> subs, String var)
	{
		ArrayList<String> result = new ArrayList<String>();
		for(String sub: subs)
		{
			String[] arr = sub.split("/");
			String str = arr[1];
			if(str.equals(var))
				result.add(arr[0]);
		}
		
		return result;
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
