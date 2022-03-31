/**
 * Semi-automatically generated files used to measure predicate coverage.
 * Details can be found at http://mir.cs.illinois.edu/coverage
 */
package fibonacciheap;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import issta2006.fibheap.Node;

public class FibonacciHeap {

    private final static double oneOverLogPhi = 1.0 / Math.log((1.0 + Math
            .sqrt(5.0)) / 2.0);

    private final static int FALSE = 0;
    private final static int TRUE = 1;

    private FibonacciHeapNode minNode;

    private int size;

    public void delete(FibonacciHeapNode x) {
        // make x as small as possible
        decreaseKey(x, Integer.MIN_VALUE);

        // remove the smallest, which decreases n also
        removeMin();
    }

    public void decreaseKey(FibonacciHeapNode x, int k) {
    	if(x.contains(this.minNode)) {
	        if (k > x.key) {
	            throw new IllegalArgumentException(
	                    "decreaseKey() got larger key value");
	        } else {
	        }
	
	        x.key = k;
	        FibonacciHeapNode y = x.parent;
	        if ((y != null) && (x.key < y.key)) {
	            cut(x, y);
	            cascadingCut(y);
	        } else {
	        }
	
	        if (x.key < minNode.key) {
	            minNode = x;
	        } else {
	        }
	        
    	  }else throw new IllegalArgumentException("Node does not belong to de heap"); 
}


    protected void cascadingCut(FibonacciHeapNode y) {
        FibonacciHeapNode z = y.parent;
        if (z != null) {
            if (y.mark == FALSE) {
                y.mark = TRUE;
            } else {
                cut(y, z);
                cascadingCut(z);
            }
        } else {
        }

    }

    protected void cut(FibonacciHeapNode x, FibonacciHeapNode y) {
        x.left.right = x.right;
        x.right.left = x.left;
        y.degree--;
        if (y.child == x) {
            y.child = x.right;
        } else {
        }

        if (y.degree == 0) {
            y.child = null;
        } else {
        }

        x.left = minNode;
        x.right = minNode.right;
        minNode.right = x;
        x.right.left = x;
        x.parent = null;
        x.mark = FALSE;
    }

    public FibonacciHeapNode removeMin() {
        FibonacciHeapNode z = minNode;
        if (z != null) {
            int numKids = z.degree;
            FibonacciHeapNode x = z.child;
            FibonacciHeapNode tempRight;
            while (numKids > 0) {
                tempRight = x.right;
                x.left.right = x.right;
                x.right.left = x.left;
                x.left = minNode;
                x.right = minNode.right;
                minNode.right = x;
                x.right.left = x;
                x.parent = null;
                x = tempRight;
                numKids--;
            }
            z.left.right = z.right;
            z.right.left = z.left;
            if (z == z.right) {
                minNode = null;
            } else {
                minNode = z.right;
                consolidate();
            }

            size--;
        } else {
        }

        return z;
    }

    protected void consolidate() {
        int arraySize = ((int) Math.floor(Math.log(size) * oneOverLogPhi)) + 1;
        List<FibonacciHeapNode> array = new ArrayList<FibonacciHeapNode>(
                arraySize);
        for (int i = 0; i < arraySize; i++) {
            array.add(null);
        }

        int numRoots = 0;
        FibonacciHeapNode x = minNode;
        if (x != null) {
            numRoots++;
            x = x.right;
            while (x != minNode) {
                numRoots++;
                x = x.right;
            }
        } else {
        }

        while (numRoots > 0) {
            int d = x.degree;
            FibonacciHeapNode next = x.right;
            for (;;) {
                FibonacciHeapNode y = array.get(d);
                if (y == null) {
                    break;
                } else {
                }

                if (x.key > y.key) {
                    FibonacciHeapNode temp = y;
                    y = x;
                    x = temp;
                } else {
                }

                link(y, x);
                array.set(d, null);
                d++;
            }

            array.set(d, x);
            x = next;
            numRoots--;
        }
        minNode = null;
        for (int i = 0; i < arraySize; i++) {
            FibonacciHeapNode y = array.get(i);
            if (y == null) {
                continue;
            } else {
            }

            if (minNode != null) {
                y.left.right = y.right;
                y.right.left = y.left;
                y.left = minNode;
                y.right = minNode.right;
                minNode.right = y;
                y.right.left = y;
                if (y.key < minNode.key) {
                    {
                        minNode = y;
                    }
                } else {
                }
            } else {
                minNode = y;
            }
        }
    }

    protected void link(FibonacciHeapNode y, FibonacciHeapNode x) {
        y.left.right = y.right;
        y.right.left = y.left;
        y.parent = x;
        if (x.child == null) {
            x.child = y;
            y.right = y;
            y.left = y;
        } else {
            y.left = x.child;
            y.right = x.child.right;
            x.child.right = y;
            y.right.left = y;
        }

        x.degree++;
        y.mark = FALSE;
    }
    
    
    public void insert(int c) {
        FibonacciHeapNode n = new FibonacciHeapNode();
        insert(n, c);
    }

    private void insert(FibonacciHeapNode node, int key) {
        node.key = key;
        if (minNode != null) {
            node.left = minNode;
            node.right = minNode.right;
            minNode.right = node;
            node.right.left = node;
            if (key < minNode.key) {
                minNode = node;
            } else {
            }
        } else {
            minNode = node;
        }

        size++;
    }

    private int getMin() {
        FibonacciHeapNode temp = minNode;
        int min = minNode.key;
        do {
            if (temp.key < min) {
                min = temp.key;
            } else {
            }

            temp = temp.right;
        } while (temp != minNode);
        return min;
    }

    public String toString() {
        if (minNode == null) {
            return "FibonacciHeap=[]";
        } else {
        }

        Stack<FibonacciHeapNode> stack = new Stack<FibonacciHeapNode>();
        stack.push(minNode);
        StringBuffer buf = new StringBuffer(512);
        buf.append("FibonacciHeap=[");
        while (!stack.empty()) {
            FibonacciHeapNode curr = stack.pop();
            buf.append(curr + (curr == minNode ? "MIN" : ""));
            buf.append(", ");
            if (curr.child != null) {
                stack.push(curr.child);
            } else {
            }

            FibonacciHeapNode start = curr;
            curr = curr.right;
            while (curr != start) {
                buf.append(curr + (curr == minNode ? "MIN" : ""));
                buf.append(", ");
                if (curr.child != null) {
                    stack.push(curr.child);
                } else {
                }

                curr = curr.right;
            }
        }
        buf.append(']');
        return buf.toString();
    }
    
}
