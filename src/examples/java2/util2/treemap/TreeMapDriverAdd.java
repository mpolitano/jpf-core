/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2.treemap;

import gov.nasa.jpf.vm.Verify;
import java2.util2.*;
public class TreeMapDriverAdd {

	private static TreeMap generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		TreeMap l = new TreeMap();
		for (int i=0; i<=maxLength; i++){
			try{
				switch (Verify.random(1)){
					case 0:
						l.put(Verify.getInt(0,scope),Verify.getInt(0,scope));
						break;
					case 1:
						l.remove(Verify.getInt(0,scope));		
						break;
				}
			}catch(java2.util2.NoSuchElementException|java.util.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
		}
		}
		return l;
	}

	public static void main(String[] args) {
		int scope=1;
			TreeMap tree= generateStructure(scope);
			tree.put(Verify.getInt(0,scope),Verify.getInt(0,scope));
			// assert tree.repOK();

	}


}

