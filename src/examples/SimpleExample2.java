import gov.nasa.jpf.annotation.NumericBound;
import gov.nasa.jpf.vm.StateComparator;

public class SimpleExample2 {
  @NumericBound(max = 1002)
  int iTest = 1000;
  int iTest2 = 1;

  public static void main(String[] args) {
    SimpleExample2 se = new SimpleExample2();

    while (true) {
      se.iTest++;
      se.iTest2 = (se.iTest2 + 1) % 3;
      StateComparator.markState("TAG1");
    }
  }

}
