package project1;

import java.util.ArrayList;

public class Searcher {

	static StatesQueue queue;
	static String [] operators = {"F","RR","RL"}; 
	
	public static Node initialize(int maxWidth, int maxHeight)
	{
		Grid initialGrid = new Grid(maxWidth,maxHeight);
		int numberOfPadsRemaningWithoutRocks = initialGrid.numberOfPads ;
		boolean atTeleportalCell = false;
		int costToReachThisNode = 0;
		String actionTakenFromParentToReachThisNode = "Nothing";
		Node initialState = new Node(initialGrid,numberOfPadsRemaningWithoutRocks,costToReachThisNode,null,actionTakenFromParentToReachThisNode);
		int estimatedCostFromThisNodeToTheGoal = estimatedCostFromANodeToTheGoal(initialState);
		initialState.setEstimatedCostFromANodeToTheGoal(estimatedCostFromThisNodeToTheGoal);
		
		return initialState;
		
	}
	
	
	public static Node search(Node initialState,String strategy, boolean visualize)
	{
		queue.enque(initialState, "Initial State");
		int iterations = 0;
		
		while(!queue.isEmpty())
		{
			Node currentNode = queue.deque();
			if(isGoal(currentNode))
			{
				System.out.println("REACHED A SOLUTION!");
				return currentNode;
			}
			ArrayList<Node> childern = expand(currentNode);
			queue.enque(childern, strategy);
		}
		
		System.out.println("Reached end of the queue without finding a goal state, termintaed after " + iterations + " interation");
		return null;
	}
	
	
	public static void getSolution(Node initialState,String strategy, boolean visualize)
	{
		Node goal = search(initialState,strategy,visualize);
		ArrayList<Node> pathToGoal = new ArrayList<Node>();
		
		if(!(goal == null))
		{
			while(!(goal.Parent==null))
			{
				pathToGoal.add(goal);
				goal = goal.Parent;
			}
			
		}
	}
	
	
	public static boolean isGoal(Node currentNode) {
		
		return (currentNode.atTeleportalCell && currentNode.numberOfPadsRemaningWithoutRocks == 0)? true:false;
	}


	public static ArrayList<Node> expand(Node n)
	{
		ArrayList<Node> childern = new ArrayList<Node>();
		Node newChild;
		for(String operator: operators)
		{  
			newChild = operate(n,operator);
			if(!(newChild == null))
			 childern.add(newChild);
		}
		
		return childern;
		
	}
	
	public static Node operate(Node parentNode,String operation)
	{
		
		if(operation.equals("RR"))
		{  
		   int costToReachThisNode = ++parentNode.costToReachThisNode;
		   Node childNode = new Node(parentNode.grid.clone(),parentNode.numberOfPadsRemaningWithoutRocks,costToReachThisNode,parentNode,operation);
		   childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode));
		   childNode.grid.rotateRight();
		   return childNode;
		}
		if(operation.equals("RL"))
		{  
		   int costToReachThisNode = ++parentNode.costToReachThisNode;
		   Node childNode = new Node(parentNode.grid.clone(),parentNode.numberOfPadsRemaningWithoutRocks,costToReachThisNode,parentNode,operation);
		   childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode));
		   childNode.grid.rotateLeft();
		   return childNode;
		}
		
		if(operation.equals("F"))
		{
			if(parentNode.grid.facingAnEdge(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation))
				return null;
			if(parentNode.grid.facingAnObstacle(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation))
				return null;
			if(parentNode.grid.facingARock(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation))
			{
				Cell nextCell = parentNode.grid.getNextCell(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation);
				if(parentNode.grid.rockCanMove(nextCell.i, nextCell.j, parentNode.grid.r2d2Orientation))
				{     
					   int costToReachThisNode = ++parentNode.costToReachThisNode;
					   Node childNode = new Node(parentNode.grid.clone(),parentNode.numberOfPadsRemaningWithoutRocks,costToReachThisNode,parentNode,operation);
					   childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode));
					   childNode.grid.moveRock(nextCell.i, nextCell.j, parentNode.grid.r2d2Orientation);
                       childNode.grid.moveR2D2(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation);
                       return childNode;
				}
				else
				{
					return null;
				}
						
			}
			
			   int costToReachThisNode = ++parentNode.costToReachThisNode;
			   Node childNode = new Node(parentNode.grid.clone(),parentNode.numberOfPadsRemaningWithoutRocks,costToReachThisNode,parentNode,operation);
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
	
	
	public static void main(String [] args)
	{
		Node n1 = new Node(null);
		Node n2 = new Node(n1);
		Node n3 = new Node(n2);
		
		Node goal = n2;
		ArrayList<Node> pathToGoal = new ArrayList<Node>();
		
		if(!(goal == null))
		{
			while(!(goal.Parent==null))
			{
				System.out.println(goal);
				pathToGoal.add(goal);
				goal = goal.Parent;
			}
			
		}
		
		System.out.println(pathToGoal);
		
		
	}

	
}
