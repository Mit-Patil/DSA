# Greedy

Quick-revision notes for the **Greedy** pattern. Click a problem in the index to jump to its summary, or open the Java solution directly.

## When to use this pattern

- The question asks for the **minimum or maximum** of something, and there is a clear "best choice first" rule
- Sorting the data first makes the best choice easy to pick
- Scheduling, intervals, matching items to each other, jumping, running totals
- You can explain in one sentence why the best choice now never hurts the final answer (if you can't, greedy may be wrong and Dynamic Programming may be needed)

## Styles

| Style | Idea | Used in |
|-------|------|---------|
| **Sort, then match** | Sort both lists and match the smallest need with the smallest supply that works, using two pointers that never move back | 455 |

---

## Index

| # | Problem | Difficulty | Style | Solution |
|---|---------|------------|-------|----------|
| 455 | [Assign Cookies](#455-assign-cookies) | Easy | Sort, then match | [Java](LC455_AssignCookies.java) |

**Total solved: 1**

---

## 455. Assign Cookies

[LeetCode](https://leetcode.com/problems/assign-cookies/) · Easy · [Solution](LC455_AssignCookies.java)

**Problem:** Each child `i` has a greed factor `g[i]` (the minimum cookie size they accept), and each cookie `j` has a size `s[j]`. A cookie can be given to a child if `s[j] >= g[i]`. Each child gets at most one cookie and each cookie goes to at most one child. Return the maximum number of content children.

**Key points**
- The condition is `s[j] >= g[i]`: the cookie only needs to be **at least** as big, and nothing is added together
- A cookie cannot be used twice. Looping over all cookies again for each child lets one cookie feed many children
- Give the least greedy child the **smallest cookie that works**, so bigger cookies stay for the greedier children
- Sort both arrays, and the cookie pointer only moves forward. A cookie that is too small for the current child is too small for every later child, so it is thrown away
- The number of content children is the value of the child pointer at the end
- The cookie array can be empty, and the answer is then `0`

**Examples**
```
Input:  g = [1,2,3], s = [1,1]
Output: 1

Input:  g = [1,2], s = [1,2,3]
Output: 2
```

**Constraints:** `1 <= g.length <= 3 * 10^4`, `0 <= s.length <= 3 * 10^4`, `1 <= g[i], s[j] <= 2^31 - 1`

**Approach:** Sort `g` and `s`. Keep a child pointer `i = 0`. For each cookie `s[j]` from smallest to biggest, if `i < g.length` and `s[j] >= g[i]`, give it to that child and do `i++`. Return `i`.

**Complexity:** Time `O(n log n + m log m)` · Space `O(1)` extra

[Back to index](#index)