package amortizeddictionary;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This is a J-Unit Test for the Dictionary class
 * @author fafzal
 */
public class DictionaryTest {
	//create a new dictionary object
	Dictionary d = new Dictionary(new int[0]);

	/*
	 * test case for the add method.
	 */
	@Test
	public void testAdd() {
		//adds values in unsorted order
		d.add(2);
		d.add(3);
		d.add(1);
		
		//sorted array with same values
		int[] tmp = {1,2,3};
		
		//test against the sorted array
		assertArrayEquals(d.getArray(), tmp);
	}
	
	/*
	 * test case for the remove method
	 */
	@Test
	public void testRemove(){
		//adds unsorted values
		d.add(4);
		d.add(6);
		d.add(5);
		
		//removes 6
		d.remove(6);
		
		//array without 6, and sorted
		int[] tmp = {4,5};
		
		//tests current array against new array
		assertArrayEquals(d.getArray(), tmp);
	}
	
	/*
	 * test case for contains method.
	 * testing to see if contains is true
	 */
	@Test 
	public void testContainsTrue(){
		//adds values in unsorted order
		d.add(8);
		d.add(10);
		d.add(9);
		
		//tests to see if it contains 10
		assertEquals(d.contains(10), true);
	}
	
	/*
	 * test case for contains method.
	 * testing to see if contains is false
	 */
	@Test
	public void testContainsFalse(){
		//add values in unsorted order
		d.add(11);
		d.add(13);
		d.add(12);
		
		//test if d contains is false on value 14
		assertEquals(d.contains(14), false);
	}
	
	
	/*
	 * test case for merge
	 */
	@Test 
	public void testMerge(){
		//create a new dictionary
		Dictionary d1 = new Dictionary(new int[0]);
		
		// add values to d
		d.add(4);
		d.add(9);
		d.add(10);
		
		// add values to d1
		d1.add(5);
		d1.add(1);
		d1.add(8);
		
		//call the merge function
		d.merge(d1);
		
		//this is what the new array should look like
		int[] tmp = {1,4,5,8,9,10};
		
		//test d's array to merged sorted array
		assertArrayEquals(d.getArray(), tmp);
	}
}
