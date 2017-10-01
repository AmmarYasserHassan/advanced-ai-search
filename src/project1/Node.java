package project1;

public class Node {
	
	//These operators together form the state
	Grid grid;
	int numberOfPadsRemaningWithoutRocks;
	boolean atTeleportalCell;

	
	int costToReachThisNode;
	Node Parent;
	String actionTakenFromParentToReachThisNode;
	int depth;

	
	
	public Node(Grid grid, int numberOfPadsRemaningWithoutRocks,int costToReachThisNode,
			Node parent, String actionTakenFromParentToReachThisNode, int depth) {
		this.grid = grid;
		this.numberOfPadsRemaningWithoutRocks = numberOfPadsRemaningWithoutRocks;
		this.atTeleportalCell = false;;
		this.costToReachThisNode = costToReachThisNode;
		Parent = parent;
		this.actionTakenFromParentToReachThisNode = actionTakenFromParentToReachThisNode;
		this.depth = depth;
	}




	public Node()
	{
		
	}

}
