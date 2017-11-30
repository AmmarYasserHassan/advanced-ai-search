package project3;

import java.util.ArrayList;

public class Literal {
	
	String name;
	ArrayList<Literal> arity;
	boolean isConstant;
	boolean isVariable;
	boolean isPredicate;
	
	public Literal(String name,boolean isConstant,boolean isVariable, boolean isPredicate) {
		this.name = name;
		this.arity = new ArrayList<Literal>();
		this.isConstant = isConstant;
		this.isVariable = isVariable;
		this.isPredicate = isPredicate;

	}
	
	public Literal(String name,ArrayList<Literal> vars) {
		this.name = name;
		this.arity = vars;
		this.isConstant = false;
		this.isVariable = false;
		this.isPredicate = true;

	}

	@Override
	public String toString() {
		
		if(this.isVariable || this.isConstant)
			return this.name;
		
		String vars ="";
		for (int i = 0; i < this.arity.size(); i++) {
			
			if(i ==this.arity.size()-1)
			vars+=this.arity.get(i)+")";
			else
			vars+=this.arity.get(i)+",";
		}
		return name + "("+vars;
	}
	
	
	public boolean occurs(String varToCompare)
	{
		for(Literal l: this.arity)
		{
			if(l.isVariable)
			{
				if(l.name.equals(varToCompare))
					return true;
			}
		}
		
		return false;
	}
	
	public String convertToListNotation() {
		
		String name = this.getNameOfPredicate(this.toString());
		String vars = "";
		
		for (int i = 0; i < this.arity.size(); i++) {
			if(i ==this.arity.size()-1)
			vars+= this.convertToListNotation(this.arity.get(i).toString());
			else
			vars+= this.convertToListNotation(this.arity.get(i).toString())+ " ";	
		}
		
		return "("+ name + " " + vars + ")";
	}
	
	public String getNameOfPredicate(String folTerm)
	{
		String name = "";
		
		for (int i = 0; i < folTerm.length(); i++) {
			if(folTerm.charAt(i)=='(')
				break;
			name+=folTerm.charAt(i);
		}
		return name;
	}
	

	
	public String convertToListNotation(String term) {
		
		String list = term.replace(","," ");
		
		if(term.contains("(")){
		String prefix = "("+this.getNameOfPredicate(term)+" ";
		String suffix = list.substring(2, list.length());
		return prefix+suffix;
		}
		else
			return term;
		
	}

	
	public String applySubtituion(String sigma)
	{
		String sub = "";
		String var = "";
		boolean addSub = true;
		
		for (int i = 0; i < sigma.length(); i++) {
			if(sigma.charAt(i)=='/')
				addSub = false;
			
			if(addSub)
			sub+=sigma.charAt(i);
			else
			var+=sigma.charAt(i);
				
		}
		var = var.substring(1);
		return this.toString().replace(var,sub);
		
	}
	
	public static void main(String[] args){
		
		
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
		System.out.println(l.toString());
//	    System.out.println(l.applySubtituion("f(a)/X"));
//	    System.out.println(l.applySubtituion("b/Y"));
		
		ArrayList<Literal> vars2 = new ArrayList<Literal>();
		vars2.add(consA);
		Literal l2 = new Literal("f",vars2);
		System.out.println(l2.toString());
		
		ArrayList<Literal> vars3 = new ArrayList<Literal>();
		vars3.add(l2);
		Literal l3 = new Literal("h",vars3);
		System.out.println(l3.toString());
		
		ArrayList<Literal> vars4 = new ArrayList<Literal>();
		vars4.add(l);
		vars4.add(l2);
		Literal l4 = new Literal("p",vars4);
		System.out.println(l4.toString());

//		System.out.println(l3.toString());
		System.out.println(l3.convertToListNotation());
//		
////		System.out.println(l2.toString());
//		System.out.println(l4.toString());
//	    System.out.println(l4.applySubtituion("h2(l)/X"));


		
		
	}
	
}
