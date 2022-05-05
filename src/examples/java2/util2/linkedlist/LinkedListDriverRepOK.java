/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2;

import gov.nasa.jpf.vm.Verify;

public class LinkedListDriverRepOK {

	private static LinkedList generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		LinkedList l = new LinkedList();
		for (int i=0; i<=maxLength; i++){
			try{
				switch (Verify.random(0)){
					case 0:
						l.add(Verify.getInt(0,scope));
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
		// System.out.println(list);
		try{
			assert list.repOK();
			list.add(Verify.getInt(0,scope));
			list.remove(Verify.getInt(0,scope));
			list.removeFirst();
		}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){

		}
	}


}

