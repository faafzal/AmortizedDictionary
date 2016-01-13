package amortizeddictionary;

import java.util.ArrayList;

/**
 * Amortized-Array Based Dictionary
 * Consists of sorted arrays
 * Each array has a different length
 * Each array has a length that is power of 2
 * contains(e) if e is in one of the arrays
 * @author fafzal
 *
 */
public class AAD{
	
	//create an arraylist of dictionary objects
	private ArrayList<Dictionary> list = null;
	
	/*
	 * constructor sets the current list to a new
	 * list
	 */
	public AAD(){
		
		this.setList(new ArrayList<Dictionary>());
		
	}

	/*
	 * Adds integer e to the list by creating a singleton array
	 * then creating a dictionary object.
	 * Stores the new object at the top.
	 * then structures list.
	 */
	public void add(int e){
		
		//singleton array containing e
		int[] tmp = {e};
		//new dictionary object, pass singleton array 
		Dictionary d = new Dictionary(tmp);
		//add d to the current list the top, because it is the dictionary
		this.getList().add(0, d);
		//call the helper method to structure the list
		this.structureList();
		
	}
	
	/*
	 * Structures the current list
	 */
	public void structureList(){
		
		/*
		 * While loop to see if we need to merge again.
		 */
		while(true){
			
			int merge = 0;
			
			 //loops through the list 
			for(int i = 0; i < this.getList().size()-1; i++){
				
				/*
				 * checks if the current dictionary's array has the same length
				 * as the next dictionary's array
				 */
				if(this.getList().get(i).getArray().length == this.getList().get(i+1).getArray().length){
					
					//if so, merge current dictionary with next dictionary
					this.getList().get(i).merge(this.getList().get(i+1));
					//remove the next dictionary, since we merged it
					this.getList().remove(i+1);
					// set merge value to 1
					merge = 1;
					
				}
				
			}
			
			//if there has not been a merge, break. Else check to see if you need to merge
			//again.
			if(merge == 0){
				break;
			}
		
		}
		
	}
	
	/*
	 * function checks if the length is a power of two
	 * returns true if it is, false if not.
	 */
	public boolean isPowerOfTwo(int length){
		
	  if (length == 0){
		  
		  return false;
		  
	  }
	  
	  while (length != 1){
		  
	    if (length%2 != 0){
	    	
	    	return false;
	    	
	    }
	  
	    length = length/2;
	    
	  }
	 
	  return true;
	  
	}
	
	/*
	 * splits the smallest array 
	 */
	public void splitSmallArray(){
		
		//create an array list of integers
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		
		//if smallest array does not exist, remove that array
		if(this.getList().get(0).getArray().length < 1){
			
			this.getList().remove(0);
			
		}
		
		//keep going if the smallest array is not a power of two
		while(this.isPowerOfTwo(this.getList().get(0).getArray().length) == false){
			
			//add first value of smallest array in to the tmp (list of integers)
			tmp.add(this.getList().get(0).getArray()[0]);
			//remove that value from the smallest array
			this.getList().get(0).remove(this.getList().get(0).getArray()[0]);
			
		}
		
		//add values from tmp back into the list
		for(int i = 0; i < tmp.size(); i++){
			
			this.add(tmp.get(i));
			
		}
		
	}
	
	/*
	 * Remove value from list of dictionaries
	 */
	public void remove(int e){
		
		//find if list contains e and store index value
		int index = this.contains(e);
		
		//if index is -1, list does not contain e so we are done.
		if(index == -1){
			
			return;
		
		// else it's in the smallest array
		}else if(index == 0){
			
			//remove it from that dictionary and we are done
			this.getList().get(index).remove(e);
			//call our helper function to split up the smallest array
			this.splitSmallArray();
			
		}else{
			
			//remove it from that dictionary
			this.getList().get(index).remove(e);
			
			//find the biggest element from the smallest array
			int max = 0;
			for(int i = 0; i < this.getList().get(0).getArray().length; i++){
				
				if(max < this.getList().get(0).getArray()[i]){
					
					max = this.getList().get(0).getArray()[i];
					
				}
				
			}
			
			//steal it from the smallest array
			this.getList().get(0).remove(max);
			//add it to the array we just removed e from
			this.getList().get(index).add(max);
			//split up the smallest array
			this.splitSmallArray();
			
		}
		
	}
	
	/*
	 * Look for e in each dictionary. Starting with the smallest dictionaries first.
	 * return index if found or -1 if not found
	 */
	public int contains(int e){
		
		for(int i = 0; i < this.getList().size(); i++){
			
			if(this.getList().get(i).contains(e) == true){
				
				return i;
				
			}
			
		}
		
		return -1;
		
	}
	
	/*
	 * Combines a new aad with current aad
	 * Works similar to the merge dictionary function.
	 * Merges dictionaries from new aad with current aad in
	 * a sorted fashion.
	 */
	public void combine(AAD aad){
		
		int len = this.getList().size() + aad.getList().size();
		ArrayList<Dictionary> newList = new ArrayList<Dictionary>();
		
		Dictionary lower = null;
		int j = 0;
		int k = 0;
		
		for(int i = 0; i < len; i++){
			
			if(j == this.getList().size()){
				
				lower = aad.getList().get(k);
				k++;
				
			}else if(k == aad.getList().size()){
				
				lower = this.getList().get(j);
				j++;
				
				
			}else{
				
				if(this.getList().get(j).getArray().length < aad.getList().get(k).getArray().length){
					
					lower = this.getList().get(j);
					j++;
					
				}else{
					
					lower = aad.getList().get(k);
					k++;
					
				}
				
			}
			
			newList.add(lower);
			
		}
		
		//sets list to new list
		this.setList(newList);
		//re structures the list
		this.structureList();
		
	}
	
	/*
	 * Returns a new dictionary (a long single sorted array)
	 * from all the small arrays in aad. 
	 */
	public Dictionary toDictionary(){
		
		Dictionary newDictionary = new Dictionary(new int[0]);
		
		for(int i = 0; i < this.getList().size(); i++){
			
			newDictionary.merge(this.getList().get(i));
			
		}
		
		return newDictionary;
		
	}
	
	/*
	 * It should normally done by a View class
	 * It prints all the elements in each dictionary
	 */
	public void printElements(){
		
		for(int i = 0; i < this.getList().size(); i++){
			
			this.getList().get(i).printElements();
			
		}
		
	}
	
	/*
	 * setters and getters for list below
	 */
	public void setList(ArrayList<Dictionary> l){
		
		this.list = l;
		
	}
	
	public ArrayList<Dictionary> getList(){
		
		return this.list;
		
	}

}
