package fibonacciheap;

import java.util.HashSet;
import java.util.Set;

public class FibonacciHeapNode {

    protected int key; // the key of the node

    protected int degree; // number of children of the node

    protected FibonacciHeapNode parent; // the parent of the node

    protected FibonacciHeapNode child; // a child of the node

    protected FibonacciHeapNode left; // the node to the left of the current

    protected FibonacciHeapNode right; // the node to the right of the current

    protected int mark; // a special mark

    public FibonacciHeapNode() {
        left = this;
        right = this;
    }

    public int getSize(FibonacciHeapNode fibNode) {
        int result = 1;
        if (child != null)
            result += child.getSize(child);
        if (right != fibNode)
            result += right.getSize(fibNode);
        return result;
    }

    public boolean contains(FibonacciHeapNode start) {
        FibonacciHeapNode temp = start;
        do {
            if (temp == this)
                return true;
            else
                temp = temp.right;
        } while (temp != start);
        return false;
    }

    @Override
    public String toString() {
        String ret = "(";
        if (parent == null)
            ret += "P:null";
        else
            ret += "P:";// + parent.key;
        // ret += "K:" + key + ";D:" + degree + ")";
        ret += "K:" + ";D:" + degree + ")";
        if (child != null)
            ret += "C:";// + child.key;
        else
            ret += "C:null";
        if (left != null)
            ret += "L:";// + left.key;
        else
            ret += "L:null";
        if (right != null)
            ret += "R:";// + right.key;
        else
            ret += "R:null";
        return ret;
    }

    private boolean isEqualTo(FibonacciHeapNode node) {
        FibonacciHeapNode tempThis = this;
        FibonacciHeapNode tempThat = node;
        do {
            if ((tempThis.key != tempThat.key)
                    || (tempThis.degree != tempThat.degree)
                    || (tempThis.mark != tempThat.mark)
                    || ((tempThis.child != null) && (tempThat.child == null))
                    || ((tempThis.child == null) && (tempThat.child != null))
                    || ((tempThis.child != null) && (!tempThis.child
                            .isEqualTo(tempThat.child))))
                return false;
            else {
                tempThis = tempThis.right;
                tempThat = tempThat.right;
            }
        } while (tempThis.right != this);
        return true;
    }

    public boolean equals(Object that) {
        if ((!(that instanceof FibonacciHeapNode)) || (that == null))
            return false;
        return isEqualTo((FibonacciHeapNode) that);
    }

    private FibonacciHeapNode findKey(FibonacciHeapNode start, int k) {
        FibonacciHeapNode temp = start;
        do
            if (temp.key == k)
                return temp;
            else {
                FibonacciHeapNode child_temp = null;
                if ((temp.key < k) && (temp.child != null))
                    child_temp = temp.child.findKey(temp.child, k);
                if (child_temp != null)
                    return child_temp;
                else
                    temp = temp.right;
            }
        while (temp != start);
        return null;
    }

    private boolean numberOfChildrenIsCorrect() {
        if ((child == null) || (degree == 0))
            return ((child == null) && (degree == 0));
        else {
            HashSet<Integer> child_set = new HashSet<Integer>();
            FibonacciHeapNode temp_child = child;
            for (int i = 0; i < degree; i++) {
                // Would crash for some reason... (I got a segmentation fault)
                // if (!child_set.add(temp_child)) return false; // found a
                // loop!
                if (!child_set.add(temp_child.hashCode()))
                    return false; // found a
                // loop!
                temp_child = temp_child.right;
                if (temp_child == null)
                    return false;
            }
            return (temp_child == child);
        }
    }

    int numberOfChildren() {
        if (child == null)
            return 0;
        int num = 1;
        for (FibonacciHeapNode current = child.right; current != child; current = current.right) {
            num++;
        }
        return num;
    }

    boolean checkDegrees() {
        FibonacciHeapNode current = this;
        do {
            if (current.numberOfChildren() != current.degree)
                return false;
            if (current.child != null)
                if (!current.child.checkDegrees())
                    return false;
            current = current.right;
        } while (current != this);
        return true;
    }

    boolean checkHeapified() {
        touch(key);
        if (child == null)
            return true;
        FibonacciHeapNode current = child;
        do {
            if (current.key < key)
                return false;
            if (!current.checkHeapified())
                return false;
            current = current.right;
        } while (current != child);
        return true;
    }

    void touch(int key) {
    }

    boolean isStructural(Set<FibonacciHeapNode> visited,
            FibonacciHeapNode parent) {
        FibonacciHeapNode current = this;
        do {
            if (current.parent != parent)
                return false;
            // if (!visited.add(new IdentityWrapper(current)))
            if (!visited.add(current))
                return false;
            if ((current.child != null)
                    && (!current.child.isStructural(visited, current)))
                return false;
            if (current.right == null)
                return false;
            if (current.right.left != current)
                return false;
            current = current.right;
        } while (current != this);
        return true;
    }

}
