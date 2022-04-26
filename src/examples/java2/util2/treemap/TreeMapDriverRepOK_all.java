/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2.treemap;

import gov.nasa.jpf.vm.Verify;

public class TreeMapDriverRepOK_all {

	private static TreeMap generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		TreeMap l = new TreeMap();
		for (int i=0; i<=maxLength; i++){
			switch (Verify.random(7)){
			case 0:
				// System.out.println("adding...");
				l.put(Verify.getInt(0,scope),Verify.getInt(0,scope));
				// assert l.repOK();
			case 1:
				// System.out.println("adding...");
				l.containsKey(Verify.getInt(0,scope));
				// assert l.repOK();		
			case 2:
				// System.out.println("adding...");
				l.size();
				// assert l.repOK();		
			case 3:
				// System.out.println("adding...");
				l.containsValue(Verify.getInt(0,scope));
				// assert l.repOK();		
			case 4:
				// System.out.println("adding...");
				l.get(Verify.getInt(0,scope));
				// assert l.repOK();		
			case 5:
				// System.out.println("adding...");
				l.firstKey();
				// assert l.repOK();		
			case 6:
				// System.out.println("adding...");
				l.remove(Verify.getInt(0,scope));
				// assert l.repOK();		
			case 7:
				// System.out.println("adding...");
				l.clear();
				// assert l.repOK();	
			
			}
		}
		return l;
	}

	public static void main(String[] args) {
		int scope=2;
		try {
			TreeMap tree= generateStructure(scope);
			// System.out.println("struc...");
			// System.out.println(tree);
		
			tree.repOK();
		} catch (Exception e) {
		}
		
	}


}

