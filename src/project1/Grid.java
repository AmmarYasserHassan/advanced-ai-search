package project1;


public class Grid {

	Cell[][] grid;
	int length;
	int width;
	int numberOfPads;
	Cell r2d2;
	String r2d2Orientation;
	String [] locations = {"North","East","South","West"};

	public Grid(int min, int max) {
		this.length = min;
		this.width = max;
		this.grid = new Cell[this.length][this.width];
	}

	
	public void initializeGrid() {

		boolean teleportalAdded = false;
		
		int newI = (int) (Math.random() * this.length);
		int newJ = (int) (Math.random() * this.width);
		this.grid[newI][newJ] = new Cell(newI,newJ);
		this.grid[newI][newJ].addElement("R2D2");
		
		this.r2d2 = this.grid[newI][newJ];
	
		
		this.r2d2Orientation = this.locations[(int) (Math.random() *4)];
		
		// loop for adding the main elements (Teleportal, R2-D2)
		while (!teleportalAdded) {
				 newI = (int) (Math.random() * this.length);
				 newJ = (int) (Math.random() * this.width);
				String mainElement = "Teleportal";
				if (this.grid[newI][newJ] == null) {
						teleportalAdded = true;
					this.grid[newI][newJ] = new Cell(newI,newJ);
					this.grid[newI][newJ].addElement(mainElement);
					
					
				}
		}

		
		int maxPadsRocks = (int) ((this.length*this.width-2)/2)-1;
		int numberOfPadsToBeAdded = (int) (Math.random()*maxPadsRocks)+1;
		int numberOfRocksToBeAdded = numberOfPadsToBeAdded;
		this.numberOfPads = numberOfRocksToBeAdded;
 		
	    while(numberOfPadsToBeAdded >0)
	    {
			 newI = (int) (Math.random() * this.length);
			 newJ = (int) (Math.random() * this.width);
			 if (this.grid[newI][newJ] == null) {
				this.grid[newI][newJ] = new Cell(newI,newJ);
				this.grid[newI][newJ].addElement("Pads");
				numberOfPadsToBeAdded--;
			}	 
	    	
	    }
		
	    
	    while(numberOfRocksToBeAdded >0)
	    {
			 newI = (int) (Math.random() * this.length);
			 newJ = (int) (Math.random() * this.width);
			 if (this.grid[newI][newJ] == null) {
				this.grid[newI][newJ] =new Cell(newI,newJ);
				this.grid[newI][newJ].addElement("Rocks");
				numberOfRocksToBeAdded--;
			}	 
	    	
	    }
	    String[] possibleElements = {"Empty", "Immovable"};
	    
	 // loops for adding (Empty,Immovable)
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.grid[i][j] == null) {
					this.grid[i][j] = new Cell(newI,newJ);
					int index = (int) (Math.random() * 2);
  
					String element = possibleElements[index];
					this.grid[i][j].addElement(element);
				}

			}
		}
	}

	public void showGrid() {
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {

				System.out.print(this.grid[i][j].toString() + " | ");
			}

			System.out.println();
			System.out.println();
		}
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
	
	public Grid clone()
	{
		Grid newGrid = new Grid(this.length,this.width);
		
		for(int i=0; i<this.length;i++)
		{
			for(int j=0;j<this.width;j++)
			{
				newGrid.grid[i][j] = this.grid[i][j].clone();
			}
		}
		newGrid.length = this.length;
		newGrid.width = this.width;
		newGrid.numberOfPads = this.numberOfPads;
		newGrid.r2d2 = this.r2d2.clone();
		newGrid.r2d2Orientation = this.r2d2Orientation;
		return newGrid;
		
	}

	public boolean facingAnEdge(int i, int j,String orientation)
	{
		switch(orientation)
		{

		case "North":  
			if(i==0)
			return true;	
			break;
			
		case "East": 
			if(j==this.width)
			return true;	
			break;
		
		case "South": 
			if(i==this.length)
				return true;
			     break;
		case "West": 
			if(j==0)
				return true;	
				break;
		
			default: return false;
		
		}
		return false;
	}
	
	public boolean facingAnObstacle(int i, int j,String orientation)
	{
		Cell nextCell = getNextCell(i, j, orientation);
		if(nextCell.elements.contains("Immovable")){
			return true;
		}
		return false;
	}
	
	public boolean facingARock(int i, int j,String orientation)
	{
		Cell nextCell = getNextCell(i, j, orientation);
		if(nextCell.elements.contains("Rock")){
			return true;
		}
		return false;
	}

	
	public Cell getNextCell(int i, int j,String orientation)
	{
		if(facingAnEdge(i, j, orientation))
		{
			System.err.println("Ahmed Wageeh says: trying to get next cell while facing edge");
			return null;
		}
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
	
	public boolean rockCanMove(int i, int j,String orientation)
	{
		Cell nextCell = getNextCell(i, j, orientation);
		if(nextCell.elements.contains("Rock") || nextCell.elements.contains("Immovable") || facingAnEdge(i, j, orientation)){
			return false;
		}
		return true;
	}
	
	
	public void moveR2D2(int i, int j, String orientation)
	{
		Cell nextCell = getNextCell(i, j, orientation);
		nextCell.addElement("R2D2");
		this.grid[i][j].removeElement("R2D2");
	}
	
	public void moveRock(int i, int j, String orientation)
	{
		Cell nextCell = getNextCell(i, j, orientation);
		nextCell.addElement("Rock");
		this.grid[i][j].removeElement("Rock");
	}
	
	public static void main(String[] args) {
		Grid g = new Grid(3, 3);

		g.initializeGrid();
		g.showGrid();
		 
		System.out.println(g.r2d2Orientation);
		
		g.rotateLeft();
		
		System.out.println(g.r2d2Orientation);

	}

}
