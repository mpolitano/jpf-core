import gov.nasa.jpf.vm.Verify;
import gov.nasa.jpf.annotation.FilterField;
import java.util.*;

/**
 * Semi-automatically generated files used to measure predicate coverage.
 * Details can be found at http://mir.cs.illinois.edu/coverage
 */


public class BinTree {
	 static class  Node {
	    public int value;

	    public Node left, right;

	    public Node(int x) {
	        value = x;
	        left = null;
	        right = null;
	    }

	    
	    
	}

    private Node root;
    private int size;
    
    public BinTree() {
        root = null;
    }
    
    public int size() {
        return size;
    }

    
    @Override
    public String toString() {
        return root != null ? root.toString() : "null";
    }

    public void add(int x) {
        Node current = root;
        if (root == null) {
            root = new Node(x);
            size++;
            return;
        } else {
        }

        while (current.value != x) {
            if (x < current.value) {
                if (current.left == null) {
                    current.left = new Node(x);
                    size++;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = new Node(x);
                    size++;
                } else {
                    current = current.right;
                }
            }
        }
    }
    
    public boolean find(int x) {
        Node current = root;
        while (current != null) {
            if (current.value == x) {
                return true;
            } else {
            }

            if (x < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }
	public void remove(int key) {
	    root = removeNode(root, key);
	}
	
	private Node removeNode(Node n, int key) {
	    if (n == null) {
	        return null;
	    }
	    
	    if (key ==  n.value) {
	        // n is the node to be removed
	        if (n.left == null && n.right == null) {
	        	size--;
	            return null;
	        }
	        if (n.left == null) {
	        	size--;
	            return n.right;
	        }
	        if (n.right == null) {
	        	size--;
	        	//size--;
	            return n.left;
	        }
	        int smallVal = smallest(n.right);
	        n.value = smallVal;
	        n.right = removeNode(n.right, smallVal);
	        return n; 
	    }
	    else if (key < n.value) {
	        n.left = removeNode(n.left, key);
	        return n;
	    }
	    else {
	        n.right = removeNode(n.right, key);
	        return n;
	    }
	}
	
	private int smallest(Node n){
	    if (n.left == null) {
	        return n.value;
	    } else {
	        return smallest(n.left);
	    }
	}



public static int N = 20;

	public static void testDriver(){
		int scope=3;
		int maxLength=Verify.getInt(0,scope);
		BinTree t = new BinTree();
		System.out.println("------ New tree ------");
		for (int i=0; i<=maxLength; i++){
			switch (Verify.random(0)){
			case 0:
				System.out.println("adding...");
				t.add(Verify.getInt(0,scope));
				// assert t.repOK();
				break;
		
			
			// case 1:
			// 	System.out.println("rm...");
			// 	t.remove(Verify.getInt(0,scope));
			// 	assert t.repOK();
			// 	break;
		
			
			// case 2:
			// 	System.out.println("find...");
			// 	t.find(Verify.getInt(0,scope));
			// 	assert t.repOK();
			// 	break;
		
			}
			System.out.println("------ DUMMY ------");

		}
		System.out.println("------ End of tree ------");
		System.out.println(t);
		t.add(Verify.getInt(0,scope));
		// assert t.repOK();
	}


	public static void main(String[] args){
//		Utils.readScope();
		testDriver(); // with 2 you do not get complete coverage
		System.out.println();
	}

//	public static void main(String[] args) {
//		BinTree b = new BinTree();
//		while (true) {
//			b.add(5);
//			b.remove(5);
//			assert b.size ==0;
//			System.out.print(1);
//			Verify.breakTransition("");
//		}
//	}

}
