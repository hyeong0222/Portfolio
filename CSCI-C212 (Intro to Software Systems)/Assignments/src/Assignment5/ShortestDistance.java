////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 5 Part 1
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  April 20th
//
//
//////////////////////////////////////////////////////////////////////////////////
package Assignment5;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class ShortestDistance {
	
	Map<String, LinkedList<DistanceTo>> neighbors;
	
	
	public ShortestDistance() {
		
		neighbors = new HashMap<String, LinkedList<DistanceTo>> ();
		
		// Distance from Pendleton to neighboring cities
		LinkedList<DistanceTo> PendletonSet = new LinkedList<DistanceTo> ();
		PendletonSet.add(new DistanceTo("Pierre", 2));
		PendletonSet.add(new DistanceTo("Pueblo", 8));
		PendletonSet.add(new DistanceTo("Phoenix", 4));
		neighbors.put("Pendleton", PendletonSet);
		
		// Distance from Pierre to neighboring cities
		LinkedList<DistanceTo> PierreSet = new LinkedList<DistanceTo> ();
		PierreSet.add(new DistanceTo("Pendleton", 2));
		PierreSet.add(new DistanceTo("Pueblo", 3));
		neighbors.put("Pierre", PierreSet);

		// Distance from Pierre to neighboring cities
		LinkedList<DistanceTo> PuebloSet = new LinkedList<DistanceTo> ();
		PuebloSet.add(new DistanceTo("Pendleton", 8));
		PuebloSet.add(new DistanceTo("Pierre", 3));
		PuebloSet.add(new DistanceTo("Phoenix", 3));
		PuebloSet.add(new DistanceTo("Peoria", 3));
		neighbors.put("Pueblo", PuebloSet);
		
		// Distance from Phoenix to neighboring cities
		LinkedList<DistanceTo> PhoenixSet = new LinkedList<DistanceTo> (); 
		PhoenixSet.add(new DistanceTo("Pendleton", 4));
		PhoenixSet.add(new DistanceTo("Pueblo", 3));
		PhoenixSet.add(new DistanceTo("Peoria", 4));
		PhoenixSet.add(new DistanceTo("Pittsburgh", 10));
		PhoenixSet.add(new DistanceTo("Pensacola", 5));
		neighbors.put("Phoenix", PhoenixSet);

		// Distance from Peoria to neighboring cities
		LinkedList<DistanceTo> PeoriaSet = new LinkedList<DistanceTo> ();
		PeoriaSet.add(new DistanceTo("Pueblo", 3));
		PeoriaSet.add(new DistanceTo("Phoenix", 4));
		PeoriaSet.add(new DistanceTo("Pittsburgh", 5));
		neighbors.put("Peoria", PeoriaSet);

		// Distance from Pittsburgh to neighboring cities
		LinkedList<DistanceTo> PittsburghSet = new LinkedList<DistanceTo> ();
		PittsburghSet.add(new DistanceTo("Peoria", 5));
		PittsburghSet.add(new DistanceTo("Phoenix", 10));
		PittsburghSet.add(new DistanceTo("Princeton", 2));
		PittsburghSet.add(new DistanceTo("Pensacola", 4));
		neighbors.put("Pittsburgh", PittsburghSet);

		// Distance from Princeton to neighboring cities
		LinkedList<DistanceTo> PrincetonSet = new LinkedList<DistanceTo> ();
		PrincetonSet.add(new DistanceTo("Pittsburgh", 2));
		PrincetonSet.add(new DistanceTo("Pensacola", 5));
		neighbors.put("Princeton", PrincetonSet);

		// Distance from Pensacola to neighboring cities
		LinkedList<DistanceTo> PensacolaSet = new LinkedList<DistanceTo> ();
		PensacolaSet.add(new DistanceTo("Phoenix", 5));
		PensacolaSet.add(new DistanceTo("Pittsburgh", 4));
		PensacolaSet.add(new DistanceTo("Princeton", 5));
		neighbors.put("Pensacola", PensacolaSet);
		
	}
	
	
	public Map<String, Integer> shortestKnownDistance(String start) {
		Map<String, Integer> shortestKnownDistance = new HashMap<String, Integer>();
		PriorityQueue<DistanceTo> queue = new PriorityQueue<DistanceTo> ();
		
		// calculating distance from a city
		DistanceTo from = new DistanceTo (start, 0);
		
		// adding the distance to the queue
		queue.add(from);
		
		// Calculate the shortest distance from a city to its neighbor city 
		while (!queue.isEmpty()) {
			DistanceTo smallestElement = queue.poll();
			if (!shortestKnownDistance.containsKey(smallestElement.getTarget())) {
				int d = smallestElement.getDistance();
				shortestKnownDistance.put(smallestElement.getTarget(), d);
				LinkedList<DistanceTo> cities = neighbors.get(smallestElement.getTarget());
				for (DistanceTo city : cities) {
					DistanceTo path = new DistanceTo(city.getTarget(), d+city.getDistance());
					queue.add(path);
				}	
			}
		}
		
		// return the shortest distance
		return shortestKnownDistance;
		
	}
	
	
	public static void main (String [] args) {
		ShortestDistance sd = new ShortestDistance();
		Map<String, Integer> result = sd.shortestKnownDistance("Princeton");
		System.out.println(result);
	}
	

}
