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

---

## Index

| # | Problem | Difficulty | Style | Solution |
|---|---------|------------|-------|----------|
| 1046 | [Last Stone Weight](#1046-last-stone-weight) | Easy | Max-heap | [Java](LC1046_LastStoneWeight.java) |

**Total solved: 1**

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