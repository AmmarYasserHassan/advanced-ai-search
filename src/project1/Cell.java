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
		for (int i = 0; i < this.elements.size(); i++) {
			newCell.addElement(this.elements.get(i));
		}
		return newCell;
	}

	@Override
	public String toString() {
		return  "" + elements ;
	}
    
	
	
	
	

	
	

}
