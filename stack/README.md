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
| **Stack with extra data** | Each stack item carries more than its value (for example the minimum so far), so one pop restores everything | 155 |
| **Evaluate / combine** | Push numbers, and when an operator arrives, pop the last two, combine them and push the result | 150 |

---

## Index

| # | Problem | Difficulty | Style | Solution |
|---|---------|------------|-------|----------|
| 20 | [Valid Parentheses](#20-valid-parentheses) | Easy | Matching | [Java](LC20_ValidParentheses.java) |
| 155 | [Min Stack](#155-min-stack) | Medium | Stack with extra data | [Java](LC155_MinStack.java) |
| 150 | [Evaluate Reverse Polish Notation](#150-evaluate-reverse-polish-notation) | Medium | Evaluate / combine | [Java](LC150_EvaluateReversePolishNotation.java) |

**Total solved: 3**

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


---

## 155. Min Stack

[LeetCode](https://leetcode.com/problems/min-stack/) · Medium · [Solution](LC155_MinStack.java)

**Problem:** Design a stack that supports `push`, `pop`, `top` and `getMin` (the smallest element currently in the stack), each in `O(1)` time.

**Key points**
- Scanning the stack for the minimum would be `O(n)`, so the minimum must be remembered
- When the top is popped, the **previous** minimum has to come back, so one `min` variable is not enough
- Each push stores "the minimum so far" next to the value, so a pop brings the old minimum back automatically
- Keep the stacks as fields of the class, created in the constructor
- Do not use a fake starting minimum, because values go from `-2^31` to `2^31 - 1`

**Example**
```
Input:  ["MinStack","push","push","push","getMin","pop","top","getMin"]
        [[],[-2],[0],[-3],[],[],[],[]]
Output: [null,null,null,null,-3,null,0,-2]
```

**Constraints:** `-2^31 <= val <= 2^31 - 1`, `pop`, `top` and `getMin` are always called on a non-empty stack, at most `3 * 10^4` calls

**Approach:** Use two stacks, `stack` for the values and `minStack` for the minimum so far. On `push(v)`, push `v` on `stack` and push `Math.min(v, minStack.peek())` on `minStack` (or `v` if it is empty). `pop` removes from both. `top` peeks `stack`, and `getMin` peeks `minStack`. Other ways: one stack of boxes `int[]{value, min}` where `peek()[0]` is the value and `peek()[1]` is the minimum, or one stack plus a `min` variable that pushes the old minimum under every new minimum and restores it when that minimum is popped.

**Complexity:** Time `O(1)` per operation · Space `O(n)`

[Back to index](#index)

---

## 150. Evaluate Reverse Polish Notation

[LeetCode](https://leetcode.com/problems/evaluate-reverse-polish-notation/) · Medium · [Solution](LC150_EvaluateReversePolishNotation.java)

**Problem:** Given an array of string tokens that form an expression in Reverse Polish Notation (the operator comes after its two numbers), evaluate it and return the integer result.

**Key points**
- Valid operators are `+`, `-`, `*`, `/`, and division truncates toward zero (Java `int / int` already does this)
- The first number popped is the **right** side and the second pop is the **left** side, which matters for `-` and `/`
- Compare strings with `.equals()`, not `==`
- Negative numbers such as `"-11"` are numbers, so compare the whole token with `"-"` instead of checking the first character
- A `switch` expression on a `String` needs a `default` branch

**Examples**
```
Input:  tokens = ["2","1","+","3","*"]
Output: 9      ((2 + 1) * 3)

Input:  tokens = ["4","13","5","/","+"]
Output: 6      (4 + (13 / 5))

Input:  tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
```

**Constraints:** `1 <= tokens.length <= 10^4`, each token is an operator or an integer in `[-200, 200]`, no division by zero, the expression is valid

**Approach:** Go through the tokens with a stack of integers. If the token is a number, push it. If it is an operator, pop the second value, then the first value, compute `first op second`, and push the result. At the end, the single value left in the stack is the answer.

**Complexity:** Time `O(n)` · Space `O(n)`

[Back to index](#index)