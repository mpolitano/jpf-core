package bintree;

import gov.nasa.jpf.vm.Verify;


public class BinTreeDriverRepOK_all {
	

	//FOR PAPER
	public static BinTree generateStruc(int scope){
		int maxLength=Verify.getInt(0,scope);
		BinTree t = new BinTree();
		for (int i=0; i<=maxLength; i++){
			switch (Verify.random(1)){
			case 0:
				System.out.println("adding...");
				t.add(Verify.getInt(0,scope));
				// assert t.repOK();
				break;
			case 1:
				System.out.println("find...");
				t.find(Verify.getInt(0,scope));
				break;	
			}
		}
		return t;
	}


	public static void main(String[] args){
		int scope=2;
		BinTree t=generateStruc(scope); 
		t.repOK();
		System.out.println();
	}

}
