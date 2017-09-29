package project1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Grid {

	Cell[][] theGrid;
	int length;
	int width;
	String[] possibleElements = { "Empty", "Pad", "Rock", "Immovable" };
	String[] mainElements = { "R2-D2", "Teleportal" };

	public Grid(int min, int max) {
		// this.length = min + (int)(Math.random() * max);
		// this.width = min + (int)(Math.random() * max);
		this.length = min;
		this.width = max;
		this.theGrid = new Cell[this.length][this.width];
	}

	public void initializeGrid() {

		boolean teleportalAdded = false;
		boolean R2D2Added = false;

		// loops for adding the main elements (Teleportal, R2-D2)
		while (!teleportalAdded && !R2D2Added) {
			for (int i = 0; i < mainElements.length; i++) {
				int newI = (int) (Math.random() * this.length);
				int newJ = (int) (Math.random() * this.width);

				String mainElement = this.mainElements[i];
				if (this.theGrid[newI][newJ] == null) {

					if (mainElement.equals("Teleportal"))
						teleportalAdded = true;

					if (mainElement.equals("R2-D2"))
						R2D2Added = true;

					this.theGrid[newI][newJ] = new Cell();
					this.theGrid[newI][newJ].addElement(mainElement);
				}
			}
		}

		// loops for adding (Empty, Pad, Rock, Immovable)
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				System.out.println(this.theGrid[i][j]);
				if (this.theGrid[i][j] == null) {
					this.theGrid[i][j] = new Cell();
					int index = (int) (Math.random() * 4);

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
		Grid g = new Grid(3, 3);

		g.initializeGrid();
		g.showGrid();

	}

}
