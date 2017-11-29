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
	
	public String convertToListNotation() {
		
		String conventional = this.toString();
		String list = conventional.replace(","," ");
		String prefix = "("+this.name+" ";
		String suffix = list.substring(2, list.length());
		return prefix+suffix;
		
		
	}

	
	public static void main(String[] args){
		ArrayList<String> vars = new ArrayList<String>();
		
		vars.add("X");
		vars.add("Y");
		
		Literal l = new Literal("p",vars);
		System.out.println(l.toString());
		System.out.println(l.convertToListNotation());
		
	
		ArrayList<String> vars2 = new ArrayList<String>();
		vars2.add("a");
		Literal l2 = new Literal("f",vars2);
		
		ArrayList<String> vars3 = new ArrayList<String>();
		vars3.add(l2.convertToListNotation());
		Literal l3 = new Literal("h",vars3);
		
		System.out.println(l3.toString());
		System.out.println(l3.convertToListNotation());

		
		
	}
	
}
