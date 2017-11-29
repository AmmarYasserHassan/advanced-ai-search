package project1;

import java.io.PrintWriter;

public class Test {

	public static Grid testingGrid() {
		Cell[][] g = new Cell[3][3];
		g[0][0] = new Cell(0, 0);
		g[0][1] = new Cell(0, 1, "Immovable");
		g[0][2] = new Cell(0, 2, "Immovable");

		g[1][0] = new Cell(1, 0, "R2D2");
		g[1][1] = new Cell(1, 1, "Rock");
		g[1][2] = new Cell(1, 2, "Pad");

		g[2][0] = new Cell(2, 0);
		g[2][1] = new Cell(2, 1, "Teleportal");
		g[2][2] = new Cell(2, 2);

		Grid grid = new Grid(3, 3);
		grid.grid = g;
		grid.r2d2Orientation = "East";
		grid.r2d2 = g[1][0];
		grid.numberOfPadsRemaningWithoutRocks = 1;

		return grid;
	}

	public static Grid testingGrid6() {
		Cell[][] g = new Cell[3][3];
		g[0][0] = new Cell(0, 0, "Pad");
		g[0][1] = new Cell(0, 1);
		g[0][2] = new Cell(0, 2);

		g[1][0] = new Cell(1, 0, "Rock");
		g[1][1] = new Cell(1, 1);
		g[1][2] = new Cell(1, 2, "R2D2");

		g[2][0] = new Cell(2, 0, "Teleportal");
		g[2][1] = new Cell(2, 1);
		g[2][2] = new Cell(2, 2);

		Grid grid = new Grid(3, 3);
		grid.grid = g;
		grid.r2d2Orientation = "South";
		grid.r2d2 = g[1][2];
		grid.numberOfPadsRemaningWithoutRocks = 1;

		return grid;
	}

	public static Grid testingGrid3() {
		Cell[][] g = new Cell[3][3];
		g[0][0] = new Cell(0, 0);
		g[0][1] = new Cell(0, 1, "R2D2");
		g[0][2] = new Cell(0, 2);

		g[1][0] = new Cell(1, 0, "Pad");
		g[1][1] = new Cell(1, 1, "Rock");
		g[1][2] = new Cell(1, 2);

		g[2][0] = new Cell(2, 0);
		g[2][1] = new Cell(2, 1, "Teleportal");
		g[2][2] = new Cell(2, 2);

		Grid grid = new Grid(3, 3);
		grid.grid = g;
		grid.r2d2Orientation = "East";
		grid.r2d2 = g[0][1];
		grid.numberOfPadsRemaningWithoutRocks = 1;

		return grid;
	}

	public static Grid testingGrid4() {
		Cell[][] g = new Cell[3][3];
		g[0][0] = new Cell(0, 0);
		g[0][1] = new Cell(0, 1);
		g[0][2] = new Cell(0, 2);

		g[1][0] = new Cell(1, 0, "R2D2");
		g[1][1] = new Cell(1, 1, "Rock");
		g[1][2] = new Cell(1, 2, "Teleportal");

		g[2][0] = new Cell(2, 0);
		g[2][1] = new Cell(2, 1, "Pad");
		g[2][2] = new Cell(2, 2);

		Grid grid = new Grid(3, 3);
		grid.grid = g;
		grid.r2d2Orientation = "East";
		grid.r2d2 = g[1][0];
		grid.numberOfPadsRemaningWithoutRocks = 1;

		return grid;
	}

	public static Grid testingGrid5() {
		Cell[][] g = new Cell[3][3];
		g[0][0] = new Cell(0, 0, "Pad");
		g[0][1] = new Cell(0, 1, "R2D2");
		g[0][2] = new Cell(0, 2);

		g[1][0] = new Cell(1, 0);
		g[1][1] = new Cell(1, 1, "Rock");
		g[1][2] = new Cell(1, 2);

		g[2][0] = new Cell(2, 0, "Teleportal");
		g[2][1] = new Cell(2, 1);
		g[2][2] = new Cell(2, 2);

		Grid grid = new Grid(3, 3);
		grid.grid = g;
		grid.r2d2Orientation = "West";
		grid.r2d2 = g[0][1];
		grid.numberOfPadsRemaningWithoutRocks = 1;

		return grid;
	}

	public static Grid testingGrid1() {
		Cell[][] g = new Cell[3][3];
		g[0][0] = new Cell(0, 0);
		g[0][1] = new Cell(0, 1);
		g[0][2] = new Cell(0, 2, "Pad");

		g[1][0] = new Cell(1, 0, "Teleportal");
		g[1][1] = new Cell(1, 1, "Rock");
		g[1][2] = new Cell(1, 2);

		g[2][0] = new Cell(2, 0, "R2D2");
		g[2][1] = new Cell(2, 1);
		g[2][2] = new Cell(2, 2);

		Grid grid = new Grid(3, 3);
		grid.grid = g;
		grid.r2d2Orientation = "South";
		grid.r2d2 = g[2][0];
		grid.numberOfPadsRemaningWithoutRocks = 1;

		return grid;
	}

	public static Grid testingGrid2() {
		Cell[][] g = new Cell[4][4];
		g[0][0] = new Cell(0, 0);
		g[0][1] = new Cell(0, 1);
		g[0][2] = new Cell(0, 2);
		g[0][3] = new Cell(0, 3);

		g[1][0] = new Cell(1, 0, "R2D2");
		g[1][1] = new Cell(1, 1);
		g[1][2] = new Cell(1, 2);
		g[1][3] = new Cell(1, 3, "Rock");

		g[2][0] = new Cell(2, 0);
		g[2][1] = new Cell(2, 1);
		g[2][2] = new Cell(2, 2);
		g[2][3] = new Cell(2, 3, "Pad");

		g[3][0] = new Cell(3, 0);
		g[3][1] = new Cell(3, 1);
		g[3][2] = new Cell(3, 2, "Teleportal");
		g[3][3] = new Cell(3, 3);

		Grid grid = new Grid(4, 4);
		grid.grid = g;
		grid.r2d2Orientation = "East";
		grid.r2d2 = g[1][0];
		grid.numberOfPadsRemaningWithoutRocks = 1;

		return grid;
	}


	public static Grid testingGridKB1(){
		
		Cell[][] g = new Cell[3][3];
		g[0][0] = new Cell(0, 0,"R2D2");
		g[0][1] = new Cell(0, 1, "Rock");
		g[0][2] = new Cell(0, 2, "Pad");

		g[1][0] = new Cell(1, 0);
		g[1][1] = new Cell(1, 1, "Rock");
		g[1][1].addElement("Teleportal");
		g[1][2] = new Cell(1, 2, "Pad");

		g[2][0] = new Cell(2, 0);
		g[2][1] = new Cell(2, 1);
		g[2][2] = new Cell(2, 2);

		Grid grid = new Grid(3, 3);
		grid.grid = g;
		grid.r2d2Orientation = "East";
		grid.r2d2 = g[0][0];
		grid.numberOfPadsRemaningWithoutRocks = 1;

		return grid;
		
	}
	
	

	public static Grid testingGridKB2(){
		
		Cell[][] g = new Cell[3][3];
		g[0][0] = new Cell(0, 0,"Immovable");
		g[0][1] = new Cell(0, 1);
		g[0][2] = new Cell(0, 2);

		g[1][0] = new Cell(1, 0,"R2D2");
		g[1][1] = new Cell(1, 1, "Rock");
		g[1][2] = new Cell(1, 2, "Pad");

		g[2][0] = new Cell(2, 0);
		g[2][1] = new Cell(2, 1);
		g[2][2] = new Cell(2, 2,"Teleportal");

		Grid grid = new Grid(3, 3);
		grid.grid = g;
		grid.r2d2Orientation = "East";
		grid.r2d2 = g[1][0];
		grid.numberOfPadsRemaningWithoutRocks = 1;

		return grid;
		
	}
	
	public static Grid testingGrid10() {
		Cell[][] g = new Cell[5][5];
		g[0][0] = new Cell(0, 0);
		g[0][1] = new Cell(0, 1);
		g[0][2] = new Cell(0, 2);
		g[0][3] = new Cell(0, 3);
		g[0][4] = new Cell(0, 4);

		g[1][0] = new Cell(1, 0);
		g[1][1] = new Cell(1, 1, "Teleportal");
		g[1][2] = new Cell(1, 2);
		g[1][3] = new Cell(1, 3, "Immovable");
		g[1][4] = new Cell(1, 4, "R2D2");

		g[2][0] = new Cell(2, 0);
		g[2][1] = new Cell(2, 1);
		g[2][2] = new Cell(2, 2, "Rock");
		g[2][3] = new Cell(2, 3);
		g[2][4] = new Cell(2, 4);

		g[3][0] = new Cell(3, 0);
		g[3][1] = new Cell(3, 1);
		g[3][2] = new Cell(3, 2);
		g[3][3] = new Cell(3, 3);
		g[3][4] = new Cell(3, 4, "Immovable");

		g[4][0] = new Cell(4, 0);
		g[4][1] = new Cell(4, 1);
		g[4][2] = new Cell(4, 2);
		g[4][3] = new Cell(4, 3);
		g[4][4] = new Cell(4, 4, "Pad");

		Grid grid = new Grid(5, 5);
		grid.grid = g;
		grid.r2d2Orientation = "West";
		grid.r2d2 = g[1][4];
		grid.numberOfPadsRemaningWithoutRocks = 1;

		return grid;
	}

	public static Grid testingGrid11() {
		Cell[][] g = new Cell[5][5];
		g[0][0] = new Cell(0, 0);
		g[0][1] = new Cell(0, 1);
		g[0][2] = new Cell(0, 2);
		g[0][3] = new Cell(0, 3);
		g[0][4] = new Cell(0, 4);

		g[1][0] = new Cell(1, 0, "Pad");
		g[1][1] = new Cell(1, 1, "Teleportal");
		g[1][2] = new Cell(1, 2);
		g[1][3] = new Cell(1, 3, "Immovable");
		g[1][4] = new Cell(1, 4, "R2D2");

		g[2][0] = new Cell(2, 0);
		g[2][1] = new Cell(2, 1);
		g[2][2] = new Cell(2, 2, "Rock");
		g[2][3] = new Cell(2, 3);
		g[2][4] = new Cell(2, 4);

		g[3][0] = new Cell(3, 0, "Rock");
		g[3][1] = new Cell(3, 1);
		g[3][2] = new Cell(3, 2);
		g[3][3] = new Cell(3, 3);
		g[3][4] = new Cell(3, 4, "Immovable");

		g[4][0] = new Cell(4, 0);
		g[4][1] = new Cell(4, 1);
		g[4][2] = new Cell(4, 2);
		g[4][3] = new Cell(4, 3);
		g[4][4] = new Cell(4, 4, "Pad");

		Grid grid = new Grid(5, 5);
		grid.grid = g;
		grid.r2d2Orientation = "West";
		grid.r2d2 = g[1][4];
		grid.numberOfPadsRemaningWithoutRocks = 2;

		return grid;
	}

	public static Grid testingGrid9() {

		Cell[][] g = new Cell[3][3];
		g[0][0] = new Cell(0, 0);
		g[0][1] = new Cell(0, 1, "Immovable");
		g[0][2] = new Cell(0, 2, "Immovable");

		g[1][0] = new Cell(1, 0, "R2D2");
		g[1][1] = new Cell(1, 1, "Rock");
		g[1][2] = new Cell(1, 2, "Pad");
		g[1][1].addElement("Teleportal");

		g[2][0] = new Cell(2, 0);
		g[2][1] = new Cell(2, 1, "Teleportal");
		g[2][2] = new Cell(2, 2);

		Grid grid = new Grid(3, 3);
		grid.grid = g;
		grid.r2d2Orientation = "West";
		grid.r2d2 = g[1][0];
		grid.numberOfPadsRemaningWithoutRocks = 1;

		return grid;
	}

	public static Grid testingGrid12() {
		Cell[][] g = new Cell[5][5];
		g[0][0] = new Cell(0, 0);
		g[0][1] = new Cell(0, 1);
		g[0][2] = new Cell(0, 2);
		g[0][3] = new Cell(0, 3);
		g[0][4] = new Cell(0, 4);

		g[1][0] = new Cell(1, 0, "Pad");
		g[1][1] = new Cell(1, 1, "Teleportal");
		g[1][2] = new Cell(1, 2);
		g[1][3] = new Cell(1, 3, "Immovable");
		g[1][4] = new Cell(1, 4, "R2D2");

		g[2][0] = new Cell(2, 0);
		g[2][1] = new Cell(2, 1, "Rock");
		g[2][2] = new Cell(2, 2, "Rock");
		g[2][3] = new Cell(2, 3);
		g[2][4] = new Cell(2, 4);

		g[3][0] = new Cell(3, 0);
		g[3][1] = new Cell(3, 1);
		g[3][2] = new Cell(3, 2);
		g[3][3] = new Cell(3, 3);
		g[3][4] = new Cell(3, 4, "Immovable");

		g[4][0] = new Cell(4, 0);
		g[4][1] = new Cell(4, 1);
		g[4][2] = new Cell(4, 2);
		g[4][3] = new Cell(4, 3);
		g[4][4] = new Cell(4, 4, "Pad");

		Grid grid = new Grid(5, 5);
		grid.grid = g;
		grid.r2d2Orientation = "West";
		grid.r2d2 = g[1][4];
		grid.numberOfPadsRemaningWithoutRocks = 2;

		return grid;
	}

	public static Grid testingGrid10by10() {
		Cell[][] g = new Cell[10][10];
		g[0][0] = new Cell(0, 0, "Immovable");
		g[0][1] = new Cell(0, 1, "Pad");
		g[0][2] = new Cell(0, 2, "Rock");
		g[0][3] = new Cell(0, 3);
		g[0][4] = new Cell(0, 4);
		g[0][5] = new Cell(0, 5);
		g[0][6] = new Cell(0, 6);
		g[0][7] = new Cell(0, 7);
		g[0][8] = new Cell(0, 8);
		g[0][9] = new Cell(0, 9, "Teleportal");

		g[1][0] = new Cell(1, 0);
		g[1][1] = new Cell(1, 1);
		g[1][2] = new Cell(1, 2);
		g[1][3] = new Cell(1, 3);
		g[1][4] = new Cell(1, 4);
		g[1][5] = new Cell(1, 5, "Immovable");
		g[1][6] = new Cell(1, 6);
		g[1][7] = new Cell(1, 7);
		g[1][8] = new Cell(1, 8);
		g[1][9] = new Cell(1, 9);

		g[2][0] = new Cell(2, 0);
		g[2][1] = new Cell(2, 1);
		g[2][2] = new Cell(2, 2);
		g[2][3] = new Cell(2, 3);
		g[2][4] = new Cell(2, 4);
		g[2][5] = new Cell(2, 5);
		g[2][6] = new Cell(2, 6);
		g[2][7] = new Cell(2, 7);
		g[2][8] = new Cell(2, 8);
		g[2][9] = new Cell(2, 9);

		g[3][0] = new Cell(3, 0);
		g[3][1] = new Cell(3, 1);
		g[3][2] = new Cell(3, 2);
		g[3][3] = new Cell(3, 3, "Rock");
		g[3][4] = new Cell(3, 4, "Pad");
		g[3][5] = new Cell(3, 5);
		g[3][6] = new Cell(3, 6, "Rock");
		g[3][7] = new Cell(3, 7, "Pad");
		g[3][8] = new Cell(3, 8);
		g[3][9] = new Cell(3, 9);

		g[4][0] = new Cell(4, 0);
		g[4][1] = new Cell(4, 1);
		g[4][2] = new Cell(4, 2);
		g[4][3] = new Cell(4, 3);
		g[4][4] = new Cell(4, 4);
		g[4][5] = new Cell(4, 5);
		g[4][6] = new Cell(4, 6);
		g[4][7] = new Cell(4, 7);
		g[4][8] = new Cell(4, 8);
		g[4][9] = new Cell(4, 9);

		g[5][0] = new Cell(5, 0);
		g[5][1] = new Cell(5, 1);
		g[5][2] = new Cell(5, 2);
		g[5][3] = new Cell(5, 3);
		g[5][4] = new Cell(5, 4);
		g[5][5] = new Cell(5, 5);
		g[5][6] = new Cell(5, 6);
		g[5][7] = new Cell(5, 7);
		g[5][8] = new Cell(5, 8);
		g[5][9] = new Cell(5, 9);

		g[6][0] = new Cell(6, 0);
		g[6][1] = new Cell(6, 1);
		g[6][2] = new Cell(6, 2);
		g[6][3] = new Cell(6, 3);
		g[6][4] = new Cell(6, 4);
		g[6][5] = new Cell(6, 5);
		g[6][6] = new Cell(6, 6);
		g[6][7] = new Cell(6, 7);
		g[6][8] = new Cell(6, 8);
		g[6][9] = new Cell(6, 9);

		g[7][0] = new Cell(7, 0);
		g[7][1] = new Cell(7, 1);
		g[7][2] = new Cell(7, 2);
		g[7][3] = new Cell(7, 3);
		g[7][4] = new Cell(7, 4);
		g[7][5] = new Cell(7, 5);
		g[7][6] = new Cell(7, 6);
		g[7][7] = new Cell(7, 7);
		g[7][8] = new Cell(7, 8);
		g[7][9] = new Cell(7, 9);

		g[8][0] = new Cell(8, 0, "Immovable");
		g[8][1] = new Cell(8, 1, "Immovable");
		g[8][2] = new Cell(8, 2);
		g[8][3] = new Cell(8, 3);
		g[8][4] = new Cell(8, 4);
		g[8][5] = new Cell(8, 5);
		g[8][6] = new Cell(8, 6);
		g[8][7] = new Cell(8, 7, "Immovable");
		g[8][8] = new Cell(8, 8);
		g[8][9] = new Cell(8, 9);

		g[9][0] = new Cell(9, 0);
		g[9][1] = new Cell(9, 1);
		g[9][2] = new Cell(9, 2, "R2D2");
		g[9][3] = new Cell(9, 3, "Immovable");
		g[9][4] = new Cell(9, 4);
		g[9][5] = new Cell(9, 5);
		g[9][6] = new Cell(9, 6);
		g[9][7] = new Cell(9, 7);
		g[9][8] = new Cell(9, 8);
		g[9][9] = new Cell(9, 9);

		Grid grid = new Grid(10, 10);
		grid.grid = g;
		grid.r2d2Orientation = "North";
		grid.r2d2 = g[9][2];
		grid.numberOfPadsRemaningWithoutRocks = 3;

		return grid;
	}

	public static Grid testingGrid10by102() {
		Cell[][] g = new Cell[10][10];
		g[0][0] = new Cell(0, 0, "Immovable");
		g[0][1] = new Cell(0, 1, "Pad");
		g[0][2] = new Cell(0, 2, "Rock");
		g[0][3] = new Cell(0, 3);
		g[0][4] = new Cell(0, 4);
		g[0][5] = new Cell(0, 5);
		g[0][6] = new Cell(0, 6);
		g[0][7] = new Cell(0, 7);
		g[0][8] = new Cell(0, 8);
		g[0][9] = new Cell(0, 9);

		g[1][0] = new Cell(1, 0);
		g[1][1] = new Cell(1, 1);
		g[1][2] = new Cell(1, 2);
		g[1][3] = new Cell(1, 3);
		g[1][4] = new Cell(1, 4);
		g[1][5] = new Cell(1, 5);
		g[1][6] = new Cell(1, 6);
		g[1][7] = new Cell(1, 7);
		g[1][8] = new Cell(1, 8);
		g[1][9] = new Cell(1, 9);

		g[2][0] = new Cell(2, 0);
		g[2][1] = new Cell(2, 1);
		g[2][2] = new Cell(2, 2);
		g[2][3] = new Cell(2, 3);
		g[2][4] = new Cell(2, 4);
		g[2][5] = new Cell(2, 5);
		g[2][6] = new Cell(2, 6);
		g[2][7] = new Cell(2, 7);
		g[2][8] = new Cell(2, 8);
		g[2][9] = new Cell(2, 9);

		g[3][0] = new Cell(3, 0);
		g[3][1] = new Cell(3, 1);
		g[3][2] = new Cell(3, 2);
		g[3][3] = new Cell(3, 3);
		g[3][4] = new Cell(3, 4);
		g[3][5] = new Cell(3, 5);
		g[3][6] = new Cell(3, 6);
		g[3][7] = new Cell(3, 7);
		g[3][8] = new Cell(3, 8);
		g[3][9] = new Cell(3, 9);

		g[4][0] = new Cell(4, 0);
		g[4][1] = new Cell(4, 1);
		g[4][2] = new Cell(4, 2);
		g[4][3] = new Cell(4, 3);
		g[4][4] = new Cell(4, 4);
		g[4][5] = new Cell(4, 5);
		g[4][6] = new Cell(4, 6);
		g[4][7] = new Cell(4, 7);
		g[4][8] = new Cell(4, 8);
		g[4][9] = new Cell(4, 9);

		g[5][0] = new Cell(5, 0);
		g[5][1] = new Cell(5, 1);
		g[5][2] = new Cell(5, 2);
		g[5][3] = new Cell(5, 3);
		g[5][4] = new Cell(5, 4);
		g[5][5] = new Cell(5, 5);
		g[5][6] = new Cell(5, 6);
		g[5][7] = new Cell(5, 7, "R2D2");
		g[5][8] = new Cell(5, 8);
		g[5][9] = new Cell(5, 9);

		g[6][0] = new Cell(6, 0);
		g[6][1] = new Cell(6, 1);
		g[6][2] = new Cell(6, 2);
		g[6][3] = new Cell(6, 3);
		g[6][4] = new Cell(6, 4);
		g[6][5] = new Cell(6, 5);
		g[6][6] = new Cell(6, 6);
		g[6][7] = new Cell(6, 7);
		g[6][8] = new Cell(6, 8);
		g[6][9] = new Cell(6, 9);

		g[7][0] = new Cell(7, 0);
		g[7][1] = new Cell(7, 1);
		g[7][2] = new Cell(7, 2);
		g[7][3] = new Cell(7, 3);
		g[7][4] = new Cell(7, 4);
		g[7][5] = new Cell(7, 5);
		g[7][6] = new Cell(7, 6);
		g[7][7] = new Cell(7, 7);
		g[7][8] = new Cell(7, 8);
		g[7][9] = new Cell(7, 9);

		g[8][0] = new Cell(8, 0);
		g[8][1] = new Cell(8, 1);
		g[8][2] = new Cell(8, 2);
		g[8][3] = new Cell(8, 3);
		g[8][4] = new Cell(8, 4);
		g[8][5] = new Cell(8, 5);
		g[8][6] = new Cell(8, 6);
		g[8][7] = new Cell(8, 7);
		g[8][8] = new Cell(8, 8);
		g[8][9] = new Cell(8, 9);

		g[9][0] = new Cell(9, 0);
		g[9][1] = new Cell(9, 1);
		g[9][2] = new Cell(9, 2);
		g[9][3] = new Cell(9, 3, "Teleportal");
		g[9][4] = new Cell(9, 4);
		g[9][5] = new Cell(9, 5);
		g[9][6] = new Cell(9, 6);
		g[9][7] = new Cell(9, 7);
		g[9][8] = new Cell(9, 8);
		g[9][9] = new Cell(9, 9);

		Grid grid = new Grid(10, 10);
		grid.grid = g;
		grid.r2d2Orientation = "South";
		grid.r2d2 = g[5][6];
		grid.numberOfPadsRemaningWithoutRocks = 1;

		return grid;
	}
	
	
	

	public static void main(String[] args) {
		
		
		// Project 2 start
		
		HelpR2D2 searcher = new HelpR2D2();
		
		Node initialState = searcher.genGrid(3, 3);
		initialState.grid = testingGridKB1();
		initialState.grid.showGrid();
		
		PrintWriter out = null;
		
		try{
			 out = new PrintWriter( "kb1.pl" );
			 for(int i =0; i<initialState.grid.createKB().size();i++)
				 out.println( initialState.grid.createKB().get(i) );
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
				out.close();
		}
		
		Node initialState1 = searcher.genGrid(3, 3);
		initialState1.grid = testingGridKB2();
		initialState1.grid.showGrid();
		
		
		try{
			 out = new PrintWriter( "kb2.pl" );
			 for(int i =0; i<initialState1.grid.createKB().size();i++)
				 out.println( initialState1.grid.createKB().get(i) );
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
				out.close();
		}
		
//		
		 
			
			
			
	   // Rest is project 1
			
			
		
		// uncomment if you do want to allow repeated states
		//searcher.setEliminateRepeatedStates(false);
		
		// uncomment if you want to use h1, h2 is used by default
		//searcher.setUseFirstHeuristic(true);
		
		// search strategies' names
		/*
		 * BFS
		 * DFS
		 * IDS
		 * UCS
		 * GRS
		 * ASS
		 * 
		 */
		

		// -------------------------------
		// TESTING WITH 3X3
		// -------------------------------
	
		// Example 1
//		Node initialState = searcher.genGrid(3, 3);
//		initialState.grid = testingGrid();
//		initialState.grid.showGrid();
//	    for(int i =0; i<initialState.grid.createKB().size();i++)
//		System.out.println(initialState.grid.createKB().get(i));
//		searcher.getSolution(initialState, "BFS", true);
		
		// Example 2
		//Node initialState = searcher.genGrid(3, 3);
//		initialState.grid = testingGrid1();
//		initialState.grid.showGrid();
//		searcher.getSolution(initialState, "BFS", false);
		
		// Example 3
		//Node initialState = searcher.genGrid(3, 3);
//		initialState.grid = testingGrid3();
//		initialState.grid.showGrid();
//		searcher.getSolution(initialState, "BFS", false);
		
		// Example 4
		//Node initialState = searcher.genGrid(3, 3);
//		initialState.grid = testingGrid4();
//		initialState.grid.showGrid();
//		searcher.getSolution(initialState, "BFS", false);
		
		// Example 5 **
//		Node initialState = searcher.genGrid(3, 3);
//		initialState.grid = testingGrid5();
//		initialState.grid.showGrid();
//		searcher.getSolution(initialState, "BFS", false);
		
		
		// Example 6 *
//		Node initialState = searcher.genGrid(3, 3);
//		initialState.grid = testingGrid6();
//		initialState.grid.showGrid();
//		searcher.getSolution(initialState, "BFS", false);
		
		// Example 7
//		Node initialState = searcher.genGrid(3, 3);
//		initialState.grid = testingGrid9();
//		initialState.grid.showGrid();
//		searcher.getSolution(initialState, "BFS", false);
		
		
		
		// -------------------------------
		// TESTING WITH 4X4
		// -------------------------------
		
			
//		 Example 8 ***
//		Node initialState = searcher.genGrid(4, 4);	
//		initialState.grid = testingGrid2();
//		initialState.grid.showGrid();
//		searcher.getSolution(initialState, "BFS", false);
		
		
		// -------------------------------
		// TESTING WITH 5X5
		// -------------------------------
			
//		 Example 9
//		Node initialState = searcher.genGrid(5, 5);	
//		initialState.grid = testingGrid10();
//		initialState.grid.showGrid();
//		searcher.getSolution(initialState, "BFS", false);
		
		// Example 10
//		Node initialState = searcher.genGrid(5, 5);	
//		initialState.grid = testingGrid11();
//		initialState.grid.showGrid();
//		searcher.getSolution(initialState, "BFS", false);
		
		// Example 11 ****
//		Node initialState = searcher.genGrid(5, 5);	
//		initialState.grid = testingGrid12();
//		initialState.grid.showGrid();
//		searcher.getSolution(initialState, "GRS", false);
		
		
		// -------------------------------
		// TESTING WITH 10X10
		// -------------------------------

		//Example 12 ***
//		Node initialState = searcher.genGrid(10, 10);	
//		initialState.grid = testingGrid10by102();
//		initialState.grid.showGrid();
//		searcher.getSolution(initialState, "BFS", false);
		
		
		//Example 13, this is the hardest it will take some time ******
//		Node initialState = searcher.genGrid(10, 10);	
//		initialState.grid = testingGrid10by10();
//		initialState.grid.showGrid();
//		 for(int i =0; i<initialState.grid.createKB().size();i++)
//				System.out.println(initialState.grid.createKB().get(i));
//		searcher.getSolution(initialState, "BFS", false);
		
		// -------------------------------
		// TESTING A Random grid, change width and length as you like
		// -------------------------------
		
		//Node initialState = searcher.genGrid(3, 3);
		//searcher.getSolution(initialState, "BFS", false);
		

		
	}
}
