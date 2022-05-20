package builders;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of <a href="http://en.wikipedia.org/wiki/Disjoint-set_data_structure">Union
 * Find</a> data structure. Union Find is a disjoint-set data structure. It supports two operations:
 * finding the set a specific element is in, and merging two sets. The implementation uses union by
 * rank and path compression to achieve an amortized cost of $O(\alpha(n))$ per operation where
 * $\alpha$ is the inverse Ackermann function. UnionFind uses the hashCode and equals method of the
 * elements it operates on.
 *
 * @author Tom Conerly
 */
public class UnionFind
{
    private final Map<Integer, Integer> parentMap;
    private final Map<Integer, Integer> rankMap;
    private int count; // number of components

    /**
     * Creates a UnionFind instance with all the elements in separate sets.
     * 
     * @param elements the initial elements to include (each element in a singleton set).
     */
    /*
    public UnionFind(int numElems)
    {
    	Set<Integer> elements = new LinkedHashSet<>();
    	for (int i = 0; i < numElems; i++) 
    		elements.add(i);
    	
        parentMap = new LinkedHashMap<>();
        rankMap = new HashMap<>();
        for (Integer element : elements) {
            parentMap.put(element, element);
            rankMap.put(element, 0);
        }
        count = elements.size();
    }
    */
    
    public UnionFind() {
    	parentMap = new LinkedHashMap<>();
        rankMap = new HashMap<>();
    }

    /**
     * Adds a new element to the data structure in its own set.
     *
     * @param element The element to add.
     */
    public void addElement(Integer element)
    {
        if (parentMap.keySet().contains(element))
            throw new IllegalArgumentException(
                "element is already contained in UnionFind: " + element);
        parentMap.put(element, element);
        rankMap.put(element, 0);
        count++;
    }

    /**
     * @return map from element to parent element
     */
    protected Map<Integer, Integer> getParentMap()
    {
        return parentMap;
    }

    /**
     * @return map from element to rank
     */
    protected Map<Integer, Integer> getRankMap()
    {
        return rankMap;
    }

    /**
     * Returns the representative element of the set that element is in.
     *
     * @param element The element to find.
     *
     * @return The element representing the set the element is in.
     */
    public Integer find(final Integer element)
    {
        if (!parentMap.keySet().contains(element)) {
            throw new IllegalArgumentException(
                "element is not contained in this UnionFind data structure: " + element);
        }

        Integer current = element;
        while (true) {
            Integer parent = parentMap.get(current);
            if (parent.equals(current)) {
                break;
            }
            current = parent;
        }
        final Integer root = current;

        current = element;
        while (!current.equals(root)) {
            Integer parent = parentMap.get(current);
            parentMap.put(current, root);
            current = parent;
        }

        return root;
    }

    /**
     * Merges the sets which contain element1 and element2. No guarantees are given as to which
     * element becomes the representative of the resulting (merged) set: this can be either
     * find(element1) or find(element2).
     *
     * @param element1 The first element to union.
     * @param element2 The second element to union.
     */
    public void union(Integer element1, Integer element2)
    {
        if (!parentMap.keySet().contains(element1) || !parentMap.keySet().contains(element2)) {
            throw new IllegalArgumentException("elements must be contained in given set");
        }

        Integer parent1 = find(element1);
        Integer parent2 = find(element2);

        // check if the elements are already in the same set
        if (parent1.equals(parent2)) {
            return;
        }

        int rank1 = rankMap.get(parent1);
        int rank2 = rankMap.get(parent2);
        if (rank1 > rank2) {
            parentMap.put(parent2, parent1);
        } else if (rank1 < rank2) {
            parentMap.put(parent1, parent2);
        } else {
            parentMap.put(parent2, parent1);
            rankMap.put(parent1, rank1 + 1);
        }
        count--;
    }

    /**
     * Tests whether two elements are contained in the same set.
     * 
     * @param element1 first element
     * @param element2 second element
     * @return true if element1 and element2 are contained in the same set, false otherwise.
     */
    public boolean inSameSet(Integer element1, Integer element2)
    {
        return find(element1).equals(find(element2));
    }

    /**
     * Returns the number of sets. Initially, all items are in their own set. The smallest number of
     * sets equals one.
     * 
     * @return the number of sets
     */
    public int numberOfSets()
    {
        assert count >= 1 && count <= parentMap.keySet().size();
        return count;
    }

    /**
     * Returns the total number of elements in this data structure.
     * 
     * @return the total number of elements in this data structure.
     */
    public int size()
    {
        return parentMap.size();
    }

    /**
     * Resets the UnionFind data structure: each element is placed in its own singleton set.
     */
    public void reset()
    {
        for (Integer element : parentMap.keySet()) {
            parentMap.put(element, element);
            rankMap.put(element, 0);
        }
        count = parentMap.size();
    }

    /**
     * Returns a string representation of this data structure. Each component is represented as
     * $\left{v_i:v_1,v_2,v_3,...v_n\right}$, where $v_i$ is the representative of the set.
     * 
     * @return string representation of this data structure
     */
    public String toString()
    {
        Map<Integer, Set<Integer>> setRep = new LinkedHashMap<>();
        for (Integer t : parentMap.keySet()) {
            Integer representative = find(t);
            if (!setRep.keySet().contains(representative))
                setRep.put(representative, new LinkedHashSet<Integer>());
            setRep.get(representative).add(t);
        }

        StringBuilder res = new StringBuilder(); 
        for (Integer t: setRep.keySet()) {
        	res.append(t)
        	.append(":")
        	.append(setRep.get(t).toString())
        	.append("\n");
        }

        return res.toString();
    }
    
    
    
}