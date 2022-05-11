/*
/*
 * @(#)LinkedList.java  1.46 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java2.util2;

import gov.nasa.jpf.vm.Verify;
import java2.util2.*;

public class HashSetDriverRemove {

  private static HashSet generateStructure(int maxScope) {

    int scope=maxScope;
    int maxLength=Verify.getInt(0,scope);
    HashSet h = new HashSet();
    for (int i=0; i<=maxLength; i++){
      try{  
        switch (Verify.random(0)){
          case 0:
            h.add(Verify.getInt(0,scope));
            // assert l.repOK();      
            break;

        }
      }catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
      
      }
    }
    return h;
  }

  public static void main(String[] args) {
    int scope=2;
    try{
      HashSet tree= generateStructure(scope);
      tree.remove(Verify.getInt(0,scope));
    }catch(java2.util2.NoSuchElementException|java.lang.IndexOutOfBoundsException e){
      
    }
  }
}

