package project3;

import java.util.ArrayList;

public class Literal {
	
	String name;
	ArrayList<String> arity;
	
	public Literal(String name) {
		this.name = name;
		this.arity = new ArrayList<String>();

	}
	
	public Literal(String name,ArrayList<String> vars) {
		this.name = name;
		this.arity = vars;

	}

	@Override
	public String toString() {
		String vars ="";
		for (int i = 0; i < this.arity.size(); i++) {
			
			if(i ==this.arity.size()-1)
			vars+=this.arity.get(i)+")";
			else
			vars+=this.arity.get(i)+",";
		}
		return name + "("+vars;
	}
	
	public void convertToListNotation() {
		
		String name = this.getNameOfPredicate(this.toString());
		String vars = "";
		
		for (int i = 0; i < this.arity.size(); i++) {
			if(i ==this.arity.size()-1)
			vars+= this.convertToListNotation(this.arity.get(i).toString());
			else
			vars+= this.convertToListNotation(this.arity.get(i).toString())+ " ";	
		}
		
		System.out.println("("+ name + " " + vars + ")");
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

	
	public static void main(String[] args){
		ArrayList<String> vars = new ArrayList<String>();
		
		vars.add("X");
		vars.add("Y");
		
		Literal l = new Literal("g",vars);
		
	
		ArrayList<String> vars2 = new ArrayList<String>();
		vars2.add("a");
		Literal l2 = new Literal("f",vars2);
		
//		ArrayList<String> vars3 = new ArrayList<String>();
//		vars3.add(l2.toString());
//		Literal l3 = new Literal("h",vars3);
		
		
		ArrayList<String> vars4 = new ArrayList<String>();
		vars4.add(l.toString());
		vars4.add(l2.toString());
		Literal l4 = new Literal("p",vars4);
		
//		
//		System.out.println(l3.toString());
//		System.out.println(l3.convertToListNotation());
//		
//		System.out.println(l2.toString());
		System.out.println(l4.toString());
		l4.convertToListNotation();

		
		
	}
	
}
