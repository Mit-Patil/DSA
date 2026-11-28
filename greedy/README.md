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
| **Running best** | Scan once and keep the best answer so far, plus the one value needed to extend it (the lowest price, or the running sum) | 121, 53 |
| **Reach / jump** | Keep one number, how far you can get, and stop with `false` if the current index is beyond it | 55 |

---

## Index

| # | Problem | Difficulty | Style | Solution |
|---|---------|------------|-------|----------|
| 455 | [Assign Cookies](#455-assign-cookies) | Easy | Sort, then match | [Java](LC455_AssignCookies.java) |
| 121 | [Best Time to Buy and Sell Stock](#121-best-time-to-buy-and-sell-stock) | Easy | Running best | [Java](LC121_BestTimeToBuyAndSellStock.java) |
| 53 | [Maximum Subarray](#53-maximum-subarray) | Medium | Running best | [Java](LC53_MaximumSubarray.java) |
| 55 | [Jump Game](#55-jump-game) | Medium | Reach / jump | [Java](LC55_JumpGame.java) |

**Total solved: 4**

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


---

## 121. Best Time to Buy and Sell Stock

[LeetCode](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) · Easy · [Solution](LC121_BestTimeToBuyAndSellStock.java)

**Problem:** `prices[i]` is the price of a stock on day `i`. Choose one day to buy and a later day to sell, and return the maximum profit. If no profit is possible, return `0`.

**Key points**
- You must buy **before** you sell
- The answer is a value (the profit), so track values, not the days
- For any selling day, the best buy is the **cheapest earlier price**, so remember only `minPrice`
- Each day: update `minPrice`, or compute `price - minPrice` and keep the biggest as `best`
- The highest price in the array does not matter if it comes before the cheapest one
- If the price only goes down, `best` stays `0`

**Examples**
```
Input:  prices = [7,1,5,3,6,4]
Output: 5      (buy at 1, sell at 6)

Input:  prices = [7,6,4,3,1]
Output: 0
```

**Constraints:** `1 <= prices.length <= 10^5`, `0 <= prices[i] <= 10^4`

**Approach:** Keep `minPrice` (start at `Integer.MAX_VALUE`) and `best` (start at `0`). For each price: if it is lower than `minPrice`, it becomes the new `minPrice`. Otherwise, `best = max(best, price - minPrice)`. Return `best`.

**Complexity:** Time `O(n)` · Space `O(1)`

[Back to index](#index)

---

## 53. Maximum Subarray

[LeetCode](https://leetcode.com/problems/maximum-subarray/) · Medium · [Solution](LC53_MaximumSubarray.java)

**Problem:** Given an integer array `nums`, find the contiguous subarray (at least one element) with the largest sum, and return that sum.

**Key points**
- If the running sum so far is negative, it can only make any later subarray smaller, so drop it and start fresh (that is the greedy reason)
- The subarray must have at least one element, so start `maxSum` at `Integer.MIN_VALUE`, not `0` (an all-negative array like `[-1]` must return `-1`)
- Record `maxSum` **before** resetting the sum, otherwise a negative sum is never recorded
- Only compare with `Integer.MIN_VALUE` (with `Math.max`), never add to it
- Follow-up: a divide and conquer solution exists, but it is `O(n log n)`

**Examples**
```
Input:  nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6      ([4,-1,2,1])

Input:  nums = [1]
Output: 1

Input:  nums = [5,4,-1,7,8]
Output: 23
```

**Constraints:** `1 <= nums.length <= 10^5`, `-10^4 <= nums[i] <= 10^4`

**Approach:** Keep `sum = 0` and `maxSum = Integer.MIN_VALUE`. For each number: `sum += n`, then `maxSum = max(maxSum, sum)`, then if `sum < 0`, set `sum = 0`. Return `maxSum`.

**Complexity:** Time `O(n)` · Space `O(1)`

[Back to index](#index)

---

## 55. Jump Game

[LeetCode](https://leetcode.com/problems/jump-game/) · Medium · [Solution](LC55_JumpGame.java)

**Problem:** `nums[i]` is the maximum jump length from index `i`. Starting at index `0`, return `true` if you can reach the last index, otherwise `false`.

**Key points**
- `nums[i]` is a **maximum**: from `i` you can land on any index from `i + 1` to `i + nums[i]`, not only the farthest one
- If you can reach an index, you can reach every index before it, so the reachable indices are always `0 ... farthest`
- Keep only one number, `farthest`, and update it with `max(farthest, i + nums[i])`
- If the current index `i` is greater than `farthest`, you can never land on it, so return `false`
- Return `true` as soon as `farthest >= nums.length - 1`
- A `0` only blocks you if you cannot get past it
- Do not compare the **values** at two positions, because that says nothing about reaching an index

**Examples**
```
Input:  nums = [2,3,1,1,4]
Output: true

Input:  nums = [3,2,1,0,4]
Output: false
```

**Constraints:** `1 <= nums.length <= 10^4`, `0 <= nums[i] <= 10^5`

**Approach:** Keep `farthest = 0`. For each index `i`: if `i > farthest`, return `false`. Otherwise set `farthest = max(farthest, i + nums[i])`, and if `farthest >= nums.length - 1`, return `true`.

**Complexity:** Time `O(n)` · Space `O(1)`

[Back to index](#index)