/**
 * Semi-automatically generated files used to measure predicate coverage.
 * Details can be found at http://mir.cs.illinois.edu/coverage
 */
package builders;

public class FibHeap {
    private FibHeapNode min;

    private int n;

    public FibHeap() {
    }

    @Override
    public String toString() {
        if (min == null) {
            //(0, null, null);
            return "null";
        } else {
            //(1, null, null);
        }

        return min.toString();
    }

    private void cascadingCut(FibHeapNode y) {
        FibHeapNode z = y.parent;
        if (z != null) {
            //(2, z, null);
            if (!y.mark) {
                //(4, y, null);
                y.mark = true;
            } else {
                //(5, y, null);
                cut(y, z);
                cascadingCut(z);
            }
        } else {
            //(3, y, null);
        }
    }

    private void consolidate() {
        int D = n + 1;
        FibHeapNode A[] = new FibHeapNode[D];
        for (int i = 0; i < D; i++) {
            //(6, A[i], null);
            A[i] = null;
        }
        //(7, null, null);

        int k = 0;
        FibHeapNode x = min;
        if (x != null) {
            //(8, x, null);
            k++;
            for (x = x.right; x != min; x = x.right) {
                //(10, x, null);
                k++;
            }
            //(11, x, null);
        } else {
            //(9, min, null);
        }

        while (k > 0) {
            //(12, min, null);
            int d = x.degree;
            FibHeapNode rightNode = x.right;
            while (A[d] != null) {
                //(14, A[d], null);
                FibHeapNode y = A[d];
                if (x.cost > y.cost) {
                    //(16, x, y);
                    FibHeapNode temp = y;
                    y = x;
                    x = temp;
                } else {
                    //(17, x, y);
                }

                link(y, x);
                A[d] = null;
                d++;
            }
            //(15, x, null);
            A[d] = x;
            x = rightNode;
            k--;
        }
        //(13, min, null);
        min = null;
        for (int i = 0; i < D; i++) {
            //(18, A[i], null);
            if (A[i] != null) {
                //(20, A[i], null);
                if (min != null) {
                    //(22, A[i], null);
                    A[i].left.right = A[i].right;
                    A[i].right.left = A[i].left;
                    A[i].left = min;
                    A[i].right = min.right;
                    min.right = A[i];
                    A[i].right.left = A[i];
                    if (A[i].cost < min.cost) {
                        //(24, A[i], min);
                        min = A[i];
                    } else {
                        //(25, A[i], min);
                    }
                } else {
                    //(23, A[i], null);
                    min = A[i];
                }
            } else {
                //(21, min, null);
            }
        }
        //(19, min, null);
    }

    private void cut(FibHeapNode x, FibHeapNode y) {
        x.left.right = x.right;
        x.right.left = x.left;
        y.degree--;
        if (y.child == x) {
            //(26, x, y);
            y.child = x.right;
        } else {
            //(27, x, y);
        }

        if (y.degree == 0) {
            //(28, y, x);
            y.child = null;
        } else {
            //(29, x, y);
        }

        x.left = min;
        x.right = min.right;
        min.right = x;
        x.right.left = x;
        x.parent = null;
        x.mark = false;
    }

    public void decreaseKey(FibHeapNode x, int c) {
        if (c > x.cost) {
            //(30, x, null);
            //System.err.println("Error: new key is greater than current key.");
            //return;
            throw new IllegalArgumentException("Error: new key is greater than current key.");
        } else {
            //(31, x, null);
        }

        x.cost = c;
        FibHeapNode y = x.parent;
        if ((y != null) && (x.cost < y.cost)) {
            //(32, x, y);
            cut(x, y);
            cascadingCut(y);
        } else {
            //(33, x, null);
        }

        if (x.cost < min.cost) {
            //(34, x, min);
            min = x;
        } else {
            //(35, x, min);
        }

    }

    public void delete(FibHeapNode node) {
        decreaseKey(node, Integer.MIN_VALUE);
        removeMin();
    }

    public boolean empty() {
        return min == null;
    }

    public void insert(int c) {
        FibHeapNode n = new FibHeapNode(c);
        insert(n);
    }

    public FibHeapNode insert(FibHeapNode toInsert) {
        if (min != null) {
            //(36, min, null);
            toInsert.left = min;
            toInsert.right = min.right;
            min.right = toInsert;
            toInsert.right.left = toInsert;
            if (toInsert.cost < min.cost) {
                //(38, min, null);
                min = toInsert;
            } else {
                //(39, min, null);
            }
        } else {
            //(37, min, null);
            min = toInsert;
        }

        n++;
        return toInsert;
    }

    private void link(FibHeapNode node1, FibHeapNode node2) {
        node1.left.right = node1.right;
        node1.right.left = node1.left;
        node1.parent = node2;
        if (node2.child == null) {
            //(40, node1, node2);
            node2.child = node1;
            node1.right = node1;
            node1.left = node1;
        } else {
            //(41, node1, node2);
            node1.left = node2.child;
            node1.right = node2.child.right;
            node2.child.right = node1;
            node1.right.left = node1;
        }

        node2.degree++;
        node1.mark = false;
    }

    public FibHeapNode min() {
        return min;
    }

    public FibHeapNode removeMin() {
        FibHeapNode z = min;
        if (z != null) {
            //(42, z, null);
            int i = z.degree;
            FibHeapNode x = z.child;
            while (i > 0) {
                //(44, x, z);
                FibHeapNode nextChild = x.right;
                x.left.right = x.right;
                x.right.left = x.left;
                x.left = min;
                x.right = min.right;
                min.right = x;
                x.right.left = x;
                x.parent = null;
                x = nextChild;
                i--;
            }
            //(45, x, z);
            z.left.right = z.right;
            z.right.left = z.left;
            if (z == z.right) {
                //(46, x, z);
                min = null;
            } else {
                //(47, x, z);
                min = z.right;
                consolidate();
            }

            n--;
        } else {
            //(43, z, null);
        }

        return z;
    }

    public int size() {
        return n;
    }

    public FibHeap union(FibHeap heap1, FibHeap heap2) {
        FibHeap heap = new FibHeap();
        if ((heap1 != null) && (heap2 != null)) {
            //(48, heap1.min, heap2.min);
            heap.min = heap1.min;
            if (heap.min != null) {
                //(50, heap1.min, heap2.min);
                if (heap2.min != null) {
                    //(52, heap1.min, heap2.min);
                    heap.min.right.left = heap2.min.left;
                    heap2.min.left.right = heap.min.right;
                    heap.min.right = heap2.min;
                    heap2.min.left = heap.min;
                    if (heap2.min.cost < heap1.min.cost) {
                        //(54, heap1.min, heap2.min);
                        heap.min = heap2.min;
                    } else {
                        //(55, heap1.min, heap2.min);
                    }
                } else {
                    //(53, heap1.min, heap2.min);
                }
            } else {
                //(51, heap1.min, heap2.min);
                heap.min = heap2.min;
            }

            heap.n = heap1.n + heap2.n;
        } else {
            //(49, heap1.min, heap2.min);
        }

        return heap;
    }
    /*
    public static void main(String Argv[]) {
        FibHeap_pred h = new FibHeap_pred();
        h.insert(3);
        System.out.println(h.min().cost);
        h.insert(2);
        System.out.println(h.min().cost);
        h.insert(4);
        System.out.println(h.min().cost);
        h.insert(1);
        System.out.println(h.min().cost);
        h.removeMin();
        System.out.println(h.min().cost);
        h.removeMin();
        System.out.println(h.min().cost);
        h.removeMin();
        System.out.println(h.min().cost);
        h.removeMin();
        h.removeMin();
        h.removeMin();
    }

    private static int NUM_OF_PREDICATES = 14;

    public static int numOfPredicates() {
        return NUM_OF_PREDICATES;
    }
    */


}
