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


## Pseudocode for BFS

```plaintext

    function BFS(matrix, startRow, startCol):
    directions = [[-1, 0], [0, 1], [1, 0], [0, -1]]  # Directions for up, right, down, left
    queue = empty queue
    queue.enqueue([startRow, startCol])

    visited = 2D array of false with dimensions [matrix.length][matrix[0].length]
    visited[startRow][startCol] = true

    while queue is not empty:
        current = queue.dequeue()
        row = current[0]
        col = current[1]

        process(matrix[row][col])  # Process the current cell

        for each direction in directions:
            newPos = [row + direction[0], col + direction[1]]

            if newPos is within bounds of matrix and not visited[newPos[0]][newPos[1]]:
                queue.enqueue(newPos)
                visited[newPos[0]][newPos[1]] = true

    function isInBounds(pos, matrix):
    row = pos[0]
    col = pos[1]
    return row >= 0 and row < matrix.length and col >= 0 and col < matrix[0].length
    
    function process(value):
    print value

```