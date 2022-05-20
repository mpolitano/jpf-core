/**
 * Semi-automatically generated files used to measure predicate coverage.
 * Details can be found at http://mir.cs.illinois.edu/coverage
 */
package builders;

public class IntRedBlackTree_pred {

    protected final static boolean RED = false;
    protected final static boolean BLACK = true;

    protected Node root;
    protected int size;

    public static class Node {
        int key;
        Object value;
        Node parent;
        Node left, right;
        boolean color;

        @Override
        public String toString() {
            String s = "{c:" + color + ";v" + value + "["
                    + (left != null ? left.toString() : null) + "]["
                    + (right != null ? right.toString() : null) + "]}";
            return s;
        }

    }

    protected Node parent(Node n) {
        return n == null ? null : n.parent;
    }

    protected Node left(Node n) {
        return n == null ? null : n.left;
    }

    protected Node right(Node n) {
        return n == null ? null : n.right;
    }

    public boolean containsKey(Integer key) {
        return findNode(key) != null;
    }

    public Object get(Integer key) {
        Node x = findNode(key);
        return x == null ? null : x.value;
    }

    public void put(Integer key, Object value) {
        Node x = new Node();
        x.key = key;
        x.value = value;
        treeInsert(x);
        size++;
    }

    public Object remove(Integer key) {
        Node ret = treeDelete(findNode(key));
        if (ret == null) {
            //(0, null);
            return null;
        } else {
            //(1, ret);
        }

        size--;
        return ret.value;
    }

    protected boolean getColor(Node x) {
        return x == null ? BLACK : x.color;
    }

    protected void setColor(Node x, boolean color) {
        if (x != null) {
            //(2, x);
            x.color = color;
        } else {
            //(3, null);
        }
    }

    protected Node findNode(Integer key) {
        Node cur = root;
        while (cur != null && key.compareTo(cur.key) != 0) {
            //(4, cur);
            if (key.compareTo(cur.key) < 0) {
                //(6, cur);
                cur = cur.left;
            } else {
                //(7, cur);
                cur = cur.right;
            }
        }
        //(5, cur);
        return cur;
    }

    protected void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            //(8, y);
            y.left.parent = x;
        } else {
            //(9, y);
        }

        y.parent = x.parent;
        if (x.parent == null) {
            //(10, y);
            root = y;
        } else {
            //(11, x);
            if (x == x.parent.left) {
                //(12, y);
                x.parent.left = y;
            } else {
                //(13, y);
                x.parent.right = y;
            }
        }

        y.left = x;
        x.parent = y;
    }

    protected void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            //(14, y);
            y.right.parent = x;
        } else {
            //(15, y);
        }

        y.parent = x.parent;
        if (x.parent == null) {
            //(16, y);
            root = y;
        } else {
            //(17, y);
            if (x == x.parent.right) {
                //(18, y);
                x.parent.right = y;
            } else {
                //(19, y);
                x.parent.left = y;
            }
        }

        y.right = x;
        x.parent = y;
    }

    protected void treeInsert(Node z) {
        Node y = null;
        Node x = root;
        while (x != null) {
            //(20, x);
            y = x;
            if (((Integer) z.key).compareTo(x.key) < 0) {
                //(22, y);
                x = x.left;
            } else {
                //(23, y);
                if (((Integer) z.key).compareTo(x.key) == 0) {
                    //(24, y);
                    {
                        x.value = z.value;
                        size--;
                        return;
                    }
                } else {
                    //(25, y);
                    x = x.right;
                }
            }
        }
        //(21, y);
        z.parent = y;
        if (y == null) {
            //(26, y);
            root = z;
        } else {
            //(27, y);
            if (((Integer) z.key).compareTo(y.key) < 0) {
                //(28, y);
                y.left = z;
            } else {
                //(29, y);
                y.right = z;
            }
        }

        z.left = null;
        z.right = null;
        z.color = RED;
        treeInsertFix(z);
    }

    protected void treeInsertFix(Node z) {
        while (getColor(z.parent) == RED) {
            //(30, z);
            if (parent(z) == left(parent(parent(z)))) {
                //(32, z);
                Node y = right(parent(parent(z)));
                if (getColor(y) == RED) {
                    //(34, y);
                    setColor(parent(z), BLACK);
                    setColor(y, BLACK);
                    setColor(parent(parent(z)), RED);
                    z = parent(parent(z));
                } else {
                    //(35, y);
                    if (z == right(parent(z))) {
                        //(36, y);
                        z = parent(z);
                        leftRotate(z);
                    } else {
                        //(37, y);
                    }

                    setColor(parent(z), BLACK);
                    setColor(parent(parent(z)), RED);
                    if (parent(parent(z)) != null) {
                        //(38, y);
                        rightRotate(parent(parent(z)));
                    } else {
                        //(39, y);
                    }
                }
            } else {
                //(33, z);
                {
                    Node y = left(parent(parent(z)));
                    if (getColor(y) == RED) {
                        //(40, y);
                        {
                            setColor(parent(z), BLACK);
                            setColor(y, BLACK);
                            setColor(parent(parent(z)), RED);
                            z = parent(parent(z));
                        }
                    } else {
                        //(41, y);
                        {
                            if (z == left(parent(z))) {
                                //(42, y);
                                {
                                    z = parent(z);
                                    rightRotate(z);
                                }
                            } else {
                                //(43, y);
                            }

                            setColor(parent(z), BLACK);
                            setColor(parent(parent(z)), RED);
                            if (parent(parent(z)) != null) {
                                //(44, y);
                                leftRotate(parent(parent(z)));
                            } else {
                                //(45, y);
                            }

                        }
                    }

                }
            }
        }
        //(31, z);
        root.color = BLACK;
    }

    protected Node treeDelete(Node z) {
        if (z == null) {
            //(46, null);
            return null;
        } else {
            //(47, z);
        }

        Node x, y;
        if (z.left == null || z.right == null) {
            //(48, z);
            y = z;
        } else {
            //(49, z);
            y = getIOS(z);
        }

        if (y.left != null) {
            //(50, y);
            x = y.left;
        } else {
            //(51, y);
            x = y.right;
        }

        if (x != null) {
            //(52, x);
            x.parent = y.parent;
        } else {
            //(53, null);
        }

        if (y.parent == null) {
            //(54, y);
            root = x;
        } else {
            //(55, y);
            if (y == y.parent.left) {
                //(56, y);
                y.parent.left = x;
            } else {
                //(57, y);
                y.parent.right = x;
            }
        }

        if (y != z) {
            //(58, z);
            z.key = y.key;
            z.value = y.value;
        } else {
            //(59, z);
        }

        if (getColor(y) == BLACK) {
            //(60, y);
            if (x == null) {
                //(62, y);
                treeDeleteFix(y);
            } else {
                //(63, x);
                treeDeleteFix(x);
            }
        } else {
            //(61, x);
        }

        return y;
    }

    protected void treeDeleteFix(Node x) {
        while (x.parent != null && getColor(x) == BLACK) {
            //(64, x);
            if (x == x.parent.left || x.parent.left == null) {
                //(66, x);
                Node w = x.parent.right;
                if (w == null) {
                    //(68, null);
                    return;
                } else {
                    //(69, w);
                }

                if (getColor(w) == RED) {
                    //(70, w);
                    w.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                } else {
                    //(71, w);
                }

                if (getColor(w.left) == BLACK && getColor(w.right) == BLACK) {
                    //(72, w);
                    w.color = RED;
                    x = x.parent;
                } else {
                    //(73, w);
                    if (getColor(w.right) == BLACK) {
                        //(74, w);
                        w.left.color = BLACK;
                        rightRotate(w);
                        w = x.parent.right;
                    } else {
                        //(75, w);
                    }

                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    if (w.right != null) {
                        //(76, w);
                        w.right.color = BLACK;
                    } else {
                        //(77, w);
                    }

                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                //(67, x);
                Node w = x.parent.left;
                if (w == null) {
                    //(78, null);
                    return;
                } else {
                    //(79, w);
                }

                if (getColor(w) == RED) {
                    //(80, w);
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                } else {
                    //(81, w);
                }

                if (getColor(w.right) == BLACK && getColor(w.left) == BLACK) {
                    //(82, w);
                    w.color = RED;
                    x = x.parent;
                } else {
                    //(83, w);
                    if (getColor(w.left) == BLACK) {
                        //(84, w);
                        w.right.color = BLACK;
                        leftRotate(w);
                        w = x.parent.left;
                    } else {
                        //(85, w);
                    }

                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    if (w.left != null) {
                        //(86, w);
                        w.left.color = BLACK;
                    } else {
                        //(87, w);
                    }

                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        //(65, x);
        x.color = BLACK;
    }

    protected Node getIOS(Node z) {
        Node x, y = null;
        x = z.right;
        while (x != null) {
            //(88, x);
            y = x;
            x = x.left;
        }
        //(89, z);
        return y;
    }

    @Override
    public String toString() {
        return root == null ? "null" : root.toString();
    }

    private static int NUM_OF_PREDICATES = 6;

    public static int numOfPredicates() {
        return NUM_OF_PREDICATES;
    }

    

}
