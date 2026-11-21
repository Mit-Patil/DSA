# Heap / Top K

Quick-revision notes for the **Heap** pattern (`PriorityQueue` in Java). Click a problem in the index to jump to its summary, or open the Java solution directly.

## When to use this pattern

- You need the **biggest or smallest** item again and again, while the data changes
- "Find the **k-th** largest / smallest" or "top **k**" problems
- Data arrives as a **stream** and you always need the current best
- Sorting everything would be too slow or has to be redone after every change

## Styles

| Style | Idea | Used in |
|-------|------|---------|
| **Max-heap** | `new PriorityQueue<>(Collections.reverseOrder())`, always take the biggest, change it, put the result back | 1046 |
| **Min-heap of size k** | Keep only the `k` largest items. The top is the smallest of them, which is the k-th largest. If the size goes above `k`, `poll` | 703, 215 |
| **Custom order** | The heap stores items (numbers, words, points, row indices) and the comparator calculates a score. `poll()` removes whatever the comparator puts first, so the item to throw away must be on top: lowest count for "k most frequent" (347), farthest distance for "k closest" (973). Add a **tie-break** when scores can be equal: alphabetical order (692) or the index itself (1337) | 347, 973, 692, 1337 |

---

## Index

| # | Problem | Difficulty | Style | Solution |
|---|---------|------------|-------|----------|
| 1046 | [Last Stone Weight](#1046-last-stone-weight) | Easy | Max-heap | [Java](LC1046_LastStoneWeight.java) |
| 703 | [Kth Largest Element in a Stream](#703-kth-largest-element-in-a-stream) | Easy | Min-heap of size k | [Java](LC703_KthLargestElementInAStream.java) |
| 215 | [Kth Largest Element in an Array](#215-kth-largest-element-in-an-array) | Medium | Min-heap of size k | [Java](LC215_KthLargestElementInAnArray.java) |
| 347 | [Top K Frequent Elements](#347-top-k-frequent-elements) | Medium | Custom order | [Java](LC347_TopKFrequentElements.java) |
| 973 | [K Closest Points to Origin](#973-k-closest-points-to-origin) | Medium | Custom order | [Java](LC973_KClosestPointsToOrigin.java) |
| 692 | [Top K Frequent Words](#692-top-k-frequent-words) | Medium | Custom order + tie-break | [Java](LC692_TopKFrequentWords.java) |
| 1337 | [The K Weakest Rows in a Matrix](#1337-the-k-weakest-rows-in-a-matrix) | Easy | Custom order + tie-break | [Java](LC1337_TheKWeakestRowsInAMatrix.java) |

**Total solved: 7**

---

## 1046. Last Stone Weight

[LeetCode](https://leetcode.com/problems/last-stone-weight/) · Easy · [Solution](LC1046_LastStoneWeight.java)

**Problem:** Given an array of stone weights, repeatedly smash the two heaviest stones together. If they are equal, both are destroyed. Otherwise the heavier one stays with the difference as its new weight. Return the weight of the last stone, or `0` if none is left.

**Key points**
- The two heaviest stones are needed every turn, and the stones change after each smash, so a max-heap fits
- `PriorityQueue` is a min-heap by default, so use `Collections.reverseOrder()` for a max-heap
- The first stone taken out (`x`) is always at least as heavy as the second (`y`), so `x - y` is never negative
- Stop when the heap has one stone left (or none, if the last two were equal)
- Adding a `0` stone back is harmless, because smashing it with another stone gives that stone back unchanged

**Examples**
```
Input:  stones = [2,7,4,1,8,1]
Output: 1

Input:  stones = [1]
Output: 1
```

**Constraints:** `1 <= stones.length <= 30`, `1 <= stones[i] <= 1000`

**Approach:** Put all the stones in a max-heap. While the heap has more than one stone, poll the two heaviest (`x` then `y`) and, if they differ, add `x - y` back. When the loop ends, return the remaining stone, or `0` if the heap is empty.

**Complexity:** Time `O(n log n)` · Space `O(n)`

[Back to index](#index)

---

## 703. Kth Largest Element in a Stream

[LeetCode](https://leetcode.com/problems/kth-largest-element-in-a-stream/) · Easy · [Solution](LC703_KthLargestElementInAStream.java)

**Problem:** Design a class that starts with an integer `k` and some scores. Each call to `add(val)` adds a new score and returns the `k`-th largest score so far.

**Key points**
- The constructor only sets things up and returns nothing. Only `add` returns a value
- A max-heap does not help, because its top is the 1st largest and not the k-th
- Keep a **min-heap with at most `k` items**: it holds the `k` largest scores, and its top is the k-th largest
- After adding, if the size is above `k`, `poll` the smallest, since it can no longer be in the top `k`
- Keep `k` and the heap as fields, and use `this.k = k` in the constructor
- The constructor can reuse `add` for every starting score
- Duplicates are kept as separate items

**Examples**
```
Input:  ["KthLargest","add","add","add","add","add"]
        [[3,[4,5,8,2]],[3],[5],[10],[9],[4]]
Output: [null,4,5,5,8,8]

Input:  ["KthLargest","add","add","add","add"]
        [[4,[7,7,7,7,8,3]],[2],[10],[9],[9]]
Output: [null,7,7,7,8]
```

**Constraints:** `0 <= nums.length <= 10^4`, `1 <= k`, `-10^4 <= nums[i], val <= 10^4`, at most `10^4` calls to `add`, there are always at least `k` elements when `add` is called

**Approach:** Store `k` and a min-heap. In the constructor, call `add` for every number in `nums`. In `add(val)`, add `val` to the heap, and if `size() > k`, call `poll()` to remove the smallest. Return `peek()`.

**Complexity:** Constructor `O(n log k)` · `add` `O(log k)` · Space `O(k)`

[Back to index](#index)

---

## 215. Kth Largest Element in an Array

[LeetCode](https://leetcode.com/problems/kth-largest-element-in-an-array/) · Medium · [Solution](LC215_KthLargestElementInAnArray.java)

**Problem:** Given an integer array `nums` and an integer `k`, return the `k`-th largest element in the sorted order (not the k-th distinct element).

**Key points**
- Same idea as 703, but the whole array is given at once, with no class
- A min-heap of size `k` ends up holding the `k` largest numbers, and its top is the answer
- Duplicates count separately
- Sorting works in `O(n log n)`, but the problem asks for a solution without sorting
- Quickselect is `O(n)` on average, and a counting array also works because the values are in a small range

**Examples**
```
Input:  nums = [3,2,1,5,6,4], k = 2
Output: 5

Input:  nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
```

**Constraints:** `1 <= k <= nums.length <= 10^5`, `-10^4 <= nums[i] <= 10^4`

**Approach:** Go through the array with a min-heap. Add each number, and if the size goes above `k`, `poll` the smallest. After the loop, `peek()` is the k-th largest.

**Complexity:** Time `O(n log k)` · Space `O(k)`

[Back to index](#index)

---

## 347. Top K Frequent Elements

[LeetCode](https://leetcode.com/problems/top-k-frequent-elements/) · Medium · [Solution](LC347_TopKFrequentElements.java)

**Problem:** Given an integer array `nums` and an integer `k`, return the `k` most frequent elements, in any order.

**Key points**
- Two steps: count with a `HashMap`, then keep the top `k` with a heap
- A heap of plain numbers orders by value, so it needs a comparator that orders by **count**
- The heap stores the numbers, and the comparator looks up `count.get(number)`
- The `HashMap` must be declared before the heap, because the comparator uses it
- Loop over the **distinct** numbers (`keySet()`), not over every element
- Use a min-heap by count of size `k`: when the size goes above `k`, `poll` removes the least frequent number
- The result comes out from the least to the most frequent, and any order is accepted
- Scanning for the maximum `k` times is `O(k * d)`, which can reach `O(n^2)`

**Examples**
```
Input:  nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Input:  nums = [1], k = 1
Output: [1]

Input:  nums = [1,2,1,2,1,2,3,1,3,2], k = 2
Output: [1,2]
```

**Constraints:** `1 <= nums.length <= 10^5`, `-10^4 <= nums[i] <= 10^4`, `k` is between `1` and the number of distinct elements, the answer is unique

**Approach:** Count every number with `count.merge(n, 1, Integer::sum)`. Create a min-heap with the comparator `(a, b) -> Integer.compare(count.get(a), count.get(b))`. For each distinct number, add it, and if the size goes above `k`, `poll`. Then poll the `k` remaining numbers into the answer array. A faster alternative is bucket sort (an array where the index is the frequency), which is `O(n)`.

**Complexity:** Time `O(n + d log k)` (`d` is the number of distinct values, at most `O(n log k)`) · Space `O(d + k)`

[Back to index](#index)

---

## 973. K Closest Points to Origin

[LeetCode](https://leetcode.com/problems/k-closest-points-to-origin/) · Medium · [Solution](LC973_KClosestPointsToOrigin.java)

**Problem:** Given an array of points `[x, y]` and an integer `k`, return the `k` points closest to the origin `(0, 0)`, in any order.

**Key points**
- Compare the squared distance `x * x + y * y`, because the square root never changes which point is closer, and skipping it avoids decimals
- Square each coordinate, adding them (`x + y`) is a different number and gives wrong answers
- For the `k` **smallest** distances, use a **max-heap** by distance, so the farthest of the kept points is on top and is the one `poll` removes
- With `Integer.compare(d(b), d(a))`, the larger distance goes first (top)
- A negative result from the comparator means the first argument goes closer to the top
- Points are `int[]`, so `p[0]` is `x` and `p[1]` is `y`
- `maxHeap.toArray(new int[k][])` gives the result, since the heap holds exactly `k` points at the end

**Examples**
```
Input:  points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]

Input:  points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
```

**Constraints:** `1 <= k <= points.length <= 10^4`, `-10^4 <= xi, yi <= 10^4`

**Approach:** Create a max-heap of points with the comparator `(a, b) -> Integer.compare(dist(b), dist(a))`, where `dist(p) = p[0] * p[0] + p[1] * p[1]`. Add each point, and if the size goes above `k`, `poll` the farthest. The heap then holds the `k` closest points. Sorting all points would be `O(n log n)`, and quickselect is `O(n)` on average.

**Complexity:** Time `O(n log k)` · Space `O(k)`

[Back to index](#index)

---

## 692. Top K Frequent Words

[LeetCode](https://leetcode.com/problems/top-k-frequent-words/) · Medium · [Solution](LC692_TopKFrequentWords.java)

**Problem:** Given an array of strings `words` and an integer `k`, return the `k` most frequent words, sorted by frequency from highest to lowest. Words with the same frequency are sorted in alphabetical order.

**Key points**
- Same idea as 347 (count with a `HashMap`, then a min-heap of size `k`), but words with the **same count** need a tie-break
- Comparing only the counts leaves ties unordered, so the wrong word can be removed and the output order can be wrong
- `poll()` removes whatever the comparator puts first, so the **worst** candidate must come first: lower count first, and for equal counts the alphabetically **later** word first
- `b.compareTo(a)` gives that reverse alphabetical order (`a.compareTo(b)` would keep the wrong words)
- `Integer.compare(cb, ca)` in the tie branch would compare two equal counts and always return `0`
- The heap pops from the worst to the best, so use `Collections.reverse` on the result
- Declare the `HashMap` before the heap, because the comparator uses it
- Unbox the counts into `int` variables before comparing with `!=`, to avoid the Integer cache trap

**Examples**
```
Input:  words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]

Input:  words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
```

**Constraints:** `1 <= words.length <= 500`, `1 <= words[i].length <= 10`, lowercase English letters, `k` is between `1` and the number of unique words. Follow-up: `O(n log k)` time and `O(n)` extra space

**Approach:** Count every word with `merge`. Create a min-heap of words with the comparator: if the counts differ, `Integer.compare(ca, cb)`, otherwise `b.compareTo(a)`. For each distinct word, add it, and if the size goes above `k`, `poll`. Then poll all remaining words into a list and reverse it.

**Complexity:** Time `O(n + d log k)` (`d` is the number of distinct words) · Space `O(d + k)`

[Back to index](#index)

---

## 1337. The K Weakest Rows in a Matrix

[LeetCode](https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/) · Easy · [Solution](LC1337_TheKWeakestRowsInAMatrix.java)

**Problem:** A binary matrix has `1`s (soldiers) on the left and `0`s (civilians) on the right in every row. Row `i` is weaker than row `j` if it has fewer soldiers, or the same number of soldiers and `i < j`. Return the indices of the `k` weakest rows, ordered from weakest to strongest.

**Key points**
- Count the soldiers of each row in an `int[]` (a `HashMap` that only stores rows with soldiers causes a `NullPointerException` for a row of all `0`s)
- The heap stores **row indices**, and the comparator looks up `soldiers[row]`
- For the `k` weakest rows, use a **max-heap**, so the strongest of the kept rows is on top and is the one `poll` removes
- Different counts: `Integer.compare(soldiers[b], soldiers[a])` puts the stronger row first
- Same count: `Integer.compare(b, a)` puts the larger index first, because it is the stronger one on a tie. Comparing the counts again would always return `0`
- `poll()` gives the strongest first, so fill the result array **from the back** (`k - 1` down to `0`)
- Each row is sorted, so the soldiers could also be counted with a binary search

**Examples**
```
Input:  mat = [[1,1,0,0,0],
               [1,1,1,1,0],
               [1,0,0,0,0],
               [1,1,0,0,0],
               [1,1,1,1,1]], k = 3
Output: [2,0,3]

Input:  mat = [[1,0,0,0],
               [1,1,1,1],
               [1,0,0,0],
               [1,0,0,0]], k = 2
Output: [0,2]
```

**Constraints:** `m == mat.length`, `n == mat[i].length`, `2 <= n, m <= 100`, `1 <= k <= m`, `mat[i][j]` is `0` or `1`

**Approach:** Count the `1`s of every row into `soldiers[]`. Create a max-heap of row indices ordered by soldiers (more soldiers first), and on equal soldiers by the larger index first. Add each row index, and if the size goes above `k`, `poll`. Then poll the remaining rows into the result from the last position to the first.

**Complexity:** Time `O(m * n + m log k)` · Space `O(m)`

[Back to index](#index)