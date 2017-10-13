package project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StatesQueue 
{
	ArrayList<Node> states;

	
	public StatesQueue()
	{
		this.states = new ArrayList<Node>();
	}
 
	public void enque(ArrayList<Node> nodes, String order)
	{
		for(Node node:nodes)
		{
			this.enque(node, order);
		}
	}
	
	public void enque(Node n, String order)
	{

		switch(order)
		{
		case "Initial State": this.states.add(n); break;
		case "BFS" : this.states.add(n); break;
		case "DFS" : this.states.add(0,n); break;
		case "IDS" : this.states.add(0,n); break;
		case "GR":
		case "AS" :
		case "UC" : this.states.add(n);
		
		Collections.sort(this.states, new Comparator<Node>() {
	        public int compare(Node n1, Node n2)
	        {

	            if(n1.costToReachThisNode==n2.costToReachThisNode)
	            	return 0;
	             
	            if(n1.costToReachThisNode<n2.costToReachThisNode)
	            	return -1;
	            
	            return 1;
	        }
	    });
		break;
		}
		
			
	}
	
	public Node deque()
	{
		Node n = this.states.get(0);
		this.states.remove(0);
		return n;
	}
	
	public boolean isEmpty()
	{
		return this.states.isEmpty();
	}
	public int size()
	{
		return this.states.size();
	}
	
	public void show()
	{

		for(int i=0; i< this.states.size();i++)
		{
			System.out.println(this.states.get(i).costToReachThisNode);
		}

	}


	@Override
	public String toString() {
		return "StatesQueue [" + states + "]";
	}
	

	
}
