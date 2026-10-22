# Hashing

Quick-revision notes for the **Hashing** pattern. Click a problem in the index to jump to its summary, or open the Java solution directly.

## When to use this pattern

- The question is "have I **seen this before**?" or "does a **duplicate** exist?"
- You need to **count** how often each value appears
- You need to find a **pair or value that matches** something, and can look it up instead of searching for it
- A nested loop `O(n^2)` can become one pass `O(n)` by remembering what you have already seen

## Styles

| Style | Structure | Used in |
|-------|-----------|---------|
| **Seen-before** | `HashSet`, `add` returns `false` if the value is already there | 217 |
| **Lookup** | `HashMap<value, index>`, look up the complement before storing the current value | 1 |
| **Frequency count** | `int[26]` array (or `HashMap<char, count>`), `++` for one string and `--` for the other | 242 |

---

## Index

| # | Problem | Difficulty | Style | Solution |
|---|---------|------------|-------|----------|
| 217 | [Contains Duplicate](#217-contains-duplicate) | Easy | Seen-before | [Java](LC217_ContainsDuplicate.java) |
| 1 | [Two Sum](#1-two-sum) | Easy | Lookup | [Java](LC1_TwoSum.java) |
| 242 | [Valid Anagram](#242-valid-anagram) | Easy | Frequency count | [Java](LC242_ValidAnagram.java) |

**Total solved: 3**

---

## 217. Contains Duplicate

[LeetCode](https://leetcode.com/problems/contains-duplicate/) · Easy · [Solution](LC217_ContainsDuplicate.java)

**Problem:** Given an integer array `nums`, return `true` if any value appears at least twice, and `false` if every element is distinct.

**Key points**
- Only the existence of a duplicate matters, not its index or count
- A `HashSet` is enough, a `HashMap` is not needed
- `set.add(x)` returns `false` when `x` is already in the set

**Examples**
```
Input:  nums = [1,2,3,1]
Output: true

Input:  nums = [1,2,3,4]
Output: false

Input:  nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
```

**Constraints:** `1 <= n <= 10^5`, `-10^9 <= nums[i] <= 10^9`

**Approach:** Go through the array once. For each number, call `seen.add(x)`. If it returns `false`, the number was already seen, so return `true`. If the loop finishes, return `false`. (Alternative: sort first and compare neighbours, `O(n log n)` time but `O(1)` extra space.)

**Complexity:** Time `O(n)` · Space `O(n)`

[Back to index](#index)

---

## 1. Two Sum

[LeetCode](https://leetcode.com/problems/two-sum/) · Easy · [Solution](LC1_TwoSum.java)

**Problem:** Given an array `nums` and an integer `target`, return the indices of the two numbers that add up to `target`.

**Key points**
- Exactly one valid answer exists
- The same element cannot be used twice
- The answer can be returned in any order
- Look up the complement **before** storing the current number, so a number can't match itself (this also handles `[3,3]`)

**Examples**
```
Input:  nums = [2,7,11,15], target = 9
Output: [0,1]

Input:  nums = [3,2,4], target = 6
Output: [1,2]

Input:  nums = [3,3], target = 6
Output: [0,1]
```

**Constraints:** `2 <= n <= 10^4`, `-10^9 <= nums[i], target <= 10^9`, exactly one valid answer

**Approach:** Keep a `HashMap<value, index>`. For each `nums[i]`, look up `target - nums[i]`. If it is in the map, return its stored index and `i`. Otherwise store `nums[i]` with its index and continue.

**Complexity:** Time `O(n)` · Space `O(n)`

[Back to index](#index)

---

## 242. Valid Anagram

[LeetCode](https://leetcode.com/problems/valid-anagram/) · Easy · [Solution](LC242_ValidAnagram.java)

**Problem:** Given two strings `s` and `t`, return `true` if `t` is an anagram of `s` (same letters with the same counts, in any order), and `false` otherwise.

**Key points**
- Different lengths can never be anagrams, so check the length first
- Both strings contain only lowercase letters, so `int[26]` is enough
- `int[26]` is much faster than a `HashMap` (no boxing, no hashing)
- For Unicode input, 26 slots are not enough, so use a `HashMap`

**Examples**
```
Input:  s = "anagram", t = "nagaram"
Output: true

Input:  s = "rat", t = "car"
Output: false
```

**Constraints:** `1 <= s.length, t.length <= 5 * 10^4`, lowercase English letters only

**Approach:** If the lengths differ, return `false`. Use `count = new int[26]`. For each index, do `count[s[i] - 'a']++` and `count[t[i] - 'a']--`. Then every count must be `0`. Alternative: build a `HashMap` of character counts for each string and compare them with `equals` (slower, but works for any characters, including Unicode).

**Complexity:** Time `O(n)` · Space `O(1)` (26 slots), or `O(k)` for a `HashMap` with `k` distinct characters

[Back to index](#index)