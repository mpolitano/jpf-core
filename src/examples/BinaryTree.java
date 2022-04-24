import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import gov.nasa.jpf.vm.StateComparator;

public class BinaryTree<E> {

  Object[] elements;
  int modCount;
  int size;

  public BinaryTree() {
    modCount = 0;
    size = 0;
    elements = new Object[16];
  }

  public void push(E e) {
    if (e == null) {
      throw new NullPointerException();
    }
    modCount++;
    ensureCapacity(size + 1);
    elements[size] = e;
    size++;
  }

  public void remove(E e) {
    if (e == null) {
      throw new NullPointerException();
    }
    modCount++;
    boolean found = false;
    for (int ii = 0; ii < size; ii++) {
      if (elements[ii].equals(e)) {
        elements[ii] = elements[size - 1];
        elements[size - 1] = null;
        size--;
        found = true;
      }
    }

    if (!found) {
      throw new NoSuchElementException();
    }
  }

  private static class Wrapper<E> {
    private E element;
    private boolean isSearched;
    private int index;

    public Wrapper(E element, int index) {
      this.element = element;
      this.isSearched = false;
      this.index = index;
    }
  }

  private class DepthFirstIterator<E> implements Iterator<E> {

    Wrapper<E>[] wrappedElements = new Wrapper[size + 1];
    int cursor = 0;
    int lastModCount = modCount;
    Wrapper<E> next = null;

    private static final int FIRST_INDEX = 1;

    @SuppressWarnings("unchecked")
    public DepthFirstIterator() {
      for (int ii = FIRST_INDEX; ii < size + 1; ii++) {
        wrappedElements[ii] = new Wrapper(elements[ii - 1], ii);
      }
      cursor = FIRST_INDEX;
    }

    @Override
    public boolean hasNext() {
      checkModification();
      if (next != null) {
        return true;
      }

      next = nextChild(cursor);
      if (next != null) {
        cursor = next.index;
      }
      return next != null;
    }

    @Override
    public E next() {
      checkModification();
      if (next != null) {
        E result = next.element;
        next = null;
        return result;
      }

      Wrapper<E> result = nextChild(cursor);
      if (result != null) {
        cursor = result.index;
        return result.element;
      }

      throw new NoSuchElementException();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    private void checkModification() {
      if (lastModCount != modCount) {
        throw new ConcurrentModificationException();
      }
    }

    private Wrapper<E> nextChild(int index) {
      if (wrappedElements[index] != null && !wrappedElements[index].isSearched) {
        // itself
        wrappedElements[index].isSearched = true;
        return wrappedElements[index];
      }

      int leftChildIndex = index * 2;
      if (size >= leftChildIndex && wrappedElements[leftChildIndex] != null
          && !wrappedElements[leftChildIndex].isSearched) {
        // left child exists
        wrappedElements[leftChildIndex].isSearched = true;
        return wrappedElements[leftChildIndex];
      }

      int rightChildIndex = index * 2 + 1;
      if (size >= rightChildIndex && wrappedElements[rightChildIndex] != null
          && !wrappedElements[rightChildIndex].isSearched) {
        // right child exists
        wrappedElements[rightChildIndex].isSearched = true;
        return wrappedElements[rightChildIndex];
      }

      int parentIndex = index / 2;
      if (parentIndex >= 1) {
        // if parent exists, check parent's child
        return nextChild(parentIndex);
      }

      return null;
    }
  }

  public Iterator<E> iterateDepthFirst() {
    return new DepthFirstIterator<E>();
  }

  public Iterator<E> iterateBreadthFirst() {

    return new Iterator<E>() {
      int cursor = 0;
      int lastModCount = modCount;

      @Override
      public boolean hasNext() {
        checkModification();
        return cursor != size;
      }

      @SuppressWarnings("unchecked")
      @Override
      public E next() {
        checkModification();
        Object result = elements[cursor];
        cursor++;
        return (E) result;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }

      private void checkModification() {
        if (lastModCount != modCount) {
          throw new ConcurrentModificationException();
        }
      }

    };
  }

  public void ensureCapacity(int paramInt) {
    int ii = this.elements.length;
    if (paramInt > ii) {
      int jj = ii * 3 / 2 + 1;
      if (jj < paramInt) {
        jj = paramInt;
      }
      this.elements = Arrays.copyOf(this.elements, jj);
    }
  }

  public static void main(String[] args) {
    BinaryTree<Integer> bt = new BinaryTree<Integer>();

  }
}
