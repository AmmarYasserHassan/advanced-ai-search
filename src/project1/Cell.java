package project1;

import java.util.ArrayList;

public class Cell 
{
	
	ArrayList<String> elements;
	boolean containsTeleportal;
	
	
	
	public Cell(ArrayList<String> elements) {
		this.elements = elements;
		this.containsTeleportal = false;
	}


	public Cell() {
		this.elements = new ArrayList<String>();
		this.containsTeleportal = false;
	}
	
	
	public void addElement(String elm)
	{
		this.elements.add(elm);
	}
	
	public void removeElement(String elm)
	{
		this.elements.remove(elm);
	}


	@Override
	public String toString() {
		return  "" + elements ;
	}
    
	
	
	
	

	
	

}
