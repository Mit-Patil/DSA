# Binary Search

Quick-revision notes for the **Binary Search** pattern. Click a problem in the index to jump to its summary, or open the Java solution directly.

## When to use this pattern

- Array is **sorted** (or behaves like one)
- The question asks for **O(log n)** time
- You need to find a target, or the **position where it would go**
- A 2D structure can be treated as one sorted list

## Styles

| Style | Setup | Used in |
|-------|-------|---------|
| **Exact search** | `while (left <= right)`, return `mid` when found | 704, 74 |
| **Insert position** | Same loop, but when not found return `left` (first index with a bigger value) | 35 |
| **2D as 1D** | Search `0 ... m*n - 1`, convert with `row = mid / n`, `col = mid % n` | 74 |
| **First / last position** | On a match, save `mid` as the answer, then keep searching left (first) or right (last) | 34 |
| **Search on the answer** | No array. Binary search on a range of values using a true/false check that flips only once | 278 |

---

## Index

| # | Problem | Difficulty | Style | Solution |
|---|---------|------------|-------|----------|
| 704 | [Binary Search](#704-binary-search) | Easy | Exact search | [Java](LC704_BinarySearch.java) |
| 35 | [Search Insert Position](#35-search-insert-position) | Easy | Insert position | [Java](LC35_SearchInsertPosition.java) |
| 74 | [Search a 2D Matrix](#74-search-a-2d-matrix) | Medium | 2D as 1D | [Java](LC74_Search2DMatrix.java) |
| 34 | [Find First and Last Position of Element in Sorted Array](#34-find-first-and-last-position-of-element-in-sorted-array) | Medium | First / last position | [Java](LC34_FindFirstAndLastPosition.java) |
| 278 | [First Bad Version](#278-first-bad-version) | Easy | Search on the answer | [Java](LC278_FirstBadVersion.java) |

**Total solved: 5**

---

## 704. Binary Search

[LeetCode](https://leetcode.com/problems/binary-search/) · Easy · [Solution](LC704_BinarySearch.java)

**Problem:** Given a sorted array `nums` and a `target`, return the index of `target`, or `-1` if it is not present.

**Key points**
- Array is sorted in ascending order with unique values
- Must run in `O(log n)`
- Use `mid = left + (right - left) / 2` to avoid overflow

**Examples**
```
Input:  nums = [-1,0,3,5,9,12], target = 9
Output: 4

Input:  nums = [-1,0,3,5,9,12], target = 2
Output: -1
```

**Constraints:** `1 <= n <= 10^4`, `-10^4 < nums[i], target < 10^4`, unique values, sorted ascending

**Approach:** `left = 0`, `right = n - 1`. Check `mid`. Equal → return. `nums[mid] < target` → `left = mid + 1`. Otherwise → `right = mid - 1`. Loop while `left <= right`.

**Complexity:** Time `O(log n)` · Space `O(1)`

[Back to index](#index)

---

## 35. Search Insert Position

[LeetCode](https://leetcode.com/problems/search-insert-position/) · Easy · [Solution](LC35_SearchInsertPosition.java)

**Problem:** Given a sorted array of distinct integers and a `target`, return its index if found. Otherwise return the index where it would be inserted in order.

**Key points**
- Must run in `O(log n)`
- Target may not exist in the array
- Answer can be `0` (before everything) or `n` (after everything)

**Examples**
```
Input:  nums = [1,3,5,6], target = 5
Output: 2

Input:  nums = [1,3,5,6], target = 2
Output: 1

Input:  nums = [1,3,5,6], target = 7
Output: 4
```

**Constraints:** `1 <= n <= 10^4`, `-10^4 <= nums[i], target <= 10^4`, distinct values, sorted ascending

**Approach:** Run the normal binary search. If the target is never found, the loop ends with `left > right`, and `left` is the first index with a bigger value. Return `left`.

**Complexity:** Time `O(log n)` · Space `O(1)`

[Back to index](#index)

---

## 74. Search a 2D Matrix

[LeetCode](https://leetcode.com/problems/search-a-2d-matrix/) · Medium · [Solution](LC74_Search2DMatrix.java)

**Problem:** An `m x n` matrix has sorted rows, and the first number of each row is bigger than the last number of the previous row. Return `true` if `target` is in the matrix.

**Key points**
- Reading the matrix row by row gives one fully sorted list
- Must run in `O(log(m * n))`
- Index `mid` maps to `matrix[mid / n][mid % n]`, where `n` is the number of columns

**Examples**
```
Input:  matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Input:  matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
```

**Constraints:** `1 <= m, n <= 100`, `-10^4 <= matrix[i][j], target <= 10^4`

**Approach:** Treat the matrix as one array of size `m * n`. Binary search on `left = 0`, `right = m * n - 1`. For each `mid`, read `matrix[mid / n][mid % n]` and compare it with the target.

**Complexity:** Time `O(log(m * n))` · Space `O(1)`

[Back to index](#index)

---

## 34. Find First and Last Position of Element in Sorted Array

[LeetCode](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) · Medium · [Solution](LC34_FindFirstAndLastPosition.java)

**Problem:** Given a sorted array `nums` (duplicates allowed) and a `target`, return `[first index, last index]` of the target. Return `[-1, -1]` if it is not present.

**Key points**
- Array is sorted in non-decreasing order, so duplicates sit next to each other
- Must run in `O(log n)`
- The array can be empty

**Examples**
```
Input:  nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Input:  nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Input:  nums = [], target = 0
Output: [-1,-1]
```

**Constraints:** `0 <= n <= 10^5`, `-10^9 <= nums[i], target <= 10^9`, sorted non-decreasing

**Approach:** Run two binary searches. For the **first** index: when `nums[mid] == target`, save `ans = mid` and keep searching left (`right = mid - 1`). For the **last** index: same, but keep searching right (`left = mid + 1`). If the target is never found, both return `-1`.

**Complexity:** Time `O(log n)` · Space `O(1)`

[Back to index](#index)

---

## 278. First Bad Version

[LeetCode](https://leetcode.com/problems/first-bad-version/) · Easy · [Solution](LC278_FirstBadVersion.java)

**Problem:** Versions `1 ... n` are in order, and once a version is bad, all later versions are bad. Using `isBadVersion(version)`, find the first bad version with as few API calls as possible.

**Key points**
- No array is given, you search on the version numbers `1 ... n`
- Versions look like `F F F T T`, the answer is the first `T`
- `n` can be as large as `2^31 - 1`, so use `mid = left + (right - left) / 2` to avoid overflow
- Versions start at `1`, so `left = 1`

**Examples**
```
Input:  n = 5, bad = 4
Output: 4

Input:  n = 1, bad = 1
Output: 1
```

**Constraints:** `1 <= bad <= n <= 2^31 - 1`

**Approach:** `left = 1`, `right = n`. If `isBadVersion(mid)` is true, the answer is `mid` or earlier, so `right = mid - 1`. Otherwise `left = mid + 1`. When the loop ends, `left` is the first bad version.

**Complexity:** Time `O(log n)` (API calls) · Space `O(1)`

[Back to index](#index)