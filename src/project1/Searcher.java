package project1;

import java.util.ArrayList;
import java.util.Collections;

public class Searcher {

	static StatesQueue queue = new StatesQueue(); 
	static String [] operators = {"F","RR","RL"}; 
	//static String [] operators = {"F","RR"}; 
	static int numberOfNodesExlored=0;
	
	public static Node initialize(int maxWidth, int maxHeight)
	{
		Grid initialGrid = new Grid(maxWidth,maxHeight);
		int costToReachThisNode = 0;
		String actionTakenFromParentToReachThisNode = "Nothing";
		Node initialState = new Node(initialGrid,costToReachThisNode,null,actionTakenFromParentToReachThisNode);
		int estimatedCostFromThisNodeToTheGoal = estimatedCostFromANodeToTheGoal(initialState);
		initialState.setEstimatedCostFromANodeToTheGoal(estimatedCostFromThisNodeToTheGoal);
		
		return initialState;
		
	}
	
	
	public static Node search(Node initialState,String strategy, boolean visualize)
	{
		
		queue.enque(initialState, "Initial State");
		
		numberOfNodesExlored =1;
		
		int iterations = 0;
		
		while(iterations<600000)
		{
//		while(!queue.isEmpty())
//		{
		
//		while(numberOfNodesExlored<600000)
//		{
			Node currentNode = queue.deque();
			


			
			if(R2D2Became2(currentNode))
			{
				System.out.println("iterations = " + iterations);
				return currentNode;
			}
				
			 
			if(isGoal(currentNode))
			{
				System.out.println("REACHED A SOLUTION!");
				return currentNode;
			}
			ArrayList<Node> childern = expand(currentNode);
			numberOfNodesExlored+= childern.size();
//			System.out.println("size is : " + childern.size());
			queue.enque(childern, strategy);
			
			iterations++;
		}
		
		System.out.println("Reached end of the queue without finding a goal state, termintaed after " + iterations + " interation");
		return null;
	}
	
	public static boolean R2D2Became2(Node n)
	{
		Grid g = n.grid;
		
		int counter = 0;
		
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g.width; j++) {
				if(g.grid[i][j].elements.contains("R2D2"))
					counter++;
			}
		}
		
		if(counter>1)
			return true;
		
		return false;
	}
	
	public static void getSolution(Node initialState,String strategy, boolean visualize)
	{
		Node goal = search(initialState,strategy,visualize);
		ArrayList<Node> pathToGoal = new ArrayList<Node>();
		
		if(goal != null)
		{
			while(goal.Parent!=null)
			{
				pathToGoal.add(goal);
				goal = goal.Parent;
			}
			
		}
		pathToGoal.add(initialState);
		
		Collections.reverse(pathToGoal);
		
        for (int i = 0; i < pathToGoal.size(); i++) {
			pathToGoal.get(i).grid.showGrid();
			System.out.println(pathToGoal.get(i).grid.r2d2Orientation);
			System.out.println(pathToGoal.get(i).actionTakenFromParentToReachThisNode);
			System.out.println();
		}		
	
	}
	
	
	public static boolean isGoal(Node currentNode) {

		return (currentNode.grid.atTeleportalCell && currentNode.grid.numberOfPadsRemaningWithoutRocks == 0)? true:false;
	}


	public static ArrayList<Node> expand(Node n)
	{
		ArrayList<Node> childern = new ArrayList<Node>();
		Node newChild;
		for(String operator: operators)
		{  
			newChild = operate(n,operator);
			if(!(newChild == null))
			{
//				System.out.println();
//				System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" );
//				System.out.println();
//				newChild.grid.showGrid();
//				System.out.println(newChild.grid.r2d2Orientation);
//				System.out.println();
//				System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
//				System.out.println();
			 childern.add(newChild);
			 
			}
		}
		
		return childern;
		
	}
	
	public static Node operate(Node parentNode,String operation)
	{
		
		if(operation.equals("RR"))
		{  
		   int costToReachThisNode = ++parentNode.costToReachThisNode;
		   Node childNode = new Node(parentNode.grid.clone(),costToReachThisNode,parentNode,operation);
		   childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode));
		   childNode.grid.rotateRight();
		   return childNode;
		}
		if(operation.equals("RL"))
		{  
		   int costToReachThisNode = ++parentNode.costToReachThisNode;
		   Node childNode = new Node(parentNode.grid.clone(),costToReachThisNode,parentNode,operation);
		   childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode));
		   childNode.grid.rotateLeft();
		   return childNode;
		}
		
		if(operation.equals("F"))
		{
			if(parentNode.grid.facingAnEdge(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation)){
				System.out.println("facing an edge " + parentNode.grid.r2d2Orientation);
				return null;
				}
			if(parentNode.grid.facingAnObstacle(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation)){
				System.out.println("facing an obstacle");
				return null;
				}
			if(parentNode.grid.facingARock(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation))
			{
				System.out.println("facing a rock");
				Cell nextCell = parentNode.grid.getNextCell(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation);
				if(parentNode.grid.rockCanMove(nextCell.i, nextCell.j, parentNode.grid.r2d2Orientation))
				{     
					System.out.println("rock can move");
					   int costToReachThisNode = ++parentNode.costToReachThisNode;
					   Node childNode = new Node(parentNode.grid.clone(),costToReachThisNode,parentNode,operation);
					   childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode));
					   childNode.grid.moveRock(nextCell.i, nextCell.j, parentNode.grid.r2d2Orientation);
                       childNode.grid.moveR2D2(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation);
                       return childNode;
				}
				else
				{
					System.out.println("rock cant move");
					return null;
				}
						
			}
			System.out.println("facing nothing " + parentNode.grid.r2d2Orientation);
			   int costToReachThisNode = ++parentNode.costToReachThisNode;
			   Node childNode = new Node(parentNode.grid.clone(),costToReachThisNode,parentNode,operation);
			   childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode));
			   childNode.grid.moveR2D2(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation);
               return childNode;
			
			
		}
		
		System.err.println("Ahmed Wageeh says: Unknown operation");
		return null;
	}
	
	// TODO calculate the heurristic value
	public static int estimatedCostFromANodeToTheGoal(Node node)
	{
		return 1;
	}
	
	
	public static Grid testingGrid()
	{
        Cell [][] g = new Cell[3][3];
        g[0][0] = new Cell(0,0);
        g[0][1] = new Cell(0,1,"Immovable");
        g[0][2] = new Cell(0,2,"Immovable");
        
        g[1][0] = new Cell(1,0,"R2D2");
        g[1][1] = new Cell(1,1,"Rock");
        g[1][2] = new Cell(1,2,"Pad");
        
        g[2][0] = new Cell(2,0);
        g[2][1] = new Cell(2,1,"Teleportal");
        g[2][2] = new Cell(2,2);
         
       
        
       Grid grid = new Grid(3,3);
       grid.grid=g;
       grid.r2d2Orientation = "East";
       grid.r2d2 = g[1][0];
       grid.numberOfPads = 1;
       
        
        return grid;
	}
	
	public static Grid testingGrid2()
	{
        Cell [][] g = new Cell[4][4];
        g[0][0] = new Cell(0,0);
        g[0][1] = new Cell(0,1);
        g[0][2] = new Cell(0,2);
        g[0][3] = new Cell(0,3);
        
        g[1][0] = new Cell(1,0,"R2D2");
        g[1][1] = new Cell(1,1);
        g[1][2] = new Cell(1,2);
        g[1][3] = new Cell(0,3,"Rock");
        
        g[2][0] = new Cell(2,0);
        g[2][1] = new Cell(2,1);
        g[2][2] = new Cell(2,2);
        g[2][3] = new Cell(2,3, "Pad");
        
        
        g[3][0] = new Cell(2,0);
        g[3][1] = new Cell(2,1);
        g[3][2] = new Cell(2,2,"Teleportal");
        g[3][3] = new Cell(2,3);
       
        
       Grid grid = new Grid(4,4);
       grid.grid=g;
       grid.r2d2Orientation = "East";
       grid.r2d2 = g[1][0];
       grid.numberOfPads = 1;
       
        
        return grid;
	}
	
	public static void main(String [] args)
	{
		
		Node init = initialize(4,4);
//		init.grid = testingGrid();
		init.grid = testingGrid2();
	//	init.grid.showGrid();
//		System.out.println(init.grid.numberOfPads);
//		System.out.println(init.grid.r2d2Orientation);
//		System.out.println(init.grid.r2d2);
		
//		

		
//		testingGrid().showGrid();
//		System.out.println(testingGrid().r2d2Orientation);
		
	  // search(init, "BFS",false).grid.showGrid();
		
		getSolution(init, "BFS", false);
		
//		init.grid.showGrid();
//		System.out.println(init.grid.r2d2Orientation);
//		
//		System.out.println("XXXXXXXXXXXXXX");
//		
//		operate(init, "F").grid.showGrid();;
		
		
		
	}

	
}
