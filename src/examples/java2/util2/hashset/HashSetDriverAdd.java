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

public class HashSetDriverAdd {

  private static HashSet generateStructure(int maxScope) {

    int scope=maxScope;
    HashSet h = new HashSet();
    int maxLength=Verify.getInt(0,scope);
    for (int i=1; i<=maxLength; i++){
      try{  
        switch (Verify.random(0)){
          case 0:
            h.add(Verify.getInt(0,scope));
            break;

        }
      }catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
      
      }
    }
    return h;
  }

  public static void main(String[] args) {
    int scope=1;
    HashSet tree= generateStructure(scope);
    tree.add(Verify.getInt(0,scope));
    assert tree.repOK();
  }


}

