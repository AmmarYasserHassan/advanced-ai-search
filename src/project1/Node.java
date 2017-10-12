package project1;

public class Node {
	
	//These operators together form the state
	Grid grid;
	int numberOfPadsRemaningWithoutRocks;
	boolean atTeleportalCell;

	
	int costToReachThisNode;
	Node Parent;
	String actionTakenFromParentToReachThisNode;
	int estimatedCostFromThisNodeToTheGoal;

	
	
	public Node(Grid grid, int numberOfPadsRemaningWithoutRocks,int costToReachThisNode,
			Node parent, String actionTakenFromParentToReachThisNode) {
		this.grid = grid;
		this.numberOfPadsRemaningWithoutRocks = numberOfPadsRemaningWithoutRocks;
		this.atTeleportalCell = false;;
		this.costToReachThisNode = costToReachThisNode;
		Parent = parent;
		this.actionTakenFromParentToReachThisNode = actionTakenFromParentToReachThisNode;
	}

	public void setEstimatedCostFromANodeToTheGoal(int heuristicValue)
	{
		this.estimatedCostFromThisNodeToTheGoal = heuristicValue;
	}
	
   



	public Node(Node Parent)
	{
		this.Parent = Parent;
	}

}
