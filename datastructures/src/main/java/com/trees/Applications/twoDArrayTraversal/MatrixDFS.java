package main.java.com.trees.Applications.twoDArrayTraversal;

public class MatrixDFS {
    // Direction vectors for up, right, down, and left
    private static final int[][] DIRECTIONS = {
            {-1, 0},  // up
            {0, 1},   // right
            {1, 0},   // down
            {0, -1}   // left
    };

    public static void dfs(int[][] matrix, int row, int col, boolean[][] visited) {
        // Base case: if the current cell is out of bounds or already visited, return
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || visited[row][col]) {
            return;
        }

        // Mark the current cell as visited
        visited[row][col] = true;

        // Process the current cell (e.g., print the value or store it in a result list)
        System.out.print(matrix[row][col] + " " );

        // Explore all four directions
        for (int[] direction : DIRECTIONS) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            dfs(matrix, newRow, newCol, visited);
        }
    }

    public static void main(String[] args) {
        // Example 2D matrix
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // Initialize the visited array
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        // Start DFS from a given starting point (e.g., top-left corner of the matrix)
        int startRow = 0;
        int startCol = 0;
        dfs(matrix, startRow, startCol, visited);
    }
}
