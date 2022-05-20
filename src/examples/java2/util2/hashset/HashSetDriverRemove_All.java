/*
/*
 * @(#)LinkedList.java  1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2.hashset;

import gov.nasa.jpf.vm.Verify;
import java2.util2.*;

public class HashSetDriverRemove_All {

  private static HashSet generateStructure(int maxScope) {

    int scope=maxScope;
    HashSet h = null;
    switch (Verify.random(3)){
 		case 0:
 			h = new HashSet(Verify.getInt(0,scope),Verify.getInt(1,scope));
 			break;
 		case 1:
 			h = new HashSet(Verify.getInt(0,scope));
 			break;
 		case 2:
 			h = new HashSet();
 			break;
    	}
    int maxLength=Verify.getInt(0,scope);
    for (int i=1; i<=maxLength; i++){
      try{  
        switch (Verify.random(6)){
          case 0:
            h.add(Verify.getInt(0,scope));
            // assert l.repOK();  
            break;    
          case 1:
            h.remove(Verify.getInt(0,scope));
            // assert l.repOK();      
            break;
          case 2:
            h.clear();
            // assert l.repOK();
            break;
          case 3:
            h.contains(Verify.getInt(0,scope));
            break;
          case 4:
            h.iterator();
            break;
          case 5:
            h.size();
            break;
          case 6:
            h.clone();
            break;
        }
      }catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
      
      }
    }
    return h;
  }

  public static void main(String[] args) {
    int scope=1;
    try{
      HashSet tree= generateStructure(scope);
      tree.remove(Verify.getInt(0,scope));
    }catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
      
    }
  }


}

