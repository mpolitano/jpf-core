import gov.nasa.jpf.annotation.NumericBound;
import gov.nasa.jpf.vm.StateComparator;

public class SimpleExample {

  public static void main(String[] args) {
    @NumericBound(max = 1020)
    int iTest = 1000;

    while (true) {
      iTest++;
      System.out.println("iTest=" + iTest);
      // Verify.breakTransition("Test");
      StateComparator.markState("TAG1");

    }
  }
}
