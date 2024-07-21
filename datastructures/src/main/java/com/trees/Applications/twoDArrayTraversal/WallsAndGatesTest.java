package main.java.com.trees.Applications.twoDArrayTraversal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WallsAndGatesTest {

    @Test
    void testWallsAndGates() {
        WallsAndGates wg = new WallsAndGates();

        int INF = 2147483647;
        int[][] testCase1 = {
                {INF, -1, 0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {0, -1, INF, INF}
        };
        int[][] expected1 = {
                {3, -1, 0, 1},
                {2, 2, 1, -1},
                {1, -1, 2, -1},
                {0, -1, 3, 4}
        };
        wg.wallsAndGates(testCase1);
        assertArrayEquals(expected1, testCase1);

        int[][] testCase2 = {
                {0, INF, INF, INF},
                {INF, INF, INF, INF},
                {INF, INF, INF, INF},
                {INF, INF, INF, INF}
        };
        int[][] expected2 = {
                {0, 1, 2, 3},
                {1, 2, 3, 4},
                {2, 3, 4, 5},
                {3, 4, 5, 6}
        };
        wg.wallsAndGates(testCase2);
        assertArrayEquals(expected2, testCase2);

        int[][] testCase3 = {
                {-1, 0, INF, -1},
                {INF, -1, INF, -1},
                {INF, -1, 0, -1},
                {-1, INF, INF, -1}
        };
        int[][] expected3 = {
                {-1, 0, 1, -1},
                {INF, -1, 1, -1},
                {INF, -1, 0, -1},
                {-1, 2, 1, -1}
        };
        wg.wallsAndGates(testCase3);
        assertArrayEquals(expected3, testCase3);

        int[][] testCase4 = {
                {INF, INF, INF, INF},
                {INF, -1, INF, INF},
                {INF, -1, 0, INF},
                {INF, INF, INF, INF}
        };
        int[][] expected4 = {
                {4, 3, 2, 3},
                {5, -1, 1, 2},
                {4, -1, 0, 1},
                {3, 2, 1, 2}
        };
        wg.wallsAndGates(testCase4);
        assertArrayEquals(expected4, testCase4);

        int[][] testCase5 = {
                {0, INF},
                {INF, INF},
                {INF, INF},
                {INF, INF},
                {INF, INF},
                {INF, INF},
                {INF, INF}
        };
        int[][] expected5 = {
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 5},
                {5, 6},
                {6, 7}
        };
        wg.wallsAndGates(testCase5);
        assertArrayEquals(expected5, testCase5);

        int[][] testCase6 = {
                {INF, 0},
                {INF, INF},
                {INF, INF},
                {INF, INF},
                {INF, INF},
                {INF, INF},
                {INF, INF}
        };
        int[][] expected6 = {
                {1, 0},
                {2, 1},
                {3, 2},
                {4, 3},
                {5, 4},
                {6, 5},
                {7, 6}
        };
        wg.wallsAndGates(testCase6);
        assertArrayEquals(expected6, testCase6);

        int[][] testCase7 = {
                {0, -1, INF, INF},
                {INF, INF, -1, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, INF},
                {INF, INF, INF, 0}
        };
        int[][] expected7 = {
                {0, -1, 5, 4},
                {1, 2, -1, 3},
                {2, 1, 2, -1},
                {3, -1, 1, 2},
                {4, 3, 2, 0}
        };
        wg.wallsAndGates(testCase7);
        assertArrayEquals(expected7, testCase7);

        int[][] testCase8 = {
                {INF, INF, INF, 0},
                {INF, -1, INF, INF},
                {INF, INF, -1, INF},
                {0, -1, INF, INF},
                {INF, INF, INF, INF}
        };
        int[][] expected8 = {
                {3, 2, 1, 0},
                {2, -1, 2, 1},
                {1, 2, -1, 2},
                {0, -1, 2, 3},
                {1, 2, 3, 4}
        };
        wg.wallsAndGates(testCase8);
        assertArrayEquals(expected8, testCase8);

        int[][] testCase9 = {
                {0, INF, INF, 0},
                {INF, -1, INF, INF},
                {INF, INF, -1, INF},
                {0, -1, INF, INF},
                {INF, INF, INF, 0}
        };
        int[][] expected9 = {
                {0, 1, 1, 0},
                {1, -1, 2, 1},
                {2, 2, -1, 2},
                {0, -1, 2, 3},
                {1, 2, 3, 0}
        };
        wg.wallsAndGates(testCase9);
        assertArrayEquals(expected9, testCase9);

        int[][] testCase10 = {
                {INF, INF, INF},
                {INF, 0, INF},
                {INF, INF, INF}
        };
        int[][] expected10 = {
                {2, 1, 2},
                {1, 0, 1},
                {2, 1, 2}
        };
        wg.wallsAndGates(testCase10);
        assertArrayEquals(expected10, testCase10);
        int[][] testCase11 = {
                {INF, INF, INF, INF},
                {INF, INF, INF, INF},
                {INF, INF, INF, INF},
                {INF, INF, INF, INF}
        };
        int[][] expected11 = {
                {INF, INF, INF, INF},
                {INF, INF, INF, INF},
                {INF, INF, INF, INF},
                {INF, INF, INF, INF}
        };
        wg.wallsAndGates(testCase11);
        assertArrayEquals(expected11, testCase11);

        int[][] testCase12 = {
                {0}
        };
        int[][] expected12 = {
                {0}
        };
        wg.wallsAndGates(testCase12);
        assertArrayEquals(expected12, testCase12);

        int[][] testCase13 = {
                {-1}
        };
        int[][] expected13 = {
                {-1}
        };
        wg.wallsAndGates(testCase13);
        assertArrayEquals(expected13, testCase13);

        int[][] testCase14 = {
                {INF}
        };
        int[][] expected14 = {
                {INF}
        };
        wg.wallsAndGates(testCase14);
        assertArrayEquals(expected14, testCase14);

        int[][] testCase15 = {
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}
        };
        int[][] expected15 = {
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}
        };
        wg.wallsAndGates(testCase15);
        assertArrayEquals(expected15, testCase15);

        // Additional test cases to cover all scenarios

        int[][] testCase16 = {
                {INF, INF, INF},
                {INF, 0, INF},
                {0, INF, INF}
        };
        int[][] expected16 = {
                {2, 1, 2},
                {1, 0, 1},
                {0, 1, 2}
        };
        wg.wallsAndGates(testCase16);
        assertArrayEquals(expected16, testCase16);

        int[][] testCase17 = {
                {INF, -1, 0},
                {INF, -1, INF},
                {INF, INF, INF}
        };
        int[][] expected17 = {
                {3, -1, 0},
                {2, -1, 1},
                {3, 2, 2}
        };
        wg.wallsAndGates(testCase17);
        assertArrayEquals(expected17, testCase17);

        int[][] testCase18 = {
                {INF, INF, INF, 0},
                {INF, -1, INF, INF},
                {INF, INF, INF, INF},
                {INF, INF, INF, INF}
        };
        int[][] expected18 = {
                {3, 2, 1, 0},
                {2, -1, 2, 1},
                {3, 2, 3, 2},
                {4, 3, 4, 3}
        };
        wg.wallsAndGates(testCase18);
        assertArrayEquals(expected18, testCase18);

        int[][] testCase19 = {
                {0, -1, INF, INF},
                {INF, INF, -1, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, INF},
                {INF, INF, INF, 0}
        };
        int[][] expected19 = {
                {0, -1, 5, 4},
                {1, 2, -1, 3},
                {2, 3, 2, -1},
                {3, -1, 1, 2},
                {4, 3, 2, 0}
        };
        wg.wallsAndGates(testCase19);
        assertArrayEquals(expected19, testCase19);

        int[][] testCase20 = {
                {INF, 0, INF},
                {-1, INF, INF},
                {INF, INF, INF}
        };
        int[][] expected20 = {
                {1, 0, 1},
                {-1, 1, 2},
                {2, 2, 3}
        };
        wg.wallsAndGates(testCase20);
        assertArrayEquals(expected20, testCase20);
    }
}