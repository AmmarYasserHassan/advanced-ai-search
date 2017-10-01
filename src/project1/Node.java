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
	
	public Node()
	{}

}
