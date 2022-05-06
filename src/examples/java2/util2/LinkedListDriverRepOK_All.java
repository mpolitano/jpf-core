/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2;

import gov.nasa.jpf.vm.Verify;

public class LinkedListDriverRepOK_All {

	private static LinkedList generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		LinkedList l = new LinkedList();
		for (int i=0; i<=maxLength; i++){
			try{
				switch (Verify.random(7)){
					case 0:
						// System.out.println("adding...");
						l.add(Verify.getInt(0,scope));
						// assert l.repOK();
					case 1:
						// System.out.println("addF...");
						l.addFirst(Verify.getInt(0,scope));
						// assert l.repOK();
					case 2:
						// System.out.println("rm...");
						l.remove(Verify.getInt(0,scope));
						// assert l.repOK();	
					case 3:
						// System.out.println("rmF...");
						l.removeFirst();
						// assert l.repOK();
					case 4:
						// System.out.println("rmF...");
						l.set(Verify.getInt(0,scope),Verify.getInt(0,scope));
						// assert l.repOK();
					case 5:
						// System.out.println("rmF...");
						l.removeLast();
						// assert l.repOK();
					case 6:
						// System.out.println("rmF...");
						l.addLast(Verify.getInt(0,scope));
						// assert l.repOK();	
					case 7:
						// System.out.println("rmF...");
						l.clear();
						// assert l.repOK();
	
				}											
			}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
			
			}
		}
		return l;
	}

	public static void main(String[] args) {
		int scope=2;
	
			LinkedList list= generateStructure(scope);
			// System.out.println("struc...");
			// System.out.println(list);
		try {
			// assert list.repOK();
			list.add(Verify.getInt(0,scope));
			list.remove(Verify.getInt(0,scope));
			list.removeFirst();	
		}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
		}
		
	}


}

