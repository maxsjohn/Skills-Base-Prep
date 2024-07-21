package main.java.com.trees.Applications.twoDArrayTraversal;

import java.util.Arrays;

public class WallsAndGates {

    static int INF = 2147483647;
    static int GATE = 0;
    static int PATH = INF;
    static int WALL = -1;

    public static void print2DArray(int[][] array) {
        for (int[] row : array) {
            for (int element : row) {
                System.out.printf("%12d", element); // Adjust the width as needed
            }
            System.out.println();
        }
    }

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }

        print2DArray(rooms);
        System.out.println();

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == GATE) {
                    dfs(rooms, i, j, 0);
                }
            }
        }

        print2DArray(rooms);
        System.out.println("Done");
        System.out.println();
    }

    private void dfs(int[][] rooms, int row, int col, int level) {
        if (row < 0 || col < 0 || row >= rooms.length || col >= rooms[0].length) {
            return;
        }

        if (rooms[row][col] < level || rooms[row][col] == WALL) {
            return;
        }

        rooms[row][col] = level;

        int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            dfs(rooms, newRow, newCol, level + 1);
        }
    }

    public static void main(String[] args) {
        int[][] rooms = {
                { INF, -1, 0, INF },
                { INF, INF, INF, -1 },
                { INF, -1, INF, -1 },
                { 0, -1, INF, INF }
        };

        WallsAndGates solution = new WallsAndGates();
        solution.wallsAndGates(rooms);
    }
}