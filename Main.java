package amortizeddictionary;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Dictionary d1 = new Dictionary(new int[0]);
		Dictionary d2 = new Dictionary(new int[0]);
		
		d1.add(5);
		d1.add(1);
		d1.add(2);
		d1.add(4);
		
		d2.add(6);
		d2.add(8);
		d2.add(3);
		
		System.out.println("4? " + d1.contains(4));
		
		d1.remove(4);
		
		System.out.println("4? " + d1.contains(4));
		
		d1.merge(d2);
		
		System.out.println("All Elements in d1");
		
		d1.printElements();
		
		//{9}
		//{1,5,7,8}
		
		AAD aad = new AAD();
		
		aad.add(8);
		aad.add(1);
		aad.add(7);
		aad.add(5);
		aad.add(9);
		
		System.out.println("All Elements In AAD");
		
		aad.printElements();
		
		aad.remove(7);
		
		System.out.println("Removed 7");
		
		aad.printElements();
		
		AAD aad2 = new AAD();
		
		aad2.add(7);
		aad2.add(11);
		aad2.add(25);
		
		aad.combine(aad2);
		
		System.out.println("aad + aad2");
		
		aad.printElements();
		
		System.out.println("Turn aad int to a dictionary");
		
		Dictionary d3 = aad.toDictionary();
		d3.printElements();
		
	}

}
