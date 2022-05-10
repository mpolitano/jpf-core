/*
 * Copyright (C) 2014, United States Government, as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 * All rights reserved.
 *
 * Symbolic Pathfinder (jpf-symbc) is licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0. 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package java2.util2;


import gov.nasa.jpf.vm.Verify;


/**
 *
 * @author Mithun Acharya
 *
 *  * Arguments for concrete execution:
 * BSTDriverSequences
 *
 * Arguments for symbolic execution:
 * +vm.insn_factory.class=gov.nasa.jpf.symbc.SymbolicInstructionFactory
 * +vm.classpath=.
 * +vm.storage.class=
 * +search.multiple_errors=true
 * +symbolic.method=add(sym);remove(sym);find(sym)
 * +jpf.report.console.finished=
 * +jpf.listener=gov.nasa.jpf.symbc.sequences.SymbolicSequenceListener
 * BSTDriverSequences
 *
 */
public class DriverLinkedList {

	public static void testDriver(){
		int scope=2;
		int maxLength=scope+2;
		LinkedList t = new LinkedList();
		// System.out.println("------ New tree ------");
		for (int i=0; i<maxLength; i++){
			Verify.beginAtomic();
			switch (Verify.random(0)){
			case 0:
				// System.out.println("adding...");
				t.add(Verify.getInt(0,scope));
				break;
			}
			Verify.endAtomic();
			Verify.breakTransition(null);
			// System.out.println("------ DUMMY ------");

		}
		// System.out.println("------ End of tree ------");
	}

	public static void main(String[] args){
//		Utils.readScope();
		testDriver(); // with 2 you do not get complete coverage
//		Debug.printPC("Path Condition: ");
		System.out.println();
	}
}
