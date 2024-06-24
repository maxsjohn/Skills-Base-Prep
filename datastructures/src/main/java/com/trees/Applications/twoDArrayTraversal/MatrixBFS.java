package main.java.com.trees.Applications.twoDArrayTraversal;

import java.util.*;

public class MatrixBFS {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // Start BFS from a given starting point (e.g., top-left corner of the matrix)
        int startRow = 0;
        int startCol = 0;
        matrixBfs(matrix, startRow, startCol);
    }

    public static void matrixBfs(int[][] matrix, int startRow, int startCol) {
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            process(matrix[row][col]);

            Arrays.stream(directions)
                    .map(direction -> new int[]{row + direction[0], col + direction[1]})
                    .filter(newPos -> isInBounds(newPos, matrix) && !visited[newPos[0]][newPos[1]])
                    .forEach(newPos -> {
                        queue.offer(newPos);
                        visited[newPos[0]][newPos[1]] = true;
                    });
        }
    }

    private static boolean isInBounds(int[] pos, int[][] matrix) {
        int row = pos[0];
        int col = pos[1];
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    private static void process(int value) {
        System.out.print(value + " ");
    }
}
