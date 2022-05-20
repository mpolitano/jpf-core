/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package builders.schedule;

import gov.nasa.jpf.vm.Verify;

public class ScheduleDriverRepOK {

	private static Schedule generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		Schedule l = new Schedule();
		for (int i=0; i<=maxLength; i++){
			switch (Verify.random(2)){
			case 0:
				// System.out.println("adding...");
				l.addProcess(Verify.getInt(0,scope));
				// assert l.repOK();			
			case 1:
				// System.out.println("block...");
				l.blockProcess();
				// assert l.repOK();			
			case 2:
				// System.out.println("adding...");
				l.quantumExpire();
				// assert l.repOK();			
		}
		}
		return l;
	}

	public static void main(String[] args) {
		int scope=2;
		try {
			Schedule tree= generateStructure(scope);
			// System.out.println("struc...");
			// System.out.println(tree);
		
			tree.repOK();
		} catch (Exception e) {
		}
		
	}


}

