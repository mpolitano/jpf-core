/**
 * Semi-automatically generated files used to measure predicate coverage.
 * Details can be found at http://mir.cs.illinois.edu/coverage
 */
package builders;

public class BinomialHeap_pred {

    private BinomialHeapNode Nodes;

    private int size;

    public BinomialHeap_pred() {
        Nodes = null;
        size = 0;
    }

    public String toString() {
        return Nodes != null ? Nodes.toString() : "null";
    }

    public int size() {
        return size;
    }

    public int findMinimum() {
        return Nodes.findMinNode().getKey();
    }

    private void merge(BinomialHeapNode binHeap) {
        BinomialHeapNode temp1 = Nodes, temp2 = binHeap;
        while ((temp1 != null) && (temp2 != null)) {
            if (temp1.getDegree() == temp2.getDegree()) {
                BinomialHeapNode tmp = temp2;
                temp2 = temp2.getSibling();
                tmp.setSibling(temp1.getSibling());
                temp1.setSibling(tmp);
                temp1 = tmp.getSibling();
            } else {
                if (temp1.getDegree() < temp2.getDegree()) {
                    if ((temp1.getSibling() == null)
                            || (temp1.getSibling().getDegree() > temp2.getSibling().getDegree())) {
                        BinomialHeapNode tmp = temp2;
                        temp2 = temp2.getSibling();
                        tmp.setSibling(temp1.getSibling());
                        temp1.setSibling(tmp);
                        temp1 = tmp.getSibling();
                    } else {
                        temp1 = temp1.getSibling();
                    }
                } else {
                    BinomialHeapNode tmp = temp1;
                    temp1 = temp2;
                    temp2 = temp2.getSibling();
                    temp1.setSibling(tmp);
                    if (tmp == Nodes) {
                        Nodes = temp1;
                    } else {
                    }
                }
            }
        }
        if (temp1 == null) {
            temp1 = Nodes;
            while (temp1.getSibling() != null) {
                temp1 = temp1.getSibling();
            }
            temp1.setSibling(temp2);
        } else {
        }
    }

    private void unionNodes(BinomialHeapNode binHeap) {
        merge(binHeap);
        BinomialHeapNode prevTemp = null, temp = Nodes, nextTemp = Nodes.getSibling();
        while (nextTemp != null) {
            if ((temp.getDegree() != nextTemp.getDegree())
                    || ((nextTemp.getSibling() != null) && (nextTemp.getSibling().getDegree() == temp.getDegree()))) {
                prevTemp = temp;
                temp = nextTemp;
            } else {
                if (temp.getKey() <= nextTemp.getKey()) {
                    temp.setSibling(nextTemp.getSibling());
                    nextTemp.setParent(temp);
                    nextTemp.setSibling(temp.getChild());
                    temp.setChild(nextTemp);
                    temp.setDegree(temp.getDegree()+1);
                } else {
                    if (prevTemp == null) {
                        Nodes = nextTemp;
                    } else {
                        prevTemp.setSibling(nextTemp);
                    }

                    temp.setParent(nextTemp);
                    temp.setSibling(nextTemp.getChild());
                    nextTemp.setChild(temp);
                    nextTemp.setDegree(nextTemp.getDegree()+1);;
                    temp = nextTemp;
                }
            }

            nextTemp = temp.getSibling();
        }
    }

    public void insert(int value) {
        //if (value > 0) {
            BinomialHeapNode temp = new BinomialHeapNode(value);
            if (Nodes == null) {
                Nodes = temp;
                size = 1;
            } else {
                unionNodes(temp);
                size++;
            }
       // } else {
       // }
    }

    public int extractMin() {
        if (Nodes == null) {
            return -1;
        } else {
        }

        BinomialHeapNode temp = Nodes, prevTemp = null;
        BinomialHeapNode minNode = Nodes.findMinNode();
        while (temp.getKey() != minNode.getKey()) {
            prevTemp = temp;
            temp = temp.getSibling();
        }
        if (prevTemp == null) {
            Nodes = temp.getSibling();
        } else {
            prevTemp.setSibling(temp.getSibling());
        }

        temp = temp.getChild();
        BinomialHeapNode fakeNode = temp;
        while (temp != null) {
            temp.setParent(null);
            temp = temp.getSibling();
        }
        if ((Nodes == null) && (fakeNode == null)) {
            size = 0;
        } else {
            if ((Nodes == null) && (fakeNode != null)) {
                Nodes = fakeNode.reverse(null);
                size = Nodes.getSize();
            } else {
                if ((Nodes != null) && (fakeNode == null)) {
                    size = Nodes.getSize();
                } else {
                    unionNodes(fakeNode.reverse(null));
                    size = Nodes.getSize();
                }
            }
        }

        return minNode.getKey();
    }

    public void decreaseKeyValue(int old_value, int new_value) {
        BinomialHeapNode temp = Nodes.findANodeWithKey(old_value);
        if (temp == null) {
            return;
        } else {
        }

        temp.setKey(new_value);
        BinomialHeapNode tempParent = temp.getParent();
        while ((tempParent != null) && (temp.getKey() < tempParent.getKey())) {
            int z = temp.getKey();
            temp.setKey(tempParent.getKey());
            tempParent.setKey(z);
            temp = tempParent;
            tempParent = tempParent.getParent();
        }
    }

    public void delete(int value) {
        if ((Nodes != null) && (Nodes.findANodeWithKey(value) != null)) {
            decreaseKeyValue(value, findMinimum() - 1);
            extractMin();
        } else {
        }

    }

    public static void main(String[] Argv) {
        BinomialHeap_pred b = new BinomialHeap_pred();
        b.insert(3);
        b.insert(5);
        System.out.println("min: " + b.findMinimum());
        System.out.println("size: " + b.size);
        b.extractMin();
        System.out.println("min: " + b.findMinimum());
    }

    private static int NUM_OF_PREDICATES = 9;

    public static int numOfPredicates() {
        return NUM_OF_PREDICATES;
    }

}
