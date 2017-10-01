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

	
	public void enque(Node n, String order)
	{
		
		switch(order)
		{
		case "BF" : this.states.add(n); break;
		case "DF" : this.states.add(0,n); break;
		case "ID" : this.states.add(0,n); break;
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
	
	
	
	public static void main(String [] args)
	{
		Node n1 = new Node();
		n1.costToReachThisNode = 1;
		
		Node n2 = new Node();
		n2.costToReachThisNode = 5;
		Node n3 = new Node();
		n3.costToReachThisNode = 3;
		Node n4 = new Node();
		
		n4.costToReachThisNode = -3;
		
		
		StatesQueue s = new StatesQueue();
		
		s.enque(n1, "UCS");
		s.enque(n2, "UCS");
		s.enque(n3, "UCS");
		s.enque(n4, "UCS");
		
		s.show();
	}
	
	
}
