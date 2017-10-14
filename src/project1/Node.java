package project1;

public class Node {
	
	// The state
	Grid grid;
	
	// g(n)
	int costToReachThisNode;
	
	Node Parent;
	String actionTakenFromParentToReachThisNode;
	
	// h(n)
	int estimatedCostFromThisNodeToTheGoal;

	public Node(Grid grid,int costToReachThisNode,
			Node parent, String actionTakenFromParentToReachThisNode) {
		this.grid = grid;
		this.costToReachThisNode = costToReachThisNode;
		Parent = parent;
		this.actionTakenFromParentToReachThisNode = actionTakenFromParentToReachThisNode;
	}
   

	public Node(Node Parent)
	{
		this.Parent = Parent;
	}
	
	public void setEstimatedCostFromANodeToTheGoal(int heuristicValue)
	{
		this.estimatedCostFromThisNodeToTheGoal = heuristicValue;
	}

	public int getEstimatedCostFromANodeToTheGoal()
	{
		return this.estimatedCostFromThisNodeToTheGoal;
	}

	@Override
	public String toString() {
		return "Node [grid=" + grid + ", Parent=" + Parent + ", actionTakenFromParentToReachThisNode="
				+ actionTakenFromParentToReachThisNode + "]";
	}
	
 
	
}
