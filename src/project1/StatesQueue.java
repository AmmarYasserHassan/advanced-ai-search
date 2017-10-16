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
		case "UCS" : this.states.add(n);
		
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
		case "GR":
		case "AS" : break;
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
			System.out.println(this.states.get(i).name);
		}

	}


	@Override
	public String toString() {
		return "StatesQueue [" + states + "]";
	}
	

	public static void main(String []args){
		
//		Node n1 = new Node("N1");
//		Node n2 = new Node("N2");
//		Node n3 = new Node("N3");
//		Node n4 = new Node("N4");
//		
//		StatesQueue s = new StatesQueue();
//		
//		s.enque(n1,"DFS");
//		s.enque(n2,"DFS");
//		s.enque(n3,"DFS");
//		s.enque(n4,"DFS");
//		
//		s.show();
		
		
	}
}
