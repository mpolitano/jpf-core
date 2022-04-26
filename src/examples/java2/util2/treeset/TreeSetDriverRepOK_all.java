/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2.treeset;

import gov.nasa.jpf.vm.Verify;

public class TreeSetDriverRepOK_all {

	private static TreeSet generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		TreeSet l = new TreeSet();
		for (int i=0; i<=maxLength; i++){
			switch (Verify.random(6)){
			case 0:
				// System.out.println("adding...");
				l.add(Verify.getInt(0,scope));
				// assert l.repOK();	
			case 1:
				// System.out.println("adding...");
				l.contains(Verify.getInt(0,scope));
				// assert l.repOK();		
			case 2:
				// System.out.println("adding...");
				l.size();
				// assert l.repOK();		
			case 3:
				// System.out.println("adding...");
				l.remove(Verify.getInt(0,scope));
				// assert l.repOK();		
			case 4:
				// System.out.println("adding...");
				l.first();
				// assert l.repOK();		
			case 5:
				// System.out.println("adding...");
				l.last();
				// assert l.repOK();		
			case 6:
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
			TreeSet tree= generateStructure(scope);
			// System.out.println("struc...");
			// System.out.println(tree);
		
			tree.repOK();
		} catch (Exception e) {
		}
		
	}


}

