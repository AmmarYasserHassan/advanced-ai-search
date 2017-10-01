package project1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Grid {

	Cell[][] theGrid;
	int length;
	int width;
	String[] possibleElements = { "Empty", "Immovable"};

	public Grid(int min, int max) {
		// this.length = min + (int)(Math.random() * max);
		// this.width = min + (int)(Math.random() * max);
		this.length = min;
		this.width = max;
		this.theGrid = new Cell[this.length][this.width];
	}

	public void initializeGrid() {

		boolean teleportalAdded = false;

		
		
		int newI = (int) (Math.random() * this.length);
		int newJ = (int) (Math.random() * this.width);
		this.theGrid[newI][newJ] = new Cell();
		this.theGrid[newI][newJ].addElement("R2-D2");
		
		// loop for adding the main elements (Teleportal, R2-D2)
		while (!teleportalAdded) {
				 newI = (int) (Math.random() * this.length);
				 newJ = (int) (Math.random() * this.width);
				String mainElement = "Teleportal";
				if (this.theGrid[newI][newJ] == null) {
						teleportalAdded = true;
					this.theGrid[newI][newJ] = new Cell();
					this.theGrid[newI][newJ].addElement(mainElement);
				}
		}

		
		int maxPadsRocks = (int) ((this.length*this.width-2)/2)-1;
		int numberOfPadsToBeAdded = (int) (Math.random()*maxPadsRocks)+1;
		int numberOfRocksToBeAdded = numberOfPadsToBeAdded;
 
		
		System.out.println(numberOfPadsToBeAdded);
		
	    while(numberOfPadsToBeAdded >0)
	    {
			 newI = (int) (Math.random() * this.length);
			 newJ = (int) (Math.random() * this.width);
			 if (this.theGrid[newI][newJ] == null) {
				this.theGrid[newI][newJ] = new Cell();
				this.theGrid[newI][newJ].addElement("Pads");
				numberOfPadsToBeAdded--;
			}	 
	    	
	    }
		
	    
	    while(numberOfRocksToBeAdded >0)
	    {
			 newI = (int) (Math.random() * this.length);
			 newJ = (int) (Math.random() * this.width);
			 if (this.theGrid[newI][newJ] == null) {
				this.theGrid[newI][newJ] = new Cell();
				this.theGrid[newI][newJ].addElement("Rocks");
				numberOfRocksToBeAdded--;
			}	 
	    	
	    }
	    
	    
	 // loops for adding (Empty,Immovable)
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.theGrid[i][j] == null) {
					this.theGrid[i][j] = new Cell();
					int index = (int) (Math.random() * 2);

					String element = this.possibleElements[index];
					this.theGrid[i][j].addElement(element);
				}

			}
		}
	}

	public void showGrid() {
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {

				System.out.print(this.theGrid[i][j].toString() + " | ");
			}

			System.out.println();
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Grid g = new Grid(5, 5);

		g.initializeGrid();
		g.showGrid();

	}

}
