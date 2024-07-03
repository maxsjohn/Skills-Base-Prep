package main.java.com.trees.Applications;

import java.util.LinkedList;
import java.util.Queue;

public class OrangeRotting {

    public int orangesRotting(int[][] grid) {
        int errorCount = -1;
        int minutes = 0;
        int freshOranges = 0;

        Queue<int[]> rottenQueue = new LinkedList<>();

        if (grid == null || grid.length == 0) {
            return errorCount;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    rottenQueue.offer(new int[]{i, j});
                }
                if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        if (freshOranges == 0) {
            return 0;
        }

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!rottenQueue.isEmpty()) {
            minutes++;
            int currentQueueLength = rottenQueue.size();
            int processedItems = 0;

            while (processedItems < currentQueueLength) {
                processedItems++;
                int[] currentItem = rottenQueue.poll();
                int currentRow = currentItem[0];
                int currentColumn = currentItem[1];

                for (int[] direction : directions) {
                    int newRow = currentRow + direction[0];
                    int newColumn = currentColumn + direction[1];

                    if (newRow >= 0 && newColumn >= 0 && newColumn < grid[0].length
                            && newRow < grid.length && grid[newRow][newColumn] == 1) {
                        grid[newRow][newColumn] = 2;
                        rottenQueue.offer(new int[]{newRow, newColumn});
                        freshOranges--;
                    }
                }
            }
        }

        if (freshOranges != 0) {
            return errorCount;
        }

        return minutes - 1;
    }

    public static void main(String[] args) {
        OrangeRotting solution = new OrangeRotting();

        int[][] grid1 = {{0}};
        System.out.println(solution.orangesRotting(grid1)); // Expected output: 0

        int[][] grid2 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        System.out.println(solution.orangesRotting(grid2)); // Expected output: -1

        int[][] grid3 = {{2, 1, 0}, {1, 1, 0}, {0, 1, 2}};
        System.out.println(solution.orangesRotting(grid3)); // Expected output: 2

        int[][] grid4 = {{2, 1, 0}, {0, 1, 0}, {0, 0, 2}};
        System.out.println(solution.orangesRotting(grid4)); // Expected output: 2

        int[][] grid5 = {{2, 2, 0}, {2, 2, 0}, {0, 0, 2}};
        System.out.println(solution.orangesRotting(grid5)); // Expected output: 0
    }
}