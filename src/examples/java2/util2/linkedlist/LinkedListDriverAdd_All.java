/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2.linkedlist;

import gov.nasa.jpf.vm.Verify;
import java2.util2.*;

public class LinkedListDriverAdd_All {

	private static LinkedList generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		LinkedList l = new LinkedList();
		for (int i=1; i<=maxLength; i++){
			try{
				switch (Verify.random(8)){
					case 0:
						// System.out.println("adding...");
						l.add(Verify.getInt(0,scope));
						break;
					case 1:
						// System.out.println("addF...");
						l.addFirst(Verify.getInt(0,scope));
						break;
					case 2:
						// System.out.println("rm...");
						l.remove(Verify.getInt(0,scope));
						break;	
					case 3:
						// System.out.println("rmF...");
						l.removeFirst();
						break;
					case 4:
						// System.out.println("rmF...");
						l.set(Verify.getInt(0,scope),Verify.getInt(0,scope));
						break;
					case 5:
						// System.out.println("rmF...");
						l.removeLast();
						break;
					case 6:
						// System.out.println("rmF...");
						l.addLast(Verify.getInt(0,scope));
						break;
					case 7:
						// System.out.println("rmF...");
						l.clear();
						break;
					case 8:
						// System.out.println("rmF...");
						l.get(Verify.getInt(0,scope));
						break;
						
	
				}											
     		}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
			
			}
		}
		return l;
	}

	public static void main(String[] args) {
		int scope=1;
		LinkedList list= generateStructure(scope);
		list.add(Verify.getInt(0,scope));		
		assert list.repOK();

	}


}

