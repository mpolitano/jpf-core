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

public class HashMapDriverAdd_All {

	private static HashMap generateStructure(int maxScope) {

		int scope=maxScope;
		HashMap h = null;
		switch (Verify.random(3)){
		case 0:
			h = new HashMap(Verify.getInt(1,scope),Verify.getInt(1,scope));
			break;
		case 1:
			h = new HashMap(Verify.getInt(1,scope));
			break;
		case 2:
			h = new HashMap();
			break;
		}
		h = new HashMap();
		int maxLength=Verify.getInt(0,scope);

		for (int i=1; i<=maxLength; i++){
			try{	
				switch (Verify.random(14)){
					case 0:
						h.put(Verify.getInt(0,scope),Verify.getInt(0,scope));
						break;
					case 1:
						h.remove(Verify.getInt(0,scope));
						assert h.repOK();			
						break;
					case 2:
						h.clear();
						assert h.repOK();			
						break;
					case 3:
						h.entrySet();
						assert h.repOK();			
						break;
					case 4:
						h.values();
						assert h.repOK();			
						break;
					case 5:
						h.keySet();
						assert h.repOK();			
						break;
					case 6:
						h.containsValue(Verify.getInt(0,scope));
						assert h.repOK();			
						break;
					case 7:
						h.containsKey(Verify.getInt(0,scope));
						assert h.repOK();			
						break;
					case 8:
						h.get(Verify.getInt(0,scope));
						assert h.repOK();			
						break;
					case 9:
						h.putAll(h);
						assert h.repOK();			
						break;
					case 10:
						h.clone();
						assert h.repOK();			
						break;
					case 11:
						h.toString();
						assert h.repOK();			
						break;
					case 12:
						h.values();
						assert h.repOK();			
						break;
					case 13:
						h.hashCode();
						assert h.repOK();			
						break;
				}
     		}catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
			
			}
		}
		return h;
	}

	public static void main(String[] args) {
		int scope=1;

		HashMap hashmap= generateStructure(scope);
		hashmap.put(Verify.getInt(0,scope),Verify.getInt(0,scope));
		try { 
			assert hashmap.repOK();		
		}catch(java.lang.ArrayIndexOutOfBoundsException e) {}

		
	}


}

