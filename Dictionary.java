package amortizeddictionary;

/**
 * 
 * Dictionary class
 * It stores stuff, removes stuff, and checks if stuff has been stored
 * It uses a sorted dynamic array structure.
 * @author fafzal
 *
 */
public class Dictionary{
	private int[] array = null;
	
	/*
	 * Constructor takes an array as input.
	 * Sets the array to the given input.
	 */
	public Dictionary(int[] arr){
		this.setArray(arr);
	}
	
	/*
	 * Makes a new array that is one size bigger,
	 * and copies e and all the the elements into
	 * it so that the new array is sorted.
	 */
	public void add(int e){
		int[] newArray = new int[this.getArray().length+1];
		int lower = 0;
		int insert = 0;
		int j = 0;
		
		for(int i = 0; i < newArray.length; i++){
			// If all of the old arrays values have been added, lower is equal e.
			if(j == this.getArray().length){
				lower = e;
			// else if e has been inserted, add all of old arrays elements. Increment j
			}else if(insert == 1){
				lower = this.getArray()[j];
				j++;
			//else check to see which is lower
			}else{
				/* if old array element is less than e, lower value is old array element,
				 * increment j. 
				 */
				if(this.getArray()[j] < e){
					lower = this.getArray()[j];
					j++;
				//else set lower equal to e, make insert equal to 1 
				}else{
					lower = e;
					insert = 1;
				}
			}	
			//add the lower element to the new array
			newArray[i] = lower;
		}
	
		//set the current array to the new array
		this.setArray(newArray);
	}
	
	/*
	 * Stores current array in a temporary array.
	 * Sets the current array to an empty array;
	 * Adds all the elements back into the array, but not e.
	 * It removes one number at a time. If array has duplicates, only one
	 * is removed.
	 * 
	 */
	public void remove(int e){
		//checks if the dictionary contains e. If false, then we are done.
		if(this.contains(e) == false){
			return;
		}
		
		//create temporary array
		int[] tmp = this.getArray();
		//set current array to an empty array;
		this.setArray(new int[0]);
		int found = 0;
		
		/*
		 * Loops through the entire array. Adds elements back into
		 * the array if element is not equal to e or e has been found.
		 * (Removing only one e, incase of duplicates) 
		 */
		for(int i = 0; i < tmp.length; i++){
			if(tmp[i] != e || found == 1){
				this.add(tmp[i]);
			}else{
				found = 1;
			}
		}
	}
	
	/*
	 * Binary search the array. Check if e is in the array.
	 * Return true or false
	 */
	public boolean contains(int e){
		int high = this.getArray().length-1;
		int low = 0;
		
		while(low <= high){
			int mid = low + (high-low)/2;
			
			if(this.getArray()[mid] == e){
				return true;
			}else if (this.getArray()[mid] < e){
				low = mid+1;
			}else{
				high = mid-1;
			}
		}

		return false;	
	}
	
	
	/*
	 * Merges a new dictionaries array with current array.
	 * Sets the current array to the new array. New array
	 * is sorted.
	 */
	public void merge(Dictionary d){
		//a will be the current array
		int[] a = this.getArray();
		//b will be the input d's array
		int[] b = d.getArray();
		//new array length will be the length of a plus length b
		int[] c = new int[a.length + b.length];
		int lower = 0;
		int aCount = 0;
		int bCount = 0;
		
		/*
		 * This loops checks to see which value is lower.
		 * then adds the lower value to the new array.
		 */
		for(int i = 0; i < c.length; i++){	
			/*
			 * if all of a's elements are in the new array, then just add the rest of
			 * b's elements. Vice Versa if all of b's elements have been added to the 
			 * new array. Else, check to see if a[a index] is less than b[b index]
			 * Set lower to be the lower value and add it into the array.
			 */
			if(aCount == a.length){
				lower = b[bCount];
				bCount++;
			}else if(bCount == b.length){
				lower = a[aCount];
				aCount++;
			}else{
				if(a[aCount] < b[bCount]){
					lower = a[aCount];
					aCount++;
				}else{
					lower = b[bCount];
					bCount++;
				}
			}	
			
			//set array c (new array) i to lower value.
			c[i] = lower;
		}
	
		//set the current array to new merge sorted array.
		this.setArray(c);
	}
	
	/*
	 * Prints All The Elements of the dictionary
	 * It should normally be done by a View Class
	 */
	public void printElements(){
		for(int i = 0; i < this.getArray().length; i++){
			System.out.print(this.getArray()[i] + " ");
		}
		
		System.out.println();
	}
	
	/*
	 * setters and getters for the array
	 */
	public void setArray(int[] a){
		this.array = a;
	}
	
	public int[] getArray(){
		return this.array;
	}
}
