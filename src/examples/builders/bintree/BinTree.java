package bintree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import gov.nasa.jpf.vm.Verify;


public class BinTree {

	
	int key;
	
	public BinTree left;

	public BinTree right;
	
	
	public BinTree() {
		this.key = 0;
		this.left = null;
		this.right = null;
	}
	
	public BinTree(int key) {
		this.key = key;
		this.left = null;
		this.right = null;
	}

	public boolean find(int k) {
		if (key == k){
			return true;
		} else {
			return ((left != null) && left.find(key)) || ((right != null) && right.find(key));
		}
	}

	public int count() {
		int n = 1;
		if (left != null)
			n += left.count();
		if (right != null)
			n += right.count();
		return n;
	}



	public void bfsTraverse() {
		List<BinTree> worklist = new LinkedList<BinTree>();
		worklist.add(this);
		while (!worklist.isEmpty()) {
			BinTree node = worklist.remove(0);
			if (node.left != null)
				worklist.add(node.left);
			if (node.right != null)
				worklist.add(node.right);
		}
	}

	public void dfsTraverse() {
		if (left != null)
			left.dfsTraverse();
		if (right != null)
			right.dfsTraverse();
	}
	
	
	public void add(int key) {
		List<BinTree> worklist = new LinkedList<BinTree>();
		worklist.add(this);
		while (!worklist.isEmpty()) {
			BinTree node = worklist.remove(0);
			
			if (node.left != null)
				worklist.add(node.left);
			else {
				node.left = new BinTree(key);
				return;
			}
			
			if (node.right != null)
				worklist.add(node.right);
			else {
				node.right = new BinTree(key);
				return;
			}
		}
	}
	

	public boolean repOK() {
        Set<BinTree> visited = new HashSet<BinTree>();
        LinkedList<BinTree> worklist = new LinkedList<BinTree>();
        visited.add(this);
        worklist.add(this);
        while (!worklist.isEmpty()) {
        	BinTree node = worklist.removeFirst();
        	BinTree left = node.left;
            if (left != null) {
                if (!visited.add(left))
                    return false; // Not acyclic!
                worklist.add(left);
            }
            BinTree right = node.right;
            if (right != null) {
                if (!visited.add(right))
                    return false; // Not acyclic!
                worklist.add(right);
            }
        }
        return true;
    }


}