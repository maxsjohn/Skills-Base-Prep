package main.java.com.trees.Applications;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IslandProblem1 {

    /**
     * Counts the number of islands in a given 2D binary grid.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     *
     * @param grid 2D binary grid where '1' represents land and '0' represents water
     * @return number of islands
     */
    public int numIslands(char[][] grid) {
        // Check if the grid is null or empty
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int count = 0; // Initialize island count
        int rows = grid.length;
        int cols = grid[0].length;

        // Iterate through each cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If a land cell is found, it indicates the start of a new island
                if (grid[i][j] == '1') {
                    count++; // Increment island count
                    bfs(i, j, grid); // Perform BFS to mark the entire island
                }
            }
        }
        return count; // Return the total number of islands
    }

    /**
     * Performs a BFS to visit all cells in the current island and marks them as visited.
     *
     * @param curRow the current row position
     * @param curCol the current column position
     * @param grid   the grid representing the map
     */
    private void bfs(int curRow, int curCol, char[][] grid) {
        // Initialize the queue for BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{curRow, curCol});

        // Define the directions for moving up, right, down, and left
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // Mark the initial cell as visited by setting it to '0'
        grid[curRow][curCol] = '0';

        while (!queue.isEmpty()) {
            int[] current = queue.poll(); // Get the current cell
            int row = current[0];
            int col = current[1];

            // Explore all four directions
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                // Check if the new position is within the bounds and if it's land ('1')
                if (newRow >= 0 && newCol >= 0 && newRow < grid.length && newCol < grid[0].length && grid[newRow][newCol] == '1') {
                    // Mark the new land cell as visited by setting it to '0'
                    grid[newRow][newCol] = '0';
                    // Add the new position to the queue to continue BFS
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }

    public static void main(String[] args) {
        IslandProblem1 solution = new IslandProblem1();

        // Test Case 1
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println("Number of Islands (Test Case 1): " + solution.numIslands(grid1)); // Output: 1

        // Test Case 2
        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("Number of Islands (Test Case 2): " + solution.numIslands(grid2)); // Output: 3

        // Test Case 3
        char[][] grid3 = {
                {'1', '0', '0', '0', '0'},
                {'0', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '0'}
        };
        System.out.println("Number of Islands (Test Case 3): " + solution.numIslands(grid3)); // Output: 4

        // Test Case 4 (Edge Case)
        char[][] grid4 = {
                {'0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println("Number of Islands (Test Case 4): " + solution.numIslands(grid4)); // Output: 0
    }
}