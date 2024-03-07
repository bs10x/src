import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GradingPatternTest {

  @Test
  public void testMatch001A() {
    int[][] a = {
        { 1, 2, 3, 2, 1},
        { 2, 1, 3, 1, 3},
        { 1, 2, 3, 2, 1},
        { 3, 1, 3, 1, 3},
        { 1, 2, 1, 2, 1}
    };

    int[][] m1 = {
        { 1, 2},
        { 2, 1}
    };

    MatchRecord r;

    r = Pattern.match(a, m1);
    assertNotNull(r);
    assertEquals(0, r.x);
    assertEquals(0, r.y);
    assertEquals(0, r.count);
  }

  @Test
  public void testMatch002A() {
    int[][] a = {
        { 1, 2, 1, 3, 1},
        { 3, 1, 2, 1, 2},
        { 1, 3, 1, 3, 1},
        { 3, 1, 3, 1, 3},
        { 1, 3, 1, 3, 1}
    };

    int[][] m2 = {
        { 2, 1},
        { 1, 2}
    };

    MatchRecord r;

    r = Pattern.match(a, m2);
    assertNotNull(r);
    assertEquals(0, r.x);
    assertEquals(1, r.y);
    assertEquals(0, r.count);
  }

  @Test
  public void testMatch003A() {
    int[][] a = {
        { 1, 2, 1, 2, 1},
        { 2, 1, 2, 1, 2},
        { 1, 2, 1, 2, 1},
        { 2, 1, 2, 1, 2},
        { 1, 2, 1, 2, 1}
    };

    int[][] m3 = {
        {3,3},
        {3,3}
    };

    MatchRecord r;

    r = Pattern.match(a, m3);
    assertNotNull(r);
    boolean legalX = (r.x >= 0) && (r.x <= 3);
    boolean legalY = (r.y >= 0) && (r.y <= 3);
    assertTrue(legalX && legalY);
    assertEquals(4, r.count);
  }

  @Test
  public void testMatch004A() {
    int[][] b  = {
        { 1, 2, 1, 2, 1},
        { 2, 1, 2, 1, 2},
        { 1, 2, 3, 3, 1},
        { 2, 1, 3, 3, 3},
        { 1, 2, 3, 1, 3}
    };

    int[][] m3 = {
        {3,3},
        {3,3}
    };

    MatchRecord r;

    r = Pattern.match(b, m3);
    assertNotNull(r);
    assertEquals(2, r.x);
    assertEquals(2, r.y);
    assertEquals(0, r.count);
  }


  @Test
  public void testMatch005A() {
    int[][] c  = {
        { 1, 2, 1, 2, 1},
        { 2, 1, 2, 1, 2},
        { 1, 2, 1, 2, 1},
        { 2, 1, 2, 3, 3},
        { 1, 2, 1, 3, 3}
    };

    int[][] m3 = {
        {3,3},
        {3,3}
    };

    MatchRecord r;

    r = Pattern.match(c, m3);
    assertNotNull(r);
    assertEquals(3, r.x);
    assertEquals(3, r.y);
    assertEquals(0, r.count);
  }

  /** new tests */

  @Test
  public void testMatch01() {
    int[][] a = {
        { 2, 3, 4, 3, 2 },
        { 3, 2, 4, 2, 4 },
        { 2, 3, 4, 3, 2 },
        { 4, 2, 4, 2, 4 },
        { 2, 3, 2, 3, 2 }
    };

    int[][] m1 = {
        { 2, 3 },
        { 3, 2 }
    };

    MatchRecord r;

    r = Pattern.match(a, m1);
    assertNotNull(r);
    assertEquals(0, r.x);
    assertEquals(0, r.y);
    assertEquals(0, r.count);
  }

  @Test
  public void testMatch02() {
    int[][] a = {
        { 2, 3, 2, 4, 2 },
        { 4, 2, 3, 2, 3 },
        { 2, 4, 2, 4, 2 },
        { 4, 2, 4, 2, 4 },
        { 2, 4, 2, 4, 2 }
    };

    int[][] m2 = {
        { 3, 2 },
        { 2, 3 }
    };

    MatchRecord r;

    r = Pattern.match(a, m2);
    assertNotNull(r);
    assertEquals(0, r.x);
    assertEquals(1, r.y);
    assertEquals(0, r.count);
  }

  @Test
  public void testMatch03() {
    int[][] a = {
        { 2, 3, 2, 3, 2 },
        { 3, 2, 3, 2, 3 },
        { 2, 3, 2, 3, 2 },
        { 3, 2, 3, 2, 3 },
        { 2, 3, 2, 3, 2 }
    };

    int[][] m3 = {
        { 4, 4 },
        { 4, 4 }
    };

    MatchRecord r;

    r = Pattern.match(a, m3);
    assertNotNull(r);
    boolean legalX = (r.x >= 0) && (r.x <= 3);
    boolean legalY = (r.y >= 0) && (r.y <= 3);
    assertTrue(legalX && legalY);
    assertEquals(4, r.count);
  }

  @Test
  public void testMatch04() {
    int[][] b = {
        {9, 7, 3, 9, 8},
        {5, 3, 5, 6, 2},
        {5, 7, 1, 8, 1},
        {8, 7, 7, 5, 8},
        {9, 5, 3, 4, 0}
    };

    int[][] m3 = {
        { -1, -1 },
        { -1, -1 }
    };

    MatchRecord r;

    r = Pattern.match(b, m3);
    assertNotNull(r);
    boolean legalX = (r.x >= 0) && (r.x <= 3);
    boolean legalY = (r.y >= 0) && (r.y <= 3);
    assertTrue(legalX && legalY);
    assertEquals(4, r.count);
  }

  @Test
  public void testMatch05() {
    int[][] c = {
        {4, 2, 3, 7, 4, 7, 8},
        {4, 5, 5, 4, 7, 1, 8},
        {0, 6, 8, 4, 2, 0, 4},
        {7, 6, 7, 2, 0, 7, 5},
        {2, 6, 7, 9, 8, 8, 2},
        {7, 8, 9, 5, 4, 5, 9},
        {6, 7, 6, 7, 3, 7, 0}
    };

    int[][] m3 = {
        {114, 100, 100},
        {100, 100, 405},
        {100, 120, 100}
    };

    MatchRecord r;

    r = Pattern.match(c, m3);
    assertNotNull(r);
    boolean legalX = (r.x >= 0) && (r.x <= 4);
    boolean legalY = (r.y >= 0) && (r.y <= 4);
    assertTrue(legalX && legalY);
    assertEquals(9, r.count);
  }

  @Test
  public void testMatch06() {
    int[][] c = {
        {4, 2, 2, 5, 8, 9, 2, 9, 3, 5},
        {9, 1, 2, 7, 1, 5, 6, 6, 3, 6},
        {9, 5, 4, 5, 2, 7, 3, 9, 4, 4},
        {1, 3, 3, 4, 9, 0, 5, 3, 4, 8},
        {1, 9, 1, 1, 0, 6, 6, 7, 5, 8},
        {4, 7, 9, 2, 5, 3, 4, 3, 7, 1},
        {6, 2, 2, 6, 9, 8, 7, 5, 9, 0},
        {4, 7, 8, 6, 4, 6, 3, 8, 9, 2},
        {8, 4, 2, 8, 9, 9, 9, 2, 1, 5},
        {7, 1, 2, 4, 8, 6, 2, 6, 8, 5}
    };

    int[][] m3 = {
        {-1, -1, -1, -1 , -1},
        {-1, -1, -1, -1 , -1},
        {-1, -1, -1, -1 , -1},
        {-1, -1, -1, -1 , -1},
        {-1, -1, -1, -1 , -1}
    };

    MatchRecord r;

    r = Pattern.match(c, m3);
    assertNotNull(r);
    boolean legalX = (r.x >= 0) && (r.x <= 5);
    boolean legalY = (r.y >= 0) && (r.y <= 5);
    assertTrue(legalX && legalY);
    assertEquals(25, r.count);
  }

  @Test
  public void testMatch07() {
    int[][] b = {
        {6, 6, 6, 6, 7},
        {9, 2, 5, 2, 0},
        {7, 1, 9, 1, 4},
        {3, 0, 0, 8, 4},
        {6, 2, 5, 8, 2}
    };

    int[][] m3 = {
        { 1, 4 },
        { 8, 4 }
    };

    MatchRecord r;

    r = Pattern.match(b, m3);
    assertNotNull(r);
    assertEquals(2, r.x);
    assertEquals(3, r.y);
    assertEquals(0, r.count);
  }

  @Test
  public void testMatch08() {
    int[][] c = {
        {6, 3, 7, 1, 1, 9, 5},
        {3, 9, 4, 3, 3, 6, 0},
        {1, 9, 3, 4, 1, 5, 3},
        {9, 5, 1, 8, 1, 4, 7},
        {2, 8, 4, 4, 4, 4, 1},
        {3, 0, 7, 8, 4, 8, 9},
        {0, 5, 9, 0, 0, 9, 6}
    };

    int[][] m3 = {
        {4, 3, 3},
        {3, 4, 1},
        {1, 8, 1}
    };

    MatchRecord r;

    r = Pattern.match(c, m3);
    assertNotNull(r);
    assertEquals(1, r.x);
    assertEquals(2, r.y);
    assertEquals(0, r.count);
  }

  @Test
  public void testMatch09() {
    int[][] c = {
        {4, 9, 7, 3, 1, 2, 2, 2, 2, 8},
        {0, 9, 7, 8, 3, 6, 2, 6, 8, 4},
        {5, 8, 5, 5, 8, 8, 1, 9, 4, 4},
        {3, 8, 0, 5, 3, 2, 0, 5, 9, 7},
        {7, 2, 2, 4, 0, 4, 8, 3, 2, 7},
        {2, 0, 1, 1, 2, 8, 7, 3, 7, 1},
        {1, 6, 4, 9, 1, 8, 9, 4, 2, 6},
        {6, 8, 1, 2, 9, 7, 3, 8, 5, 9},
        {9, 8, 2, 1, 5, 2, 9, 7, 8, 7},
        {2, 1, 7, 0, 8, 9, 9, 3, 1, 7}
    };

    int[][] m3 = {
        {1, 2, 8, 7, 3},
        {9, 1, 8, 9, 4},
        {2, 9, 7, 3, 8},
        {1, 5, 2, 9, 7},
        {0, 8, 9, 9, 3},

    };

    MatchRecord r;

    r = Pattern.match(c, m3);
    assertNotNull(r);
    assertEquals(5, r.x);
    assertEquals(3, r.y);
    assertEquals(0, r.count);
  }

  @Test
  public void testMatch10() {

    int[][] b = {
        {3, 0, 0, 6, 4},
        {3, 0, 0, 0, 0},
        {2, 3, 0, 0, 0},
        {5, 0, 0, 0, 7},
        {8, 0, 0, 0, 7}
    };

    int[][] m3 = {
        { 0, 0 },
        { 0, 0 }
    };

    MatchRecord r;

    r = Pattern.match(b, m3);
    assertNotNull(r);

    boolean correct =
        ((r.x == 0) && (r.y == 1)) ||
            ((r.x == 1) && (r.y == 2)) ||
            ((r.x == 1) && (r.y == 3)) ||
            ((r.x == 2) && (r.y == 2)) ||
            ((r.x == 3) && (r.y == 1)) ||
            ((r.x == 3) && (r.y == 2));

    assertTrue(correct);
    assertEquals(0, r.count);
  }

  @Test
  public void testMatch11() {

    int[][] c = {
        {7, 2, 4, 8, 0, 0, 0},
        {4, 9, 0, 0, 0, 0, 0},
        {8, 1, 0, 0, 0, 0, 0},
        {1, 0, 0, 0, 0, 6, 3},
        {2, 0, 0, 0, 0, 9, 4},
        {1, 0, 0, 0, 0, 1, 8},
        {4, 1, 1, 3, 2, 5, 1}
    };

    int[][] m3 = {
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0}
    };

    MatchRecord r;

    r = Pattern.match(c, m3);
    assertNotNull(r);

    boolean correct =
        ((r.x == 0) && (r.y == 4)) ||
            ((r.x == 1) && (r.y == 2)) ||
            ((r.x == 2) && (r.y == 2)) ||
            ((r.x == 3) && (r.y == 1)) ||
            ((r.x == 3) && (r.y == 2));

    assertTrue(correct);
    assertEquals(0, r.count);
  }

  @Test
  public void testMatch12() {

    int[][] c = {
        {3, 4, 7, 0, 0, 0, 0, 0, 5, 5},
        {2, 3, 0, 0, 0, 0, 0, 0, 1, 4},
        {2, 1, 0, 0, 0, 0, 0, 0, 0, 5},
        {7, 8, 0, 0, 0, 0, 0, 0, 5, 9},
        {2, 5, 0, 0, 0, 0, 0, 0, 6, 7},
        {4, 3, 0, 0, 0, 0, 0, 0, 9, 4},
        {4, 1, 0, 0, 0, 0, 0, 0, 8, 2},
        {4, 4, 0, 0, 0, 0, 0, 0, 9, 3},
        {8, 1, 7, 0, 0, 0, 0, 0, 9, 2},
        {2, 5, 8, 0, 0, 0, 0, 0, 9, 2}
    };

    int[][] m3 = {
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}

    };

    MatchRecord r;

    r = Pattern.match(c, m3);
    assertNotNull(r);

    boolean correct =
        ((r.x == 0) && (r.y == 3)) ||
            ((r.x == 1) && (r.y == 2)) ||
            ((r.x == 1) && (r.y == 3)) ||
            ((r.x == 2) && (r.y == 2)) ||
            ((r.x == 2) && (r.y == 3)) ||
            ((r.x == 3) && (r.y == 2)) ||
            ((r.x == 3) && (r.y == 3)) ||
            ((r.x == 4) && (r.y == 3)) ||
            ((r.x == 5) && (r.y == 3));

    assertTrue(correct);
    assertEquals(0, r.count);
  }

  @Test
  public void testMatch13() {
    int[][] b = {
        {6, 2, 8, 9, 5},
        {9, 9, 6, 4, 7},
        {4, 9, 7, 3, 5},
        {2, 3, 1, 6, 7},
        {9, 9, 6, 5, 6}
    };

    int[][] m3 = {
        { 6, 4 },
        { 7, -1 }
    };

    MatchRecord r;

    r = Pattern.match(b, m3);
    assertNotNull(r);
    assertEquals(1, r.x);
    assertEquals(2, r.y);
    assertEquals(1, r.count);
  }

  @Test
  public void testMatch14() {
    int[][] c = {
        {6, 5, 9, 0, 1, 5, 0},
        {9, 8, 7, 1, 2, 9, 3},
        {8, 1, 5, 2, 3, 0, 9},
        {1, 8, 6, 7, 7, 1, 8},
        {8, 3, 4, 0, 0, 8, 8},
        {0, 1, 7, 9, 7, 4, 5},
        {5, 9, 6, 6, 7, 2, 6}

    };

    int[][] m3 = {
        {8, 6, 7},
        {15, 4, 0},
        {1, 7, 9}
    };

    MatchRecord r;

    r = Pattern.match(c, m3);
    assertNotNull(r);
    assertEquals(3, r.x);
    assertEquals(1, r.y);
    assertEquals(1, r.count);
  }

  @Test
  public void testMatch15() {
    int[][] c = {
        {6, 2, 7, 7, 3, 9, 7, 5, 4, 0},
        {4, 1, 6, 7, 5, 2, 4, 1, 0, 9},
        {2, 0, 6, 5, 1, 5, 6, 8, 6, 5},
        {4, 8, 8, 6, 2, 2, 6, 3, 8, 7},
        {0, 8, 7, 9, 3, 2, 5, 3, 7, 1},
        {3, 4, 0, 3, 6, 7, 6, 0, 1, 4},
        {8, 9, 6, 5, 2, 0, 4, 7, 3, 4},
        {1, 4, 7, 4, 5, 8, 4, 5, 8, 3},
        {0, 8, 5, 2, 6, 7, 3, 7, 4, 3},
        {3, 8, 7, 8, 0, 5, 9, 5, 3, 6}
    };

    int[][] m3 = {
        {1, 5, 6, 8, 6},
        {2, 2, 6, -1, 8},
        {3, 2, 5, 3, 7},
        {6, 7, 6, 0, 1},
        {2, 0, 4, 7, 3},

    };

    MatchRecord r;

    r = Pattern.match(c, m3);
    assertNotNull(r);
    assertEquals(2, r.x);
    assertEquals(4, r.y);
    assertEquals(1, r.count);
  }

  @Test
  public void testMatch16() {
    int[][] b = {
        {6, 2, 8, 9, 5},
        {9, 9, 6, 4, 7},
        {4, 9, 7, 3, 5},
        {2, 3, 1, 6, 7},
        {9, 9, 6, 5, 6}
    };

    int[][] m3 = {
        { 6, 30 },
        { 7, 31 }
    };

    MatchRecord r;

    r = Pattern.match(b, m3);
    assertNotNull(r);
    assertEquals(1, r.x);
    assertEquals(2, r.y);
    assertEquals(2, r.count);
  }

  @Test
  public void testMatch17() {
    int[][] c = {
        {6, 5, 9, 0, 1, 5, 0},
        {9, 8, 7, 1, 2, 9, 3},
        {8, 1, 5, 2, 3, 0, 9},
        {1, 8, 6, 7, 7, 1, 8},
        {8, 3, 4, 0, 0, 8, 8},
        {0, 1, 7, 9, 7, 4, 5},
        {5, 9, 6, 6, 7, 2, 6}

    };

    int[][] m3 = {
        {8, 6, 17},
        {17, 4, 17},
        {1, 7, 17}
    };

    MatchRecord r;

    r = Pattern.match(c, m3);
    assertNotNull(r);
    assertEquals(3, r.x);
    assertEquals(1, r.y);
    assertEquals(4, r.count);
  }

  @Test
  public void testMatch18() {
    int[][] c = {
        {6, 2, 7, 7, 3, 9, 7, 5, 4, 0},
        {4, 1, 6, 7, 5, 2, 4, 1, 0, 9},
        {2, 0, 6, 5, 1, 5, 6, 8, 6, 5},
        {4, 8, 8, 6, 2, 2, 6, 3, 8, 7},
        {0, 8, 7, 9, 3, 2, 5, 3, 7, 1},
        {3, 4, 0, 3, 6, 7, 6, 0, 1, 4},
        {8, 9, 6, 5, 2, 0, 4, 7, 3, 4},
        {1, 4, 7, 4, 5, 8, 4, 5, 8, 3},
        {0, 8, 5, 2, 6, 7, 3, 7, 4, 3},
        {3, 8, 7, 8, 0, 5, 9, 5, 3, 6}
    };

    int[][] m3 = {
        {1, 5, 6, 8, 6},
        {2, -1, 6, -1, 8},
        {3, -1, -1, -1, 7},
        {6, -1, 6, 0, 1},
        {2, 0, 4, 7, 3},

    };

    MatchRecord r;

    r = Pattern.match(c, m3);
    assertNotNull(r);
    assertEquals(2, r.x);
    assertEquals(4, r.y);
    assertEquals(6, r.count);
  }

  @Test
  public void testMatch19() {
    int [][] c = {
        {3, 9, 15},
        {14, 3, 23},
        {19, 12, 28}
    };

    int [][] m = {
        {1,  2},
        {19, 5}
    };

    MatchRecord r = Pattern.match(c,  m);
    assertNotNull(r);
    assertEquals(1, r.x);
    assertEquals(0, r.y);
    assertEquals(3, r.count);
  }

  @Test
  public void testMatch20() {
    int [][] c = {
        {1002, 1004, 8,  9000, 200},
        {1004, 80,   1,  3,    200},
        {4,    3 ,   5,  9,    200},
        {15,   19,   25, 800,  90},
        {1,    8,    13, 15,   2}
    };

    int [][] m = {
        {10,  1,   300},
        {200, 25,  800},
        {1000, 1000, 1000}
    };

    MatchRecord r = Pattern.match(c,  m);
    assertNotNull(r);
    assertEquals(2, r.x);
    assertEquals(1, r.y);
    assertEquals(7, r.count);
  }

  @Test
  public void testMatch21() {
    int [][] c = {
        {1, 2, 5},
        {4, 8, 6},
        {4, 3, 2}
    };

    int [][] m = {
        {5}
    };

    MatchRecord r = Pattern.match(c,  m);
    assertNotNull(r);
    assertEquals(0, r.x);
    assertEquals(2, r.y);
    assertEquals(0, r.count);
  }

  @Test
  public void testMatch22() {
    int[][] c = {
        {1, 1},
        {1, 1}
    };

    int[][] m3 = {
        {1, 1},
        {1, 1}
    };

    MatchRecord r;

    r = Pattern.match(c, m3);
    assertNotNull(r);
    assertEquals(0, r.x);
    assertEquals(0, r.y);
    assertEquals(0, r.count);
  }
}