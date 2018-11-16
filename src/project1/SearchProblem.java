package project1;

import java.util.ArrayList;

public abstract class SearchProblem {
	public String[] operators;
	public abstract Node genGrid(int maxWidth, int maxHeight);
	public abstract Node search(Node initialState,String strategy, boolean visualize);
	public abstract ArrayList<Node> expand(Node n);
	public abstract boolean isGoal(Node n);	
	

}
