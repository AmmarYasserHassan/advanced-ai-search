package project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class HelpR2D2 extends SearchProblem {

	StatesQueue queue = new StatesQueue();
	static String[] operators = { "F", "RR", "RL" };
	HashMap<String, Boolean> previousStates = new HashMap<String, Boolean>();
	int numberOfNodesEnqueued = 0;
	int nodesExpanded = 0;
	boolean eliminateRepeatedStates = true;
	boolean useFirstHeuristic = false;

	public Node genGrid(int maxWidth, int maxHeight) {
		Grid initialGrid = new Grid(maxWidth, maxHeight);
		int costToReachThisNode = 0;
		int depth = 0;
		String actionTakenFromParentToReachThisNode = "Nothing";
		Node initialState = new Node(initialGrid, costToReachThisNode, null, actionTakenFromParentToReachThisNode,
				depth);
		int estimatedCostFromThisNodeToTheGoal = estimatedCostFromANodeToTheGoal(initialState, useFirstHeuristic);
		initialState.setEstimatedCostFromANodeToTheGoal(estimatedCostFromThisNodeToTheGoal);
		return initialState;

	}
	
	

	public void setEliminateRepeatedStates(boolean eliminateRepeatedStates) {
		this.eliminateRepeatedStates = eliminateRepeatedStates;
	}



	public void setUseFirstHeuristic(boolean useFirstHeuristic) {
		this.useFirstHeuristic = useFirstHeuristic;
	}



	public Node search(Node initialState, String strategy, boolean visualize) {
		if (initialState.grid.infeasible())
			return null;
		queue.enque(initialState, "Initial State");
		while (!queue.isEmpty()) {

			Node currentNode = queue.deque();

			if (visualize) {
				System.out.println();
				System.out.println("----------------------------------------------------- order of current node: "
						+ nodesExpanded);
				System.out.println();
				currentNode.grid.showGrid();
				System.out.println("Action taken from parent: " + currentNode.actionTakenFromParentToReachThisNode);
				System.out.println("h(n): " + currentNode.estimatedCostFromThisNodeToTheGoal);
				System.out.println("g(n): " + currentNode.costToReachThisNode);
				System.out.println();
				System.out.println("-----------------------------------------------------");
				System.out.println();
			}

			if (isGoal(currentNode)) {
				System.out.println("REACHED A SOLUTION! Expnaded:  " + nodesExpanded  + " nodes");
				return currentNode;
			}
			ArrayList<Node> childern = expand(currentNode);
			numberOfNodesEnqueued += childern.size();
			queue.enque(childern, strategy);
			nodesExpanded++;
			if (nodesExpanded % 100000 == 0)
				System.out.println(nodesExpanded);
		}

		System.out.println("Reached end of the queue without finding a goal state, termintaed after " + nodesExpanded
				+ " iterations");
		return null;
	}

	public void getSolution(Node initialState, String strategy, boolean visualize) {
		Node goal;
		if (strategy.equals("IDS"))
			goal = iterativeDeepening(initialState);
		else
			goal = search(initialState, strategy, visualize);
		
		int cost = goal.costToReachThisNode;
		ArrayList<Node> pathToGoal = new ArrayList<Node>();

		if (goal != null) {
			while (goal.Parent != null) {
				pathToGoal.add(goal);
				goal = goal.Parent;
			}

		}
		pathToGoal.add(initialState);
		Collections.reverse(pathToGoal);

		for (int i = 0; i < pathToGoal.size(); i++) {
			pathToGoal.get(i).grid.showGrid();
			System.out.println(pathToGoal.get(i).actionTakenFromParentToReachThisNode);
			System.out.println();
		}

		System.out.println("Path to goal consists of: " + (pathToGoal.size()-1) + " moves");
		System.out.println("Path cost of this goal node is: " + cost);

	}

	public boolean isGoal(Node currentNode) {

		return (currentNode.grid.atTeleportalCell && currentNode.grid.numberOfPadsRemaningWithoutRocks == 0);
	}

	public ArrayList<Node> expand(Node n) {
		ArrayList<Node> childern = new ArrayList<Node>();
		Node newChild;
		boolean canOperate = true;
		for (String operator : operators) {

			if (n.actionTakenFromParentToReachThisNode.equals("RR") && operator.equals("RL")
					|| n.actionTakenFromParentToReachThisNode.equals("RL") && operator.equals("RR"))
				canOperate = false;

			if (canOperate) {
				newChild = operate(n, operator);
				if (newChild != null) {

					if (eliminateRepeatedStates) {
						if (!previousStates.containsKey(newChild.grid.getGridHash())) {
							childern.add(newChild);
							previousStates.put(newChild.grid.getGridHash(), true);
						}
					} else {
						childern.add(newChild);
					}

				}
			}
		}

		return childern;

	}

	public Node operate(Node parentNode, String operation) {
		int newDepth = parentNode.depth + 1;
		int costToReachThisNode = 0;
		if (operation.equals("RR")) {
			
			costToReachThisNode = parentNode.costToReachThisNode +2;
			Node childNode = new Node(parentNode.grid.copy(), costToReachThisNode, parentNode, operation,
					newDepth);
			childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode, useFirstHeuristic));
			childNode.grid.rotateRight();
			return childNode;
		}
		if (operation.equals("RL")) {
			costToReachThisNode = parentNode.costToReachThisNode +2;
			Node childNode = new Node(parentNode.grid.copy(), costToReachThisNode, parentNode, operation,
					newDepth);
			childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode, useFirstHeuristic));
			childNode.grid.rotateLeft();
			return childNode;
		}

		if (operation.equals("F")) {
			if (parentNode.grid.facingAnEdge(parentNode.grid.r2d2, parentNode.grid.r2d2Orientation)) {
				return null;
			}
			if (parentNode.grid.facingAnObstacle(parentNode.grid.r2d2, parentNode.grid.r2d2Orientation)) {
				return null;
			}
			if (parentNode.grid.facingARock(parentNode.grid.r2d2, parentNode.grid.r2d2Orientation)) {
				Cell rockCell = parentNode.grid.getNextCell(parentNode.grid.r2d2, parentNode.grid.r2d2Orientation);
				if (parentNode.grid.rockCanMove(rockCell, parentNode.grid.r2d2Orientation)) {
					costToReachThisNode = parentNode.costToReachThisNode +1;
					Node childNode = new Node(parentNode.grid.copy(), costToReachThisNode, parentNode,
							operation, newDepth);
					childNode.setEstimatedCostFromANodeToTheGoal(
							estimatedCostFromANodeToTheGoal(childNode, useFirstHeuristic));
					rockCell = childNode.grid.getNextCell(childNode.grid.r2d2, childNode.grid.r2d2Orientation);
					childNode.grid.moveRock(rockCell, childNode.grid.r2d2Orientation);
					childNode.grid.moveR2D2(childNode.grid.r2d2, childNode.grid.r2d2Orientation);
					return childNode;
				} else
					return null;

			}

			costToReachThisNode = parentNode.costToReachThisNode +1;
			Node childNode = new Node(parentNode.grid.copy(), costToReachThisNode, parentNode, operation,
					newDepth);
			childNode.setEstimatedCostFromANodeToTheGoal(estimatedCostFromANodeToTheGoal(childNode, useFirstHeuristic));
			childNode.grid.moveR2D2(childNode.grid.r2d2, childNode.grid.r2d2Orientation);
			return childNode;

		}

		return null;
	}

	public int manhtanDistance(Cell c1, Cell c2) {
		return Math.abs(c1.i - c2.i) + Math.abs(c1.j - c2.j);
	}

	public int estimatedCostFromANodeToTheGoal(Node node, boolean h1) {

		return h1 ? h1(node) : h2(node);

	}

	// Go to a random rock with no pad first
	public int h1(Node n) {
		if (isGoal(n))
			return 0;

		Cell rockCell = getRockNotOnPadCell(n);
		Cell r2d2Cell = n.grid.r2d2;
		if (rockCell == null)
			return 1;
		else
			return manhtanDistance(rockCell, r2d2Cell);
	}

	// Go to nearest rock first
	public int h2(Node n) {
		if (isGoal(n))
			return 0;

		Cell rockCell = getFartherRockNotOnPadCell(n);
		if (rockCell == null)
			return 1;

		return manhtanDistance(n.grid.r2d2, rockCell);
	}

	public Cell getRockNotOnPadCell(Node n) {
		Grid g = n.grid;
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g.width; j++) {
				if (g.grid[i][j].elements.contains("Rock") && !g.grid[i][j].elements.contains("Pad")) {
					return g.grid[i][j];
				}
			}
		}

		return null;
	}

	public Cell getFartherRockNotOnPadCell(Node n) {
		Grid g = n.grid;
		int max = 0;
		int temp = 0;
		Cell tempCell = null;
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g.width; j++) {
				if (g.grid[i][j].elements.contains("Rock") && !g.grid[i][j].elements.contains("Pad")) {
					temp = manhtanDistance(g.grid[i][j], n.grid.r2d2);
					if (temp >= max) {
						max = temp;
						tempCell = g.grid[i][j];
					}
				}
			}
		}

		return tempCell;
	}

	public Node depthLimitedSearch(Node initialState, int limit) {
		queue.enque(initialState, "Initial State");

		while (!queue.isEmpty()) {

			Node currentNode = queue.deque();
			nodesExpanded++;
			if (isGoal(currentNode)) {
				System.out
						.println("REACHED A SOLUTION! in " + limit + " depth and in " + nodesExpanded + " iterations");
				return currentNode;
			}

			if (currentNode.depth < limit) {
				ArrayList<Node> childern = expand(currentNode);
				numberOfNodesEnqueued += childern.size();
				queue.enque(childern, "DFS");
			}

			if (nodesExpanded % 100000 == 0) {
				System.out.println(queue.size());
				System.out.println(nodesExpanded);
			}

		}

		return null;
	}

	public Node iterativeDeepening(Node initialState) {
		int limit = 0;
		while (true) {
			Node goal = depthLimitedSearch(initialState, limit);
			if (goal != null)
				return goal;
			previousStates.clear();
			numberOfNodesEnqueued = 0;
			nodesExpanded = 0;
			limit++;
		}
	}



}
