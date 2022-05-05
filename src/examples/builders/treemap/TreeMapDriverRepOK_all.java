package treemap;

import gov.nasa.jpf.vm.Verify;


public class TreeMapDriverRepOK_all {

    public static TreeMap generatedStruct(int scope){
        int maxLength=Verify.getInt(0,scope);
        TreeMap t = new TreeMap();
        for (int i=0; i<=maxLength; i++){
            switch (Verify.random(4)){
            case 0:
                // System.out.println("adding...");
                t.put(Verify.getInt(0,scope));
            case 1:
                // System.out.println("rm...");
                t.remove(Verify.getInt(0,scope));
            case 2:
                // System.out.println("size...");
                t.size();
            case 3:
                // System.out.println("contains...");
                t.containsKey(Verify.getInt(0,scope));
            case 4:
                // System.out.println("contains...");
                t.concreteString(Verify.getInt(0,scope));
            }

        }
        return t;
    }

    public static void main(String[] args){
        int scope=2;
        TreeMap t = generatedStruct(scope);   
        assert t.repOK();
        // System.out.println();
    }
}