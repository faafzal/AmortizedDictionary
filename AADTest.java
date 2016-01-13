package amortizeddictionary;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * J-Unit Test for AAD class
 * @author fafzal
 *
 */
public class AADTest {
	
	//create a new AAD object
	AAD aad = new AAD();

	/*
	 * Test add function. Structure I'm aiming for shown in comments
	 * I normally like to have single asserts in each test case, but I thought it would
	 * get lengthy if I did so.
	 */
	@Test
	public void testAdd() {
		
		//{2,3}
		//{1,6,7,9}
		//{2,4,5,8,9,14,20,25}
		
		/*
		 * add values in unsorted fashion
		 */
		aad.add(5);
		aad.add(25);
		aad.add(14);
		aad.add(4);
		aad.add(8);
		aad.add(20);
		aad.add(9);
		aad.add(2);
		
		aad.add(1);
		aad.add(6);
		aad.add(7);
		aad.add(9);
		
		aad.add(3);
		aad.add(2);
		
		/*
		 * Test to see if it maintains structure
		 */
		int[] arr1 = {2,3};
		int[] arr2 = {1,6,7,9};
		int[] arr3 = {2,4,5,8,9,14,20,25};
		
		assertArrayEquals(aad.getList().get(0).getArray(), arr1);
		assertArrayEquals(aad.getList().get(1).getArray(), arr2);
		assertArrayEquals(aad.getList().get(2).getArray(), arr3);
		
	}
	
	/*
	 * test case for is power of two, hoping it to be true
	 */
	@Test
	public void testIsPowerOfTwoTrue(){

		assertEquals(aad.isPowerOfTwo(16), true);
		
	}
	
	/*
	 * test case for is power of two, hoping it to be false
	 */
	@Test
	public void testIsPowerOfTwoFalse(){
		
		assertEquals(aad.isPowerOfTwo(10), false);
		
	}
	
	/*
	 * Testing remove, given contains is true
	 */
	@Test
	public void testRemoveContainsTrue(){
		
		aad.add(9);
		aad.add(7);
		aad.add(6);
		aad.add(1);
		aad.add(3);
		
		aad.remove(9);
		
		int[] tmp = {1,3,6,7};
		
		assertArrayEquals(aad.getList().get(0).getArray(), tmp);
		
	}
	
	/*
	 * Testing remove, given contains is false
	 */
	@Test
	public void testRemoveContainsFalse(){
		
		aad.add(7);
		aad.add(6);
		aad.add(1);
		aad.add(3);
		
		aad.remove(4);
		
		int[] tmp = {1,3,6,7};
		
		assertArrayEquals(aad.getList().get(0).getArray(), tmp);
		
	}
	
	/*
	 * Test case for contains method, hoping for it to be true
	 */
	@Test
	public void testContainsTrue(){
		
		aad.add(9);
		aad.add(7);
		aad.add(6);
		aad.add(1);
		aad.add(3);
		
		assertEquals(this.aad.contains(3), 0);	
		
	}
	
	/*
	 * Test case  for contains method, hoping for it to be false
	 */
	public void testContainsFalse(){
		
		aad.add(7);
		aad.add(6);
		aad.add(1);
		
		assertEquals(aad.contains(5), -1);
		
	}
	
	/*
	 * test case for toDictionary method
	 */
	@Test 
	public void testToDictionary(){
		
		/*
		 * Add values in unsorted fashion 
		 */
		aad.add(25);
		aad.add(20);
		aad.add(14);
		aad.add(9);
		aad.add(8);
		aad.add(5);
		aad.add(4);
		aad.add(2);
		
		aad.add(9);
		aad.add(7);
		aad.add(6);
		aad.add(1);
		
		aad.add(3);
		aad.add(2);
		
		// list should maintain structure
		
		int[] arr = {1,2,2,3,4,5,6,7,8,9,9,14,20,25};
		
		// to dictionary should create a new dictionary with an array value
		//that looks like arr.
		assertArrayEquals(aad.toDictionary().getArray(), arr);
		
	}
	


}
