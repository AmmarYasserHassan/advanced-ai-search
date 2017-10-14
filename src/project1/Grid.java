package project1;


public class Grid {

	Cell[][] grid;
	int length;
	int width;
	Cell r2d2;
	String r2d2Orientation;
	boolean atTeleportalCell;
	int numberOfPadsRemaningWithoutRocks;
	static String [] locations = {"North","East","South","West"};

	public Grid(int min, int max) {
		this.length = min;
		this.width = max;
		this.grid = new Cell[this.length][this.width];
		this.atTeleportalCell = false;
		this.initializeGrid();
	}
	
	public Grid(int min, int max,boolean value) {
		this.length = min;
		this.width = max;
		this.grid = new Cell[this.length][this.width];
		this.atTeleportalCell = false;
	}
	
	public void initializeGrid() {

		boolean teleportalAdded = false;
		
		int newI = (int) (Math.random() * this.length);
		int newJ = (int) (Math.random() * this.width);
		this.grid[newI][newJ] = new Cell(newI,newJ);
		
		// Added R2D2
		this.grid[newI][newJ].addElement("R2D2");
		this.r2d2 = this.grid[newI][newJ];
		this.r2d2Orientation = locations[(int) (Math.random() *4)];
		
		// loop for adding Teleportal
		while (!teleportalAdded) {
				 newI = (int) (Math.random() * this.length);
				 newJ = (int) (Math.random() * this.width);
				 
				if (this.grid[newI][newJ] == null) {
					teleportalAdded = true;
					this.grid[newI][newJ] = new Cell(newI,newJ);
					this.grid[newI][newJ].addElement("Teleportal");
				}
		}

		
		//TODO
		
		int maxPadsRocks = (int) ((this.length*this.width-2)/2)-1;
		int numberOfPadsToBeAdded = (int) (Math.random()*maxPadsRocks)+1;
		//int numberOfPadsToBeAdded = 1;
		int numberOfRocksToBeAdded = numberOfPadsToBeAdded;
		this.numberOfPadsRemaningWithoutRocks = numberOfRocksToBeAdded;
 		
	    while(numberOfPadsToBeAdded >0)
	    {
			 newI = (int) (Math.random() * this.length);
			 newJ = (int) (Math.random() * this.width);
			 if (this.grid[newI][newJ] == null) {
				this.grid[newI][newJ] = new Cell(newI,newJ);
				this.grid[newI][newJ].addElement("Pad");
				numberOfPadsToBeAdded--;
			}	 
	    	
	    }
		
	    
	    while(numberOfRocksToBeAdded >0)
	    {
			 newI = (int) (Math.random() * this.length);
			 newJ = (int) (Math.random() * this.width);
			 if (this.grid[newI][newJ] == null) {
				this.grid[newI][newJ] =new Cell(newI,newJ);
				this.grid[newI][newJ].addElement("Rock");
				numberOfRocksToBeAdded--;
			}	 
	    	
	    }
	  
	    
	 // loops for adding Immovable
	   
	    //TODO change chance
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.grid[i][j] == null) {
					this.grid[i][j] = new Cell(i,j);
					int index = (int) (Math.random() * 10);
					if(index==1)
					{
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
				if(this.grid[i][j].elements.contains("R2D2"))
				{
				switch(this.r2d2Orientation)
				{
				case "North": System.out.print(" ^ ");break;
				case "East": System.out.print(" > ");break;
				case "West": System.out.print(" < ");break;
				case "South": System.out.print(" v ");break;
				default: break;
				}
				}
				System.out.print(" | ");
			}			
			System.out.println();
			System.out.println();
		}
		System.out.println(this.r2d2Orientation);
	}

	
	
	public void rotateRight()
	{
	   int index = 0;
	   
	   for (int i = 0; i < locations.length; i++) {
		if(locations[i].equals(r2d2Orientation))
			index = i;
	   }
	   
	   this.r2d2Orientation = locations[(index+1)%locations.length];
	   
	}
	
	public void rotateLeft()
	{
		 int index = 0;
		 
		   for (int i = 0; i < locations.length; i++) {
			if(locations[i].equals(r2d2Orientation))
				index = i;
		   }
		   
		   if(index ==0)
		   {
			   this.r2d2Orientation  = locations[3];
		   }
		   else
		   {
			   this.r2d2Orientation  = locations[(index-1)];
		   }
	}
	
	public Grid copy()
	{
		Grid newGrid = new Grid(this.length,this.width,false);
		
		for(int i=0; i<this.length;i++)
		{
			for(int j=0;j<this.width;j++)
			{
				Cell newCell = this.grid[i][j].copy();
				newGrid.grid[i][j] = newCell;
				if(this.grid[i][j].elements.contains("R2D2")){
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

	public boolean facingAnEdge(Cell cell,String orientation)
	{
		switch(orientation)
		{

		case "North":  
			if(cell.i==0)
			return true;	
			break;
			
		case "East": 
			if(cell.j==this.width-1)
			return true;	
			break;
		
		case "South": 
			if(cell.i==this.length-1)
				return true;
			     break;
		case "West": 
			if(cell.j==0)
				return true;	
				break;
		
			default: return false;
		
		}
		return false;
	}
	
	public boolean facingAnObstacle(Cell cell,String orientation)
	{
		Cell nextCell = getNextCell(cell,orientation);
		if(nextCell.elements.contains("Immovable")){
			return true;
		}
		return false;
	}
	
	public boolean facingARock(Cell cell,String orientation)
	{
		Cell nextCell = getNextCell(cell, orientation);
		if(nextCell.elements.contains("Rock")){
			return true;
		}
		return false;
	}

	
	public Cell getNextCell(Cell cell,String orientation)
	{
		if(facingAnEdge(cell, orientation))
		{
			//we should never be here
			System.err.println("Ahmed Wageeh says: trying to get next cell while facing edge");
			//Return same cell cause forward should make you stuck there
			return cell;
		}
		
		int i = cell.i;
		int j = cell.j;
		switch(orientation)
		{

		case "North":  
			return this.grid[i-1][j];
		case "East": 
			return this.grid[i][j+1];
		case "South": 
			return this.grid[i+1][j];
		case "West": 
			return this.grid[i][j-1];
		
		}
		
		return null;
	}
	
	public boolean rockCanMove(Cell cell,String orientation)	
	{
		if(facingAnEdge(cell, orientation))
			return false;
		Cell nextCell = getNextCell(cell, orientation);
		if(nextCell.elements.contains("Rock") || nextCell.elements.contains("Immovable")){
			return false;
		}
		return true;
	}
	
	
	public void moveR2D2(Cell cell, String orientation)
	{
		Cell nextCell = getNextCell(cell, orientation);
		cell.removeElement("R2D2");
		nextCell.addElement("R2D2");
		this.r2d2 = nextCell;
		
		if(this.r2d2.elements.contains("Teleportal"))
			this.atTeleportalCell = true;
		else
			this.atTeleportalCell = false;
	
	}
	
	public void moveRock(Cell cell, String orientation)
	{
		
		Cell rockCell = cell;
		Cell nextCell = getNextCell(cell, orientation);
		cell.removeElement("Rock");
		nextCell.addElement("Rock");
		
		
		if(rockCell.elements.contains("Pad") && !nextCell.elements.contains("Pad"))
			this.numberOfPadsRemaningWithoutRocks++;
		if(!rockCell.elements.contains("Pad") && nextCell.elements.contains("Pad"))
			this.numberOfPadsRemaningWithoutRocks--;
		
	}
	
	public boolean infeasible() {
		return (thereIsARockInACorner() || thereIsARockSidedByAnEdgeAndNoReachablePadInFront());
	}
	
	public boolean thereIsARockInACorner() {
		
		boolean result = (  
				   this.grid[0][0].elements.contains("Rock") 
				|| this.grid[0][this.width-1].elements.contains("Rock")
				|| this.grid[this.length-1][0].elements.contains("Rock") 
				|| this.grid[this.length-1][this.width-1].elements.contains("Rock")
				);
		
		if(result)
			System.out.println("Rock at a corner, infeasible Solution");
		return result;
	}
	
	

	public boolean thereIsARockSidedByAnEdgeAndNoReachablePadInFront() {
		
		boolean containsRock = false;
		boolean containsPad = false;
		boolean padInFrontOfRock = false;
		
		for(int j =0; j<this.width;j++)
		{
			if(this.grid[0][j].elements.contains("Rock"))
				containsRock = true;
			if(this.grid[0][j].elements.contains("Pad"))
				containsPad = true;
		}
		
		if(containsRock)
			if(containsPad)
			{
				System.out.println("Rock at an edge with no pad infront of it, infeasible Solution");
				return true;
			}
				
		
		for(int j =0; j<this.width;j++)
		{
			if(this.grid[this.length-1][j].elements.contains("Rock"))
				containsRock = true;
			if(this.grid[this.length-1][j].elements.contains("Pad"))
				containsPad = true;
		}
		
		if(containsRock)
			if(containsPad)
			{
				System.out.println("Rock at an edge with no pad infront of it, infeasible Solution");
				return true;
			}
		
		for(int i =0; i<this.length;i++)
		{
			if(this.grid[i][0].elements.contains("Rock"))
				containsRock = true;
			if(this.grid[i][0].elements.contains("Pad"))
				containsPad = true;
		}
		
		if(containsRock)
			if(containsPad)
			{
				System.out.println("Rock at an edge with no pad infront of it, infeasible Solution");
				return true;
			}
		
		for(int i =0; i<this.length;i++)
		{
			
			if(this.grid[i][this.width-1].elements.contains("Rock"))
				containsRock = true;
			if(this.grid[i][this.width-1].elements.contains("Pad"))
				containsPad = true;
		}
		
		if(containsRock)
			if(containsPad)
			{
				System.out.println("Rock at an edge with no pad infront of it, infeasible Solution");
				return true;
			}
		
		return padInFrontOfRock;
		
	}

	public static void main(String[] args) {
		
		Grid g = new Grid(3, 3);
		g.showGrid();

		Grid g2 = g.copy();
		g2.showGrid();
		
		//System.out.println();
		 
//        System.out.println(g.r2d2.i + "," + g.r2d2.j);		
//		g.rotateLeft();
//		g.moveR2D2(g.r2d2.i, g.r2d2.j, g.r2d2Orientation);
//		
//		System.out.println(g.r2d2Orientation);
//		 System.out.println(g.r2d2.i + "," + g.r2d2.j);	
//		 g.showGrid();

	}
		

}
