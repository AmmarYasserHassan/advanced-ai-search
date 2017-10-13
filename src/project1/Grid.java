package project1;


public class Grid {

	Cell[][] grid;
	int length;
	int width;
	int numberOfPads;
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
	
	public void initializeGrid() {

//		boolean teleportalAdded = false;
//		
//		int newI = (int) (Math.random() * this.length);
//		int newJ = (int) (Math.random() * this.width);
//		this.grid[newI][newJ] = new Cell(newI,newJ);
//		// Added R2D2
//		this.grid[newI][newJ].addElement("R2D2");
//		this.r2d2 = this.grid[newI][newJ];
//		this.r2d2Orientation = locations[(int) (Math.random() *4)];
//		
//		// loop for adding Teleportal
//		while (!teleportalAdded) {
//				 newI = (int) (Math.random() * this.length);
//				 newJ = (int) (Math.random() * this.width);
//				String mainElement = "Teleportal";
//				if (this.grid[newI][newJ] == null) {
//						teleportalAdded = true;
//					this.grid[newI][newJ] = new Cell(newI,newJ);
//					this.grid[newI][newJ].addElement(mainElement);
//					
//					
//				}
//		}
//
//		
//		int maxPadsRocks = (int) ((this.length*this.width-2)/2)-1;
////		int numberOfPadsToBeAdded = (int) (Math.random()*maxPadsRocks)+1;
//		int numberOfPadsToBeAdded = 1;
//		int numberOfRocksToBeAdded = numberOfPadsToBeAdded;
//		this.numberOfPads = numberOfRocksToBeAdded;
//		this.numberOfPadsRemaningWithoutRocks = numberOfPads;
// 		
//	    while(numberOfPadsToBeAdded >0)
//	    {
//			 newI = (int) (Math.random() * this.length);
//			 newJ = (int) (Math.random() * this.width);
//			 if (this.grid[newI][newJ] == null) {
//				this.grid[newI][newJ] = new Cell(newI,newJ);
//				this.grid[newI][newJ].addElement("Pad");
//				numberOfPadsToBeAdded--;
//			}	 
//	    	
//	    }
//		
//	    
//	    while(numberOfRocksToBeAdded >0)
//	    {
//			 newI = (int) (Math.random() * this.length);
//			 newJ = (int) (Math.random() * this.width);
//			 if (this.grid[newI][newJ] == null) {
//				this.grid[newI][newJ] =new Cell(newI,newJ);
//				this.grid[newI][newJ].addElement("Rock");
//				numberOfRocksToBeAdded--;
//			}	 
//	    	
//	    }
//	  
//	    
//	 // loops for adding Immovable
//	   
//	    //TODO change chance
//		for (int i = 0; i < this.length; i++) {
//			for (int j = 0; j < this.width; j++) {
//				if (this.grid[i][j] == null) {
//					this.grid[i][j] = new Cell(newI,newJ);
//					int index = (int) (Math.random() * 15);
//					if(index==1)
//					{
//					String element = "Immovable";
//					this.grid[i][j].addElement(element);
//					}
//				}
//
//			}
//		}
//		Cell [][] g = new Cell[3][3];
//        g[0][0] = new Cell(0,0,"Pad");
//        g[0][1] = new Cell(0,1);
//        g[0][2] = new Cell(0,2);
//        
//        g[1][0] = new Cell(1,0,"Rock");
//        g[1][1] = new Cell(1,1);
//        g[1][2] = new Cell(1,2,"R2D2");
//        
//        g[2][0] = new Cell(2,0,"Teleportal");
//        g[2][1] = new Cell(2,1);
//        g[2][2] = new Cell(2,2);
//         
//       
//        
//     
//       this.grid=g;
//       this.r2d2Orientation = "South";
//       this.r2d2 = g[1][2];
//       this.numberOfPads = 1;
       
       
       
       
		  Cell [][] g = new Cell[3][3];
	        g[0][0] = new Cell(0,0);
	        g[0][1] = new Cell(0,1);
	        g[0][2] = new Cell(0,2);
	        
	        g[1][0] = new Cell(1,0,"R2D2");
	        g[1][1] = new Cell(1,1,"Rock");
	        g[1][2] = new Cell(1,2,"Teleportal");
	        
	        g[2][0] = new Cell(2,0);
	        g[2][1] = new Cell(2,1,"Pad");
	        g[2][2] = new Cell(2,2);
	         
	       
	        
	       this.grid=g;
	       this.r2d2Orientation = "East";
	       this.r2d2 = g[1][0];
	       this.numberOfPads = 1;
         
       
      
	}

	public void showGrid() {
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				System.out.print(this.grid[i][j].toString() + " | ");
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
		newGrid.atTeleportalCell = this.atTeleportalCell;
		newGrid.numberOfPadsRemaningWithoutRocks = this.numberOfPadsRemaningWithoutRocks;
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
			if(j==this.width-1)
			return true;	
			break;
		
		case "South": 
			if(i==this.length-1)
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
//		System.out.println(nextCell.i + " , " + nextCell.j + " " + nextCell.elements);
		if(nextCell.elements.contains("Rock")){
			//System.out.println("Rock at " + nextCell.i + "," + nextCell.j);
			return true;
		}
		return false;
	}

	
	public Cell getNextCell(int i, int j,String orientation)
	{
		if(facingAnEdge(i, j, orientation))
		{
			//we should never be here
			System.err.println("Ahmed Wageeh says: trying to get next cell while facing edge");
			//Return same cell cause forward should make you stuck there
			return this.grid[i][j];
		}
		
//		System.out.println("["+i+","+j+"]");
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
		if(facingAnEdge(i, j, orientation))
			return false;
		Cell nextCell = getNextCell(i, j, orientation);
		if(nextCell.elements.contains("Rock") || nextCell.elements.contains("Immovable")){
			return false;
		}
		return true;
	}
	
	
	public void moveR2D2(int i, int j, String orientation)
	{
		Cell nextCell = getNextCell(i, j, orientation);
//		System.out.println(nextCell);
		this.grid[i][j].removeElement("R2D2");
		//this.r2d2.removeElement("R2D2");
		nextCell.addElement("R2D2");
		
		//System.out.println("old cell " + this.r2d2.i + "," + this.r2d2.j + " " + this.r2d2.elements);
		this.r2d2 = nextCell;
		//System.out.println("new cell " + this.r2d2.i + "," + this.r2d2.j+ " " + this.r2d2.elements);

		
		if(this.r2d2.elements.contains("Teleportal"))
			this.atTeleportalCell = true;
		else
			this.atTeleportalCell = false;
	
	}
	
	public void moveRock(int i, int j, String orientation)
	{
		
//		System.out.println("i moved a rock");
		
		Cell rockCell = grid[i][j];
		Cell nextCell = getNextCell(i, j, orientation);
		this.grid[i][j].removeElement("Rock");
		nextCell.addElement("Rock");
		
		
		if(rockCell.elements.contains("Pad") && !nextCell.elements.contains("Pad"))
			this.numberOfPadsRemaningWithoutRocks++;
		if(!rockCell.elements.contains("Pad") && nextCell.elements.contains("Pad"))
			this.numberOfPadsRemaningWithoutRocks--;
			
		
		
	}
	
	public static void main(String[] args) {
		Grid g = new Grid(3, 3);

		g.showGrid();
		
		//System.out.println();
		 
//        System.out.println(g.r2d2.i + "," + g.r2d2.j);		
//		g.rotateLeft();
//		g.moveR2D2(g.r2d2.i, g.r2d2.j, g.r2d2Orientation);
//		
//		System.out.println(g.r2d2Orientation);
//		 System.out.println(g.r2d2.i + "," + g.r2d2.j);	
//		 g.showGrid();

	}
//		

}
