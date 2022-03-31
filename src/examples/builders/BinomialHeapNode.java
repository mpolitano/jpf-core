package builders;

public class BinomialHeapNode {
        private int key;

        private int degree;

        private BinomialHeapNode parent;
        private BinomialHeapNode sibling;
        private BinomialHeapNode child;

        public BinomialHeapNode(int k) {
            key = k;
            degree = 0;
            parent = null;
            sibling = null;
            child = null;
        }

        @Override
        public String toString() {
            String res = "{" + "[" + key + "]" + "[" + degree + "]" + "{"
                    + (sibling != null ? sibling.toString() : null) + "}" + "("
                    + (child != null ? child.toString() : null) + ")}";
            return res;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int value) {
            key = value;
        }

        public int getDegree() {
            return degree;
        }

        public void setDegree(int deg) {
            degree = deg;
        }

        public BinomialHeapNode getParent() {
            return parent;
        }

        public void setParent(BinomialHeapNode par) {
            parent = par;
        }

        public BinomialHeapNode getSibling() {
            return sibling;
        }

        public void setSibling(BinomialHeapNode nextBr) {
            sibling = nextBr;
        }

        public BinomialHeapNode getChild() {
            return child;
        }

        public void setChild(BinomialHeapNode firstCh) {
            child = firstCh;
        }

        public int getSize() {
            return (1 + ((child == null) ? 0 : child.getSize()) + ((sibling == null) ? 0
                    : sibling.getSize()));
        }

        public BinomialHeapNode reverse(BinomialHeapNode sibl) {
            BinomialHeapNode ret;
            if (sibling != null) {
                ret = sibling.reverse(this);
            } else {
                ret = this;
            }

            sibling = sibl;
            return ret;
        }

        public BinomialHeapNode findMinNode() {
            BinomialHeapNode x = this, y = this;
            int min = x.key;
            while (x != null) {
                if (x.key < min) {
                    y = x;
                    min = x.key;
                } else {
                }

                x = x.sibling;
            }
            return y;
        }

        public BinomialHeapNode findANodeWithKey(int value) {
            BinomialHeapNode temp = this, node = null;
            while (temp != null) {
                if (temp.key == value) {
                    node = temp;
                    break;
                } else {
                }

                if (temp.child == null) {
                    temp = temp.sibling;
                } else {
                    node = temp.child.findANodeWithKey(value);
                    if (node == null) {
                        temp = temp.sibling;
                    } else {
                        break;
                    }
                }
            }
            return node;
        }
        
    }
