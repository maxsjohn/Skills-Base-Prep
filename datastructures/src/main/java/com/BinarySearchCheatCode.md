# Binary Search Cheat Sheet

| Problem Type                                 | Condition (`while`)  | Midpoint Calculation  | `l` Update Condition                      | `r` Update Condition                   |
|----------------------------------------------|----------------------|-----------------------|-------------------------------------------|----------------------------------------|
| **Exact Value Search**                       | `l <= r`             | `mid = (l + r) / 2`   | `l = mid + 1` if `arr[mid] < target`     | `r = mid - 1` if `arr[mid] > target`  |
| **First Occurrence of Target**               | `l < r`              | `mid = (l + r) / 2`   | `l = mid + 1` if `arr[mid] < target`     | `r = mid` if `arr[mid] >= target`     |
| **Last Occurrence of Target**                | `l < r`              | `mid = (l + r + 1) / 2` | `l = mid` if `arr[mid] <= target`        | `r = mid - 1` if `arr[mid] > target`  |
| **Find Minimum Valid Value (e.g. Koko Bananas)** | `l < r`              | `mid = (l + r) / 2`   | `l = mid + 1` if `condition(mid) > h`    | `r = mid` if `condition(mid) <= h`    |
| **Find Maximum Valid Value**                 | `l <= r`             | `mid = (l + r) / 2`   | `l = mid + 1` if `condition(mid) is valid`| `r = mid - 1` if `condition(mid) is invalid`|
| **Square Root (without precision)**          | `l <= r`             | `mid = (l + r) / 2`   | `l = mid + 1` if `mid*mid < x`           | `r = mid - 1` if `mid*mid > x`        |
| **Peak Element (Find Local Max)**            | `l < r`              | `mid = (l + r) / 2`   | `l = mid + 1` if `arr[mid] < arr[mid + 1]`| `r = mid` if `arr[mid] > arr[mid + 1]`|
| **Search Insert Position**                   | `l <= r`             | `mid = (l + r) / 2`   | `l = mid + 1` if `arr[mid] < target`     | `r = mid - 1` if `arr[mid] >= target` |
| **Rotated Sorted Array Min**                 | `l < r`              | `mid = (l + r) / 2`   | `l = mid + 1` if `arr[mid] > arr[r]`     | `r = mid` if `arr[mid] <= arr[r]`     |
