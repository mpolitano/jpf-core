/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2.treeset;

import gov.nasa.jpf.vm.Verify;
import java2.util2.*;

public class TreeSetDriverAdd {

	private static TreeSet generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		TreeSet l = new TreeSet();
		for (int i=0; i<=maxLength; i++){
			try{
				switch (Verify.random(0)){
					case 0:
						// System.out.println("adding...");
						l.add(Verify.getInt(0,scope));
						break;
						// assert l.repOK();			
				}
			}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
			}			
		}
		return l;
	}

	public static void main(String[] args) {
		int scope=1;

		TreeSet tree= generateStructure(scope);
		tree.add(Verify.getInt(0,scope));
	
	}


}

