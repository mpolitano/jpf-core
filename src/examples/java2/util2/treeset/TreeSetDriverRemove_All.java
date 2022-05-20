/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package java2.util2.treeset;

import gov.nasa.jpf.vm.Verify;

public class TreeSetDriverRemove_All {


private static TreeSet generateStructure(int maxScope) {

	int scope=maxScope;
	int maxLength=Verify.getInt(0,scope);
	TreeSet l = new TreeSet();
	for (int i=1; i<=maxLength; i++){
		try{
			switch (Verify.random(10)){
				case 0:
					l.add(Verify.getInt(0,scope));
					l.repOK();
					break;
				case 1:
					l.contains(Verify.getInt(0,scope));
					l.repOK();
					break;
				case 2:
					l.size();
					l.repOK();
				case 3:
					l.remove(Verify.getInt(0,scope));
					l.repOK();
					break;
				case 4:
						l.headSet(Verify.getInt(0,scope));
					l.repOK();
						break;
				case 5:
						l.tailSet(Verify.getInt(0,scope));
					l.repOK();
						break;	
				case 6:
						l.subSet(Verify.getInt(0,scope),Verify.getInt(0,scope));
					l.repOK();
						break;		
				case 7:
						l.clear();
					l.repOK();
						break;
					case 8:
						l.first();
					l.repOK();
						break;
					 case 9:
						l.clone();
					l.repOK();
						break;
					 case 10:
						l.last();
					l.repOK();
						break;
			}

 		}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException|java.lang.IllegalArgumentException e){
		}
	
	}
	return l;
}

	public static void main(String[] args) {
		int scope=1;
		try{
			TreeSet tree= generateStructure(scope);
			tree.remove(Verify.getInt(0,scope));
     	}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
		}
	}


}

