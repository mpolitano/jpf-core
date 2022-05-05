/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2.treemap;

import gov.nasa.jpf.vm.Verify;

public class TreeMapDriverRepOK_All {

	private static TreeMap generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		TreeMap l = new TreeMap();
		for (int i=0; i<=maxLength; i++){
			try{

				switch (Verify.random(7)){
					case 0:
						l.put(Verify.getInt(0,scope),Verify.getInt(0,scope));
					case 1:
						l.containsKey(Verify.getInt(0,scope));
					case 2:
						l.size();
					case 3:
						l.containsValue(Verify.getInt(0,scope));
					case 4:
						l.get(Verify.getInt(0,scope));
					case 5:
						l.firstKey();
					case 6:
						l.remove(Verify.getInt(0,scope));
					case 7:
						l.clear();
				}
			}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
			}
		
		}
		return l;
	}

	public static void main(String[] args) {
		int scope=2;
			TreeMap tree= generateStructure(scope);
		try {
			assert tree.repOK();
			tree.put(Verify.getInt(0,scope),Verify.getInt(0,scope));
			tree.remove(Verify.getInt(0,scope));
		}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
		}
		
	}


}

