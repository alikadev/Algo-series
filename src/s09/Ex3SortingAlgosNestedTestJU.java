package s09;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static s09.Ex2SortResultChecker.isSortingResultCorrect;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Ex3SortingAlgosNestedTestJU {
  @FunctionalInterface
  interface ISorting {
    void sort(int[] t);
  }

  //=========================================
  public record SortingAlgo(ISorting sorter, String algoName) {
  }

  //=========================================
  static abstract class CommonNestedClass {
    abstract SortingAlgo myAlgo();

    @TestFactory
    Stream<DynamicTest> commonSuite() {
      return testsForAllDataInputs(myAlgo(), varietyOfArrays());
    }
  }

  //=========================================
  @Nested
  @Order(0)
  class Sort00 extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[0];
    }
  }

  @Nested
  @Order(1)
  class Sort01 extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[1];
    }
  }

  @Nested
  @Order(2)
  class Sort02 extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[2];
    }
  }

  @Nested
  @Order(3)
  class Sort03 extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[3];
    }
  }

  @Nested
  @Order(4)
  class Sort04 extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[4];
    }
  }

  @Nested
  @Order(5)
  class Sort05 extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[5];
    }
  }

  @Nested
  @Order(6)
  class Sort06 extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[6];
    }
  }

  @Nested
  @Order(7)
  class Sort07 extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[7];
    }
  }

  @Nested
  @Order(8)
  class Sort08 extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[8];
    }
  }

  @Nested
  @Order(9)
  class Sort09 extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[9];
    }
  }

  @Nested
  @Order(10)
  class Sort10 extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[10];
    }
  }

  @Nested
  @Order(11)
  class Sort11 extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[111];
    }
  }

  @Nested
  @Order(12)
  class ArraysSort extends CommonNestedClass {
    SortingAlgo myAlgo() {
      return algos()[12];
    }
  }

  static SortingAlgo[] algos() {
    return new SortingAlgo[]{
            new SortingAlgo(BuggySorting::sort00, "sort00"),
            new SortingAlgo(BuggySorting::sort01, "sort01"),
            new SortingAlgo(BuggySorting::sort02, "sort02"),
            new SortingAlgo(BuggySorting::sort03, "sort03"),
            new SortingAlgo(BuggySorting::sort04, "sort04"),
            new SortingAlgo(BuggySorting::sort05, "sort05"),
            new SortingAlgo(BuggySorting::sort06, "sort06"),
            new SortingAlgo(BuggySorting::sort07, "sort07"),
            new SortingAlgo(BuggySorting::sort08, "sort08"),
            new SortingAlgo(BuggySorting::sort09, "sort09"),
            new SortingAlgo(BuggySorting::sort10, "sort10"),
            new SortingAlgo(BuggySorting::sort11, "sort11"),
            new SortingAlgo(Arrays::sort, "Arrays.sort")
    };
  }

  static Stream<DynamicTest> testsForAllDataInputs(SortingAlgo sortingAlgo, int[][] arrays) {
    Stream<DynamicTest> s2 = Stream.of(dynamicTest(sortingAlgo.algoName() + " specialUsage1",
            () -> doSpecialTest1(sortingAlgo.sorter)));
    Stream<DynamicTest> s3 = Stream.of(dynamicTest(sortingAlgo.algoName() + " specialUsage2",
            () -> doSpecialTest2(sortingAlgo.sorter)));
    Stream<DynamicTest> s1 = Arrays.stream(arrays)
            .map(t -> dynamicTest(displayName(sortingAlgo, t),
                    () -> doTest(sortingAlgo.sorter, t)));
    return Stream.concat(Stream.concat(s1, s2), s3);
  }

  static String displayName(SortingAlgo algo, int[] t) {
    return algo.algoName() + " on " + Arrays.toString(t);
  }

  static int[][] varietyOfArrays() {
    return new int[][]{
            // TODO: invent relevant inputs as test cases
            {0, 0},
            {1, 1},
    };
  }

  private static void doTest(ISorting sorter, int[] t) {
    int[] b = Arrays.copyOf(t, t.length);
    sorter.sort(b);
    assertTrue(isSortingResultCorrect(t, b));
  }

  private static void doSpecialTest1(ISorting sorter) {
    // TODO: with a more elaborate usage, try to detect the subtler bug in sort08...
  }

  private static void doSpecialTest2(ISorting sorter) {
    // TODO: with a more elaborate usage, try to detect the subtler bug in sort10...
  }
}
