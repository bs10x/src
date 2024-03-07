import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ArrayUtilTest {

  @Test
  public void testZeroInsert() {
    assertArrayEquals(new int[0], ArrayUtil.zeroInsert(new int[0]), "expected: empty array");

    assertArrayEquals(new int[]{1, 2, 3}, ArrayUtil.zeroInsert(new int[] {1, 2, 3}), "expected: no inserted 0");
    assertArrayEquals(new int[]{-1, -2, -3}, ArrayUtil.zeroInsert(new int[] {-1, -2, -3}), "expected: no inserted 0");

    assertArrayEquals(new int[]{-1, 0, 2, 3}, ArrayUtil.zeroInsert(new int[] {-1, 2, 3}), "expected: one inserted 0");
    assertArrayEquals(new int[]{-1, -2, 0, 3}, ArrayUtil.zeroInsert(new int[] {-1, -2, 3}), "expected: one inserted 0");
    assertArrayEquals(new int[]{1, 0, -2, -3}, ArrayUtil.zeroInsert(new int[] {1, -2, -3}), "expected: one inserted 0");
    assertArrayEquals(new int[]{1, 2, 0, -3}, ArrayUtil.zeroInsert(new int[] {1, 2, -3}), "expected: one inserted 0");
    assertArrayEquals(new int[]{-1, 0, 2, 3}, ArrayUtil.zeroInsert(new int[] {-1, 2, 3}), "expected: one inserted 0");
    assertArrayEquals(new int[]{1, 0, -2, 0, 3, 0, -4, 0, 5}, ArrayUtil.zeroInsert(new int[] {1, -2, 3, -4, 5}), "expected: four inserted 0s");
    assertArrayEquals(new int[]{-1, 0, 2, 0, -3, 0, 4, 0, -5}, ArrayUtil.zeroInsert(new int[] {-1, 2, -3, 4, -5}), "expected: four inserted 0s");
  }


  @Test
  public void testTenFollows() {
    assertFalse(ArrayUtil.tenFollows(new int[0], 0), "expected: false because start index out of bounds");
    assertFalse(ArrayUtil.tenFollows(new int[] {1, 2, 20}, 5), "expected: false because start index out of bounds");

    assertFalse(ArrayUtil.tenFollows(new int[] {1, 100, 2}, 0), "expected: false");
    assertFalse(ArrayUtil.tenFollows(new int[] {10, 100}, 1), "expected: false");
    assertFalse(ArrayUtil.tenFollows(new int[] {10, 1}, 0), "expected: false");
    assertFalse(ArrayUtil.tenFollows(new int[] {-10, 0}, 0), "expected: false");

    assertTrue(ArrayUtil.tenFollows(new int[] {1, 10, 2}, 0), "expected: true");
    assertTrue(ArrayUtil.tenFollows(new int[] {-1, -10, 2}, 0), "expected: true");
    assertTrue(ArrayUtil.tenFollows(new int[] {1, 2, 3, 4, 40, 5}, 2), "expected: true");
  }
}

