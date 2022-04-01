/**
 * Semi-automatically generated files used to measure predicate coverage.
 * Details can be found at http://mir.cs.illinois.edu/coverage
 */
package builders;
import gov.nasa.jpf.vm.Verify;
import gov.nasa.jpf.annotation.FilterField;

public class TreeMap_pred_All {

    private transient Entry root = null;

    private transient int size = 0;

    private void incrementSize() {
        size++;
    }

    private void decrementSize() {
        size--;
    }

    public TreeMap_pred_All() {
    }

    public int size() {
        return size;
    }

    public boolean containsKey(int key) {
        return getEntry(key) != null;
    }

    private Entry getEntry(int key) {
        Entry p = root;
        while (p != null) {
            //(0, p);
            if (key == p.key) {
                //(2, p);
                return p;
            } else {
                //(3, p);
                if (key < p.key) {
                    //(4, p);
                    p = p.left;
                } else {
                    //(5, p);
                    p = p.right;
                }
            }

        }
        //(1, p);
        return null;
    }

    public void put(int key) {
        Entry t = root;
        if (t == null) {
            //(6, t);
            incrementSize();
            root = new Entry(key, null);
            return;
        } else {
            //(7, t);
        }

        while (true) {
            //(8, t);
            if (key == t.key) {
                //(9, t);
                return;
            } else {
                //(10, t);
                if (key < t.key) {
                    //(11, t);
                    if (t.left != null) {
                        //(13, t);
                        t = t.left;
                    } else {
                        //(14, t);
                        incrementSize();
                        t.left = new Entry(key, t);
                        fixAfterInsertion(t.left);
                        return;
                    }

                } else {
                    //(12, t);
                    if (t.right != null) {
                        //(15, t);
                        t = t.right;
                    } else {
                        //(16, t);
                        incrementSize();
                        t.right = new Entry(key, t);
                        fixAfterInsertion(t.right);
                        return;
                    }

                }
            }

        }
    }

    public void remove(int key) {
        Entry p = getEntry(key);
        if (p == null) {
            //(17, p);
            return;
        } else {
            //(18, p);
        }

        deleteEntry(p);
        return;
    }

    @Override
    public String toString() {
        String res = "";
        if (root != null)
            res = root.toString();
        return res;
    }

    public String concreteString(int max_level) {
        String res = "";
        if (root != null)
            res = root.concreteString(max_level, 0);
        return res;
    }

    private final static boolean RED = false;

    private final static boolean BLACK = true;

    static class Entry {

        int key;

        Entry left = null;

        Entry right = null;

        Entry parent;

        boolean color = BLACK;

        Entry(int key, Entry parent) {
            this.key = key;
            this.parent = parent;
        }

        Entry(int key, Entry left, Entry right, Entry parent, boolean color) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.color = color;
        }

        public int getKey() {
            return key;
        }

        @Override
        public String toString() {
            String res = "{ " + (color == BLACK ? "B" : "R") + " ";
            if (left == null)
                res += "null";
            else
                res += left.toString();
            res += " ";
            if (right == null)
                res += "null";
            else
                res += right.toString();
            res += " }";
            return res;
        }

        public String concreteString(int max_level, int cur_level) {
            String res;
            if (cur_level == max_level) {
                res = "{ subtree }";
            } else {
                res = "{ " + (color == BLACK ? "B" : "R") + key + " ";
                if (left == null)
                    res += "null";
                else
                    res += left.concreteString(max_level, cur_level + 1);
                res += " ";
                if (right == null)
                    res += "null";
                else
                    res += right.concreteString(max_level, cur_level + 1);
                res += " }";
            }

            return res;
        }

    }

    private Entry successor(Entry t) {
        if (t == null) {
            //(19, t);
            return null;
        } else {
            //(20, t);
            if (t.right != null) {
                //(21, t);
                Entry p = t.right;
                while (p.left != null) {
                    //(23, t);
                    p = p.left;
                }
                //(24, t);
                return p;
            } else {
                //(22, t);
                Entry p = t.parent;
                Entry ch = t;
                while (p != null && ch == p.right) {
                    //(25, t);
                    ch = p;
                    p = p.parent;
                }
                //(26, t);
                return p;
            }
        }

    }

    private static boolean colorOf(Entry p) {
        return (p == null ? BLACK : p.color);
    }

    private static Entry parentOf(Entry p) {
        return (p == null ? null : p.parent);
    }

    private static void setColor(Entry p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private static Entry leftOf(Entry p) {
        return (p == null) ? null : p.left;
    }

    private static Entry rightOf(Entry p) {
        return (p == null) ? null : p.right;
    }

    private void rotateLeft(Entry p) {
        Entry r = p.right;
        p.right = r.left;
        if (r.left != null) {
            //(27, p);
            r.left.parent = p;
        } else {
            //(28, p);
        }

        r.parent = p.parent;
        if (p.parent == null) {
            //(29, p);
            root = r;
        } else {
            //(30, p);
            if (p.parent.left == p) {
                //(31, p);
                p.parent.left = r;
            } else {
                //(32, p);
                p.parent.right = r;
            }
        }

        r.left = p;
        p.parent = r;
    }

    private void rotateRight(Entry p) {
        Entry l = p.left;
        p.left = l.right;
        if (l.right != null) {
            //(33, p);
            l.right.parent = p;
        } else {
            //(34, p);
        }

        l.parent = p.parent;
        if (p.parent == null) {
            //(35, p);
            root = l;
        } else {
            //(36, p);
            if (p.parent.right == p) {
                //(37, p);
                p.parent.right = l;
            } else {
                //(38, p);
                p.parent.left = l;
            }
        }

        l.right = p;
        p.parent = l;
    }

    private void fixAfterInsertion(Entry x) {
        x.color = RED;
        while (x != null && x != root && x.parent.color == RED) {
            //(39, x);
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                //(41, x);
                Entry y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    //(43, x);
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    //(44, x);
                    if (x == rightOf(parentOf(x))) {
                        //(45, x);
                        x = parentOf(x);
                        rotateLeft(x);
                    } else {
                        //(46, x);
                    }

                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    if (parentOf(parentOf(x)) != null) {
                        //(47, x);
                        rotateRight(parentOf(parentOf(x)));
                    } else {
                        //(48, x);
                    }
                }
            } else {
                //(42, x);
                Entry y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    //(49, x);
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    //(50, x);
                    if (x == leftOf(parentOf(x))) {
                        //(51, x);
                        x = parentOf(x);
                        rotateRight(x);
                    } else {
                        //(52, x);
                    }

                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    if (parentOf(parentOf(x)) != null) {
                        //(53, x);
                        rotateLeft(parentOf(parentOf(x)));
                    } else {
                        //(54, x);
                    }
                }
            }
        }
        //(40, x);
        root.color = BLACK;
    }

    private void deleteEntry(Entry p) {
        decrementSize();
        if (p.left != null && p.right != null) {
            //(55, p);
            Entry s = successor(p);
            swapPosition(s, p);
        } else {
            //(56, p);
        }

        Entry replacement = (p.left != null ? p.left : p.right);
        if (replacement != null) {
            //(57, p);
            replacement.parent = p.parent;
            if (p.parent == null) {
                //(59, p);
                root = replacement;
            } else {
                //(62, p);
                if (p == p.parent.left) {
                    //(61, p);
                    p.parent.left = replacement;
                } else {
                	p.parent.right = replacement;
                }
            }

            p.left = p.right = p.parent = null;
            if (p.color == BLACK) {
                fixAfterDeletion(replacement);
            } else {
            }
        } else {
            if (p.parent == null) {
                root = null;
            } else {
                if (p.color == BLACK) {
                    fixAfterDeletion(p);
                } else {
                }

                if (p.parent != null) {
                    if (p == p.parent.left) {
                        p.parent.left = null;
                    } else {
                        if (p == p.parent.right) {
                            p.parent.right = null;
                        } else {
                        }
                    }

                    p.parent = null;
                } else {
                }
            }
        }

    }

    public void fixAfterDeletion(Entry x) {
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) {
                Entry sib = rightOf(parentOf(x));
                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                } else {
                }

                if (colorOf(leftOf(sib)) == BLACK
                        && colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    } else {
                    }

                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(rightOf(sib), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            } else {
                Entry sib = leftOf(parentOf(x));
                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                } else {
                }

                if (colorOf(rightOf(sib)) == BLACK
                        && colorOf(leftOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    } else {
                    }

                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }
        setColor(x, BLACK);
    }

    private void swapPosition(Entry x, Entry y) {
        Entry px = x.parent, lx = x.left, rx = x.right;
        Entry py = y.parent, ly = y.left, ry = y.right;
        boolean xWasLeftChild = px != null && x == px.left;
        boolean yWasLeftChild = py != null && y == py.left;
        if (x == py) {
            x.parent = y;
            if (yWasLeftChild) {
                y.left = x;
                y.right = rx;
            } else {
                y.right = x;
                y.left = lx;
            }
        } else {
            x.parent = py;
            if (py != null) {
                if (yWasLeftChild) {
                    py.left = x;
                } else {
                    py.right = x;
                }

            } else {
            }

            y.left = lx;
            y.right = rx;
        }

        if (y == px) {
            y.parent = x;
            if (xWasLeftChild) {
                x.left = y;
                x.right = ry;
            } else {
                x.right = y;
                x.left = ly;
            }
        } else {
            y.parent = px;
            if (px != null) {
                if (xWasLeftChild) {
                    px.left = y;
                } else {
                    px.right = y;
                }

            } else {
            }

            x.left = ly;
            x.right = ry;
        }

        if (x.left != null) {
            x.left.parent = x;
        } else {
        }

        if (x.right != null) {
            x.right.parent = x;
        } else {
        }

        if (y.left != null) {
            y.left.parent = y;
        } else {
        }

        if (y.right != null) {
            y.right.parent = y;
        } else {
        }

        boolean c = x.color;
        x.color = y.color;
        y.color = c;
        if (root == x) {
            root = y;
        } else {
            if (root == y) {
                root = x;
            } else {
            }
        }
    }

    private static int NUM_OF_PREDICATES = 6;

    public static int numOfPredicates() {
        return NUM_OF_PREDICATES;
    }

    public static void testDriver(){
        int scope=5;
        int maxLength=Verify.getInt(0,scope);
        TreeMap_pred_All t = new TreeMap_pred_All();
        for (int i=0; i<=maxLength; i++){
            switch (Verify.random(4)){
            case 0:
                System.out.println("adding...");
                t.put(Verify.getInt(0,scope));
            case 1:
                System.out.println("rm...");
                t.remove(Verify.getInt(0,scope));
            case 2:
                System.out.println("size...");
                t.size();
            case 3:
                System.out.println("contains...");
                t.containsKey(Verify.getInt(0,scope));
            case 4:
                System.out.println("contains...");
                t.concreteString(Verify.getInt(0,scope));
            }
            
            // System.out.println("------ DUMMY ------");

        }
        // System.out.println("------ End of tree ------");
        // System.out.println(t);
        t.put(Verify.getInt(0,scope));
        // assert t.repOK();
    }

    public static void main(String[] args){
        testDriver();   
        System.out.println();
    }



}
