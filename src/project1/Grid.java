package project1;

public class Grid 
{
	
	Cell[][] theGrid;
	int length;
	int width;
	String[] possibleElements = {"Empty","Pad","Rock","Immovable","Teleportal"};
	

	
	public Grid(int min, int max)
	{
		this.length = min + (int)(Math.random() * max); 
		this.width = min + (int)(Math.random() * max); 
		this.theGrid = new Cell[this.length][this.width];
	}
	
	
	public void initializeGrid()
	{
		
		boolean addedTeleportal = false;
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				
				this.theGrid[i][j] = new Cell();
				
				int index = (int)(Math.random()*5);
				String element = this.possibleElements[index];
				if(element=="Teleportal")
				{
					if(!addedTeleportal)
					{
						addedTeleportal = true;
						this.theGrid[i][j].addElement(element);
					}
					else
					{ 
						element = this.possibleElements[(int)Math.random()*4];
						this.theGrid[i][j].addElement(element);
					}
					
				}
				else
				{  
					this.theGrid[i][j].addElement(element);	
				}
				
				
			}
		}
	}

	
	public void showGrid()
	{
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				
				System.out.print(this.theGrid[i][j].toString() + " | ");
			}
			
			System.out.println();
			System.out.println();
			}
	}
	
	
	public static void main(String [] args)
	{
		 Grid g = new Grid(3,9);
		 
		 g.initializeGrid();
		 g.showGrid();

	}

}
