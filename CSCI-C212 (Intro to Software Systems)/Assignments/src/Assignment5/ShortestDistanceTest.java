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

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

// JUnit Test
class ShortestDistanceTest {

	@Test
	void testShortestKnownDistance() {
		
		ShortestDistance sd = new ShortestDistance();
		
		
		// Test when the distance is starting from Pendleton 
		Map<String, Integer> map = sd.shortestKnownDistance("Pendleton");
		
		int peoriaLength = map.get("Peoria");
		int pittsburghLength = map.get("Pittsburgh");
		int pensacolaLength = map.get("Pensacola");
		int princetonLength = map.get("Princeton");

		assertEquals(8, peoriaLength);
		assertEquals(13, pittsburghLength);
		assertEquals(9, pensacolaLength);
		assertEquals(14, princetonLength);
		
		
		// Test when the distance is starting from Pensacola 
		Map<String, Integer> map2 = sd.shortestKnownDistance("Pensacola");
		
		int peoriaLength2 = map2.get("Peoria");
		int puebloLength2 = map2.get("Pueblo");
		int pierreLength2 = map2.get("Pierre");
		int pendletonLength2 = map2.get("Pendleton");

		assertEquals(9, peoriaLength2);
		assertEquals(8, puebloLength2);
		assertEquals(11, pierreLength2);
		assertEquals(9, pendletonLength2);
		
		
		// Test when the distance is starting from Peoria 
		Map<String, Integer> map3 = sd.shortestKnownDistance("Peoria");
		
		int pendletonLength3 = map3.get("Pendleton");
		int pierreLength3 = map3.get("Pierre");
		int pensacolaLength3 = map3.get("Pensacola");
		int princetonLength3 = map3.get("Princeton");

		assertEquals(8, pendletonLength3);
		assertEquals(6, pierreLength3);
		assertEquals(9, pensacolaLength3);
		assertEquals(7, princetonLength3);
		
	}

}
