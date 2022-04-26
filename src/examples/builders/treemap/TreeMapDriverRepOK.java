package treemap;


import gov.nasa.jpf.vm.Verify;


public class TreeMapDriverRepOK{

    public static TreeMap generatedStruct(int scope){
        int maxLength=Verify.getInt(0,scope);
        TreeMap t = new TreeMap();
        for (int i=0; i<=maxLength; i++){
            switch (Verify.random(0)){
            case 0:
                // System.out.println("adding...");
                t.put(Verify.getInt(0,scope));
           }
        }
        return t;
    }

    public static void main(String[] args){
        int scope=2;
        TreeMap t = generatedStruct(scope);   
        t.repOK();
        // System.out.println();
    }
}