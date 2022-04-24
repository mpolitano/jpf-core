/*
 * @(#)LinkedList.java	1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package builders.schedule;

import gov.nasa.jpf.vm.Verify;

public class ScheduleDriverRepOK_all {

	private static Schedule generateStructure(int maxScope) {

		int scope=maxScope;
		int maxLength=Verify.getInt(0,scope);
		Schedule l = new Schedule();
		for (int i=0; i<=maxLength; i++){
			switch (Verify.random(2)){
			case 0:
				System.out.println("adding...");
				l.addProcess(Verify.getInt(0,scope));
				// assert l.repOK();			
			case 1:
				System.out.println("block...");
				l.blockProcess();
				// assert l.repOK();			
			case 2:
				System.out.println("quantumExpire...");
				l.quantumExpire();
				// assert l.repOK();
			case 3:
				// System.out.println("fini...");
				l.finishProcess();
				// assert l.repOK();			
			case 4:
				// System.out.println("finiAll...");
				l.finishAllProcesses();
				// assert l.repOK();					
			case 5:
				// System.out.println("adding...");
				l.upgradeProcessPrio(Verify.getInt(0,scope),Verify.getInt(0,scope));
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

