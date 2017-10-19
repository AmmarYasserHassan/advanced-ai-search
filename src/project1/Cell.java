package project1;

import java.util.ArrayList;

import javax.sound.midi.Synthesizer;

public class Cell 
{
	
	ArrayList<String> elements;
	int i;
	int j;
	
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
	
	
	
		public void addElement(String elm)
	{
		
		this.elements.add(elm);
	}
	
	
	public void removeElement(String elm)
	{ 
		this.elements.remove(elm);
	}

	
	public Cell copy()
	{
		Cell newCell = new Cell(this.i,this.j);
		for (int k = 0; k < this.elements.size(); k++) {
			newCell.addElement(this.elements.get(k));
		}
		return newCell;
	}

	public int getHash(){
		if(elements.size() == 0){
			return 0;
		}else if(elements.size() == 1){
			if(elements.contains("Pad"))
				return 1;
			else if(elements.contains("Rock"))
				return 2;
			else if(elements.contains("Immovable"))
				return 3;
			else if(elements.contains("R2D2"))
				return 4;
			else if(elements.contains("Teleportal"))
				return 5;
		}else{
			if(elements.contains("Pad") && elements.contains("Rock"))
				return 6;
			else if(elements.contains("Pad") && elements.contains("R2D2"))
				return 7;
			else if(elements.contains("Teleportal") && elements.contains("R2D2"))
				return 8;
			else if(elements.contains("Teleportal") && elements.contains("Rock"))
				return 9;
		}
		System.err.println("Get Cell Hash Error !!: you should never reach this case");
		return 0;
		
	}
	
	public String toString() {
		return ""+elements;
	}
    	

}
