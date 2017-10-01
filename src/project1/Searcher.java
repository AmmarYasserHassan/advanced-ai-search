package project1;

import java.util.ArrayList;

public class Searcher {

	Grid initialState;
	StatesQueue queue;
	
	public static void getInitialState()
	{
		
	}
	
	
	public static void search(Grid initialState,String strategy, boolean visualize)
	{
		
	}
	
	
//	public static ArrayList<Node> expand(Node n)
//	{
//		
//	}
	
	public static Node operate(Node parentNode,String operation)
	{
		
		if(operation.equals("RR"))
		{  
		   int childDepth = ++parentNode.depth;
		   Node childNode = new Node(parentNode.grid.clone(),parentNode.numberOfPadsRemaningWithoutRocks,parentNode.costToReachThisNode,parentNode,operation,childDepth);
		   childNode.grid.rotateRight();
		   return childNode;
		}
		if(operation.equals("RL"))
		{  
		   int childDepth = ++parentNode.depth;
		   Node childNode = new Node(parentNode.grid.clone(),parentNode.numberOfPadsRemaningWithoutRocks,parentNode.costToReachThisNode,parentNode,operation,childDepth);
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
					   int childDepth = ++parentNode.depth;
					   Node childNode = new Node(parentNode.grid.clone(),parentNode.numberOfPadsRemaningWithoutRocks,parentNode.costToReachThisNode,parentNode,operation,childDepth);
                       childNode.grid.moveRock(nextCell.i, nextCell.j, parentNode.grid.r2d2Orientation);
                       childNode.grid.moveR2D2(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation);
                       return childNode;
				}
				else
				{
					return null;
				}
						
			}
			
			   int childDepth = ++parentNode.depth;
			   Node childNode = new Node(parentNode.grid.clone(),parentNode.numberOfPadsRemaningWithoutRocks,parentNode.costToReachThisNode,parentNode,operation,childDepth);
               childNode.grid.moveR2D2(parentNode.grid.r2d2.i, parentNode.grid.r2d2.j, parentNode.grid.r2d2Orientation);
               return childNode;
			
			
		}
		
		System.err.println("Ahmed Wageeh says: Unknown operation");
		return null;
	}
	
	
}
