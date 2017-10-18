package project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Searcher {

	static StatesQueue queue = new StatesQueue(); 
	static String [] operators = {"F","RR","RL"}; 
	static HashMap<String, Boolean> previousStates = new HashMap<String, Boolean>();
	static int numberOfNodesEnqueued = 0;
	static int nodesExpanded = 0;
	static boolean eliminateRepeatedStates = true;
	
	public static Node initialize(int maxWidth, int maxHeight)
	{
		Grid initialGrid = new Grid(maxWidth,maxHeight);
		int costToReachThisNode = 0;
		int depth = 0;
		String actionTakenFromParentToReachThisNode = "Nothing";
		Node initialState = new Node(initialGrid,costToReachThisNode,null,actionTakenFromParentToReachThisNode,depth);
		int estimatedCostFromThisNodeToTheGoal = estimatedCostFromANodeToTheGoal(initialState);
		initialState.setEstimatedCostFromANodeToTheGoal(estimatedCostFromThisNodeToTheGoal);
		return initialState;
		
	}
		
	public static Node search(Node initialState,String strategy, boolean visualize)
	{
//	TODO	
//		if(initialState.grid.infeasible())
//		 return null;
			
		
		queue.enque(initialState, "Initial State");
		
//		while(iterations<7)
//		{
		while(!queue.isEmpty())
		{
		
			Node currentNode = queue.deque();
			
			if(visualize)
			{
			System.out.println();
			System.out.println("-----------------------------------------------------" + nodesExpanded );
			System.out.println();
			currentNode.grid.showGrid();
			System.out.println("Action taken from parent: " + currentNode.actionTakenFromParentToReachThisNode);
			System.out.println("h(n): " +currentNode.estimatedCostFromThisNodeToTheGoal);
			System.out.println("g(n): " +currentNode.costToReachThisNode);
			System.out.println();
			System.out.println("-----------------------------------------------------" );
			System.out.println();
			}
			
			 
			if(isGoal(currentNode))
			{
				System.out.println("REACHED A SOLUTION! in " + nodesExpanded);
				return currentNode;
			}
			ArrayList<Node> childern = expand(currentNode);
			numberOfNodesEnqueued+= childern.size();
//			if(strategy.equals("DFS"))
//				Collections.reverse(childern);
			queue.enque(childern, strategy);
			
			nodesExpanded++;
			
			if(nodesExpanded%100000==0)
				System.out.println(nodesExpanded);
		}
		
		System.out.println("Reached end of the queue without finding a goal state, termintaed after " + nodesExpanded + " iterations");
		return null;
	}
	
	public static void getSolution(Node initialState,String strategy, boolean visualize)
	{
		Node goal;
	    if(strategy.equals("IDS"))
	    	goal = iterativeDeepening(initialState);
	    else
		 goal = search(initialState,strategy,visualize);
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
		
		//initialState.grid.showGrid();
		
		Collections.reverse(pathToGoal);
		
        for (int i = 0; i < pathToGoal.size(); i++) {
			pathToGoal.get(i).grid.showGrid();
			System.out.println(pathToGoal.get(i).actionTakenFromParentToReachThisNode);
			System.out.println();
		}		
	
       System.out.println("Path to goal consists of: "+  pathToGoal.size() + " moves"); 
        
	}
	
	public static boolean isGoal(Node currentNode) {

		return (currentNode.grid.atTeleportalCell && currentNode.grid.numberOfPadsRemaningWithoutRocks == 0);
	}

	public static ArrayList<Node> expand(Node n)
	{
		ArrayList<Node> childern = new ArrayList<Node>();
		Node newChild;
		boolean canOperate = true;
		for(String operator: operators)
		{  

			if(n.actionTakenFromParentToReachThisNode.equals("RR") && operator.equals("RL") ||
					n.actionTakenFromParentToReachThisNode.equals("RL") && operator.equals("RR"))
				canOperate = false;

			if(canOperate)
			{
				newChild = operate(n,operator);
				if(newChild != null)
				{

					if(eliminateRepeatedStates){
						if(!previousStates.containsKey(newChild.grid.getGridHash())){
							childern.add(newChild);
							previousStates.put(newChild.grid.getGridHash(), true);
						}
					}else{
						childern.add(newChild);	
					}

				}
			}
		}

		return childern;

	}
	
	public static Node operate(Node parentNode,String operation)
	{
		int newDepth = parentNode.depth+1;
		
		if(operation.equals("RR"))
		{  
		   parentNode.costToReachThisNode+=2;
		   Node childNode = new Node(parentNode.grid.copy(),parentNode.costToReachThisNode,parentNode,operation,newDepth);
		   childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode));
		   childNode.grid.rotateRight();
		   return childNode;
		}
		if(operation.equals("RL"))
		{  
		   parentNode.costToReachThisNode+=2;
		   Node childNode = new Node(parentNode.grid.copy(),parentNode.costToReachThisNode,parentNode,operation,newDepth);
		   childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode));
		   childNode.grid.rotateLeft();
		   return childNode;
		}
		
		if(operation.equals("F"))
		{
			if(parentNode.grid.facingAnEdge(parentNode.grid.r2d2, parentNode.grid.r2d2Orientation)){
//				System.out.println("facing an edge " );
				return null;
				}
			if(parentNode.grid.facingAnObstacle(parentNode.grid.r2d2, parentNode.grid.r2d2Orientation)){
//				System.out.println("facing an obstacle");
				return null;
				}
			if(parentNode.grid.facingARock(parentNode.grid.r2d2, parentNode.grid.r2d2Orientation))
			{
//				System.out.println("facing a rock");
				Cell rockCell = parentNode.grid.getNextCell(parentNode.grid.r2d2, parentNode.grid.r2d2Orientation);
				if(parentNode.grid.rockCanMove(rockCell, parentNode.grid.r2d2Orientation))
				{     
//					System.out.println("rock can move");
					   ++parentNode.costToReachThisNode;
					   Node childNode = new Node(parentNode.grid.copy(),parentNode.costToReachThisNode,parentNode,operation,newDepth);
					   childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode));
					   rockCell = childNode.grid.getNextCell(childNode.grid.r2d2, childNode.grid.r2d2Orientation);
					   childNode.grid.moveRock(rockCell, childNode.grid.r2d2Orientation);
                       childNode.grid.moveR2D2(childNode.grid.r2d2, childNode.grid.r2d2Orientation);
                       return childNode;
				}
				else
				{
//					System.out.println("rock cant move");
					return null;
				}
						
			}
//			System.out.println("facing nothing ");
			
			   ++parentNode.costToReachThisNode;
			   Node childNode = new Node(parentNode.grid.copy(),parentNode.costToReachThisNode,parentNode,operation,newDepth);
			   childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode));
			   childNode.grid.moveR2D2(childNode.grid.r2d2, childNode.grid.r2d2Orientation);
               return childNode;
			
			
		}
		
		System.err.println("Ahmed Wageeh says: Unknown operation");
		return null;
	}
	
	
	public static int manhtanDistance(Cell c1,Cell c2)
	{
		return Math.abs(c1.i-c2.i) + Math.abs(c1.j-c2.j);
	}
	
	public static int estimatedCostFromANodeToTheGoal(Node node)
	{
		
		//return h1(node);
		//
		return h2(node);

	}
	

	//Go to a random rock with no pad first
	public static int h1(Node n)
	{ 
		if(isGoal(n))
			return 0;
		
	  Cell rockCell = getRockNotOnPadCell(n);
	  Cell r2d2Cell = n.grid.r2d2;
	  if(rockCell == null)
		  return 1;
	  else
		  return manhtanDistance(rockCell, r2d2Cell);
	}
	
	//Go to nearest rock first
	public static int h2(Node n)
	{   
		if(isGoal(n))
		 return 0;
		
		Cell rockCell = getFartherRockNotOnPadCell(n);
		 if(rockCell == null)
			  return 1;
		 
		return manhtanDistance(n.grid.r2d2,rockCell);
	}
	
	public static Cell getRockNotOnPadCell(Node n)
	{
		Grid g = n.grid;
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g.width; j++) {
			if(g.grid[i][j].elements.contains("Rock") && !g.grid[i][j].elements.contains("Pad"))
			{
				return g.grid[i][j];
			}
			}
		}
		
		return null;
	}
	
	public static Cell getFartherRockNotOnPadCell(Node n)
	{
		Grid g = n.grid;
		int max = 0;
		int temp = 0;
		Cell tempCell = null;
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g.width; j++) {
			if(g.grid[i][j].elements.contains("Rock") && !g.grid[i][j].elements.contains("Pad"))
			{
				temp = manhtanDistance(g.grid[i][j],n.grid.r2d2);
				if(temp>=max)
				{
					max = temp;
					tempCell = g.grid[i][j];
				}
			}
			}
		}
		
		return tempCell;
	}
	
	public static Node depthLimitedSearch(Node initialState,int limit)
	{
		queue.enque(initialState, "Initial State");
		
		while(!queue.isEmpty())
		{
		
			Node currentNode = queue.deque();
			nodesExpanded++;
			if(isGoal(currentNode))
			{
				System.out.println("REACHED A SOLUTION! in " + limit + " depth and in " + nodesExpanded + " iterations");
				return currentNode;
			}
			
			if(currentNode.depth<limit)
			{
			ArrayList<Node> childern = expand(currentNode);
			numberOfNodesEnqueued+= childern.size();
			queue.enque(childern, "DFS");	
			}
				
			if(nodesExpanded%100000==0)
			{
				System.out.println(queue.size());
				System.out.println(nodesExpanded);
			}
	
		}
		
		return null;
	}
	
	
	public static Node iterativeDeepening(Node initialState)
	{
		int limit = 0;
        while(true)
        {
        	Node goal = depthLimitedSearch(initialState, limit);
        	if(goal!=null)
        	       return goal;
        	previousStates.clear();
        	numberOfNodesEnqueued = 0;
        	nodesExpanded = 0;
        	limit++;	
        }
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
       grid.numberOfPadsRemaningWithoutRocks = 1;

       
        
        return grid;
	}
	
	public static Grid testingGrid6()
	{
        Cell [][] g = new Cell[3][3];
        g[0][0] = new Cell(0,0,"Pad");
        g[0][1] = new Cell(0,1);
        g[0][2] = new Cell(0,2);
        
        g[1][0] = new Cell(1,0,"Rock");
        g[1][1] = new Cell(1,1);
        g[1][2] = new Cell(1,2,"R2D2");
        
        g[2][0] = new Cell(2,0,"Teleportal");
        g[2][1] = new Cell(2,1);
        g[2][2] = new Cell(2,2);
         
       
        
       Grid grid = new Grid(3,3);
       grid.grid=g;
       grid.r2d2Orientation = "South";
       grid.r2d2 = g[1][2];
       
       
        
        return grid;
	}
	
	public static Grid testingGrid3()
	{
        Cell [][] g = new Cell[3][3];
        g[0][0] = new Cell(0,0);
        g[0][1] = new Cell(0,1,"R2D2");
        g[0][2] = new Cell(0,2);
        
        g[1][0] = new Cell(1,0,"Pad");
        g[1][1] = new Cell(1,1,"Rock");
        g[1][2] = new Cell(1,2);
        
        g[2][0] = new Cell(2,0);
        g[2][1] = new Cell(2,1,"Teleportal");
        g[2][2] = new Cell(2,2);
         
       
        
       Grid grid = new Grid(3,3);
       grid.grid=g;
       grid.r2d2Orientation = "East";
       grid.r2d2 = g[0][1];
       grid.numberOfPadsRemaningWithoutRocks = 1;
       
       
        
        return grid;
	}
	
	public static Grid testingGrid4()
	{
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
         
       
        
       Grid grid = new Grid(3,3);
       grid.grid=g;
       grid.r2d2Orientation = "East";
       grid.r2d2 = g[1][0];
       
       
        
        return grid;
	}
	
	public static Grid testingGrid5()
	{
        Cell [][] g = new Cell[3][3];
        g[0][0] = new Cell(0,0,"Pad");
        g[0][1] = new Cell(0,1,"R2D2");
        g[0][2] = new Cell(0,2);
        
        g[1][0] = new Cell(1,0);
        g[1][1] = new Cell(1,1,"Rock");
        g[1][2] = new Cell(1,2);
        
        g[2][0] = new Cell(2,0,"Teleportal");
        g[2][1] = new Cell(2,1);
        g[2][2] = new Cell(2,2);
         
       
        
       Grid grid = new Grid(3,3);
       grid.grid=g;
       grid.r2d2Orientation = "West";
       grid.r2d2 = g[0][1];
       grid.numberOfPadsRemaningWithoutRocks = 1;
       
        
        return grid;
	}
	
	public static Grid testingGrid1()
	{
        Cell [][] g = new Cell[3][3];
        g[0][0] = new Cell(0,0);
        g[0][1] = new Cell(0,1);
        g[0][2] = new Cell(0,2,"Pad");
        
        g[1][0] = new Cell(1,0,"Teleportal");
        g[1][1] = new Cell(1,1,"Rock");
        g[1][2] = new Cell(1,2);
        
        g[2][0] = new Cell(2,0,"R2D2");
        g[2][1] = new Cell(2,1);
        g[2][2] = new Cell(2,2);
         
       
        
       Grid grid = new Grid(3,3);
       grid.grid=g;
       grid.r2d2Orientation = "South";
       grid.r2d2 = g[2][0];
       grid.numberOfPadsRemaningWithoutRocks = 1;
       
        
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
        g[1][3] = new Cell(1,3,"Rock");
        
        g[2][0] = new Cell(2,0);
        g[2][1] = new Cell(2,1);
        g[2][2] = new Cell(2,2);
        g[2][3] = new Cell(2,3, "Pad");
        
        
        g[3][0] = new Cell(3,0);
        g[3][1] = new Cell(3,1);
        g[3][2] = new Cell(3,2,"Teleportal");
        g[3][3] = new Cell(3,3);
       
        
       Grid grid = new Grid(4,4);
       grid.grid=g;
       grid.r2d2Orientation = "East";
       grid.r2d2 = g[1][0];
       grid.numberOfPadsRemaningWithoutRocks = 1;
       
       
        
        return grid;
	}
	
	public static Grid testingGrid8()
	{
        Cell [][] g = new Cell[4][4];
        g[0][0] = new Cell(0,0);
        g[0][1] = new Cell(0,1);
        g[0][2] = new Cell(0,2,"Rock");
        g[0][3] = new Cell(0,3,"Immovable");
        
        g[1][0] = new Cell(1,0,"R2D2");
        g[1][1] = new Cell(1,1);
        g[1][2] = new Cell(1,2,"Immovable");
        g[1][3] = new Cell(1,3);
        
        g[2][0] = new Cell(2,0);
        g[2][1] = new Cell(2,1);
        g[2][2] = new Cell(2,2);
        g[2][3] = new Cell(2,3);
        
        
        g[3][0] = new Cell(3,0);
        g[3][1] = new Cell(3,1);
        g[3][2] = new Cell(3,2, "Pad");
        g[3][3] = new Cell(3,3,"Teleportal");
       
        
       Grid grid = new Grid(4,4);
       grid.grid=g;
       grid.r2d2Orientation = "East";
       grid.r2d2 = g[1][0];
       
       
        
        return grid;
	}
	
	public static Grid testingGrid10()
	{
        Cell [][] g = new Cell[5][5];
        g[0][0] = new Cell(0,0);
        g[0][1] = new Cell(0,1);
        g[0][2] = new Cell(0,2);
        g[0][3] = new Cell(0,3);
        g[0][4] = new Cell(0,4);
        
        g[1][0] = new Cell(1,0);
        g[1][1] = new Cell(1,1,"Teleportal");
        g[1][2] = new Cell(1,2);
        g[1][3] = new Cell(1,3,"Immovable");
        g[1][4] = new Cell(1,4,"R2D2");
        
        g[2][0] = new Cell(2,0);
        g[2][1] = new Cell(2,1);
        g[2][2] = new Cell(2,2,"Rock");
        g[2][3] = new Cell(2,3);
        g[2][4] = new Cell(2,4);
        
        
        g[3][0] = new Cell(3,0);
        g[3][1] = new Cell(3,1);
        g[3][2] = new Cell(3,2);
        g[3][3] = new Cell(3,3);
        g[3][4] = new Cell(3,4,"Immovable");

        g[4][0] = new Cell(4,0);
        g[4][1] = new Cell(4,1);
        g[4][2] = new Cell(4,2);
        g[4][3] = new Cell(4,3);
        g[4][4] = new Cell(4,4,"Pad");
       
        
       Grid grid = new Grid(5,5);
       grid.grid=g;
       grid.r2d2Orientation = "West";
       grid.r2d2 = g[1][4];
       grid.numberOfPadsRemaningWithoutRocks =1;
       
       
        
        return grid;
	}
	
	
	public static Grid testingGrid11()
	{
        Cell [][] g = new Cell[5][5];
        g[0][0] = new Cell(0,0);
        g[0][1] = new Cell(0,1);
        g[0][2] = new Cell(0,2);
        g[0][3] = new Cell(0,3);
        g[0][4] = new Cell(0,4);
        
        g[1][0] = new Cell(1,0,"Pad");
        g[1][1] = new Cell(1,1,"Teleportal");
        g[1][2] = new Cell(1,2);
        g[1][3] = new Cell(1,3,"Immovable");
        g[1][4] = new Cell(1,4,"R2D2");
        
        g[2][0] = new Cell(2,0);
        g[2][1] = new Cell(2,1);
        g[2][2] = new Cell(2,2,"Rock");
        g[2][3] = new Cell(2,3);
        g[2][4] = new Cell(2,4);
        
        
        g[3][0] = new Cell(3,0,"Rock");
        g[3][1] = new Cell(3,1);
        g[3][2] = new Cell(3,2);
        g[3][3] = new Cell(3,3);
        g[3][4] = new Cell(3,4,"Immovable");

        g[4][0] = new Cell(4,0);
        g[4][1] = new Cell(4,1);
        g[4][2] = new Cell(4,2);
        g[4][3] = new Cell(4,3);
        g[4][4] = new Cell(4,4,"Pad");
       
        
       Grid grid = new Grid(5,5);
       grid.grid=g;
       grid.r2d2Orientation = "West";
       grid.r2d2 = g[1][4];
       grid.numberOfPadsRemaningWithoutRocks =2;
       
       
        
        return grid;
	}
	
	public static Grid testingGrid9()
	{

        Cell [][] g = new Cell[3][3];
        g[0][0] = new Cell(0,0);
        g[0][1] = new Cell(0,1,"Immovable");
        g[0][2] = new Cell(0,2,"Immovable");
        
        g[1][0] = new Cell(1,0,"R2D2");
        g[1][1] = new Cell(1,1,"Rock");
        g[1][2] = new Cell(1,2,"Pad");
        g[1][1].addElement("Teleportal");
        
        g[2][0] = new Cell(2,0);
        g[2][1] = new Cell(2,1,"Teleportal");
        g[2][2] = new Cell(2,2);
         
       
        
       Grid grid = new Grid(3,3);
       grid.grid=g;
       grid.r2d2Orientation = "West";
       grid.r2d2 = g[1][0];
       grid.numberOfPadsRemaningWithoutRocks = 1;

       
        
        return grid;
	}
	
	public static Grid testingGrid12()
	{
        Cell [][] g = new Cell[5][5];
        g[0][0] = new Cell(0,0);
        g[0][1] = new Cell(0,1);
        g[0][2] = new Cell(0,2);
        g[0][3] = new Cell(0,3);
        g[0][4] = new Cell(0,4);
        
        g[1][0] = new Cell(1,0,"Pad");
        g[1][1] = new Cell(1,1,"Teleportal");
        g[1][2] = new Cell(1,2);
        g[1][3] = new Cell(1,3,"Immovable");
        g[1][4] = new Cell(1,4,"R2D2");
        
        g[2][0] = new Cell(2,0);
        g[2][1] = new Cell(2,1,"Rock");
        g[2][2] = new Cell(2,2,"Rock");
        g[2][3] = new Cell(2,3);
        g[2][4] = new Cell(2,4);
        
        
        g[3][0] = new Cell(3,0);
        g[3][1] = new Cell(3,1);
        g[3][2] = new Cell(3,2);
        g[3][3] = new Cell(3,3);
        g[3][4] = new Cell(3,4,"Immovable");

        g[4][0] = new Cell(4,0);
        g[4][1] = new Cell(4,1);
        g[4][2] = new Cell(4,2);
        g[4][3] = new Cell(4,3);
        g[4][4] = new Cell(4,4,"Pad");
       
        
       Grid grid = new Grid(5,5);
       grid.grid=g;
       grid.r2d2Orientation = "West";
       grid.r2d2 = g[1][4];
       grid.numberOfPadsRemaningWithoutRocks =2;
       
       
        
        return grid;
	}

	public static Grid testingGrid10by10()
	{
        Cell [][] g = new Cell[10][10];
        g[0][0] = new Cell(0,0,"Immovable");
        g[0][1] = new Cell(0,1,"Pad");
        g[0][2] = new Cell(0,2,"Rock");
        g[0][3] = new Cell(0,3);
        g[0][4] = new Cell(0,4);
        g[0][5] = new Cell(0,5);
        g[0][6] = new Cell(0,6);
        g[0][7] = new Cell(0,7);
        g[0][8] = new Cell(0,8);
        g[0][9] = new Cell(0,9,"Teleportal");
        
        g[1][0] = new Cell(1,0);
        g[1][1] = new Cell(1,1);
        g[1][2] = new Cell(1,2);
        g[1][3] = new Cell(1,3);
        g[1][4] = new Cell(1,4);
        g[1][5] = new Cell(1,5,"Immovable");
        g[1][6] = new Cell(1,6);
        g[1][7] = new Cell(1,7);
        g[1][8] = new Cell(1,8);
        g[1][9] = new Cell(1,9);
        
        
        g[2][0] = new Cell(2,0);
        g[2][1] = new Cell(2,1);
        g[2][2] = new Cell(2,2);
        g[2][3] = new Cell(2,3);
        g[2][4] = new Cell(2,4);
        g[2][5] = new Cell(2,5);
        g[2][6] = new Cell(2,6);
        g[2][7] = new Cell(2,7);
        g[2][8] = new Cell(2,8);
        g[2][9] = new Cell(2,9);
        
        g[3][0] = new Cell(3,0);
        g[3][1] = new Cell(3,1);
        g[3][2] = new Cell(3,2);
        g[3][3] = new Cell(3,3,"Rock");
        g[3][4] = new Cell(3,4,"Pad");
        g[3][5] = new Cell(3,5);
        g[3][6] = new Cell(3,6,"Rock");
        g[3][7] = new Cell(3,7,"Pad");
        g[3][8] = new Cell(3,8);
        g[3][9] = new Cell(3,9);
        
        g[4][0] = new Cell(4,0);
        g[4][1] = new Cell(4,1);
        g[4][2] = new Cell(4,2);
        g[4][3] = new Cell(4,3);
        g[4][4] = new Cell(4,4);
        g[4][5] = new Cell(4,5);
        g[4][6] = new Cell(4,6);
        g[4][7] = new Cell(4,7);
        g[4][8] = new Cell(4,8);
        g[4][9] = new Cell(4,9);
        
        g[5][0] = new Cell(5,0);
        g[5][1] = new Cell(5,1);
        g[5][2] = new Cell(5,2);
        g[5][3] = new Cell(5,3);
        g[5][4] = new Cell(5,4);
        g[5][5] = new Cell(5,5);
        g[5][6] = new Cell(5,6);
        g[5][7] = new Cell(5,7);
        g[5][8] = new Cell(5,8);
        g[5][9] = new Cell(5,9);
        
        
        g[6][0] = new Cell(6,0);
        g[6][1] = new Cell(6,1);
        g[6][2] = new Cell(6,2);
        g[6][3] = new Cell(6,3);
        g[6][4] = new Cell(6,4);
        g[6][5] = new Cell(6,5);
        g[6][6] = new Cell(6,6);
        g[6][7] = new Cell(6,7);
        g[6][8] = new Cell(6,8);
        g[6][9] = new Cell(6,9);
        
        
        g[7][0] = new Cell(7,0);
        g[7][1] = new Cell(7,1);
        g[7][2] = new Cell(7,2);
        g[7][3] = new Cell(7,3);
        g[7][4] = new Cell(7,4);
        g[7][5] = new Cell(7,5);
        g[7][6] = new Cell(7,6);
        g[7][7] = new Cell(7,7);
        g[7][8] = new Cell(7,8);
        g[7][9] = new Cell(7,9);
        
        g[8][0] = new Cell(8,0,"Immovable");
        g[8][1] = new Cell(8,1,"Immovable");
        g[8][2] = new Cell(8,2);
        g[8][3] = new Cell(8,3);
        g[8][4] = new Cell(8,4);
        g[8][5] = new Cell(8,5);
        g[8][6] = new Cell(8,6);
        g[8][7] = new Cell(8,7,"Immovable");
        g[8][8] = new Cell(8,8);
        g[8][9] = new Cell(8,9);
        
        g[9][0] = new Cell(9,0);
        g[9][1] = new Cell(9,1);
        g[9][2] = new Cell(9,2,"R2D2");
        g[9][3] = new Cell(9,3,"Immovable");
        g[9][4] = new Cell(9,4);
        g[9][5] = new Cell(9,5);
        g[9][6] = new Cell(9,6);
        g[9][7] = new Cell(9,7);
        g[9][8] = new Cell(9,8);
        g[9][9] = new Cell(9,9);
        

       
        
       Grid grid = new Grid(10,10);
       grid.grid=g;
       grid.r2d2Orientation = "North";
       grid.r2d2 = g[9][2];
       grid.numberOfPadsRemaningWithoutRocks =3;
       
       
        
        return grid;
	}

	
	public static Grid testingGrid10by102()
	{
        Cell [][] g = new Cell[10][10];
        g[0][0] = new Cell(0,0,"Immovable");
        g[0][1] = new Cell(0,1,"Pad");
        g[0][2] = new Cell(0,2,"Rock");
        g[0][3] = new Cell(0,3);
        g[0][4] = new Cell(0,4);
        g[0][5] = new Cell(0,5);
        g[0][6] = new Cell(0,6);
        g[0][7] = new Cell(0,7);
        g[0][8] = new Cell(0,8);
        g[0][9] = new Cell(0,9);
        
        g[1][0] = new Cell(1,0);
        g[1][1] = new Cell(1,1);
        g[1][2] = new Cell(1,2);
        g[1][3] = new Cell(1,3);
        g[1][4] = new Cell(1,4);
        g[1][5] = new Cell(1,5);
        g[1][6] = new Cell(1,6);
        g[1][7] = new Cell(1,7);
        g[1][8] = new Cell(1,8);
        g[1][9] = new Cell(1,9);
        
        
        g[2][0] = new Cell(2,0);
        g[2][1] = new Cell(2,1);
        g[2][2] = new Cell(2,2);
        g[2][3] = new Cell(2,3);
        g[2][4] = new Cell(2,4);
        g[2][5] = new Cell(2,5);
        g[2][6] = new Cell(2,6);
        g[2][7] = new Cell(2,7);
        g[2][8] = new Cell(2,8);
        g[2][9] = new Cell(2,9);
        
        g[3][0] = new Cell(3,0);
        g[3][1] = new Cell(3,1);
        g[3][2] = new Cell(3,2);
        g[3][3] = new Cell(3,3);
        g[3][4] = new Cell(3,4);
        g[3][5] = new Cell(3,5);
        g[3][6] = new Cell(3,6);
        g[3][7] = new Cell(3,7);
        g[3][8] = new Cell(3,8);
        g[3][9] = new Cell(3,9);
        
        g[4][0] = new Cell(4,0);
        g[4][1] = new Cell(4,1);
        g[4][2] = new Cell(4,2);
        g[4][3] = new Cell(4,3);
        g[4][4] = new Cell(4,4);
        g[4][5] = new Cell(4,5);
        g[4][6] = new Cell(4,6);
        g[4][7] = new Cell(4,7);
        g[4][8] = new Cell(4,8);
        g[4][9] = new Cell(4,9);
        
        g[5][0] = new Cell(5,0);
        g[5][1] = new Cell(5,1);
        g[5][2] = new Cell(5,2);
        g[5][3] = new Cell(5,3);
        g[5][4] = new Cell(5,4);
        g[5][5] = new Cell(5,5);
        g[5][6] = new Cell(5,6);
        g[5][7] = new Cell(5,7,"R2D2");
        g[5][8] = new Cell(5,8);
        g[5][9] = new Cell(5,9);
        
        
        g[6][0] = new Cell(6,0);
        g[6][1] = new Cell(6,1);
        g[6][2] = new Cell(6,2);
        g[6][3] = new Cell(6,3);
        g[6][4] = new Cell(6,4);
        g[6][5] = new Cell(6,5);
        g[6][6] = new Cell(6,6);
        g[6][7] = new Cell(6,7);
        g[6][8] = new Cell(6,8);
        g[6][9] = new Cell(6,9);
        
        
        g[7][0] = new Cell(7,0);
        g[7][1] = new Cell(7,1);
        g[7][2] = new Cell(7,2);
        g[7][3] = new Cell(7,3);
        g[7][4] = new Cell(7,4);
        g[7][5] = new Cell(7,5);
        g[7][6] = new Cell(7,6);
        g[7][7] = new Cell(7,7);
        g[7][8] = new Cell(7,8);
        g[7][9] = new Cell(7,9);
        
        g[8][0] = new Cell(8,0);
        g[8][1] = new Cell(8,1);
        g[8][2] = new Cell(8,2);
        g[8][3] = new Cell(8,3);
        g[8][4] = new Cell(8,4);
        g[8][5] = new Cell(8,5);
        g[8][6] = new Cell(8,6);
        g[8][7] = new Cell(8,7);
        g[8][8] = new Cell(8,8);
        g[8][9] = new Cell(8,9);
        
        g[9][0] = new Cell(9,0);
        g[9][1] = new Cell(9,1);
        g[9][2] = new Cell(9,2);
        g[9][3] = new Cell(9,3,"Teleportal");
        g[9][4] = new Cell(9,4);
        g[9][5] = new Cell(9,5);
        g[9][6] = new Cell(9,6);
        g[9][7] = new Cell(9,7);
        g[9][8] = new Cell(9,8);
        g[9][9] = new Cell(9,9);
        

       
        
       Grid grid = new Grid(10,10);
       grid.grid=g;
       grid.r2d2Orientation = "South";
       grid.r2d2 = g[5][6];
       grid.numberOfPadsRemaningWithoutRocks =1;
       
       
        
        return grid;
	}
	public static void main(String [] args){

		Node init = initialize(10,10);
		//Node init = initialize(5,5);
		init.grid = testingGrid10by10();
		//init.grid = testingGrid10by102();
		//init.grid = testingGrid11();
		
		init.grid.showGrid();
		
		//System.out.println(h2(init));
	     
       getSolution(init, "ASS", false);
  // getSolution(init, "UCS", false);
		
//		Node goal = iterativeDeepening(init);
//		Node goal = depthLimitedSearch(init, 13);
		
       

	}

	
}
