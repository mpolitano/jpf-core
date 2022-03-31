/**
 * Semi-automatically generated files used to measure predicate coverage.
 * Details can be found at http://mir.cs.illinois.edu/coverage
 */
package builders;
import gov.nasa.jpf.vm.Verify;
import gov.nasa.jpf.annotation.FilterField;
//change the class node for inner class

public class BinTree_pred_All {
	 static class  Node {
	    public int value;

	    public Node left, right;

	    public Node(int x) {
	        value = x;
	        left = null;
	        right = null;
	    }

	@Override
    public String toString() {
        return "["+left +"," +value +"," +right + "]";
    }
	    
	}

    private Node root;
    private int size;
    
    public BinTree_pred_All() {
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


	//FOR PAPER

	public static int N = 20;
	public static void testDriver(){
		int scope=3;
		int maxLength=Verify.getInt(0,scope);
		BinTree_pred_All t = new BinTree_pred_All();
		for (int i=0; i<=maxLength; i++){
			switch (Verify.random(2)){
			case 0:
				System.out.println("adding...");
				t.add(Verify.getInt(0,scope));
				// assert t.repOK();
				break;
			case 1:
				System.out.println("rm...");
				t.remove(Verify.getInt(0,scope));
				break;
			case 2:
				System.out.println("find...");
				t.find(Verify.getInt(0,scope));
				break;	
			}
		}
		t.add(Verify.getInt(0,scope));
	}


	public static void main(String[] args){
		testDriver(); 
		System.out.println();
	}

}
