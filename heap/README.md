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

---

## Index

| # | Problem | Difficulty | Style | Solution |
|---|---------|------------|-------|----------|
| 1046 | [Last Stone Weight](#1046-last-stone-weight) | Easy | Max-heap | [Java](LC1046_LastStoneWeight.java) |
| 703 | [Kth Largest Element in a Stream](#703-kth-largest-element-in-a-stream) | Easy | Min-heap of size k | [Java](LC703_KthLargestElementInAStream.java) |
| 215 | [Kth Largest Element in an Array](#215-kth-largest-element-in-an-array) | Medium | Min-heap of size k | [Java](LC215_KthLargestElementInAnArray.java) |

**Total solved: 3**

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