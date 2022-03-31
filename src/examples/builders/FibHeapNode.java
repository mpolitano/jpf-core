package builders;

public class FibHeapNode {

    public FibHeapNode parent, left, right, child;

    public boolean mark = false;
    public int cost, degree = 0;

    public FibHeapNode(int c) {
        cost = c;
        right = this;
        left = this;
    }

    public String toString(int k, boolean flag) {
        String res = "{" + mark + " ";
        if (flag)
            res += cost + " ";
        if (k != 0) {
            if (left == null || left == this)
                res += "null";
            else
                res += left.toString(k - 1, flag);
            if (right == null || right == this)
                res += "null";
            else
                res += right.toString(k - 1, flag);
            if (child == null)
                res += "null";
        }
        return res;
    }

    @Override
    public String toString() {
        return toString(0, true);
    }
}
