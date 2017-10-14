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
		this.i = i;
		this.j = j;
	}
	
	public Cell(int i, int j,String elm) {
		this.elements = new ArrayList<String>();
		this.i = i;
		this.j = j;
		this.elements.add(elm);
	}
	
	
	
	//TODO remove throw
	public void addElement(String elm)
	{
		
//		this.elements.add(elm);
		try {
			if (!(this.elements.contains(elm)))
				this.elements.add(elm);
			else {
				String s = this.toString();
				throw new Exception("Added a duplicate element at: " + s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void removeElement(String elm)
	{ 
//		this.elements.remove(elm);


		try {
			if (this.elements.contains(elm))
				this.elements.remove(elm);
			else {
				String s = this.toString();
				throw new Exception("Removed a non-existing element at: " + s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Cell copy()
	{
		Cell newCell = new Cell(this.i,this.j);
		for (int k = 0; k < this.elements.size(); k++) {
			newCell.addElement(this.elements.get(k));
		}
		return newCell;
	}

	@Override
	public String toString() {
//		return  this.i + "," + this.j + " - " + elements;
		return ""+elements;
	}
    	

}
