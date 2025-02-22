/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2.linkedlist;

import gov.nasa.jpf.vm.Verify;
import java2.util2.*;

public class LinkedListDriverRemove {

	private static LinkedList generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		LinkedList l = new LinkedList();
		for (int i=1; i<=maxLength; i++){
			try{
				switch (Verify.random(0)){
					case 0:
						l.add(Verify.getInt(0,scope));
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
		try{
			list.remove(Verify.getInt(0,scope));
			assert list.repOK();
     	}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){

		}

	}


}

