package transit;

import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class Transit {
	private TNode trainZero; // a reference to the zero node in the train layer

	/* 
	 * Default constructor used by the driver and Autolab. 
	 * DO NOT use in your code.
	 * DO NOT remove from this file
	 */ 
	public Transit() { trainZero = null; }

	/* 
	 * Default constructor used by the driver and Autolab. 
	 * DO NOT use in your code.
	 * DO NOT remove from this file
	 */
	public Transit(TNode tz) { trainZero = tz; }
	
	/*
	 * Getter method for trainZero
	 *
	 * DO NOT remove from this file.
	 */
	public TNode getTrainZero () {
		return trainZero;
	}

	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0. Store the zero node in the train layer in
	 * the instance variable trainZero.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 */
	public void makeList(int[] trainStations, int[] busStops, int[] locations) {
		this.trainZero = new TNode();
		TNode Bo = new TNode();
		TNode Wo = new TNode();
	   trainZero.setDown(Bo);
	   Bo.setDown(Wo);

	   
	   for(int i=0; i<locations.length; i++){
	   	addatend(Wo, locations[i]);
	   }
	   for(int i=0; i<busStops.length; i++){
		addatend(Bo, busStops[i]);
		nodedown(Bo, busStops[i]).setDown(nodedown(Wo, busStops[i]));
		}
		for(int i=0; i<trainStations.length; i++){
			addatend(this.trainZero, trainStations[i]);
			nodedown(this.trainZero, trainStations[i]).setDown(nodedown(Bo, trainStations[i]));
		}
	}
	
	private void addatend(TNode head, int data){
		TNode temp = new TNode(data);
		while(head.getNext()!=null){
			head= head.getNext();}
		head.setNext(temp);
	}
	private TNode nodedown(TNode head, int data){
		while(head.getLocation()!=data){
			head=head.getNext();
		}
		return head;
	}
	/**
	 * Modifies the layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param station The location of the train station to remove
	 */
	public void removeTrainStation(int station) {
	    TNode head = this.trainZero;
		TNode prev = null;
		while(head.getLocation() != station && head.getNext()!=null){
			prev=head;
			head=head.getNext();
		}
		if(head.getLocation()==station){
		prev.setNext(head.getNext());}
	}

	/**
	 * Modifies the layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param busStop The location of the bus stop to add
	 */
	public void addBusStop(int busStop) {
	    TNode head = this.trainZero.getDown();
		TNode prev = null;
		TNode Wo =new TNode();
		Wo=head.getDown();
		while(head!=null&&head.getLocation()<busStop){
			prev=head;
			head=head.getNext();
		}
		
		if(head!=null&&head.getLocation()!=busStop||head==null){
		prev.setNext(new TNode(busStop,head,nodedown(Wo, busStop)));}}
		
		
	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param destination An int representing the destination
	 * @return
	 */
	public ArrayList<TNode> bestPath(int destination) {
		ArrayList <TNode> pathnodes = new ArrayList<TNode>(); 
		TNode point1 =this.trainZero;
		TNode point2 =this.trainZero;
		pathnodes.add(point1);
		while(point1!=null){
			if(point1.getLocation()>destination&&point2.getDown()!=null){
				point1=point2.getDown();
				pathnodes.add(point1);
			}
			if((point1.getLocation()==destination&&point1.getDown()!=null)||point1.getNext()==null){
				point1=point1.getDown();
				pathnodes.add(point1);
			}
			if(point1.getNext()!=null&&point1.getLocation()<destination){
			point2=point1;
			point1=point1.getNext();
			if(point1.getLocation()<=destination){
			pathnodes.add(point1); 
				}
			}
			if(point1.getLocation()==destination&&point1.getDown()==null){
				point1=null;
				return pathnodes;
			}
		}
		return null;
	    	
}

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @return A reference to the train zero node of a deep copy
	 */
	public TNode duplicate() {
		TNode head1 = this.trainZero;
		TNode head2 = head1.getDown();
		TNode head3 = head2.getDown();
		int a[]= new int[sizeoflist(head1)-2];
		int b[]= new int[sizeoflist(head2)-2];
		int c[]= new int[sizeoflist(head3)-2];
		Transit o=new Transit();
		o.makeList(arrayfill(a, head1), arrayfill(b, head2), arrayfill(c, head3));
		return o.getTrainZero();
	}
	private int[] arrayfill(int a[],TNode head){
		int i=0;
		head=head.getNext();
		while(head!=null){
			a[i]=head.getLocation();
			head=head.getNext();
			i++;
		}
		return a;
	}

	private int sizeoflist(TNode head){
		int count=1;
		while(head!=null){
			head=head.getNext();
			count++;	
			}
			return count;
	}

	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public void addScooter(int[] scooterStops) {
		TNode Bhead = this.trainZero.getDown();
		TNode Shead = new TNode();
		TNode Whead = Bhead.getDown();
		Bhead.setDown(Shead);
		for(int i=0; i<scooterStops.length; i++){
			addatend(Shead, scooterStops[i]);
		}
		while(Bhead!=null){
			Bhead.setDown(nodedown(Shead, Bhead.getLocation()));
			Bhead=Bhead.getNext();
		}
		while(Shead!=null){
			Shead.setDown(nodedown(Whead, Shead.getLocation()));
			Shead=Shead.getNext();
		}
		
	    
	}

	/**
	 * Used by the driver to display the layered linked list. 
	 * DO NOT edit.
	 */
	public void printList() {
		// Traverse the starts of the layers, then the layers within
		for (TNode vertPtr = trainZero; vertPtr != null; vertPtr = vertPtr.getDown()) {
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// Output the location, then prepare for the arrow to the next
				StdOut.print(horizPtr.getLocation());
				if (horizPtr.getNext() == null) break;
				
				// Spacing is determined by the numbers in the walking layer
				for (int i = horizPtr.getLocation()+1; i < horizPtr.getNext().getLocation(); i++) {
					StdOut.print("--");
					int numLen = String.valueOf(i).length();
					for (int j = 0; j < numLen; j++) StdOut.print("-");
				}
				StdOut.print("->");
			}

			// Prepare for vertical lines
			if (vertPtr.getDown() == null) break;
			StdOut.println();
			
			TNode downPtr = vertPtr.getDown();
			// Reset horizPtr, and output a | under each number
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				while (downPtr.getLocation() < horizPtr.getLocation()) downPtr = downPtr.getNext();
				if (downPtr.getLocation() == horizPtr.getLocation() && horizPtr.getDown() == downPtr) StdOut.print("|");
				else StdOut.print(" ");
				int numLen = String.valueOf(horizPtr.getLocation()).length();
				for (int j = 0; j < numLen-1; j++) StdOut.print(" ");
				
				if (horizPtr.getNext() == null) break;
				
				for (int i = horizPtr.getLocation()+1; i <= horizPtr.getNext().getLocation(); i++) {
					StdOut.print("  ");

					if (i != horizPtr.getNext().getLocation()) {
						numLen = String.valueOf(i).length();
						for (int j = 0; j < numLen; j++) StdOut.print(" ");
					}
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}
	
	/**
	 * Used by the driver to display best path. 
	 * DO NOT edit.
	 */
	public void printBestPath(int destination) {
		ArrayList<TNode> path = bestPath(destination);
		for (TNode vertPtr = trainZero; vertPtr != null; vertPtr = vertPtr.getDown()) {
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// ONLY print the number if this node is in the path, otherwise spaces
				if (path.contains(horizPtr)) StdOut.print(horizPtr.getLocation());
				else {
					int numLen = String.valueOf(horizPtr.getLocation()).length();
					for (int i = 0; i < numLen; i++) StdOut.print(" ");
				}
				if (horizPtr.getNext() == null) break;
				
				// ONLY print the edge if both ends are in the path, otherwise spaces
				String separator = (path.contains(horizPtr) && path.contains(horizPtr.getNext())) ? ">" : " ";
				for (int i = horizPtr.getLocation()+1; i < horizPtr.getNext().getLocation(); i++) {
					StdOut.print(separator + separator);
					
					int numLen = String.valueOf(i).length();
					for (int j = 0; j < numLen; j++) StdOut.print(separator);
				}

				StdOut.print(separator + separator);
			}
			
			if (vertPtr.getDown() == null) break;
			StdOut.println();

			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// ONLY print the vertical edge if both ends are in the path, otherwise space
				StdOut.print((path.contains(horizPtr) && path.contains(horizPtr.getDown())) ? "V" : " ");
				int numLen = String.valueOf(horizPtr.getLocation()).length();
				for (int j = 0; j < numLen-1; j++) StdOut.print(" ");
				
				if (horizPtr.getNext() == null) break;
				
				for (int i = horizPtr.getLocation()+1; i <= horizPtr.getNext().getLocation(); i++) {
					StdOut.print("  ");

					if (i != horizPtr.getNext().getLocation()) {
						numLen = String.valueOf(i).length();
						for (int j = 0; j < numLen; j++) StdOut.print(" ");
					}
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}
}
