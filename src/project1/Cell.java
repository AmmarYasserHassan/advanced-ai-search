package project1;

import java.util.ArrayList;

public class Cell 
{
	
	ArrayList<String> elements;
	int i;
	int j;
	
	//TODO type and element that may be null
	
	public Cell(int i, int j) {
		this.elements = new ArrayList<String>();
		this.i =i;
		this.j=j;
	}
	
	public Cell(int i, int j,String elm) {
		this.elements = new ArrayList<String>();
		this.i =i;
		this.j=j;
		this.elements.add(elm);
	}
	
	
	public void addElement(String elm)
	{  
		this.elements.add(elm);
	}
	
	
	public void removeElement(String elm)
	{ 
		this.elements.remove(elm);
	}

	
	public Cell clone()
	{
		Cell newCell = new Cell(this.i,this.j);
		for (int k = 0; k < this.elements.size(); k++) {
			newCell.addElement(this.elements.get(k));
		}
		return newCell;
	}

	@Override
	public String toString() {
		return  "" + elements ;
	}
    
	
	
	
	

	
	

}
