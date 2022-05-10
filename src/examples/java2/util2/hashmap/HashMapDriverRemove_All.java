/*
/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2;

import gov.nasa.jpf.vm.Verify;
import java2.util2.*;

public class HashMapDriverRemove_All {

	private static HashMap generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		HashMap h = new HashMap();
		for (int i=0; i<=maxLength; i++){
			try{	
				switch (Verify.random(7)){
					case 0:
						h.put(Verify.getInt(0,scope),Verify.getInt(0,scope));
						// assert l.repOK();			
					case 1:
						h.remove(Verify.getInt(0,scope));
						// assert l.repOK();			
					case 2:
						h.clear();
						// assert l.repOK();
					case 3:
						h.entrySet();
						// assert l.repOK();	
					case 4:
						h.values();
						// assert l.repOK();
					case 5:
						h.keySet();
						// assert l.repOK();
					case 6:
						h.containsValue(Verify.getInt(0,scope));
						// assert l.repOK();
					case 7:
						h.containsKey(Verify.getInt(0,scope));

				}
     		}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
			
			}
		}
		return h;
	}

	public static void main(String[] args) {
		int scope=2;

		HashMap tree= generateStructure(scope);
		try {		
			// assert tree.repOK();
			tree.remove(Verify.getInt(0,scope));
     	}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
		}
		
	}


}

