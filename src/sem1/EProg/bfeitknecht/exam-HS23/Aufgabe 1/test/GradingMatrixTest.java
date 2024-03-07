import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GradingMatrixTest {

    @Test
    public void test1() {
        int[][] input = getMatrix1();
        int[][] result = Matrix.kReduce(input, 2);

        int[][] expected = {
                {3, 0, -4},
                {4, 8, -3},
                {5, 0, 4}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    public void test2() {
        int[][] input = getMatrix1();
        int[][] result = Matrix.kReduce(input, 6);

        int[][] expected = new int[][]{{8}};

        assertArrayEquals(expected, result);
    }

    @Test
    public void test3() {
        int[][] input = getMatrix1();
        int[][] result = Matrix.kReduce(input, 3);

        int[][] expected = new int[][]{
                {8, -4},
                {5, 4}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    public void test4() {
        int[][] input = getMatrix2();
        int[][] result = Matrix.kReduce(input, 2);

        int[][] expected = new int[][]{
                {0, 0},
                {-8, 17}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    public void test5() {
        assertThrows(IllegalArgumentException.class,
                () -> Matrix.kReduce(new int[5][5], 2)
        );
    }

    @Test
    public void test6() {
        assertThrows(IllegalArgumentException.class,
                () -> Matrix.kReduce(new int[6][4], 2)
        );
    }

    @Test
    public void test7() {
        assertThrows(IllegalArgumentException.class,
                () -> Matrix.kReduce(new int[4][6], 2)
        );
    }

    @Test
    public void test8() {
        int[][] input = getMatrix3();
        int[][] result = Matrix.kReduce(input, 2);

        int[][] expected = {
                {3, 0, -4},
                {4, 2, -3},
                {4, 0, -3}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    public void test9() {
        int[][] input = getMatrix3();
        int[][] result = Matrix.kReduce(input, 6);

        int[][] expected = new int[][]{{4}};

        assertArrayEquals(expected, result);
    }

    @Test
    public void test10() {
        int[][] input = getMatrix3();
        int[][] result = Matrix.kReduce(input, 3);

        int[][] expected = new int[][]{
                {4, -4},
                {4, -3}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    public void test11() {
        int[][] input = getMatrix3();
        int[][] result = Matrix.kReduce(input, 1);

        int[][] expected = new int[][]{
                {2, 3, -1, -1, 3, -4},
                {-1, 0, 2, 0, 1, -3},
                {3, 4, 2, 0, 1, -3},
                {3, 3, 2, 0, 1, -3},
                {3, 4, 0, 0, 1, -3},
                {3, 4, 0, 0, 1, -3}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    public void test12() {
        assertThrows(IllegalArgumentException.class,
                () -> Matrix.kReduce(new int[0][0], 2)
        );
    }

    @Test
    public void test13() {
        int[][] ab = new int[4][];
        int[] b = new int[4];
        ab[0] = b;
        ab[3] = b;
        b = new int[6];
        ab[1] = b;
        ab[2] = b;

        assertThrows(IllegalArgumentException.class,
                () -> Matrix.kReduce(ab, 2)
        );
    }

    @Test
    public void test14() {
        int[][] ab = new int[4][];
        int[] b = new int[4];
        ab[0] = b;
        ab[3] = b;
        b = new int[6];
        ab[1] = b;
        ab[2] = b;

        assertThrows(IllegalArgumentException.class, () -> Matrix.kReduce(ab, 4));
    }

    @Test
    public void test15() {
        int[][] ab = new int[4][];
        int[] b = new int[4];
        ab[0] = b;
        ab[3] = b;
        b = new int[6];
        ab[1] = b;
        ab[2] = b;

        assertThrows(IllegalArgumentException.class, () -> Matrix.kReduce(ab, 6));
    }

    @Test
    public void test16() {
        int[][] input = getMatrix4();
        int[][] result = Matrix.kReduce(input, 10);

        int[][] expected = {
                {-39, 40, 40},
                {-40, 40, 40},
                {-40, 40, -40}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    public void test17() {
        int[][] input = getMatrix4();
        int[][] result = Matrix.kReduce(input, 5);

        int[][] expected = {
                {-39, -37, 40, 40, 40, -38},
                {40, 0, -34, 40, 40, -40},
                {38, -38, 40, -40, 40, 40},
                {-40, -38, 40, 40, -39, -39},
                {-40, -34, 40, 38, -38, 40},
                {38, -40, 40, 40, 40, -40}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    public void test18() {
        int[][] input = getMatrix4();
        int[][] result = Matrix.kReduce(input, 2);

        int[][] expected = {
                {15, 35, -34, 40, -37, 37, -35, -40, -38, 39, -27, 30, 34, -36, 25},
                {-25, -37, 40, -33, 38, 40, 39, 28, 40, 33, 40, -34, 32, -38, 40},
                {-35, -38, 40, -38, 40, -15, 29, 22, 33, 37, 40, -29, 39, 40, -36},
                {-29, -33, 11, 24, 37, 12, -26, 40, 38, -40, -24, 33, -40, 40, -27},
                {29, 20, 40, -35, 35, 24, 30, -39, -21, 22, 22, 40, -40, 40, 32},
                {25, 38, -29, 40, 0, -39, 37, -37, -33, -30, 36, -34, 40, 36, 31},
                {-33, 25, 36, -32, 40, 32, 40, -15, 22, 19, 25, 38, 24, 37, 40},
                {-37, -36, 31, -29, 38, 40, -30, -37, -40, 19, -33, 33, -39, 21, 39},
                {-40, -29, -20, -23, -34, 35, 0, -37, 31, 40, -35, -29, 36, 18, -36},
                {40, 23, -38, 36, -26, 27, 38, 40, -25, 30, -23, 28, 21, -39, 40},
                {33, -40, -25, 40, -34, 20, -39, 38, 36, -36, 24, -30, 40, -36, 22},
                {35, -40, 23, 24, -33, 40, -37, -27, -9, 18, -34, -38, 37, 40, -36},
                {-25, 36, 28, -30, -20, 31, 35, 40, -17, -35, 29, 36, -40, 40, -39},
                {35, 24, 37, -10, -33, 29, 40, 39, -10, 17, -39, 36, -38, -30, -29},
                {-31, 38, -23, -40, -35, -36, -31, 22, -24, -35, -36, 40, 30, 40, 27}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    public void test19() {
        int[][] input = getMatrix4();

        assertThrows(IllegalArgumentException.class,
                () -> Matrix.kReduce(input, 12)
        );
    }

    @Test
    public void test20() {
        int[][] input = getMatrix4();

        assertThrows(IllegalArgumentException.class,
                () -> Matrix.kReduce(input, 31)
        );
    }

    @Test
    public void test21() {
        int[][] input = {{1}};
        int[][] result = Matrix.kReduce(input, 1);

        int[][] expected = {{1}};

        assertArrayEquals(expected, result);
    }

    @Test
    public void test22() {
        int[][] input = getMatrix2();
        int[][] result = Matrix.kReduce(input, 4);

        int[][] expected = new int[][]{{17}};

        assertArrayEquals(expected, result);
    }

    @Test
    public void test23() {
        int[][] input = getMatrix5();
        int[][] result = Matrix.kReduce(input, 5);

        int[][] expected = {{-244, -244}, {-256, -243}};

        assertArrayEquals(expected, result);
    }

    @Test
    public void test24() {
        int[][] input = getMatrix5();
        int[][] result = Matrix.kReduce(input, 2);

        int[][] expected = {
                {175, 245, -189, -183, -222},
                {-235, -244, -204, 82, 125},
                {-231, -183, -244, -181, -204},
                {188, -242, -105, -243, -217},
                {-256, -225, 139, -126, 121}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    public void test25() {
        int[][] input = getMatrix5();
        int[][] result = Matrix.kReduce(input, 10);

        int[][] expected = {{-256}};

        assertArrayEquals(expected, result);
    }

    @Test
    public void test26() {
        int[][] input = getMatrix6();
        int[][] result = Matrix.kReduce(input, 1);

        int[][] expected = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    public void test27() {
        int[][] input = getMatrix6();
        int[][] result = Matrix.kReduce(input, 3);

        int[][] expected = {{0}};

        assertArrayEquals(expected, result);
    }

    @Test
    public void test28() {
        int[][] input = getMatrix7();
        int[][] result = Matrix.kReduce(input, 2);

        int[][] expected = {
                {0, 0},
                {0, 0}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    public void test29() {
        int[][] ab = new int[4][];
        int[] b = new int[4];
        ab[0] = b;
        ab[1] = b;
        ab[2] = b;
        b = new int[3];
        ab[3] = b;

        assertThrows(IllegalArgumentException.class, () -> Matrix.kReduce(ab, 2));
    }

    @Test
    public void test30() {
        int[][] ab = new int[4][];
        int[] b = new int[4];
        ab[0] = b;
        ab[1] = b;
        ab[2] = b;
        b = new int[0];
        ab[3] = b;

        assertThrows(IllegalArgumentException.class, () -> Matrix.kReduce(ab, 2));
    }

    public static int[][] getMatrix1() {
        int[][] a =
                {{2, 3, -1, -1, 3, -4},
                        {-1, 0, 2, 0, 1, -3},
                        {3, 3, 8, 4, 1, -3},
                        {3, 4, 2, 0, 2, -2},
                        {5, 2, 0, 0, 3, -1},
                        {2, 5, 0, 0, 4, 0}};

        return a;
    }

    public static int[][] getMatrix2() {
        int[][] a =
                {{2, 4, 8, 10},
                        {-2, -4, -18, 0},
                        {1, 5, 12, -8},
                        {-4, -8, 17, 2},
                };

        return a;
    }

    public static int[][] getMatrix3() {
        int[][] a =
                {{2, 3, -1, -1, 3, -4},
                        {-1, 0, 2, 0, 1, -3},
                        {3, 4, 2, 0, 1, -3},
                        {3, 3, 2, 0, 1, -3},
                        {3, 4, 0, 0, 1, -3},
                        {3, 4, 0, 0, 1, -3}};

        return a;
    }

    public static int[][] getMatrix4() {
        int[][] a = {
                {2, -7, 35, 14, 31, -16, 29, -23, -20, 33, -28, 24, 19, -13, -11, -17, 21, -32, -5, 38, -15, -27, -30, 30, 34, -19, 22, -36, -1, 25},
                {-8, 15, -3, -39, -4, -34, 40, -25, -37, 11, 18, 37, -26, -35, -40, -12, -29, -38, -31, 39, -2, -6, 28, 16, 12, -9, 4, 7, 23, 20},
                {-4, -25, 19, 36, 13, 40, -7, -33, -32, -14, -16, -1, 7, -2, 28, 5, 0, 40, -22, -3, -28, -10, -34, 20, 25, -26, -38, 14, 40, 15},
                {30, -9, -27, -37, -3, -13, -17, 11, 38, 29, -12, 40, 39, -16, 20, 18, 31, -40, 33, 10, 40, 21, -14, -24, 32, -5, -28, 17, -15, -10},
                {20, -31, -22, -38, 18, -20, 26, -4, -2, 36, -8, -5, -6, -9, 12, -1, 14, -7, 32, -34, 40, 13, -3, -6, 21, 17, -14, -21, 40, -36},
                {-35, -23, -32, 38, 40, -27, -1, -38, -9, 40, -15, 25, -8, 29, 16, 22, 33, -6, 14, 37, -7, -2, -29, 19, 39, -40, 36, 40, -5, -13},
                {-17, -29, -1, 14, 11, -10, -5, 24, -11, -3, -8, -9, -25, 20, -34, 19, 13, -39, -19, 15, -16, -7, -40, 26, -4, -6, -8, 16, -12, -2},
                {-19, 16, -7, -33, -8, 10, -23, 20, 37, -5, 11, 12, -26, -4, -18, 40, 38, -6, -40, -2, 13, -24, 27, 33, 17, -40, 40, -10, -3, -27},
                {-9, -10, -3, 12, 30, -14, -15, -35, 26, 35, 24, -1, -21, -26, -28, -39, -12, -19, -6, 22, -4, 14, -7, -9, -11, -16, -5, -18, -37, -22},
                {29, 24, -28, 20, 40, -16, 14, -3, -8, 17, -31, 18, 30, 25, -9, -38, -21, 34, -2, -1, 22, -7, 40, -6, 19, -40, 40, 16, 31, 32},
                {-40, 25, 38, 13, -11, -20, 40, -9, -3, -35, -39, -6, -17, 37, 28, -26, -19, 32, -30, -1, 11, 36, -21, -15, -7, -2, -5, 21, 22, 31},
                {17, 18, 14, 24, 25, -29, -5, -3, -2, 40, -7, -9, 20, 30, -8, -37, -12, -33, -4, -1, 27, 32, -34, -6, 40, -23, 36, -9, -6, 19},
                {-2, -25, 14, -27, 36, -38, -3, -20, -5, 29, 32, 11, 12, 40, -10, -15, -9, 22, 16, 19, 25, -4, 28, -6, 24, -35, 20, -8, -1, -13},
                {-33, -11, 25, -2, -1, 20, -32, -8, 40, -24, 11, -13, 30, 27, -15, -5, 14, 16, -7, -3, -6, -9, -31, 38, 21, -4, -10, 37, 40, -7},
                {-37, 11, -14, -26, -12, 31, 27, -29, 17, -4, -2, 22, -30, -5, 13, -8, -6, -40, 18, 19, 26, -33, -21, 15, 32, 12, 20, -9, -1, -7},
                {34, -24, -32, -36, 23, 11, -8, -3, 38, 14, 40, -9, -14, -5, -13, -37, -6, 31, -26, -2, -28, -10, 33, 22, -39, -35, 21, -4, 39, -15},
                {-2, 15, -9, -17, -6, -4, -14, -23, 11, -28, 35, -12, 27, -16, 20, 14, 31, -10, 39, 40, -20, -5, -29, -8, -15, 36, 18, 13, 12, -1},
                {-40, 16, -26, -29, -15, -20, -7, 40, 13, -34, 24, 17, -9, -2, -37, -5, 14, 11, 30, -1, -35, -8, -28, 37, 19, -4, -3, -12, -6, -36},
                {38, 40, 23, -37, -1, -38, -2, -8, -26, 25, 27, -7, -5, -3, -34, 17, 16, -9, -6, 22, -20, -4, 28, -12, 15, -7, -2, -10, 36, -31},
                {-14, -3, 11, 17, 19, -27, 35, 36, -24, -7, -1, -10, 13, 38, -5, 40, -6, -25, -8, 30, 28, -23, -9, 20, 21, -2, -39, -37, 40, -4},
                {-32, -8, -24, 37, -4, 16, 40, -3, -1, 30, -7, 17, 20, -33, -18, -2, -6, -5, -35, 12, 14, 11, 39, -9, 23, 40, -36, 35, 22, -14},
                {27, 33, -40, -38, -25, -10, -7, -12, -8, -34, 20, -11, -39, -4, 18, 38, 36, 19, -36, -1, 24, -37, -6, -30, -14, 14, -3, -5, -20, 13},
                {35, -39, 19, -40, 23, -1, -5, 24, -7, -33, 38, -15, 26, -29, 36, -11, -9, -6, -8, 17, 11, -13, -2, 15, 31, 18, 40, -26, -36, -21},
                {-14, 25, 14, -22, 21, 12, -16, 19, 28, -3, 29, 40, -37, 16, -27, -1, 15, -7, 18, -24, -34, -4, -30, -38, -12, 37, 32, -9, -35, -5},
                {-10, -25, 26, -5, 14, -3, 18, -30, -20, -16, 15, 31, 35, 16, 12, 21, -8, 22, -27, -4, -14, 25, 11, 36, -2, 20, 40, -38, -29, 38},
                {31, -16, 36, -4, 28, -14, -12, -7, -2, 33, -5, 12, -21, 15, 18, 40, -17, -1, -35, 17, -8, 29, -30, -9, -19, -40, -24, 30, -6, -39},
                {-35, 24, -2, 16, 37, -6, -9, -10, -26, 40, 29, -11, 21, 19, 26, 31, -7, 18, -5, 14, -39, -20, 36, -3, 25, 13, 38, -30, -8, -18},
                {-12, 35, -22, 24, 11, -17, 18, -5, -14, -33, -20, 21, 40, 30, -31, 39, -10, -2, -19, 17, 32, -15, 22, 26, -9, -38, -28, -25, -29, 38},
                {-17, 33, 20, -4, -7, -23, -26, 24, -35, -13, -36, 38, -9, -31, -6, -8, 12, 15, -18, -3, -5, -28, 14, -1, 30, -2, -4, 40, -5, 26},
                {-31, -18, 38, 28, 25, -8, -40, -29, -20, -3, -9, -6, 18, 12, -5, 22, -7, -24, 20, -35, -19, -36, 40, -2, -4, -1, 15, -39, 27, 11}
        };

        return a;
    }

    public static int[][] getMatrix5() {
        int[][] a = {
                {175, -112, 245, -53, -103, -189, -183, 95, -65, -222},
                {-229, 167, -121, 87, 153, 92, 99, -150, -86, 71},
                {-235, -167, -244, 80, -204, -94, -35, 82, 64, 15},
                {-15, 222, 26, 13, -3, -154, -9, -6, 125, -42},
                {-231, -2, 234, -141, -139, -244, -16, -181, -71, -204},
                {-75, -16, -183, -124, -61, 42, -152, -31, 129, -164},
                {188, 90, -242, 63, -34, 49, 128, -208, 19, -217},
                {-237, -36, -119, 206, -105, 5, -71, -243, 33, -6},
                {-256, 171, 92, 84, -104, 18, -72, -126, -29, 26},
                {151, -71, -212, -225, 72, 139, 23, 162, 121, -77}
        };

        return a;
    }

    public static int[][] getMatrix6() {
        int[][] a = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        return a;
    }

    public static int[][] getMatrix7() {
        int[][] a = {
                {-1, 1, -1, 1},
                {1, -1, 1, -1},
                {-1, 1, -1, 1},
                {1, -1, 1, -1}
        };

        return a;
    }

}
