package builders;


public class Node {
	    public int value;

	    public Node left, right;

	    public Node(int x) {
	        value = x;
	        left = null;
	        right = null;
	    }

	    @Override
	    public String toString() {
	        String res = "{" + value + "["
	                + (left != null ? left.toString() : null) + "]" + "["
	                + (right != null ? right.toString() : null) + "]}";
	        return res;
	    }
	}