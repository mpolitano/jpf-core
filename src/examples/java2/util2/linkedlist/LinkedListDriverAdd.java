package java2.util2.linkedlist;

import gov.nasa.jpf.vm.Verify;

public class LinkedListDriverAdd{

	private static LinkedList generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		LinkedList l = new LinkedList();
		for (int i=1; i<=maxLength; i++){
			try{
				switch (Verify.random(0)){
					case 0:
						l.add(Verify.getInt(0,scope));
						assert l.repOK();
						break;			
			
				}	
			}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
			}
			// System.out.println("List " + i +"::"+ l);

		}
		return l;
	}

	public static void main(String[] args) {
		int scope=1;
		LinkedList list= generateStructure(scope);
		// System.out.println("List"+ list);

		list.add(Verify.getInt(0,scope));
		// System.out.println("List 2"+ list);

		assert list.repOK();

	}

}

