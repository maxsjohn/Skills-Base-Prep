# Depth-First Traversal (DFS) of a 2D Matrix

## Pseudocode

```plaintext
function DFS(matrix, row, col, visited):
    # Base case: if the current cell is out of bounds or already visited, return
    if row < 0 or row >= number of rows in matrix or col < 0 or col >= number of columns in matrix or visited[row][col]:
        return

    # Mark the current cell as visited
    visited[row][col] = true

    # Process the current cell (e.g., print the value or store it in a result list)
    process(matrix[row][col])

    # Direction vectors for up, right, down, and left
    directions = [
        [-1, 0],  # up
        [0, 1],   # right
        [1, 0],   # down
        [0, -1]   # left
    ]

    # Explore all four directions
    for i = 0 to 3:
        newRow = row + directions[i][0]
        newCol = col + directions[i][1]
        DFS(matrix, newRow, newCol, visited)

# Initialize the matrix and visited array
matrix = [... your 2D matrix ...]
number of rows = number of rows in matrix
number of cols = number of columns in matrix
visited = initialize 2D array of false with dimensions (number of rows x number of cols)

# Start DFS from a given starting point (startRow, startCol)
startRow = ... your starting row ...
startCol = ... your starting col ...
DFS(matrix, startRow, startCol, visited)
```