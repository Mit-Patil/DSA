# Stack

Quick-revision notes for the **Stack** pattern. Click a problem in the index to jump to its summary, or open the Java solution directly.

## When to use this pattern

- You need to remember the **most recent unfinished item** and come back to it (last in, first out)
- Matching pairs or nested structures, like brackets
- "Undo the last step" style problems
- Later: "next greater element" problems with a **monotonic stack**

## Styles

| Style | Idea | Used in |
|-------|------|---------|
| **Matching** | Push openers, and when a closer arrives, pop and check that the top is its partner | 20 |

---

## Index

| # | Problem | Difficulty | Style | Solution |
|---|---------|------------|-------|----------|
| 20 | [Valid Parentheses](#20-valid-parentheses) | Easy | Matching | [Java](LC20_ValidParentheses.java) |

**Total solved: 1**

---

## 20. Valid Parentheses

[LeetCode](https://leetcode.com/problems/valid-parentheses/) · Easy · [Solution](LC20_ValidParentheses.java)

**Problem:** Given a string made only of `(`, `)`, `{`, `}`, `[` and `]`, return `true` if every opening bracket is closed by the same type of bracket, in the correct order.

**Key points**
- The last opened bracket must be closed first, which is why a stack fits
- The partner of a closer is **not** equal to it, so check the matching partner (`)` needs `(`), not equality
- Two failure cases: a closer arrives when the stack is **empty**, and the stack is **not empty** at the end
- An odd-length string can never be valid, so it can return `false` at once

**Examples**
```
Input:  s = "()"
Output: true

Input:  s = "()[]{}"
Output: true

Input:  s = "(]"
Output: false

Input:  s = "([])"
Output: true

Input:  s = "([)]"
Output: false
```

**Constraints:** `1 <= s.length <= 10^4`, only the characters `()[]{}`

**Approach:** Keep a `HashMap` from each closer to its opener (`)` to `(`, `}` to `{`, `]` to `[`). Go through the string: if the character is an opener, push it. If it is a closer, return `false` when the stack is empty, otherwise pop the top and return `false` if it is not the matching opener. At the end, return `true` only if the stack is empty.

**Complexity:** Time `O(n)` · Space `O(n)`

[Back to index](#index)