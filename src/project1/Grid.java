package project1;

import java.util.ArrayList;

public class Grid {

	Cell[][] grid;
	int length;
	int width;
	Cell r2d2;
	String r2d2Orientation;
	boolean atTeleportalCell;
	int numberOfPadsRemaningWithoutRocks;
	static String[] locations = { "North", "East", "South", "West" };

	public Grid(int min, int max) {
		this.length = min;
		this.width = max;
		this.grid = new Cell[this.length][this.width];
		this.atTeleportalCell = false;
		this.initializeGrid();
	}

	public Grid(int min, int max, boolean value) {
		this.length = min;
		this.width = max;
		this.grid = new Cell[this.length][this.width];
		this.atTeleportalCell = false;
	}

	public void initializeGrid() {

		boolean teleportalAdded = false;

		int newI = (int) (Math.random() * this.length);
		int newJ = (int) (Math.random() * this.width);
		this.grid[newI][newJ] = new Cell(newI, newJ);

		// Added R2D2
		this.grid[newI][newJ].addElement("R2D2");
		this.r2d2 = this.grid[newI][newJ];
		this.r2d2Orientation = locations[(int) (Math.random() * 4)];

		// loop for adding Teleportal
		while (!teleportalAdded) {
			newI = (int) (Math.random() * this.length);
			newJ = (int) (Math.random() * this.width);

			if (this.grid[newI][newJ] == null) {
				teleportalAdded = true;
				this.grid[newI][newJ] = new Cell(newI, newJ);
				this.grid[newI][newJ].addElement("Teleportal");
			}
		}

		int maxPadsRocks = (int) ((this.length * this.width - 2) / 2) - 1;
		int numberOfPadsToBeAdded = (int) (Math.random() * maxPadsRocks) + 1;
		int numberOfRocksToBeAdded = numberOfPadsToBeAdded;
		this.numberOfPadsRemaningWithoutRocks = numberOfRocksToBeAdded;

		while (numberOfPadsToBeAdded > 0) {
			newI = (int) (Math.random() * this.length);
			newJ = (int) (Math.random() * this.width);
			if (this.grid[newI][newJ] == null) {
				this.grid[newI][newJ] = new Cell(newI, newJ);
				this.grid[newI][newJ].addElement("Pad");
				numberOfPadsToBeAdded--;
			}

		}

		while (numberOfRocksToBeAdded > 0) {
			newI = (int) (Math.random() * this.length);
			newJ = (int) (Math.random() * this.width);
			if (this.grid[newI][newJ] == null) {
				this.grid[newI][newJ] = new Cell(newI, newJ);
				this.grid[newI][newJ].addElement("Rock");
				numberOfRocksToBeAdded--;
			}

		}

		// loops for adding Immovable
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.grid[i][j] == null) {
					this.grid[i][j] = new Cell(i, j);
					int index = (int) (Math.random() * 10);
					if (index == 1) {
						this.grid[i][j].addElement("Immovable");
					}
				}

			}
		}

	}

	public void showGrid() {
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				System.out.print(this.grid[i][j].toString() + "  ");
				if (this.grid[i][j].elements.contains("R2D2")) {
					switch (this.r2d2Orientation) {
					case "North":
						System.out.print(" ^ ");
						break;
					case "East":
						System.out.print(" > ");
						break;
					case "West":
						System.out.print(" < ");
						break;
					case "South":
						System.out.print(" v ");
						break;
					default:
						break;
					}
				}
				System.out.print(" | ");
			}
			System.out.println();
			System.out.println();
		}
		System.out.println(this.r2d2Orientation);
	}

	public void rotateRight() {
		int index = 0;

		for (int i = 0; i < locations.length; i++) {
			if (locations[i].equals(r2d2Orientation))
				index = i;
		}

		this.r2d2Orientation = locations[(index + 1) % locations.length];

	}

	public void rotateLeft() {
		int index = 0;

		for (int i = 0; i < locations.length; i++) {
			if (locations[i].equals(r2d2Orientation))
				index = i;
		}

		if (index == 0) {
			this.r2d2Orientation = locations[3];
		} else {
			this.r2d2Orientation = locations[(index - 1)];
		}
	}

	public Grid copy() {
		Grid newGrid = new Grid(this.length, this.width, false);

		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				Cell newCell = this.grid[i][j].copy();
				newGrid.grid[i][j] = newCell;
				if (this.grid[i][j].elements.contains("R2D2")) {
					newGrid.r2d2 = newCell;
				}

			}
		}
		newGrid.length = this.length;
		newGrid.width = this.width;
		newGrid.r2d2Orientation = this.r2d2Orientation;
		newGrid.atTeleportalCell = this.atTeleportalCell;
		newGrid.numberOfPadsRemaningWithoutRocks = this.numberOfPadsRemaningWithoutRocks;
		return newGrid;

	}

	public boolean facingAnEdge(Cell cell, String orientation) {
		switch (orientation) {

		case "North":
			if (cell.i == 0)
				return true;
			break;

		case "East":
			if (cell.j == this.width - 1)
				return true;
			break;

		case "South":
			if (cell.i == this.length - 1)
				return true;
			break;
		case "West":
			if (cell.j == 0)
				return true;
			break;

		default:
			return false;

		}
		return false;
	}

	public boolean facingAnObstacle(Cell cell, String orientation) {
		Cell nextCell = getNextCell(cell, orientation);
		if (nextCell.elements.contains("Immovable")) {
			return true;
		}
		return false;
	}

	public boolean facingARock(Cell cell, String orientation) {
		Cell nextCell = getNextCell(cell, orientation);
		if (nextCell.elements.contains("Rock")) {
			return true;
		}
		return false;
	}

	public Cell getNextCell(Cell cell, String orientation) {
		if (facingAnEdge(cell, orientation)) {
			// we should never be here
		//	System.err.println("Ahmed Wageeh says: trying to get next cell while facing edge");
			// Return same cell cause forward should make you stuck there
			return cell;
		}

		int i = cell.i;
		int j = cell.j;
		switch (orientation) {

		case "North":
			return this.grid[i - 1][j];
		case "East":
			return this.grid[i][j + 1];
		case "South":
			return this.grid[i + 1][j];
		case "West":
			return this.grid[i][j - 1];

		}

		return null;
	}

	public boolean rockCanMove(Cell cell, String orientation) {
		if (facingAnEdge(cell, orientation))
			return false;
		Cell nextCell = getNextCell(cell, orientation);
		if (nextCell.elements.contains("Rock") || nextCell.elements.contains("Immovable")) {
			return false;
		}
		return true;
	}

	public void moveR2D2(Cell cell, String orientation) {
		Cell nextCell = getNextCell(cell, orientation);
		cell.removeElement("R2D2");
		nextCell.addElement("R2D2");
		this.r2d2 = nextCell;

		if (this.r2d2.elements.contains("Teleportal"))
			this.atTeleportalCell = true;
		else
			this.atTeleportalCell = false;

	}

	public void moveRock(Cell cell, String orientation) {

		Cell rockCell = cell;
		Cell nextCell = getNextCell(cell, orientation);
		cell.removeElement("Rock");
		nextCell.addElement("Rock");

		if (rockCell.elements.contains("Pad") && !nextCell.elements.contains("Pad"))
			this.numberOfPadsRemaningWithoutRocks++;
		if (!rockCell.elements.contains("Pad") && nextCell.elements.contains("Pad"))
			this.numberOfPadsRemaningWithoutRocks--;

	}

	public boolean infeasible() {
		return (thereIsARockInACorner() || thereIsARockSidedByAnEdgeAndNoReachablePadInFront());
	}

	public boolean thereIsARockInACorner() {

		boolean result = (this.grid[0][0].elements.contains("Rock")
				|| this.grid[0][this.width - 1].elements.contains("Rock")
				|| this.grid[this.length - 1][0].elements.contains("Rock")
				|| this.grid[this.length - 1][this.width - 1].elements.contains("Rock"));

		if (result)
			System.out.println("Rock at a corner, infeasible Solution");
		return result;
	}

	public boolean thereIsARockSidedByAnEdgeAndNoReachablePadInFront() {
		int rocksCount = 0;
		int padsCount = 0;

		for (int j = 0; j < this.width; j++) {
			if (this.grid[0][j].elements.contains("Rock"))
				rocksCount++;
			if (this.grid[0][j].elements.contains("Pad"))
				padsCount++;
		}

		if (rocksCount > padsCount) {
			System.out.println("Rock at an edge with no pad infront of it, infeasible Solution");
			return true;
		}

		rocksCount = 0;
		padsCount = 0;

		for (int j = 0; j < this.width; j++) {
			if (this.grid[this.length - 1][j].elements.contains("Rock"))
				rocksCount++;
			if (this.grid[this.length - 1][j].elements.contains("Pad"))
				padsCount++;
		}

		if (rocksCount > padsCount) {
			System.out.println("Rock at an edge with no pad infront of it, infeasible Solution");
			return true;
		}

		rocksCount = 0;
		padsCount = 0;

		for (int i = 0; i < this.length; i++) {
			if (this.grid[i][0].elements.contains("Rock"))
				rocksCount++;
			if (this.grid[i][0].elements.contains("Pad"))
				padsCount++;
		}

		if (rocksCount > padsCount) {
			System.out.println("Rock at an edge with no pad infront of it, infeasible Solution");
			return true;
		}

		rocksCount = 0;
		padsCount = 0;

		for (int i = 0; i < this.length; i++) {

			if (this.grid[i][this.width - 1].elements.contains("Rock"))
				rocksCount++;
			if (this.grid[i][this.width - 1].elements.contains("Pad"))
				padsCount++;
		}

		if (rocksCount > padsCount) {
			System.out.println("Rock at an edge with no pad infront of it, infeasible Solution");
			return true;
		}

		return false;

	}
	
	public ArrayList<String> createKB() {
		int k = 1;
		int rocksCount =1;
		ArrayList<String> sentences = new ArrayList<String>();
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				grid[i][j].indexInKb = k;
				for (String object : grid[i][j].elements) {
					
					if(object.equals("Rock"))
					{
						sentences.add("at(c"+k+","+object.toLowerCase()+rocksCount+",s0).");
						sentences.add("rock("+object.toLowerCase()+rocksCount+").");
						rocksCount++;
					}
					else{
					
					if(object.equals("R2D2"))
					{
						sentences.add("at(c"+k+","+object.toLowerCase()+",s0).");
					}
					else
					{
						sentences.add("at(c"+k+","+object.toLowerCase()+",_).");
					}
					}
						
				}
				
				
				
				k++;
			}

		}
		
		sentences.add("orientation("+this.r2d2Orientation.toLowerCase()+",s0).");
		sentences.add("obstacle(immovable).");
		sentences.add("pad(pad).");
		sentences.add("teleportal(teleportal).");
		
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				for(String orin:locations)
				{
					Cell temp = this.getNextCell(grid[i][j], orin);
					if(temp.indexInKb != grid[i][j].indexInKb)
					{
						sentences.add("next(c"+grid[i][j].indexInKb+",c"+temp.indexInKb+","+orin.toLowerCase()+").");
					}
				}
			}

		}
		
		
		return sentences;
	}
	

	public String getGridHash() {
		String hash = "";
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				hash += grid[i][j].getHash();
			}
		}
		switch (r2d2Orientation) {
		case "North":
			hash += "1";
			break;
		case "East":
			hash += "2";
			break;
		case "South":
			hash += "3";
			break;
		case "West":
			hash += "4";
			break;

		default:
			break;
		}
		return hash;
	}

}
