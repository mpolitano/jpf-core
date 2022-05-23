/*
/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2.hashmap;

import gov.nasa.jpf.vm.Verify;
import java2.util2.*;

public class HashMapDriverAdd {

	private static HashMap generateStructure(int maxScope) {

		int scope=maxScope;
		HashMap h = new HashMap();
		int maxLength=Verify.getInt(0,scope);

		for (int i=1; i<=maxLength; i++){
			try{	
				switch (Verify.random(0)){
					case 0:
						// System.out.println("adding...");
					h.put(Verify.getInt(0,scope),Verify.getInt(0,scope));
						break;	
					case 1:
						// System.out.println("adding...");
						h.remove(Verify.getInt(0,scope));
						break;	
					}
			}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
			
			}
		}
		return h;
	}

	public static void main(String[] args) {
		int scope=1;

		HashMap tree= generateStructure(scope);
		assert tree.repOK();
//		tree.put(Verify.getInt(0,scope),Verify.getInt(0,scope));
//		try { 
//		assert tree.repOK();		
//		}catch(java.lang.ArrayIndexOutOfBoundsException e) {}

	}


}

