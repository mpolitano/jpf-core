/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2.treemap;

import gov.nasa.jpf.vm.Verify;
import java2.util2.*;
public class TreeMapDriverAdd_All {

	private static TreeMap generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		TreeMap l = new TreeMap();
		for (int i=1; i<=maxLength; i++){
			try{

				switch (Verify.random(14)){
					case 0:
						l.put(Verify.getInt(0,scope),Verify.getInt(0,scope));
						break;
					case 1:
						l.containsKey(Verify.getInt(0,scope));
						break;						
					case 2:
						l.size();
						break;
					case 3:
						l.containsValue(Verify.getInt(0,scope));
						break;
					case 4:
						l.get(Verify.getInt(0,scope));
						break;
					case 5:
						l.remove(Verify.getInt(0,scope));
						break;						
					case 6:
						l.clear();
						break;
					case 7:
						l.keySet();
						break;
					case 8:
						l.subMap(Verify.getInt(0,scope),Verify.getInt(0,scope));
						break;
					case 9:
						l.headMap(Verify.getInt(0,scope));
						break;
					case 10:
						l.tailMap(Verify.getInt(0,scope));
						break;
					case 11: 
						l.hashCode();
						break;
					case 13: 
						l.values();
						break;
					case 14: 
						l.get(Verify.getInt(0,scope));
						break;

				}
     		}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException|java.lang.IllegalArgumentException e){
			}
		
		}
		return l;
	}

	public static void main(String[] args) {
		int scope=3;
		TreeMap tree= generateStructure(scope);
		tree.put(Verify.getInt(0,scope),Verify.getInt(0,scope));
	 	assert tree.repOK();
	}


}

